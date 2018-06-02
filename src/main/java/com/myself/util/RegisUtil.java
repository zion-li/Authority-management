package com.myself.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用正则
 *
 * @author Created by zion
 * @Date 2018/3/23
 */
public class RegisUtil {
    /**
     * 密码的正则表达式
     * 规则:密码最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符
     * ^:匹配输入字符串的开始位置，除非在方括号表达式中使用，此时它表示不接受该字符集合。要匹配 ^ 字符本身，请使用 \^
     * .:匹配除换行符 \n 之外的任何单字符
     * *:匹配前面的子表达式零次或多次
     * ?:匹配前面的子表达式零次或一次
     * +"匹配前面的子表达式一次或多次
     * ():标记一个子表达式的开始和结束位置
     * (?=exp):也叫零宽度正预测先行断言，它断言自身出现的位置的后面能匹配表达式exp
     * $:匹配输入字符串的结尾位置
     */
    public static final String PSW = "^.*(?=.{6,})(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$";

    /**
     * EMAIL正则表达式
     * 优先级：相同优先级的从左到右进行运算，不同优先级的运算先高后低
     * \:转义符
     * (), (?:), (?=), []:圆括号和方括号
     * *, +, ?, {n}, {n,}, {n,m}:限定符
     * ^, $, \任何元字符、任何字符:定位点和序列（即：位置和顺序）
     * |:或
     */
    public static final String EMAIL = "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+";

    /**
     * 电话号码正则表达式
     * 不太好
     */
    public static final String PHONE = "(^(\\d{2,4}[-_－—]?)?\\d{3,8}([-_－—]?\\d{3,8})?([-_－—]?\\d{1,7})?$)|(^0?1[35]\\d{9}$)";

    /**
     * 手机号码正则表达式
     */
    public static final String MOBILE = "^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$";

    /**
     * Integer正则表达式
     * -?:可选减号
     */
    public static final String INTEGER = "^-?(([1-9]\\d*$)|0)";

    /**
     * 正整数正则表达式 >=0
     */
    public static final String INTEGER_NEGATIVE = "^[1-9]\\d*|0$";

    /**
     * 负整数正则表达式 <= 0
     */
    public static final String INTEGER_POSITIVE = "^-[1-9]\\d*|0$";

    /**
     * Double正则表达式
     * -?:可选减号
     */
    public static final String DOUBLE = "^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$";
    /**
     * 正Double正则表达式 >=0
     */
    public static final String DOUBLE_NEGATIVE = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$";
    /**
     * 负Double正则表达式 <= 0
     */
    public static final String DOUBLE_POSITIVE = "^(-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*))|0?\\.0+|0$";

    /**
     * 年龄正则表达式,匹配0-120岁
     */
    public static final String AGE = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";

    /**
     * 邮编正则表达式,国内6位邮编
     */
    public static final String CODE = "[0-9]\\d{5}(?!\\d)";

    /**
     * 匹配由数字、26个英文字母或者下划线组成的字符串
     */
    public static final String STR_ENG_NUM_UNDERLINE = "^\\w+$";

    /**
     * 匹配由数字和26个英文字母组成的字符串
     */
    public static final String STR_ENG_NUM = "^[A-Za-z0-9]+";

    /**
     * 匹配由26个英文字母组成的字符串
     */
    public static final String STR_ENG = "^[A-Za-z]+$";

    /**
     * 匹配数字组成的字符串
     */
    public static final String STR_NUM = "^[0-9]+$";

    /**
     * 匹配中文
     */
    public static final String CN = "([\u4e00-\u9fa5]+)";

    /**
     * 过滤特殊字符串正则
     * regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
     */
    public static final String STR_SPECIAL = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

    /**
     * 判断字段是否为空 符合返回
     *
     * @param str 输入字符串
     * @return boolean
     */
    public static synchronized boolean strisNull(String str) {
        return null == str || str.trim().length() <= 0 ? true : false;
    }

    /**
     * 判断字段是非空 符合返回
     *
     * @param str 输入字符串
     * @return boolean
     */
    public static boolean strNotNull(String str) {
        return !strisNull(str);
    }

    /**
     * 字符串null转空
     *
     * @param str 输入字符串
     * @return boolean
     */
    public static String nulltoStr(String str) {
        return strisNull(str) ? "" : str;
    }

