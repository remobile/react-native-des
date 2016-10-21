package com.remobile;

public class Test {
    public static void main(String[] args) throws Exception {
        String data = "fangyunjiang is a good developer";
        String key = "ABCDEFGH";
        String base64 = Des.encrypt(data, key);
        String text = Des.decrypt(base64, key);
        System.out.println(base64);
        System.out.println(text);
    }
}
