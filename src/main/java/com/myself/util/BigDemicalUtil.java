package com.myself.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 在上面简单地介绍了银行家舍入法，目前java支持7中舍入法：

 1、 ROUND_UP：远离零方向舍入。向绝对值最大的方向舍入，只要舍弃位非0即进位。

 2、 ROUND_DOWN：趋向零方向舍入。向绝对值最小的方向输入，所有的位都要舍弃，不存在进位情况。

 3、 ROUND_CEILING：向正无穷方向舍入。向正最大方向靠拢。若是正数，舍入行为类似于ROUND_UP，若为负数，舍入行为类似于ROUND_DOWN。Math.round()方法就是使用的此模式。

 4、 ROUND_FLOOR：向负无穷方向舍入。向负无穷方向靠拢。若是正数，舍入行为类似于ROUND_DOWN；若为负数，舍入行为类似于ROUND_UP。

 5、 HALF_UP：最近数字舍入(5进)。这是我们最经典的四舍五入。

 6、 HALF_DOWN：最近数字舍入(5舍)。在这里5是要舍弃的。

 7、 HAIL_EVEN：银行家舍入法。
 * @author Created by zion
 * @Date 2018/2/22.
 */
public class BigDemicalUtil {
    /**
     * 提供精确加法计算的add方法
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static double add(double value1,double value2){
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确减法运算的sub方法
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static double sub(double value1,double value2){
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确乘法运算的mul方法
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static double mul(double value1,double value2){
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
     * @param value1 被除数
     * @param value2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double value1,double value2,int scale) throws IllegalAccessException{
        if(scale<0){
            //如果精确范围小于0，抛出异常信息。
            throw new IllegalArgumentException("精确度不能小于0");
        }else if(value2 == 0){
            //如果除数为0，抛出异常信息。
            throw new IllegalArgumentException("除数不能为0");
        }
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精确度不能小于0");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 提供精确的小数位四舍五入处理。(u)
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double roundForBank(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精确度不能小于0");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, RoundingMode.HALF_EVEN).doubleValue();
    }

    /**
     * 提供精确加法计算的add方法，确认精确度
     * @param value1 被加数
     * @param value2 加数
     * @param scale 小数点后保留几位
     * @return 两个参数求和之后，按精度四舍五入的结果
     */
    public static double add(double value1, double value2, int scale){
        return round(add(value1, value2), scale);
    }

    /**
     * 提供精确减法运算的sub方法，确认精确度
     * @param value1 被减数
     * @param value2 减数
     * @param scale 小数点后保留几位
     * @return 两个参数的求差之后，按精度四舍五入的结果
     */
    public static double sub(double value1, double value2, int scale){
        return round(sub(value1, value2), scale);
    }

    /**
     * 提供精确乘法运算的mul方法，确认精确度
     * @param value1 被乘数
     * @param value2 乘数
     * @param scale 小数点后保留几位
     * @return 两个参数的乘积之后，按精度四舍五入的结果
     */
    public static double mul(double value1, double value2, int scale){
        return round(mul(value1, value2), scale);
    }
}
