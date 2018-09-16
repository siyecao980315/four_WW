package com.wyq.ThreadTest1;

public class ThreadOutput_Synchronized1 {
	private String xx = "";  
	  
    public void output(String name) {  
        int len = name.length();  
        synchronized (xx) {  
            for (int i = 0; i < len; i++) {  
                System.out.print(name.charAt(i));  
            }  
            System.out.println();  
        }  
    }  
}
// 在测试代码里面,只new了一个Outputer对象,在两个Runable接口里面调用。
//这样才能保证用的字符串Sting的xxx是唯一的. 