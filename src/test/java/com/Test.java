package com;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/5/3.
 */
public class Test {
    static Pattern passMatcher = Pattern.compile("[0-9a-zA-Z_!@#$%&]{6,30}");
    public static void main(String[] args) {
        System.out.println(passMatcher.matcher("920226").matches());
    }
}
