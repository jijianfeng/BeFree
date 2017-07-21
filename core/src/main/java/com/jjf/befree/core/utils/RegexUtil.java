package com.jjf.befree.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jjf_lenovo on 2017/5/31.
 */
public class RegexUtil {
    public static boolean isMatches(String pattern,String content){
        return Pattern.matches(pattern, content);
    }

    public String[] getRegexArray(String pattern,String content){
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(content);
        String[] result = new String[m.groupCount()-1];
        if (m.find( )) {
            for(int i=1;i<=m.groupCount();i++){
//                System.out.println("Found value: " + m.group(i) );
                result[i-1] = m.group(i);
            }
        }
        return result;
    }
}
