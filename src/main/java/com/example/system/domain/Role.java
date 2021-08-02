package com.example.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author VioletMitsuko
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Role {
    private int id;
    private String roleCode;
    private String name;
    private Date starTime;
    private Date stopTime;
    private Integer state;
    private Integer createBy;
    private Date creationDate;
    private Integer lastUpdateBy;
    private Date lastUpdatedDate;
    private Integer lastUpdatedLogin;
    private String description;
    private String deleteFlag;

    public Role(String roleCode, String name, Date starTime, Date stopTime, Integer state, Integer createBy, Date creationDate, Integer lastUpdateBy, Date lastUpdatedDate, Integer lastUpdatedLogin, String description, String deleteFlag) {
        this.roleCode = roleCode;
        this.name = name;
        this.starTime = starTime;
        this.stopTime = stopTime;
        this.state = state;
        this.createBy = createBy;
        this.creationDate = creationDate;
        this.lastUpdateBy = lastUpdateBy;
        this.lastUpdatedDate = lastUpdatedDate;
        this.lastUpdatedLogin = lastUpdatedLogin;
        this.description = description;
        this.deleteFlag = deleteFlag;
    }
}
