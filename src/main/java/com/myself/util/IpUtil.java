package com.myself.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Ip工具类
 *
 * @author Created by zion
 * @Date 2018/3/3
 */
public class IpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    /**
     * Linux 网卡配置信息
     * eth1:
     *      HWaddr  00:16:3e:96:D5:E8 每台机器网卡的HWADDR的地址是唯一的，无需更改默认即可
     *      inet addr:10.6.202.22 Ip地址
     *      Bcast:10.6.206.127  发送广播消息的 IP 地址
     *      Mask:255.255.255.128 子网掩码
     * lo: 本地回环地址，没有用，提取的时候过滤掉
     *      inet addr:127.0.0.1
     *      Mask:255.0.0.0
     */

    public final static String LOCAL_IP = "127.0.0.1";

    /**
     * 获取本地ip
     */
    public static String getLocalIP() {
        String localIP = LOCAL_IP;
        try {
            // 获得本机的所有网络接口
            Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
            //测试此枚举是否包含更多的元素
            while (netInterfaces.hasMoreElements()) {
                //如果此枚举对象至少还有一个可提供的元素，则返回此枚举的下一个元素
                NetworkInterface networkInterface = (NetworkInterface) netInterfaces.nextElement();
                InetAddress ip = networkInterface.getInetAddresses().nextElement();
                //不是本地回环地址，静态方法getLocalHost()，返回的是本地地址，(获取内网Ip 127.X.X.X,192.X.X.X,...)
                if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {
                    localIP = ip.getHostAddress();
                    break;
                }
            }
        } catch (Exception e) {
            try {
                //host配置了域名，获取结果就不是127.0.0.1
                localIP = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
                logger.error("Getting the native Ip address failed,Failed reason:"+e.getMessage());
            }
        }
        return localIP;
    }

    /**
     * 获取本机的Mac地址,
     */
    public static String getLocalMAC() {
        StringBuilder stringBuffer = new StringBuilder();
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getLocalHost();
            byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    stringBuffer.append("-");
                }
                //一定要& 0XFF,避免负数出错
                int temp = mac[i] & 0xff;
                String str = Integer.toHexString(temp);
                if (str.length() == 1) {
                    stringBuffer.append("0").append(str);
                } else {
                    stringBuffer.append(str);
                }
            }
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
            logger.error("Getting the native MAC address failed,Failed reason:"+e.getMessage());
        }
        return stringBuffer.toString().toUpperCase();
    }

    /**
     * 通过域名得到IP
     */
    public static String getIpByHost(String hostName) {
        try {
            return InetAddress.getByName(hostName).getHostAddress();
        } catch (UnknownHostException e) {
            return hostName;
        }
    }
}
