package com.myself.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class SearchLogDto {
    /**
     * 日志类型
     */
    private Integer type;

    /**
     * 以前的
     */
    private String beforeSeg;
    /**
     * 之后的
     */
    private String afterSeg;

    /**
     * 操作者
     */
    private String operator;

    /**
     * 开始恢复时间 yyyy-MM-dd HH:mm:ss
     */
    private Date fromTime;

    /**
     * 结束恢复时间 yyyy-MM-dd HH:mm:ss
     */
    private Date toTime;
}
