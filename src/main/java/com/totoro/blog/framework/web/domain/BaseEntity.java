package com.totoro.blog.framework.web.domain;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: BaseEntity
 * @description: Entity base class
 * @author: Linh.Nguyen
 * @date: 22/01/2020
 * @version 1.0
 */
public class BaseEntity implements Serializable {
    /*Search Value*/
    private String searchValue;
    /*Create By*/
    private String createBy;
    /*Create Time*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /*Update By*/
    private String updateBy;
    /*Update Time*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /*Delete By*/
    private String deleteBy;
    /*Delete Time*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String deleteTime;
    /*Remark*/
    private String remark;

    @JsonIgnore
    private String params;

    /**
     * Request parameter
     *
     * @return JSON object
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getParams() {
        if (params == null){
            return new HashMap<>();
        }
        return JSON.parseObject(params, Map.class);
    }
}
