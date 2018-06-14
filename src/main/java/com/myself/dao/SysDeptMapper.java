package com.myself.dao;

import com.myself.model.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDeptMapper {
    int deleteByPrimaryKey(@Param("id")  Integer id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    /**
     * 部门列表
     * @return
     */
    List<SysDept> getAllDept();

    /**
     * 查询同一层级中是否存在名名称相同的部门
     * @param parentId
     * @param deptName
     * @param deptId
     * @return
     */
    int countByNameAndParentId(@Param("parentId")  int parentId, @Param("name") String deptName, @Param("id") int deptId);

    int countByParentId(@Param("deptId") int deptId);

    List<SysDept> getChildDeptListByLevel(@Param("level") String level);

    void batchUpdateLevel(@Param("sysDeptList")  List<SysDept> deptList);
}