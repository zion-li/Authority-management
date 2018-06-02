package com.myself.util;


import com.myself.model.Mail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

@Slf4j
public class MailUtil {

    /**
     * 发送邮件
     * @param mail Model
     * @return
     */
    public static boolean send(Mail mail) {

        // TODO 放在配置文件里
        String from = "";
        int port = 25;
        String host = "";
        String pass = "";
        String nickname = "";

        HtmlEmail email = new HtmlEmail();
        try {
            //发送方smtp
            email.setHostName(host);
            //编码格式
            email.setCharset("UTF-8");
            //接收方
            for (String str : mail.getReceivers()) {
                email.addTo(str);
            }
            //发送方的邮箱地址，邮箱用户名
            email.setFrom(from, nickname);
            email.setSmtpPort(port);
            email.setAuthentication(from, pass);
            email.setSubject(mail.getSubject());
            email.setMsg(mail.getMessage());
            email.send();
            log.info("{} 发送邮件到 {}", from, StringUtils.join(mail.getReceivers(), ","));
            return true;
        } catch (EmailException e) {
            log.error(from + "发送邮件到" + StringUtils.join(mail.getReceivers(), ",") + "失败", e);
            return false;
        }
    }
}

