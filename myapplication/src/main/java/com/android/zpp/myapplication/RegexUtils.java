package com.android.zpp.myapplication;

import java.util.regex.Pattern;

/**
 * @ProjectName: ZUtils
 * @Package: com.android.zpp.myapplication
 * @ClassName: RegexUtils
 * @Description:
 * @Author: zpp
 * @CreateDate: 2022/3/18 15:48
 * @UpdateUser:
 * @UpdateDate: 2022/3/18 15:48
 * @UpdateRemark:
 */
public class RegexUtils {

    /**
     * 手机号验证
     * @param input
     * @return
     */
    public static boolean isMobileSimple(final CharSequence input) {
        return isMatch(RegexConstants.REGEX_MOBILE_SIMPLE, input);
    }

    /**
     * 身份证15位
     * @param input
     * @return
     */
    public static boolean isIDCard15(final CharSequence input) {
        return isMatch(RegexConstants.REGEX_ID_CARD15, input);
    }

    /**
     * 身份证18位
     * @param input
     * @return
     */
    public static boolean isIDCard18(final CharSequence input) {
        return isMatch(RegexConstants.REGEX_ID_CARD18, input);
    }

    /**
     * 验证邮箱
     * @param input
     * @return
     */
    public static boolean isEmail(final CharSequence input) {
        return isMatch(RegexConstants.REGEX_EMAIL, input);
    }

    /**
     * 验证是否url
     * @param input
     * @return
     */
    public static boolean isURL(final CharSequence input) {
        return isMatch(RegexConstants.REGEX_URL, input);
    }

    /**
     * 正则验证
     * @param regex
     * @param input
     * @return
     */
    public static boolean isMatch(final String regex, final CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }
}
