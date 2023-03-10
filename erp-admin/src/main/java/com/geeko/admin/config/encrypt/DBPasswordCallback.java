package com.geeko.admin.config.encrypt;


import com.alibaba.druid.util.DruidPasswordCallback;
import com.geeko.admin.utils.AES256Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author JaneKim
 * @date 2023/3/9
 * @descript
 */
public class DBPasswordCallback extends DruidPasswordCallback {

    @Override
    public void setProperties(Properties properties) {
        String pwd = properties.getProperty("pwd");
        if (StringUtils.isNotBlank(pwd)) {
            try
            {
                String decode = AES256Util.decode(AES256Util.DEFAULT_SECRET_KEY, pwd);
                setPassword(decode.toCharArray());
            } catch (Exception e) {
                setPassword(pwd.toCharArray());
            }
        }
    }
}
