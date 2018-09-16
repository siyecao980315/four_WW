package com.wyq.ThreadTest1;

public class ThreadOutput_Synchronized2 {
	public void output(String name) {  
        int len = name.length();  
        synchronized (this) {  
            for (int i = 0; i < len; i++) {  
                System.out.print(name.charAt(i));  
            }  
            System.out.println();  
        }  
    }  
}
// 因为每个类的方法里面,本身就有一个this对象,
//所以不需要再使用一个新的字符串.直接使用this对象就好了。 