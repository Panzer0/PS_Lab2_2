import java.util.Scanner;
import java.util.TreeMap;

public class IndexPrinterHandler {
    TreeMap<Integer, Thread> threads;

    public IndexPrinterHandler() {
        threads = new TreeMap<Integer, Thread>();
        for(int i = IndexPrinter.MIN_INDEX; i <= IndexPrinter.MAX_INDEX; i++) {
            threads.put(i, new Thread(new IndexPrinter(i)));
        }
    }

    public void start(int index) {
        Thread thread = this.threads.get(index);
        if(thread.isAlive()) {
            thread.resume();
        }
        else  {
            thread.start();
        }
    }

    public void start(int firstIndex, int finalIndex) {
        for(int index = firstIndex; index <= finalIndex; index++) {
            Thread current = this.threads.get(index);
            if(current.isAlive()) {
                current.resume();
            }
            else  {
                current.start();
            }
        }
    }

    public void suspend(int index) {
        this.threads.get(index).suspend();
    }

    public void suspend(int firstIndex, int finalIndex) {
        for(int index = firstIndex; index <= finalIndex; index++) {
            this.threads.get(index).suspend();
        }
    }

    public static void main(String[] args) {
        try {
            IndexPrinterHandler handler = new IndexPrinterHandler();

            Scanner scanner = new Scanner(System.in);
            while(true) {
                System.out.println("""
                        Chose operation:\s
                        1 - Start thread
                        2 - Start a range of threads
                        3 - Suspend a thread
                        4 - Suspend a range of threads""");
                switch (scanner.nextInt()) {
                    case 1:
                        System.out.println("Enter thread index to start");
                        handler.start(scanner.nextInt());
                        break;
                    case 2:
                        System.out.println("Enter thread index range to start");
                        handler.start(scanner.nextInt(), scanner.nextInt());
                        break;
                    case 3:
                        System.out.println("Enter thread index to suspend");
                        handler.suspend(scanner.nextInt());
                        break;
                    case 4:
                        System.out.println("Enter thread index range to suspend");
                        handler.suspend(scanner.nextInt(), scanner.nextInt());
                        break;
                    case 5:
                        break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
