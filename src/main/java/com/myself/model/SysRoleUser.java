package com.myself.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleUser extends BaseAttribute {

    private Integer id;
    /**
     * 角色Id
     */
    private Integer roleId;

    /**
     * 用户Id
     */
    private Integer userId;
}