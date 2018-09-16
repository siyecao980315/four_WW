package com.wyq.ThreadTest1;

public class ThreadOutput_Synchronized3 {
	 public void output(String name) {  
         int len = name.length();  
         synchronized (ThreadOutput_Synchronized3.class) {  
             for (int i = 0; i < len; i++) {  
                 System.out.print(name.charAt(i));  
             }  
             System.out.println();  
         }  
     }  
}
