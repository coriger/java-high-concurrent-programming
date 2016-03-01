package coriger.chapt3.x1;

/**
 * Created by coriger on 2016/3/1.
 */
public class InterruptTest {

    public static void main(String[] args){

        Thread t = new Thread(new Runnable() {
            public void run() {
                while(true){
                    if(!Thread.currentThread().isInterrupted()){
                        System.out.println("sss");
                    }
                }
            }
        });

        t.start();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 中断线程
        t.interrupt();

    }


}
