package com.myself.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.myself.common.RequestHolder;
import com.myself.dao.SysRoleUserMapper;
import com.myself.dao.SysUserMapper;
import com.myself.model.SysRoleUser;
import com.myself.model.SysUser;
import com.myself.service.SysLogService;
import com.myself.service.SysRoleUserService;
import com.myself.util.IpUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 用户角色映射接口实现
 * @author zion
 */
public class SysRoleUserServiceImpl implements SysRoleUserService {

    @Resource
    private SysRoleUserMapper sysRoleUserMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysLogService sysLogService;

    @Override
    public void changeRoleUsers(int roleId, List<Integer> userIdList) {
        List<Integer> originUserIdList = sysRoleUserMapper.getUserIdListByRoleId(roleId);
        if (originUserIdList.size() == userIdList.size()) {
            Set<Integer> originUserIdSet = Sets.newHashSet(originUserIdList);
            Set<Integer> userIdSet = Sets.newHashSet(userIdList);
            originUserIdSet.removeAll(userIdSet);
            if (CollectionUtils.isEmpty(originUserIdSet)) {
                return;
            }
        }
        updateRoleUsers(roleId, userIdList);
        //记录日志
        sysLogService.saveRoleUserLog(roleId, originUserIdList, userIdList);
    }

    @Transactional
     void updateRoleUsers(int roleId, List<Integer> userIdList) {
        sysRoleUserMapper.deleteByRoleId(roleId);

        if (CollectionUtils.isEmpty(userIdList)) {
            return;
        }
        List<SysRoleUser> roleUserList = Lists.newArrayList();
        for (Integer userId : userIdList) {
            SysRoleUser roleUser = SysRoleUser.builder().roleId(roleId).userId(userId).build();
            roleUser.setOperateIp(RequestHolder.getCurrentUser().getUsername());
            roleUser.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
            roleUser.setOperateTime(new Date());
            roleUserList.add(roleUser);
        }
        sysRoleUserMapper.batchInsert(roleUserList);
    }

    @Override
    public List<SysUser> getListByRoleId(int roleId) {
        List<Integer> userIdList = sysRoleUserMapper.getUserIdListByRoleId(roleId);
        if (CollectionUtils.isEmpty(userIdList)) {
            return Lists.newArrayList();
        }
        return sysUserMapper.getByIdList(userIdList);
    }
}
