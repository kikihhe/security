package com.xiaohe;

/**
 * @author : 小何
 * @Description :
 * @date : 2023-01-01 22:55
 */
public class Demo {
    public static void main(String[] args) {
        int[] map = new int[300];
        String str = "oewirfgjsaknalfdsdkpaow";
        for (int i = 0; i < str.length(); i++) {
            map[str.charAt(i)]++;
        }
        for (int i = 0; i < map.length; i++) {
            if (map[i] != 0) {
                System.out.println((char)i + "出现了: " + map[i] + "次");
            }
        }
    }
}
