package com.myself.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRole extends BaseAttribute {
    /**
     * 角色Id
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色的类型，1：管理员角色，2：其他
     */
    private Integer type;

    /**
     * 状态，1：可用，0：冻结
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;
}