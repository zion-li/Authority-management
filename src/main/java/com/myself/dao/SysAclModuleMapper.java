package com.myself.dao;

import com.myself.model.SysAclModule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAclModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysAclModule record);

    int insertSelective(SysAclModule record);

    SysAclModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAclModule record);

    int updateByPrimaryKey(SysAclModule record);

    int countByNameAndParentId(@Param("parentId") int parentId,  @Param("name") String name, @Param("id") int id);

    List<SysAclModule> getChildAclModuleListByLevel(@Param("level") String level);

    void batchUpdateLevel(@Param("sysAclModuleList") List<SysAclModule> aclModuleList);

    int countByParentId(@Param("aclModuleId") int aclModuleId);

    List<SysAclModule> getAllAclModule();
}