package com.eonedu.global.util;

public class RandomIdMaker {
    public static String makeTwelveRandomId(){
        String randomId = "";
        for(int i = 0; i < 12; i++){
            randomId += (int)(Math.random() * 10);
        }
        return randomId;
    }
}
