package juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo implements Runnable {

    final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        ReadWriteLockDemo demo = new ReadWriteLockDemo();
        Thread t1 = new Thread(demo), t2 = new Thread(demo);
        t1.start();
        t2.start();
    }

    public void run() {
        //synchronized (this)//不同读操作相互阻塞
        {
            try {
                readWriteLock.readLock().lock();
                //readWriteLock.writeLock().lock();//因为读锁，写锁阻塞，此处产生死锁
                System.out.println(Thread.currentThread().getName() + "读取中...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "读取结束！");
            } finally {
                readWriteLock.readLock().unlock();
            }
        }
    }
}
