package com.myself.service;

import com.myself.param.DeptParam;

public interface SysDeptService {
    /**
     * 保存部门
     * @param param 部门参数
     */
    void save(DeptParam param);

    /**
     * 删除部门
     * @param id
     */
    void delete(int id);

    /**
     * 更新部门
     * @param param
     */
    void update(DeptParam param);
}
