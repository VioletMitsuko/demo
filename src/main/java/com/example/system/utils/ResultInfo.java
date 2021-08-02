package com.example.system.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author VioletMitsuko
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultInfo<T> {

    private Integer code;
    private String msg;
    private T data;
}
