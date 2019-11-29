package leetcode.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class PrintInOrder {
    static AtomicInteger lock=new AtomicInteger(1);
    Semaphore run2,run3;
    CountDownLatch l2,l3;
    public PrintInOrder() throws InterruptedException {
        run2=new Semaphore(0);
        run3=new Semaphore(0);
        l2=new CountDownLatch(1);

    }

    public static void main(String[] args) throws InterruptedException {
        PrintInOrder printInOrder=new PrintInOrder();
        Runnable printFirst=()-> System.out.println("first");
        Runnable printSecond=()-> System.out.println("second");
        Runnable printThird=()-> System.out.println("third");
            new Thread(()->{
                try {
                    printInOrder.second(printSecond);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(()->{
                try {
                    printInOrder.first(printFirst);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(()->{
                try {
                    printInOrder.third(printThird);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (PrintInOrder.lock) {

            printFirst.run();
            lock.addAndGet(1);
            lock.notifyAll();
        }

    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (PrintInOrder.lock) {
            while (lock.get() != 2) {
                lock.wait();
            }
            printSecond.run();
            lock.addAndGet(1);
            lock.notifyAll();
        }
    }


    public void third(Runnable printThird) throws InterruptedException {
        synchronized(PrintInOrder.lock){
            while(lock.get()!=3){
                lock.wait();
            }
            printThird.run();
            lock.notifyAll();
        }

    }

}

//class PThread implements Runnable{
//    PrintInOrder printInOrder;
//
//    public PThread(PrintInOrder printInOrder) {
//        this.printInOrder = printInOrder;
//    }
//
//    @Override
//    public void run() {
//        if(PrintInOrder.lock==1) System.out.println("first");
//        if(PrintInOrder.lock==2) System.out.println("second");
//        if(PrintInOrder.lock==3) System.out.println("third");
//    }
//}
