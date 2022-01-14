package com.webapp;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    private static int counter;
    private static final Object LOCK = new Object();
    private static final int THREADS_NUMBER = 10_000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                throw new IllegalStateException();
            }
        };
        thread0.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }
        }).start();
        System.out.println(thread0.getState());

        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        final MainConcurrency mainConcurrency = new MainConcurrency();
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        Thread.sleep(1);
        System.out.println(counter);

        final String lock1 = "lock1";
        final String lock2 = "lock2";
        deadLock(lock1, lock2);
        deadLock(lock2, lock1);
    }

    private static void deadLock(Object lock1, Object lock2) {
        new Thread(() -> {
            System.out.println("Waiting " + lock1);
            synchronized (lock1) {
                System.out.println("Holding " + lock1);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Waiting " + lock2);
                synchronized (lock1) {
                    System.out.println("Holding " + lock2);
                }
            }
        }).start();
    }

     private synchronized void inc() {
//        synchronized (MainConcurrency.class) {
//        double a = Math.sin(13.);
//         try {
//             synchronized(this) {
                 counter++;
//                 wait();
//             }
//         } catch (InterruptedException e) {
//             e.printStackTrace();
//         }
    }
}
