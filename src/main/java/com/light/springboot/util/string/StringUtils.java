package com.light.springboot.util.string;

/**
 * @author 李振振
 * @version 1.0
 * @date 2019/8/10 15:37
 */
public class StringUtils {
    public static boolean isNull(Object obj){
        return obj==null ;
    }

    public static boolean isEmpty(Object obj){
        if(obj!=null){
            if("".equals((String)obj)){
                return true;
            }else{
                return false;
            }
        }else{
            return true;
        }
    }

    public static String safe2String(Object str ,String defaultString){
        if(isNull(str)){
            return defaultString;
        }else if(isEmpty(str)){
            return defaultString;
        }else{
            return (String)str;
        }
    }
    public static String safe2String(Object str){
        String defaultString = "";
        if(isNull(str)){
            return defaultString;
        }else if(isEmpty(str)){
            return defaultString;
        }else{
            return (String) str;
        }
    }
}
