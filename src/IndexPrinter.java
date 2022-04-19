import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


public class IndexPrinter implements Runnable{
    static int MIN_INDEX = 1;
    static int MAX_INDEX = 10;

    static Semaphore semaphore = new Semaphore(1);

    int index;

    public IndexPrinter(int index) {
        this.index = index;
    }

    public void run() {
        try {
            semaphore.acquire();
            for(char c = 'a'; c <= 'z'; c++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(String.valueOf(c) + this.index%10);
            }
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
