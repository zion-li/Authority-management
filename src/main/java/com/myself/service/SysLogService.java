package com.myself.service;

import com.myself.model.SysDept;
import com.myself.model.SysUser;

public interface SysLogService {

    /**
     * 保存用户记录
     * @param before
     * @param after
     */
    void saveUserLog(SysUser before,SysUser after);

    /**
     * 保存部门日志
     * @param before
     * @param after
     */
    void saveDeptLog(SysDept before, SysDept after);
}