package com.myself.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Ip工具类
 *
 * @author Created by zion
 * @Date 2018/3/3
 */
@Slf4j
public class IpUtil {

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

    public final static Pattern pattern = Pattern.
            compile("(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})");


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
                log.error("Getting the native Ip address failed,Failed reason:"+e.getMessage());
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
            log.error("Getting the native MAC address failed,Failed reason:"+e.getMessage());
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

    /**
     * 获取外网IP
     *
     * @param request
     * @return
     */
    public static String getRemoteIp(HttpServletRequest request){
        // 最后一跳是正向代理，可能会保留真实客户端IP
        String ip=request.getHeader("x-real-ip");
        if (ip==null){
            ip=request.getRemoteAddr();
        }
        //过滤反向代理的IP
        String[] stemps=ip.split(",");
        if (stemps!=null&&stemps.length>1){
            //得到第一个IP，既是客户端的真是IP
            ip=stemps[0];
        }
        ip=ip.trim();
        if(ip.length()>23){
            ip=ip.substring(0,23);
        }
        return ip;
    }

    /**
     * 获取用户的真实IP
     * @param request
     * @return
     */
    public static String getUserIP(HttpServletRequest request){
        //优先选取 X-Real-IP
        String ip=request.getHeader("X-Real-IP");
        if (StringUtils.isBlank(ip)||"unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("x-forwarded-for");
        }
        if (StringUtils.isBlank(ip)||"unknown".equalsIgnoreCase(ip)){
            if ("0:0:0:0:0:0:0:1".equals(ip)){
                ip=LOCAL_IP;
            }
        }
        if ("unknown".equalsIgnoreCase(ip)) {
            return LOCAL_IP;
        }

        int pos=ip.indexOf(",");
        if (pos>=0){
            ip=ip.substring(0,pos);
        }
        return ip;
    }

    public static String getLastIpSegment(HttpServletRequest request) {
        String ip = getUserIP(request);
        if (ip != null) {
            ip = ip.substring(ip.lastIndexOf('.') + 1);
        } else {
            ip = "0";
        }
        return ip;
    }

    public static boolean isValidIP(HttpServletRequest request) {
        String ip = getUserIP(request);
        return isValidIP(ip);
    }

    /**
     * 判断我们获取的ip是否是一个符合规则ip
     *
     * @param ip
     * @return
     */
    public static boolean isValidIP(String ip) {
        if (StringUtils.isEmpty(ip)) {
            log.debug("ip is null. valid result is false");
            return false;
        }

        Matcher matcher = pattern.matcher(ip);
        boolean isValid = matcher.matches();
        log.debug("valid ip:" + ip + " result is: " + isValid);
        return isValid;
    }
}
