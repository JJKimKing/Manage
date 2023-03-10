package com.geeko.admin.utils;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;

/**
 * @author JaneKim
 * @date 2023/3/8
 * @descript
 */
public class RandomUIDUtils {


    private static String getUUId() {
        return UUID.randomUUID().toString();
    }


    public static String getFastUUID(){
        return UUID.fastUUID().toString();
    }
}
