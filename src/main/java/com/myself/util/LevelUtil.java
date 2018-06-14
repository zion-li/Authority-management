package com.myself.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 树形结构计算工具类
 */
public class LevelUtil {

    public final static String SEPARATOR = ".";

    public final static String ROOT = "0";

    /**
     * 计算部门的层级
     *   0
     * 0.1
     *0.1.2
     /*0.1.3
     /*0.4
     * @param parentLevel 父级的上一层
     * @param parentId 父级的id
     * @return
     */
    public static String calculateLevel(String parentLevel, int parentId) {
        //不传就是首层
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            return StringUtils.join(parentLevel, SEPARATOR, parentId);
        }
    }
}
