package xmu.vis.mapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xmu.vis.domain.NodeInfo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NodeInfoMapperTest {

    @Autowired
    private NodeInfoMapper nodeInfoMapper;

    @Test
    public void getNodeInfoByName() {
//        NodeInfo nodeInfo = nodeInfoMapper.getNodeInfoByName("梅西");
//        JSONObject jsonObject = JSON.parseObject(nodeInfo.getAttribute());
//        jsonObject.put("球队名","皇家马德里");
//        nodeInfo.setAttribute(jsonObject.toString());
//        System.out.println(nodeInfo);
    }

    @Test
    public void getNodeInfoById() {
        List<String> a = new ArrayList<String>();
        System.out.println(a.isEmpty());
        System.out.println(nodeInfoMapper.getNodeInfoById(1));
    }

    @Test
    @Transactional
    public void updateAttribute() {
//        NodeInfo nodeInfo = new NodeInfo();
//        nodeInfo.setNodeInfoId(23);
//        nodeInfo.setAttribute("123");
//        System.out.println(nodeInfoMapper.updateAttribute(nodeInfo));
    }
}