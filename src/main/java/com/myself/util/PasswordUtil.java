package com.myself.util;


import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class PasswordUtil {
    /**
     * 生成密码的基础英文值，去掉容易看错的
     */
    public final static String[] word = {
            "a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "m", "n",
            "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "M", "N",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    public final static String[] num = {"2", "3", "4", "5", "6", "7", "8", "9"};

    /**
     * 随机生成一个原始密码
     * @return
     */
    public static String randomPassword() {
        StringBuffer stringBuffer = new StringBuffer();
        //没办法添加一个延时，要不让有相同的密码
        try {
            TimeUnit.MILLISECONDS.sleep(3);
        } catch (InterruptedException e) {
            //TODO 再说
            e.printStackTrace();
        }
        Random random = new Random(new Date().getTime());
        boolean flag = false;
        int length = random.nextInt(3) + 8;
        for (int i = 0; i < length; i++) {
            if (flag) {
                stringBuffer.append(num[random.nextInt(num.length)]);
            } else {
                stringBuffer.append(word[random.nextInt(word.length)]);
            }
            flag = !flag;
        }
        return stringBuffer.toString();
    }
}
