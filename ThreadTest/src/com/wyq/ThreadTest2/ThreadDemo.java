package com.wyq.ThreadTest2;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;

class Buffer {
	private int maxSize;
	private List<Date> storage;
    Buffer(int size){
        maxSize=size;
        storage = new LinkedList<>();
    }
    //��������
    public synchronized void put()  {
        try {
            while (storage.size() ==maxSize ){//�����������
                System.out.print(Thread.currentThread().getName()+": wait \n");;
                wait();//�����߳�
            }
            storage.add(new Date());
            System.out.print(Thread.currentThread().getName()+": put:"+storage.size()+ "\n");
            Thread.sleep(1000);
            notifyAll();//�����߳�
        } catch (InterruptedException e) {
            e.printStackTrace();
        }       
    }
    //���ѷ���
    public synchronized void take() {
        try { 
            while (storage.size() ==0 ){//�����������
                System.out.print(Thread.currentThread().getName()+": wait \n");;
                wait();//�����߳�
            }
            @SuppressWarnings("unused")
			Date d=((LinkedList<Date>)storage).poll();
            
            System.out.print(Thread.currentThread().getName()+": take:"+storage.size()+ "\n");
            Thread.sleep(1000);
            notifyAll();//�����߳�
        } catch (InterruptedException e) {
            e.printStackTrace();
        }       
    } 
}
//������
class Producer implements Runnable{
	private Buffer buffer;
	Producer(Buffer b){
	    buffer=b;
	}
	@Override
	public void run() {
	    while(true){
	        buffer.put();
	    }
	}   
}
//������
class Consumer implements Runnable{
	private Buffer buffer;
	Consumer(Buffer b){
	    buffer=b;
	}
	@Override
	public void run() {
	    while(true){
	        buffer.take();
	    }
	}   
}
//


public class ThreadDemo {

	public static void main(String[] arg){
        Buffer buffer=new Buffer(10);
        Producer producer=new Producer(buffer);
        Consumer consumer=new Consumer(buffer);
        //�����߳�ִ������������
        for(int i=0;i<3;i++){
            new Thread(producer,"producer-"+i).start();
        }
        for(int i=0;i<3;i++){
            new Thread(consumer,"consumer-"+i).start();
        }
    }


}
