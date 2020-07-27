package xmu.vis.mapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xmu.vis.domain.NodeType;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NodeTypeMapperTest {

    @Autowired
    private NodeTypeMapper nodeTypeMapper;

    @Test
    public void getNodeTypeByNodeTypeId() {
        System.out.println(nodeTypeMapper.getNodeTypeByNodeTypeId(1));
    }

    @Test
    public void getTableNameByNodeTypeId() {
        System.out.println(nodeTypeMapper.getTableNameByNodeTypeId(1));
    }

    @Test
    @Transactional
    public void insertNodeType() {
        NodeType nodeType = new NodeType();
        nodeType.setName("123");
        nodeType.setAttributeTableName("123");
//        nodeType.setLevel(3);
        System.out.println(nodeTypeMapper.insertNodeType(nodeType));
    }

    @Test
    public void getNodeTypeByName() {
        System.out.println(nodeTypeMapper.getNodeTypeByName("球员"));
    }

    @Test
    public void getAllNodeType() {
        System.out.println(nodeTypeMapper.getAllNodeType());
    }

    @Test
    public void getRootNode() {
        System.out.println(nodeTypeMapper.getRootNode());
    }
}