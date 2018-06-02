package com.myself.model;

import lombok.*;

import java.util.Date;

/**
 * 权限模块
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysAclModule extends BaseAttribute {
    /**
     * 权限模块Id
     */
    private Integer id;

    /**
     * 权限模块名称
     */
    private String name;

    /**
     * 上级权限模块Id
     */
    private Integer parentId;

    /**
     * 权限模块层级
     */
    private String level;

    /**
     * 权限模块在当前层级下的顺序，由小到大
     */
    private Integer seq;

    /**
     * 状态，1：正常，0：冻结
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;
}