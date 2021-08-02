package com.example.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author VioletMitsuko
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private int id;
    private String name;
    private Integer fatherId;
    private Integer type;
    private String identifier;
    private String url;
    private String img;
    private Integer state;
    private Integer sortNum;
    private Integer createBy;
    private Date creationDate;
    private Integer lastUpdateBy;
    private Date lastUpdatedDate;
    private String description;
    private String deleteFlag;

    public Menu(String name, Integer fatherId, Integer type, String identifier, String url, String img, Integer state, Integer sortNum, Integer createBy, Date creationDate, Integer lastUpdateBy, Date lastUpdatedDate, String description, String deleteFlag) {
        this.name = name;
        this.fatherId = fatherId;
        this.type = type;
        this.identifier = identifier;
        this.url = url;
        this.img = img;
        this.state = state;
        this.sortNum = sortNum;
        this.createBy = createBy;
        this.creationDate = creationDate;
        this.lastUpdateBy = lastUpdateBy;
        this.lastUpdatedDate = lastUpdatedDate;
        this.description = description;
        this.deleteFlag = deleteFlag;
    }
}
