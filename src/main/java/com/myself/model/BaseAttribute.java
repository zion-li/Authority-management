package com.myself.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


/**
 * 基础属性
 */
@Getter
@Setter
@ToString
public class BaseAttribute {

    /**
     * 操作者
     */
    private String operator;

    /**
     * 操作时间
     */
    private Date operateTime;

    /**
     * 最后一次更新操作者的Ip地址
     */
    private String operateIp;
}
