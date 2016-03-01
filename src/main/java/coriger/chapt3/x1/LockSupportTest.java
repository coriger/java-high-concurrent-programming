package coriger.chapt3.x1;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by coriger on 2016/3/1.
 */
public class LockSupportTest {

    public static void main(String[] args){

        Thread t = new Thread(new Runnable() {
            public void run() {
                if(true){
                    System.out.println("start part");
                    // 阻塞当前线程
                    LockSupport.park();
                    System.out.println("end part "+Thread.currentThread().isInterrupted());
                }
            }
        });

        t.start();

        try {
            Thread.sleep(1000);
            System.out.println("sleep 1s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 唤醒t线程
        LockSupport.unpark(t);

        // 中断线程
//        t.interrupt();
    }


}
