package com.android.zpp.myapplication;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

/**
 * @ProjectName: ZUtils
 * @Package: com.android.zpp.myapplication
 * @ClassName: XUtils
 * @Description:
 * @Author: zpp
 * @CreateDate: 2022/3/16 9:17
 * @UpdateUser:
 * @UpdateDate: 2022/3/16 9:17
 * @UpdateRemark:
 */
public class XUtils {

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取屏幕尺寸
     *
     * @param ctx
     * @return
     */
    public static double getScreenPhysicalSize(Activity ctx) {
        DisplayMetrics dm = new DisplayMetrics();
        ctx.getWindowManager().getDefaultDisplay().getMetrics(dm);
        double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
        double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
        return Math.sqrt(x + y);
    }

    /**
     * 获取状态栏高度
     *
     * @param context 上下文
     * @return 高度
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    /**
     * px转dp
     *
     * @param context 上下文
     * @param pxValue 像素值
     * @return dp值
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / (scale <= 0 ? 1 : scale) + 0.5f);
    }

    /**
     * dp转px
     *
     * @param context 上下文
     * @param dp      dp值
     * @return 像素值
     */
    public static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 获取屏幕宽高
     *
     * @param context 上下文
     * @return 屏幕宽高
     */
    public static ScreenEntry getScreenSize(Context context) {
        ScreenEntry screenEntry = new ScreenEntry();
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        screenEntry.setHeight(screenHeight);
        screenEntry.setWeight(screenWidth);
        return screenEntry;
    }

    /**
     * 在关键字出截取内容
     * @param context    上下文
     * @param originText 原文字
     * @param textView   空间
     * @param words      关键字
     * @return 截取后的字段
     */
    public static String getLineMaxNumber(Context context, String originText, TextView textView, String words) {
        //获取原文字长度
        float originTextWidth = textView.getPaint().measureText(originText);
        Log.e("长度", "originTextWidth:" + originTextWidth);
        //获取控件长度
        float textViewWidth = getScreenSize(context).weight - dp2px(context, 80);
        Log.e("长度", "textViewWidth:" + textViewWidth);
        //控件长度大于文字长度 直接显示
        if (textViewWidth >= originTextWidth) {
            return originText;
        } else {
            //计算是否超出两行
            float lineNum = originTextWidth / textViewWidth;
            if (lineNum > 2) {
                //预计行数大于两行时
                int wordIndex = originText.indexOf(words);
                //未匹配到关键字
                if (wordIndex == -1) {
                    return originText;
                }
                String spannerText = originText.substring(0, wordIndex) + words;
                float splitTextWidth = textView.getPaint().measureText(spannerText);
                Log.e("长度", "spannerText:" + spannerText);
                //计算到第一个关键字时 是否超过两行
                float splitLineNum = splitTextWidth / textViewWidth;
                if (splitLineNum > 2) {
                    if (originText.endsWith(words)) {
                        //如果关键字在最后一个 往前截取8个字符
                        return "..." + originText.substring(wordIndex - 8);
                    }
                    Log.e("长度", "spannerText:" + originText.substring(wordIndex));
                    return "..." + originText.substring(wordIndex);
                } else {
                    return originText;
                }
            } else {
                return originText;
            }

        }
    }

    /**
     * 获取剪切板内容
     *
     * @return 剪切板内容
     */
    public static String paste(Context context) {
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (manager != null) {
            if (manager.hasPrimaryClip() && manager.getPrimaryClip().getItemCount() > 0) {
                CharSequence addedText = manager.getPrimaryClip().getItemAt(0).getText();
                String addedTextString = String.valueOf(addedText);
                if (!TextUtils.isEmpty(addedTextString)) {
                    return addedTextString;
                }
            }
        }
        return "";
    }

    /**
     * 清空剪切板
     */
    public static void clearOrPaste(Context context,String content) {
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (manager != null) {
            try {
                if (TextUtils.isEmpty(content)){
                    manager.setPrimaryClip(ClipData.newPlainText(null, ""));
                }else {
                    manager.setPrimaryClip(ClipData.newPlainText("Label", content));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断是否为平板
     * @return
     */
    public static boolean isTablet() {
        return (Resources.getSystem().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }


    /**
     * 判断是否位模拟器
     * @param context
     * @return
     */
    public static boolean isEmulator(Context context) {
        boolean checkProperty = Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.toLowerCase().contains("vbox")
                || Build.FINGERPRINT.toLowerCase().contains("test-keys")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
        if (checkProperty) {
            return true;
        }
        String operatorName = "";
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null) {
            String name = tm.getNetworkOperatorName();
            if (name != null) {
                operatorName = name;
            }
        }
        boolean checkOperatorName = operatorName.toLowerCase().equals("android");
        if (checkOperatorName) return true;

        String url = "tel:" + "123456";
        Intent intent = new Intent();
        intent.setData(Uri.parse(url));
        intent.setAction(Intent.ACTION_DIAL);
        boolean checkDial = intent.resolveActivity(context.getPackageManager()) == null;
        if (checkDial) {
            return true;
        }
        return false;
    }

}
