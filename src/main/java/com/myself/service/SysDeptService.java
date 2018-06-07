package com.myself.service;

import com.myself.param.DeptParam;

public interface SysDeptService {
    void save(DeptParam param);

    void delete(int id);

    void update(DeptParam param);
}
