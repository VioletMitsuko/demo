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
public class RoleMenu {
    private int id;
    private Date starTime;
    private Date stopTime;
    private int roleId;
    private int menuId;
    private int state;
    private int createBy;
    private Date creationDate;
    private int lastUpdateBy;
    private Date lastUpdatedDate;
    private int lastUpdatedLogin;
    private String description;
    private String deleteFlag;
}
