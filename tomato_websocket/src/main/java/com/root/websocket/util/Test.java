package com.root.websocket.util;


public class Test {
    public static String  execDynamicMethod(){
        return "{\n" +
                "\t\"root\": [{\n" +
                "\t\t\"test\": \"1\"\n" +
                "\t}, {\n" +
                "\t\t\"test\": \"4\"\n" +
                "\t}]\n" +
                "}";
    }

    public static void main(String[] args) {
        execDynamicMethod();
    }
}
