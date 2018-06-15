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
     * 检查数据是否有重复
     * @param parentId
     * @param deptName
     * @param deptId
     * @return
     */
    int countByNameAndParentId(@Param("parentId")  int parentId, @Param("name") String deptName, @Param("id") int deptId);

    int countByParentId(@Param("deptId") int deptId);

    /**
     * 获取所有以x开头的所有部门 xx .%
     * @param level
     * @return
     */
    List<SysDept> getChildDeptListByLevel(@Param("level") String level);

    /**
     * 批量更新所有的子部门
     * @param deptList
     */
    void batchUpdateLevel(@Param("sysDeptList")  List<SysDept> deptList);
}