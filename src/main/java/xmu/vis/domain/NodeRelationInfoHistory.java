package xmu.vis.domain;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class NodeRelationInfoHistory implements Serializable {

    private Integer id;

    private Integer nodeInfoId1;

    private Integer nodeInfoId2;

    private Integer relationTypeId;

    private Timestamp historyCreateTime;

    private Timestamp historyUpdateTime;

    public NodeRelationInfoHistory() {

    }

    public NodeRelationInfoHistory(NodeRelationInfo nodeRelationInfo) {
        nodeInfoId1 = nodeRelationInfo.getNodeInfoId1();
        nodeInfoId2 = nodeRelationInfo.getNodeInfoId2();
        relationTypeId = nodeRelationInfo.getRelationTypeId();
    }
}
