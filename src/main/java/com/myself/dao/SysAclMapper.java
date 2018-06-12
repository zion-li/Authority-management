package com.myself.dao;


import com.myself.beans.PageQuery;
import com.myself.model.SysAcl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAclMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SysAcl record);

    int insertSelective(SysAcl record);

    SysAcl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAcl record);

    int updateByPrimaryKey(SysAcl record);

    int countByAclModuleId( @Param("aclModuleId") Integer aclModuleId);

    List<SysAcl> getPageByAclModuleId(@Param("aclModuleId") int aclModuleId,  @Param("page")PageQuery page);

    int countByNameAndAclModuleId(@Param("aclModuleId") int aclModuleId,@Param("name") String name,@Param("id")  Integer id);

    List<SysAcl> getAll();

    List<SysAcl> getByUrl(@Param("url") String url);

    List<SysAcl> getByIdList(@Param("idList") List<Integer> idList);
}