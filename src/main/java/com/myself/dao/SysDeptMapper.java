package com.myself.dao;

import com.myself.model.SysDept;

import java.util.List;

public interface SysDeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    List<SysDept> getAllDept();

    int countByNameAndParentId(Integer parentId, String deptName, Integer deptId);

    int countByParentId(Integer id);

    List<SysDept> getChildDeptListByLevel(String level);

    void batchUpdateLevel(List<SysDept> deptList);
}