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
// ��Ϊÿ����ķ�������,�������һ��this����,
//���Բ���Ҫ��ʹ��һ���µ��ַ���.ֱ��ʹ��this����ͺ��ˡ� 