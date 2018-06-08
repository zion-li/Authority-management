package com.myself.dao;

import com.myself.model.SysAclModule;

import java.util.List;

public interface SysAclModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysAclModule record);

    int insertSelective(SysAclModule record);

    SysAclModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAclModule record);

    int updateByPrimaryKey(SysAclModule record);

    int countByNameAndParentId(Integer parentId, String aclModuleName, Integer deptId);

    List<SysAclModule> getChildAclModuleListByLevel(String level);

    void batchUpdateLevel(List<SysAclModule> aclModuleList);

    int countByParentId(Integer id);

    List<SysAclModule> getAllAclModule();
}