package xmu.vis.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xmu.vis.domain.NodeRelationInfo;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NodeRelationInfoMapperTest {

    @Autowired
    private NodeRelationInfoMapper nodeRelationInfoMapper;

    @Test
    public void getNodeRelationByNode1() {
        System.out.println(nodeRelationInfoMapper.getNodeRelationByNode1(23));
    }

    @Test
    public void getNodeRelationByNode12() {
        System.out.println(nodeRelationInfoMapper.getNodeRelationByNode12(1,51));
    }

    @Test
    public void getNode1ByNode2() {
        System.out.println(nodeRelationInfoMapper.getNode1ByNode2(51));
    }
}