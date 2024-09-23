package com.example.testcode.stringutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtil {

    
    //test에서는 객체를 생성하지 않고 바로 사용하기 때문에 static으로 선언해야한다.
    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static String[] split(String str) {
        return str.split(" ");
    }

    public static List<String> findUpperCase(String str) {

        List<String> strlist = new ArrayList<>();
        String temp = "";


        String splitStr = str.replaceAll(" ", "");

        for (int i = 0; i < splitStr.length(); i++) {

            if (Character.isUpperCase(splitStr.charAt(i))) {
                if(temp != "") {
                    strlist.add(temp);
                    temp = "";
                }
                temp = String.valueOf(splitStr.charAt(i));
            }
            if(Character.isLowerCase(splitStr.charAt(i))) {
                temp += splitStr.charAt(i);
            }
            //마지막 문자 처리
            if(i == splitStr.length()-1) {
                strlist.add(temp);
            }
        }


        return strlist;
    }
}
