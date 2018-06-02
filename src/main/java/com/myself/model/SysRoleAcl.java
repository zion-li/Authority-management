package com.myself.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleAcl extends BaseAttribute {
    private Integer id;

    /**
     * 角色Id
     */
    private Integer roleId;

    /**
     * 权限Id
     */
    private Integer aclId;
}