package com.myself.model;

import lombok.Getter;

/**
 * 具体的更新值
 */

@Getter
public class SysLogWithBLOBs extends SysLog {
    /**
     * 旧值
     */
    private String oldValue;

    /**
     * 新值
     */
    private String newValue;


    public void setOldValue(String oldValue) {
        this.oldValue = oldValue == null ? null : oldValue.trim();
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue == null ? null : newValue.trim();
    }
}