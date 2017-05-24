package com.z.roomshop.utils;

/**
 * Created by ZJer on 2017/5/19.
 */

public class LockBuffer {

    /**
     * 信号量是否为空，初始空为真
     */
    private boolean isEmpty = true;


    public synchronized void put() {
        //  当信号量不为空时等待
        while (!isEmpty) {
            try {
                this.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isEmpty = false;
        this.notifyAll();
    }

    public synchronized void get() {
        //  当信号量为空时等待
        while (isEmpty) {
            try {
                this.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isEmpty = true;
        this.notifyAll();
    }

}
