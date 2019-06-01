package com.binzikeji.my.shop.commons.utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

/**
 * @author: Chundekepa
 * @create: 2019-03-15 21:22
 **/
public class EmailSendUtils {

    @Autowired
    private Email email;

    /**
     * 发送邮件
     * @param zhuti 主题
     * @param neirong 内容
     * @param shoujianren 收件人
     * @throws EmailException
     */
    public void send(String zhuti, String neirong, String... shoujianren) throws EmailException {
        email.setSubject(zhuti);
        email.setMsg(neirong);
        email.addTo(shoujianren);
        email.send();
    }

}
