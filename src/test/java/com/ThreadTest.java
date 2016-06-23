package com;

import java.util.Date;

/**
 * Created by Administrator on 2016/5/10.
 */
public class ThreadTest {

    static Object o = new Object();

    public static void main(String[] args) {
        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();
    }
    static class ThreadB implements Runnable{

        public void run() {
            System.out.println("B"+new Date().getTime()+" :start");
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(o) {
                o.notify();
            }
            System.out.println("B"+new Date().getTime()+" :end");
        }
    }

    static class ThreadA implements Runnable{

        public void run() {
            System.out.println("A"+new Date().getTime()+" :start");
            synchronized(o){
                try {
                    o.wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("A"+new Date().getTime()+" :end");
        }
    }

}