    /**
     * 字符串null赋值默认值
     *
     * @param str    目标字符串
     * @param defaut 默认值
     * @return String
     */
    public static String nulltoStr(String str, String defaut) {
        return strisNull(str) ? defaut : str;
    }

    /**
     * 判断字段是否超长
     * 字串为空返回fasle, 超过长度{leng}返回ture 反之返回false
     *
     * @param str  目标字符串
     * @param leng 目标长度
     * @return boolean
     */
    public static boolean isLengOut(String str, int leng) {
        return strisNull(str) ? false : str.trim().length() > leng;
    }

    /**
     * 判断字段是否为Email 符合返回ture
     *
     * @param str
     * @return boolean
     */
    public static boolean isEmail(String str) {
        return regular(str, EMAIL);
    }

    /**
     * 判断是否为电话号码 符合返回
     *
     * @param str 输入字符串
     * @return boolean
     */
    public static boolean isPhone(String str) {
        return regular(str, PHONE);
    }

    /**
     * 判断是否为手机号码 符合返回
     *
     * @param str 输入字符串
     * @return boolean
     */
    public static boolean isMobile(String str) {
        return regular(str, MOBILE);
    }

    /**
     * 判断字段是否为数字 正负整数 正负浮点数
     *
     * @param str 输入字符串
     * @return boolean
     */
    public static boolean isNumber(String str) {
        return regular(str, DOUBLE);
    }

    /**
     * 判断字段是否为INTEGER
     *
     * @param str 输入字符串
     * @return boolean
     */
    public static boolean isInteger(String str) {
        return regular(str, INTEGER);
    }

    /**
     * 判断字段是否为正整数正则表达式 >=0 符合返回ture
     *
     * @param str
     * @return boolean
     */
    public static boolean isIntegerNegative(String str) {
        return regular(str, INTEGER_NEGATIVE);
    }

    /**
     * 判断字段是否为负整数正则表达式 <=0 符合返回ture
     *
     * @param str
     * @return boolean
     */
    public static boolean isIntegerPositive(String str) {
        return regular(str, INTEGER_POSITIVE);
    }

    /**
     * 判断字段是否为DOUBLE 符合返回ture
     *
     * @param str
     * @return boolean
     */
    public static boolean isDouble(String str) {
        return regular(str, DOUBLE);
    }

    /**
     * 判断字段是否为正浮点数正则表达式 >=0 符合返回ture
     *
     * @param str
     * @return boolean
     */
    public static boolean isDoubleNegative(String str) {
        return regular(str, DOUBLE_NEGATIVE);
    }

    /**
     * 判断字段是否为负浮点数正则表达式 <=0 符合返回ture
     *
     * @param str
     * @return boolean
     */
    public static boolean isDoublePositive(String str) {
        return regular(str, DOUBLE_POSITIVE);
    }

    /**
     * 判断字段是否为年龄 符合返回ture
     *
     * @param str
     * @return boolean
     */
    public static boolean isAge(String str) {
        return regular(str, AGE);
    }

    /**
     * 判断字符串是不是全部是英文字母
     *
     * @param str
     * @return boolean
     */
    public static boolean isEnglish(String str) {
        return regular(str, STR_ENG);
    }

    /**
     * 判断字符串是不是全部是英文字母+数字
     *
     * @param str
     * @return boolean
     */
    public static boolean isEngNum(String str) {
        return regular(str, STR_ENG_NUM);
    }

    /**
     * 判断字符串是不是全部是英文字母+数字+下划线
     *
     * @param str
     * @return boolean
     */
    public static boolean isEngNumAndUnderline(String str) {
        return regular(str, STR_ENG_NUM_UNDERLINE);
    }

    /**
     * 过滤特殊字符串 返回过滤后的字符串
     *
     * @param str
     * @return boolean
     */
    public static String filterStr(String str) {
        Pattern p = Pattern.compile(STR_SPECIAL);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 匹配是否符合正则表达式pattern 匹配返回true
     *
     * @param str     匹配的字符串
     * @param pattern 匹配模式
     * @return boolean
     */
    private static boolean regular(String str, String pattern) {
        if (null == str || str.trim().length() <= 0) {
            return false;
        }
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 提取中文
     */
    public static String getChinese(String paramValue) {
        StringBuilder str = new StringBuilder();
        Matcher matcher = Pattern.compile(CN).matcher(paramValue);
        while (matcher.find()) {
            str.append(matcher.group(0));
        }
        return String.valueOf(str);
    }
}
