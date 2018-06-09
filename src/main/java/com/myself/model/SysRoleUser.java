package com.myself.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
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