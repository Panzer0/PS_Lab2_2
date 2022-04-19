import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class IndexPrinter implements Runnable {
    static int MIN_INDEX = 1;
    static int MAX_INDEX = 10;

    int index;

    public IndexPrinter(int index) {
        this.index = index;
    }

    public void run() {

        for(char c = 'a'; c <= 'z'; c++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.valueOf(c) + this.index%10);
        }
    }


}
