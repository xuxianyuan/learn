package juc;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class LockInterruptDemo implements Runnable {

    final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockInterruptDemo lockInterrupt = new LockInterruptDemo();
        Thread thread1 = new Thread(lockInterrupt), thread2 = new Thread(lockInterrupt);
        thread1.start();
        thread2.start();
        thread2.interrupt();
    }

    public void run() {
        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + "成功获取锁");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "锁被中断了");
        } finally {
            try {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放锁成功");
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + "释放锁失败");
            }
        }
    }
}
