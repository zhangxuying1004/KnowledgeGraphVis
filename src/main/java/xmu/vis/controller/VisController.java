package xmu.vis.controller;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import xmu.vis.domain.NodeInfo;
import xmu.vis.domain.NodeRelationInfo;
import xmu.vis.domain.NodeType;
import xmu.vis.mapper.AttributeMapper;
import xmu.vis.mapper.NodeInfoMapper;
import xmu.vis.mapper.NodeRelationInfoMapper;
import xmu.vis.mapper.NodeTypeMapper;
import xmu.vis.service.VisService;
import xmu.vis.utils.ResponseUtil;
import xmu.vis.vo.ResNode;
import xmu.vis.vo.ResNodeType;
import xmu.vis.vo.ResRelation;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class VisController {

    @Autowired
    private NodeInfoMapper nodeInfoMapper;

    @Autowired
    private NodeRelationInfoMapper nodeRelationInfoMapper;

    @Autowired
    private NodeTypeMapper nodeTypeMapper;

    @Autowired
    private AttributeMapper attributeMapper;

    @Autowired
    private VisService visService;

    public static String initTeamName = "巴塞罗那";

    public static int playerType = 1;

    public static int teamType = 2;

    /**
     * 获得默认显示节点
     * @return
     */
    @GetMapping("/initNodes")
    public Object getInitNodes() {
        NodeInfo nodeInfo = nodeInfoMapper.getNodeInfoByName(initTeamName);
        if (nodeInfo == null) {
            return ResponseUtil.fail();
        }
        List<ResNode> resNodes = visService.getTwoLevelNode(nodeInfo);
        if (resNodes.size() == 0) {
            return ResponseUtil.fail();
        }
        return ResponseUtil.ok(resNodes);
    }

    /**
     * 获得默认显示联系
     * @return
     */
    @GetMapping("/initRelations")
    public Object getInitRelations() {
        NodeInfo nodeInfo = nodeInfoMapper.getNodeInfoByName(initTeamName);
        if (nodeInfo == null) {
            return ResponseUtil.fail();
        }
        List<ResRelation> resRelations = visService.getTwoLevelRelation(nodeInfo);
        if (resRelations.size() == 0) {
            return ResponseUtil.fail();
        }
        return ResponseUtil.ok(resRelations);
    }

    /**
     * 根据nodeInfoId查询，返回该id对应节点的各种属性
     * @param id
     * @return
     */
    @GetMapping("/getAttribute/{id}")
    public Object getAttribute(@PathVariable("id") Integer id) {
        NodeInfo nodeInfo = nodeInfoMapper.getNodeInfoById(id);
        String tableName = nodeTypeMapper.getTableNameByNodeTypeId(nodeInfo.getNodeTypeId());
        Map<String,Object> map = attributeMapper.getAttributeById(tableName,nodeInfo.getAttributeId());
        map.remove("id");
        if (map.size() == 0) {
            return ResponseUtil.fail();
        } else {
            return ResponseUtil.ok(JSONObject.fromObject(map));
        }
    }

    /**
     * 根据姓名获得全部属性
     * @param name
     * @return
     */
    @GetMapping("/getAttributeByName/{name}")
    public Object getAttributeByName(@PathVariable("name") String name) {
        NodeType nodeType = nodeTypeMapper.getNodeTypeByName(name);
        if (nodeType == null) {
            return ResponseUtil.fail();
        }
        List<String> attributes = attributeMapper.getAllAttributeByName(nodeType.getAttributeTableName());
        if (attributes.size() == 0) {
            return ResponseUtil.fail();
        } else {
            return ResponseUtil.ok(attributes);
        }
    }


    /**
     * 根据名字添加相应的属性
     * @param name
     * @param attributeName
     * @return
     */
    @PostMapping("/addAttribute/{name}/{attributeName}")
    public Object addAttribute(@PathVariable("name") String name, @PathVariable("attributeName") String attributeName){
        NodeType nodeType = nodeTypeMapper.getNodeTypeByName(name);
        String tableName = nodeType.getAttributeTableName();
        attributeMapper.addAttribute(tableName,attributeName);
        List<String> attributes = attributeMapper.getAllAttributeByName(tableName);
        if (attributes.contains(attributeName)) {
            return ResponseUtil.ok();
        } else {
            return ResponseUtil.fail();
        }
    }

    /**
     * 根据姓名删除属性
     * @param name
     * @param attributeName
     * @return
     */
    @PostMapping("/deleteAttribute/{name}/{attributeName}")
    public Object deleteAttribute(@PathVariable("name") String name, @PathVariable("attributeName") String attributeName){
        NodeType nodeType = nodeTypeMapper.getNodeTypeByName(name);
        String tableName = nodeType.getAttributeTableName();
        attributeMapper.deleteAttribute(tableName,attributeName);
        List<String> attributes = attributeMapper.getAllAttributeByName(tableName);
        if (attributes.contains(attributeName)) {
            return ResponseUtil.fail();
        } else {
            return ResponseUtil.ok();
        }
    }

    /**
     * 修改数据库的表的一列
     * @param name
     * @param oldName
     * @param newName
     * @return
     */
    @PostMapping("/updateAttribute/{name}/{oldName}/{newName}")
    public Object updateAttributeByName(@PathVariable("name") String name,
                                        @PathVariable("oldName") String oldName,
                                        @PathVariable("newName") String newName) {
        NodeType nodeType = nodeTypeMapper.getNodeTypeByName(name);
        String tableName = nodeType.getAttributeTableName();
        attributeMapper.updateColumn(tableName,oldName,newName);
        List<String> attributes = attributeMapper.getAllAttributeByName(tableName);
        if (attributes.contains(newName) && !attributes.contains(oldName)) {
            return ResponseUtil.ok();
        } else {
            return ResponseUtil.fail();
        }
    }

    /**
     * 根据id更新属性
     * @param id
     * @param map
     * @return
     */
    @PostMapping("/updateAttribute/{id}")
    public Object updateAttribute(@PathVariable("id") Integer id,@RequestBody Map<String,Object> map){
        NodeInfo nodeInfo = nodeInfoMapper.getNodeInfoById(id);
        String tableName = nodeTypeMapper.getTableNameByNodeTypeId(nodeInfo.getNodeTypeId());
        if (attributeMapper.updateAttribute(tableName,(Integer) map.get("id"),map) == 1){
            return ResponseUtil.ok();
        } else {
            return ResponseUtil.fail();
        }
    }

    @GetMapping("/getLevelRelation")
    public Object getLevelRelation(){
        List<ResNodeType> res = visService.getLevelRelation();
        if (res.size() == 0) {
            return ResponseUtil.fail();
        } else {
            return ResponseUtil.ok(res);
        }
    }

    /**
     * 添加节点类型
     * @param nodeType
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/addNodeType")
    public Object addNodeType(@RequestBody NodeType nodeType) {
        if (nodeTypeMapper.insertNodeType(nodeType) == 1) {
            if (attributeMapper.existTable(nodeType.getAttributeTableName()) == 0) {
                attributeMapper.createTable(nodeType.getAttributeTableName());
            } else {
                return ResponseUtil.fail();
            }
            return ResponseUtil.ok();
        } else {
            return ResponseUtil.fail();
        }
    }

    /**
     * 删除节点类型
     * @param name
     * @return
     */
    @PostMapping("/deleteNodeType/{name}")
    public Object deleteNodeType(@PathVariable("name") String name) {
        NodeType nodeType = nodeTypeMapper.getNodeTypeByName(name);
        attributeMapper.deleteTable(nodeType.getAttributeTableName());
        if (attributeMapper.existTable(nodeType.getAttributeTableName()) != 0) {
            return ResponseUtil.fail();
        }
        if (nodeTypeMapper.deleteNodeType(name) == 1) {
            return ResponseUtil.ok();
        } else {
            return ResponseUtil.fail();
        }
    }

    /**
     * 根据姓名获取一级关系节点
     * @param name
     * @return
     */
    @GetMapping("/getOneLevelNode/{name}")
    public Object getOneLevelNodeByName(@PathVariable("name") String name) {
        NodeInfo nodeInfo = nodeInfoMapper.getNodeInfoByName(name);
        if (nodeInfo == null) {
            return ResponseUtil.fail();
        }
        List<ResNode> resNodes = visService.getOneLevelNode(nodeInfo);
        if (resNodes.size() == 0) {
            return ResponseUtil.fail();
        }
        return ResponseUtil.ok(resNodes);
    }

    /**
     * 根据姓名获取一级关系连接
     * @param name
     * @return
     */
    @GetMapping("/getOneLevelRelation/{name}")
    public Object getOneLevelRelation(@PathVariable("name") String name) {
        NodeInfo nodeInfo = nodeInfoMapper.getNodeInfoByName(name);
        if (nodeInfo == null) {
            return ResponseUtil.fail();
        }
        List<ResRelation> resRelations = visService.getOneLevelRelation(nodeInfo);
        if (resRelations.size() == 0) {
            return ResponseUtil.fail();
        }
        return ResponseUtil.ok(resRelations);
    }

    /**
     * 根据姓名获取二级关系节点
     * @param name
     * @return
     */
    @GetMapping("/getTwoLevelNode/{name}")
    public Object getTwoLevelNodeByName(@PathVariable("name") String name) {
        NodeInfo nodeInfo = nodeInfoMapper.getNodeInfoByName(name);
        if (nodeInfo == null) {
            return ResponseUtil.fail();
        }
        List<ResNode> resNodes = new ArrayList<>();
        if (nodeInfo.getNodeTypeId() == playerType) {
            NodeRelationInfo nodeRelationInfo = nodeRelationInfoMapper.getNodeRelationByNode1(nodeInfo.getNodeInfoId());
            NodeInfo nodeInfo1 = nodeInfoMapper.getNodeInfoById(nodeRelationInfo.getNodeInfoId2());
            resNodes = visService.getTwoLevelNode(nodeInfo1);
        } else if (nodeInfo.getNodeTypeId() == teamType) {
            resNodes = visService.getTwoLevelNode(nodeInfo);
        }
        if (resNodes.size() == 0) {
            return ResponseUtil.fail();
        }
        return ResponseUtil.ok(resNodes);
    }

    /**
     * 根据姓名获取二级关系联系
     * @param name
     * @return
     */
    @GetMapping("/getTwoLevelRelation/{name}")
    public Object getTwoLevelRelation(@PathVariable("name") String name) {
        NodeInfo nodeInfo = nodeInfoMapper.getNodeInfoByName(name);
        if (nodeInfo == null) {
            return ResponseUtil.fail();
        }
        List<ResRelation> resRelations = new ArrayList<>();
        if (nodeInfo.getNodeTypeId() == playerType) {
            NodeRelationInfo nodeRelationInfo = nodeRelationInfoMapper.getNodeRelationByNode1(nodeInfo.getNodeInfoId());
            NodeInfo nodeInfo1 = nodeInfoMapper.getNodeInfoById(nodeRelationInfo.getNodeInfoId2());
            resRelations = visService.getTwoLevelRelation(nodeInfo1);
        } else if (nodeInfo.getNodeTypeId() == teamType) {
            resRelations = visService.getTwoLevelRelation(nodeInfo);
        }
        if (resRelations.size() == 0) {
            return ResponseUtil.fail();
        }
        return ResponseUtil.ok(resRelations);
    }

    @GetMapping("/getAllNodeInVis")
    public Object getAllNodeInVis() {
        String initHeadQuarters = "太平洋舰队司令部";
        String relationTypes = "下属";
        int label = 0;
        List<HashMap> result = new ArrayList<HashMap>();

        List<String> nodeList = new ArrayList<String>(Arrays.asList(initHeadQuarters));
        List<String> relationTypeList = new ArrayList<String>(Arrays.asList(relationTypes));

        while(!nodeList.isEmpty()) {
            List<String> tempNodeList = new ArrayList<String>();
            for (int i = 0; i < nodeList.size(); i++) {
                String nodeName = nodeList.get(i);
                for (int j = 0; j < relationTypeList.size(); j++) {
                    String nodeRelationType = relationTypeList.get(j);
                    List<String> childrenName = visService.getNodeNameByFatherNodeAndRelation(nodeName, nodeRelationType);
                    if (!childrenName.isEmpty()) {
                        for (int k = 0; k < childrenName.size(); k++) {
                            String childName = childrenName.get(k);
                            tempNodeList.add(childName);

                            HashMap<String, Object> map = new HashMap<String,Object>();
                            map.put("fatherNode", nodeName);
                            map.put("childNode", childName);
                            map.put("label", label);
                            map.put("nodeRelationType", nodeRelationType);
                        }
                    }
                }
            }
            label = label + 1;
            nodeList.clear();
            nodeList.addAll(tempNodeList);
        }
        if (result.size() == 0) {
            return ResponseUtil.fail();
        } else {
            return ResponseUtil.ok(result);
        }
    }
}
