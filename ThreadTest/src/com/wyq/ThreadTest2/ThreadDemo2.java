package com.wyq.ThreadTest2;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//��������ִ��put����ʱ������notEmpty.signalAll()ֻ�ỽ��notEmpty.await()�µ��������̡߳� 
// ��������ִ��take����ʱ������notFull.signalAll()ֻ�ỽ��notFull.await()�µ��������̡߳�
class Buffer1 {
    private  final Lock lock;
    private  final Condition notFull;
    private  final Condition notEmpty;
    private int maxSize;
    private List<Date> storage;
    Buffer1(int size){
        //ʹ����lock�����Ҵ�������condition���൱��������������
        lock=new ReentrantLock();
        notFull=lock.newCondition();
        notEmpty=lock.newCondition();
        maxSize=size;
        storage=new LinkedList<>();
    }
    public void put()  {
        lock.lock();
        try {   
            while (storage.size() ==maxSize ){//�����������
                System.out.print(Thread.currentThread().getName()+": wait \n");;
                notFull.await();//���������߳�
            }
            storage.add(new Date());
            System.out.print(Thread.currentThread().getName()+": put:"+storage.size()+ "\n");
            Thread.sleep(1000);         
            notEmpty.signalAll();//���������߳�
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{   
            lock.unlock();
        }
    }

    public  void take() {       
        lock.lock();
        try {  
            while (storage.size() ==0 ){//�����������
                System.out.print(Thread.currentThread().getName()+": wait \n");;
                notEmpty.await();//���������߳�
            }
            @SuppressWarnings("unused")
			Date d=((LinkedList<Date>)storage).poll();
            System.out.print(Thread.currentThread().getName()+": take:"+storage.size()+ "\n");
            Thread.sleep(1000);         
            notFull.signalAll();//���������߳�

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    } 
}

class Producer1 implements Runnable{
    private Buffer buffer;
    Producer1(Buffer b){
        buffer=b;
    }
    @Override
    public void run() {
        while(true){
            buffer.put();
        }
    }   
}
class Consumer1 implements Runnable{
    private Buffer buffer;
    Consumer1(Buffer b){
        buffer=b;
    }
    @Override
    public void run() {
        while(true){
            buffer.take();
        }
    }   
}


public class ThreadDemo2 {
	public static void main(String[] arg){
        Buffer buffer=new Buffer(10);
        Producer producer=new Producer(buffer);
        Consumer consumer=new Consumer(buffer);
        for(int i=0;i<3;i++){
            new Thread(producer,"producer-"+i).start();
        }
        for(int i=0;i<4;i++){
            new Thread(consumer,"consumer-"+i).start();
        }
    }

}
