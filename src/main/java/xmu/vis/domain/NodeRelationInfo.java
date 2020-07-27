package xmu.vis.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class NodeRelationInfo implements Serializable {

    private Integer id;

    private Integer nodeInfoId1;

    private Integer nodeInfoId2;

    private Integer relationTypeId;
}
