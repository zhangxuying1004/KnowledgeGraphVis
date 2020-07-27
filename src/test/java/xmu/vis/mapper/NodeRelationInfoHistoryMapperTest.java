package xmu.vis.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xmu.vis.domain.NodeRelationInfoHistory;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NodeRelationInfoHistoryMapperTest {

    @Autowired
    private NodeRelationInfoHistoryMapper nodeRelationInfoHistoryMapper;

    @Test
    @Transactional
    public void insertHistory() {
        NodeRelationInfoHistory his = new NodeRelationInfoHistory();
        his.setNodeInfoId1(1);
        his.setNodeInfoId2(2);
        his.setRelationTypeId(1);
        System.out.println(nodeRelationInfoHistoryMapper.insertHistory(his));
    }
}