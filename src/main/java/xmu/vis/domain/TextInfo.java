package xmu.vis.domain;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class TextInfo implements Serializable {

    private Integer id;

    private String content;

    private String extractNode1;

    private String extractRelation;

    private String extractNode2;

    // 0代表未审核，1代表放弃，2代表审核通过
    private Integer status;

    private Timestamp textCreateTime;

    private Timestamp textUpdateTime;
}
