package xmu.vis.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class RelationType implements Serializable {

    private Integer relationTypeId;

    private String name;
}
