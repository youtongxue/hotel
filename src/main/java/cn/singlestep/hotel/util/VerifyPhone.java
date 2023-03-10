package cn.singlestep.hotel.util;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/13 14:37
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验手机号码
 */
public class VerifyPhone {
    public static boolean check(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[0,1,5,6,7,8,9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            return m.matches();
        }
    }

}

