package com.forever.util.Array;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrayUtil {


    /**
     * 将Integer数组转化为字符串数组
     * @param integers
     * @return
     */
    public static String[] parseIntegerArrayToStringArray(Integer [] integers){
        String strs[] = new String[integers.length];
        for(int i=0;i<strs.length;i++){
            strs[i] = String.valueOf(integers[i]);
        }
        return strs;
    }

    /**
     * 将字符串分割并转化为Integer数组
     * @param str
     * @param splitRegx
     * @return
     */
    public static Integer[] parseStringToIntegerArray(String str,String splitRegx){
        String strs[] = replaceBlank(str).split(splitRegx);
        return parseStringArrayToIntegerArray(strs);
    }


    /**
     *去空格、回车、换行符、制表符
     */
    public static String replaceBlank(String str) {
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            str = m.replaceAll("");
        }
        return str;
    }


    /**
     * 将字符串数组转化为Integer数组
     * @param strs
     * @return
     */
    public static Integer[] parseStringArrayToIntegerArray(String [] strs){
        Integer integers[] = new Integer[strs.length];
        for(int i=0;i<strs.length;i++){
            integers[i] = Integer.parseInt(strs[i]);
        }
        return integers;
    }
}
