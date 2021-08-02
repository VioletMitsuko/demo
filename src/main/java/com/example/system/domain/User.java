package com.example.system.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author VioletMitsuko
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class User {
    private int id;
    private String userName;
    private String name;
    private char sex;
    private String phone;
    private String email;
    private String password;
    private Integer state;
    private Integer createBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date creationDate;
    private Integer lastUpdateBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastUpdatedDate;
    private Integer lastUpdatedLogin;
    private String description;
    private String deleteFlag;

    public User(String userName, String name, char sex, String phone, String email, String password, Integer state, Integer createBy, Date creationDate, Integer lastUpdateBy, Date lastUpdatedDate, Integer lastUpdatedLogin, String description, String deleteFlag) {
        this.userName = userName;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.password = password;
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
