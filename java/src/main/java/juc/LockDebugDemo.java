package juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDebugDemo implements Runnable {

    final Lock lock = new ReentrantLock();

    private void debugLock() {
        System.out.println("还没拿到资源呢");
        //state=0
        lock.lock();
        //state=1
        lock.lock();
        //state=2
        System.out.println("获取资源");
        lock.unlock();
        //state=1
    }

    public static void main(String[] args) {
        new Thread(new LockDebugDemo()).start();
    }

    public void run() {
        debugLock();
    }
}
