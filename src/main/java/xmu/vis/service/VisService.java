package xmu.vis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.vis.domain.NodeInfo;
import xmu.vis.domain.NodeRelationInfo;
import xmu.vis.domain.NodeType;
import xmu.vis.mapper.NodeInfoMapper;
import xmu.vis.mapper.NodeRelationInfoMapper;
import xmu.vis.mapper.NodeTypeMapper;
import xmu.vis.vo.ResNode;
import xmu.vis.vo.ResNodeType;
import xmu.vis.vo.ResRelation;

import javax.xml.soap.Node;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VisService {

    @Autowired
    private NodeRelationInfoMapper nodeRelationInfoMapper;

    @Autowired
    private NodeTypeMapper nodeTypeMapper;

    @Autowired
    private NodeInfoMapper nodeInfoMapper;

    public static int playerType = 1;

    public static int teamType = 2;

    public static int group1 = 1;

    public static int group2 = 2;

    public static String rel ="效力";

    public List<ResNodeType> getLevelRelation() {
        NodeType root = nodeTypeMapper.getRootNode();
        List<NodeType> allNodes = nodeTypeMapper.getAllNodeType();
        List<ResNodeType> res = new ArrayList<>();
        for (NodeType nodeType:allNodes) {
            res.add(new ResNodeType(nodeType.getNodeTypeId(),nodeType.getName(),nodeType.getPid()));
        }
        return res;
    }

    public List<ResNode> getOneLevelNode(NodeInfo nodeInfo) {
        List<ResNode> resNodes = new ArrayList<>();
        // 等于1是球员，等于2是球队
        if (nodeInfo.getNodeTypeId() == playerType) {
            NodeRelationInfo nodeRelationInfo = nodeRelationInfoMapper.getNodeRelationByNode1(nodeInfo.getNodeInfoId());
            NodeInfo nodeInfo1 = nodeInfoMapper.getNodeInfoById(nodeRelationInfo.getNodeInfoId2());
            resNodes.add(new ResNode(nodeInfo.getNodeInfoId(),nodeInfo.getName(),group1));
            resNodes.add(new ResNode(nodeInfo1.getNodeInfoId(),nodeInfo1.getName(),group2));
        } else if(nodeInfo.getNodeTypeId() == teamType) {
            List<Integer> node1s = nodeRelationInfoMapper.getNode1ByNode2(nodeInfo.getNodeInfoId());
            resNodes.add(new ResNode(nodeInfo.getNodeInfoId(),nodeInfo.getName(),group2));
            for (Integer node1Id:node1s) {
                NodeInfo tmp = nodeInfoMapper.getNodeInfoById(node1Id);
                resNodes.add(new ResNode(tmp.getNodeInfoId(),tmp.getName(),group1));
            }
        }
        return resNodes;
    }

    public List<ResRelation> getOneLevelRelation(NodeInfo nodeInfo) {
        List<ResRelation> relations = new ArrayList<>();
        // 等于1是球员，等于2是球队
        if (nodeInfo.getNodeTypeId() == playerType) {
            NodeRelationInfo nodeRelationInfo = nodeRelationInfoMapper.getNodeRelationByNode1(nodeInfo.getNodeInfoId());
            NodeInfo nodeInfo1 = nodeInfoMapper.getNodeInfoById(nodeRelationInfo.getNodeInfoId2());
            relations.add(new ResRelation(nodeInfo.getName(), nodeInfo1.getName(),rel));
        } else if(nodeInfo.getNodeTypeId() == teamType) {
            List<Integer> node1s = nodeRelationInfoMapper.getNode1ByNode2(nodeInfo.getNodeInfoId());
            for (Integer node1Id:node1s) {
                NodeInfo tmp = nodeInfoMapper.getNodeInfoById(node1Id);
                relations.add(new ResRelation(tmp.getName(),nodeInfo.getName(),rel));
            }
        }
        return relations;
    }

    public List<ResNode> getTwoLevelNode(NodeInfo nodeInfo) {
        List<ResNode> resNodes = new ArrayList<>();
        List<Integer> node1s = nodeRelationInfoMapper.getNode1ByNode2(nodeInfo.getNodeInfoId());
        resNodes.add(new ResNode(nodeInfo.getNodeInfoId(),nodeInfo.getName(),group2));
        for (Integer node1Id:node1s) {
            NodeInfo tmp = nodeInfoMapper.getNodeInfoById(node1Id);
            resNodes.add(new ResNode(tmp.getNodeInfoId(),tmp.getName(),group1));
        }
        return resNodes;
    }

    public List<ResRelation> getTwoLevelRelation(NodeInfo nodeInfo) {
        List<ResRelation> relations = new ArrayList<>();
        List<Integer> node1s = nodeRelationInfoMapper.getNode1ByNode2(nodeInfo.getNodeInfoId());
        for (Integer node1Id:node1s) {
            NodeInfo tmp = nodeInfoMapper.getNodeInfoById(node1Id);
            relations.add(new ResRelation(tmp.getName(),nodeInfo.getName(),rel));
        }
        return relations;
    }

    public List<String> getNodeNameByFatherNodeAndRelation(String fatherNodeName, String relationType) {
        NodeInfo nodeInfo = nodeInfoMapper.getNodeInfoByName(fatherNodeName);
        if (nodeInfo == null) {
            return new ArrayList<String>();
        }

//        当有多种关系类型时，修改getNode1ByNode2，以node2 id 和 nodeType为参数
        List<Integer> childrenNodeIds = nodeRelationInfoMapper.getNode1ByNode2(nodeInfo.getNodeInfoId());
        if (childrenNodeIds.isEmpty()) {
            return new ArrayList<String>();
        }
        List<String> childrenNames = new ArrayList<String>();
        for (int i = 0; i < childrenNodeIds.size(); i++) {
            int childNodeId = childrenNodeIds.get(i);
            String childName = nodeInfoMapper.getNodeNameById(childNodeId);
            childrenNames.add(childName);
        }
        return childrenNames;
    }
}
