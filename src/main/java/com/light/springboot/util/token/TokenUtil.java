package com.light.springboot.util.token;

import com.light.springboot.util.md5.MD5Utils;

/**
 * @author 李振振
 * @version 1.0
 * @date 2019/9/11 下午10:05
 */
public class TokenUtil {
    public static String getToken(long date,String userName){
        return MD5Utils.md5Encrypt32Lower(date+userName);
    }
}
