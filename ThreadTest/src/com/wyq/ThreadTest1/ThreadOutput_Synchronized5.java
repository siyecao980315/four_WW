package com.wyq.ThreadTest1;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadOutput_Synchronized5 {
	  ReentrantLock lock = new ReentrantLock();  
      public void output(String name) {  
          int len = name.length();  
          try {  
              // ÉÏËø  
              lock.lock();  
              for (int i = 0; i < len; i++) {  
                  System.out.print(name.charAt(i));  
              }  
              System.out.println();  
          } catch (Exception e) {  
          } finally {  
              // ÊÍ·ÅËø  
              lock.unlock();  
          }  
      }  
}
