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
// �ڲ��Դ�������,ֻnew��һ��Outputer����,������Runable�ӿ�������á�
//�������ܱ�֤�õ��ַ���Sting��xxx��Ψһ��. 