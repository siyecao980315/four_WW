package com.wyq.ThreadTest2;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//当生产者执行put方法时，调用notEmpty.signalAll()只会唤醒notEmpty.await()下的消费者线程。 
// 当消费者执行take方法时，调用notFull.signalAll()只会唤醒notFull.await()下的消费者线程。
class Buffer1 {
    private  final Lock lock;
    private  final Condition notFull;
    private  final Condition notEmpty;
    private int maxSize;
    private List<Date> storage;
    Buffer1(int size){
        //使用锁lock，并且创建两个condition，相当于两个阻塞队列
        lock=new ReentrantLock();
        notFull=lock.newCondition();
        notEmpty=lock.newCondition();
        maxSize=size;
        storage=new LinkedList<>();
    }
    public void put()  {
        lock.lock();
        try {   
            while (storage.size() ==maxSize ){//如果队列满了
                System.out.print(Thread.currentThread().getName()+": wait \n");;
                notFull.await();//阻塞生产线程
            }
            storage.add(new Date());
            System.out.print(Thread.currentThread().getName()+": put:"+storage.size()+ "\n");
            Thread.sleep(1000);         
            notEmpty.signalAll();//唤醒消费线程
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
            while (storage.size() ==0 ){//如果队列满了
                System.out.print(Thread.currentThread().getName()+": wait \n");;
                notEmpty.await();//阻塞消费线程
            }
            @SuppressWarnings("unused")
			Date d=((LinkedList<Date>)storage).poll();
            System.out.print(Thread.currentThread().getName()+": take:"+storage.size()+ "\n");
            Thread.sleep(1000);         
            notFull.signalAll();//唤醒生产线程

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
