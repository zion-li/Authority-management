package com.myself.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SysDept  extends BaseAttribute {
    /**
     * 部门Id
     */
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 上级部门Id
     */
    private Integer parentId;

    /**
     * 部门层级
     */
    private String level;

    /**
     * 部门在当前层级下的顺序，由小到大
     */
    private Integer seq;

    /**
     * 备注
     */
    private String remark;
}