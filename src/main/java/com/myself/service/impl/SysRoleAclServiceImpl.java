package com.myself.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.myself.common.RequestHolder;
import com.myself.dao.SysRoleAclMapper;
import com.myself.model.SysRoleAcl;
import com.myself.service.SysLogService;
import com.myself.service.SysRoleAclService;
import com.myself.util.IpUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class SysRoleAclServiceImpl implements SysRoleAclService {

    @Resource
    private SysRoleAclMapper sysRoleAclMapper;
    @Resource
    private SysLogService  sysLogService;

    @Override
    public void changeRoleAcls(int roleId, List<Integer> aclIdList) {
        List<Integer> originAclIdList = sysRoleAclMapper.getAclIdListByRoleIdList(Lists.newArrayList(roleId));
        if (originAclIdList.size() == aclIdList.size()) {
            Set<Integer> originAclIdSet = Sets.newHashSet(originAclIdList);
            Set<Integer> aclIdSet = Sets.newHashSet(aclIdList);
            originAclIdSet.removeAll(aclIdSet);
            if (CollectionUtils.isEmpty(originAclIdSet)) {
                return;
            }
        }
        updateRoleAcls(roleId, aclIdList);
        sysLogService.saveRoleAclLog(roleId, originAclIdList, aclIdList);
    }

    @Transactional
    public void updateRoleAcls(int roleId, List<Integer> aclIdList) {
        sysRoleAclMapper.deleteByRoleId(roleId);

        if (CollectionUtils.isEmpty(aclIdList)) {
            return;
        }
        List<SysRoleAcl> roleAclList = Lists.newArrayList();
        for(Integer aclId : aclIdList) {
            SysRoleAcl roleAcl = SysRoleAcl.builder().roleId(roleId).aclId(aclId).build();
            roleAcl.setOperator(RequestHolder.getCurrentUser().getUsername());
            roleAcl.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
            roleAcl.setOperateTime(new Date());
            roleAclList.add(roleAcl);
        }
        sysRoleAclMapper.batchInsert(roleAclList);
    }
}
