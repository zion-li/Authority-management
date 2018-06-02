package com.myself.util;

import com.google.common.base.Splitter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;


import java.util.*;
import java.util.stream.Collectors;

/**
 * 基础的类型转换
 *
 * @author Created by zion
 * @Date 2018/5/17.
 */
public class CommonConvert {

    /**
     * 分隔符，只支持这三种，其他的没有必要
     */
    private static final String SEP1 = ",";
    private static final String SEP2 = "|";
    private static final String SEP3 = "#";

    /**
     * list -> String
     *
     * @param list  输入List
     * @param split 仅仅 , | #
     * @return string
     */
    public static String getListToString(List<String> list, String split) {
        //单线程，没有必要使用stringBuffer
        StringBuilder sb = new StringBuilder();
        if (CollectionUtils.isNotEmpty(list)) {
            for (String perList : list) {
                switch (split) {
                    case SEP2:
                        sb.append(perList).append(SEP2);
                        break;
                    case SEP3:
                        sb.append(perList).append(SEP3);
                        break;
                    default:
                        sb.append(perList).append(SEP1);
                }
            }
        }
        if (StringUtils.isBlank(sb.toString())) {
            return sb.toString();
        } else {
            return sb.deleteCharAt(sb.length() - 1).toString();
        }
    }

    /**
     * string to ->list<String>
     * @param str 输入字符串
     * @return arrayList
     */
    public static List<String> getStringToList(String str){
        if (StringUtils.isBlank(str)){
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(str.split(SEP1)));
    }



    /**
     * list -> Array
     *
     * @param list 输入List
     * @return stringArray
     */
    public static String[] getListToArray(List<String> list) {
        return list.toArray(new String[list.size()]);
    }

    /**
     * Array -> list
     * @param array  输入数组
     * @return ArrayList
     */
    public static List<String> getArrayTOList(String[] array){
       return new ArrayList<>(Arrays.asList(array));
    }

    /**
     * list -> Set<String>
     *
     * @param list 输入List
     * @return Set
     */
    public static Set<String> getListToSet(List<String> list) {
        return new HashSet<>(list);
    }

    /**
     * Set<String>  -> list
     * @param set 输入Set
     * @return List
     */
    public static List<String> getSetToList(Set<String> set){
      return new ArrayList<>(set);
    }

    /**
     *
     * @param str
     * @return
     */
    public static List<Integer> splitToListInt(String str) {
        //去除空格,两个分隔符之间都是空格就去掉,使用omitEmptyStrings
        //去掉前导、后导空格 trimResults
        List<String> strList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(str);

        return strList.stream().map(strItem -> Integer.parseInt(strItem)).collect(Collectors.toList());
    }
}
