package xmu.vis.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class NodeInfo implements Serializable {

    private Integer nodeInfoId;

    private Integer nodeTypeId;

    private String name;

    private Integer attributeId;
}
