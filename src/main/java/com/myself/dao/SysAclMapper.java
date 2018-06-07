package com.myself.dao;


import com.myself.beans.PageQuery;
import com.myself.model.SysAcl;

import java.util.List;

public interface SysAclMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SysAcl record);

    int insertSelective(SysAcl record);

    SysAcl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAcl record);

    int updateByPrimaryKey(SysAcl record);

    int countByAclModuleId(Integer aclModuleId);

    List<SysAcl> getPageByAclModuleId(Integer aclModuleId, PageQuery page);

    int countByNameAndAclModuleId(int aclModuleId, String name, Integer id);
}