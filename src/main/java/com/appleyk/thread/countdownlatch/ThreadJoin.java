package com.appleyk.thread.countdownlatch;

/**
 * <p>利用线程的join方法</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 16:29 2020/12/8
 */
public class ThreadJoin {

    static class TaskRunner implements Runnable{
        private int number;
        public TaskRunner(int number) {
            this.number = number;
        }
        @Override
        public void run() {
            try{
                Thread.sleep(1000);
                System.out.println(number+"号,准备就绪！");
            }catch (InterruptedException e){

            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(new TaskRunner(1));
        Thread t2 = new Thread(new TaskRunner(2));
        Thread t3 = new Thread(new TaskRunner(3));
        Thread t4 = new Thread(new TaskRunner(4));
        Thread t5 = new Thread(new TaskRunner(5));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        t3.join();
        t2.join();
        t4.join();
        t5.join();

        System.out.println("裁判：各就各位，预备.....");
    }

}
