package com.wyq.ThreadTest1;

public class Threadtest1 {
    static final ThreadOutput_Synchronized4 outputer = new ThreadOutput_Synchronized4();  
    public static void main(String[] args) {  
        new Thread(new Runnable() {  
            @Override  
            public void run() {  
                while (true) {  
                    try {  
                        Thread.sleep(10);  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                    outputer.output("wyq");  
                }  
  
            }  
        }).start();  
  
        new Thread(new Runnable() {  
            @Override  
            public void run() {  
                while (true) {  
                    try {  
                        Thread.sleep(10);  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                    outputer.output("handesome");  
                }  
  
            }  
        }).start();    
    } 
} 
 