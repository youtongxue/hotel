package cn.singlestep.hotel.util;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/13 14:24
 */
public class VerifyEmail {
    private final static String A = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    public static boolean verify(String email){
        if (email.matches(A)){
            return true;
        }else {
            return false;
        }
    }
}
