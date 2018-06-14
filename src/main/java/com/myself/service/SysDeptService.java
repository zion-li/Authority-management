package com.myself.service;

import com.myself.param.DeptParam;

public interface SysDeptService {
    /**
     * 保存部门
     * @param param 部门参数
     */
    void save(DeptParam param);

    void delete(int id);

    void update(DeptParam param);
}
