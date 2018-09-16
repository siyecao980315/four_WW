package com.wyq.ThreadTest1;

public class ThreadOutput {
	
    public void output(String name) {  
        int len = name.length();  
        for(int i = 0; i < len; i++) {  
            System.out.print(name.charAt(i));  
        }  
        System.out.println();  
    }   
}
