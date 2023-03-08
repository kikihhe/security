package com.xiaohe;


public class Demo {
    public static void main(String[] args) {
        int[] map = new int[100000];
        String str = "你好，Java之父余胜军!";
        for (int i = 0; i < str.length(); i++) {
            map[str.charAt(i)]++;
        }
        for (int i = 0; i < map.length; i++) {
            if (map[i] != 0) {
                System.out.println((char)i + " 出现了: " + map[i] + "次");
            }
        }
    }
}
