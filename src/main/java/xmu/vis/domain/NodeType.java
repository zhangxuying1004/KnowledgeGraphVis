package xmu.vis.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class NodeType implements Serializable {

    private Integer nodeTypeId;

    private String name;

    private String attributeTableName;

    private Integer pid;
}
