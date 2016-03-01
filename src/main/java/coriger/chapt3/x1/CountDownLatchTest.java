package coriger.chapt3.x1;

import java.util.concurrent.CountDownLatch;

/**
 * Created by coriger on 2016/3/1.
 */
public class CountDownLatchTest {

    public static final CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void main(String[] args){

        for(int i = 0;i<10;i++){
            new Thread(new Runnable() {
                public void run() {
                    countDownLatch.countDown();
                    System.out.println(Thread.currentThread().getName()+" countDown");
                }
            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end~");
    }


}
