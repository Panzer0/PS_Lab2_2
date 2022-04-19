import java.util.Scanner;
import java.util.TreeMap;

public class IndexPrinterHandler {
    TreeMap<Integer, Thread> threads;

    public IndexPrinterHandler() {
        threads = new TreeMap<>();
        for(int i = IndexPrinter.MIN_INDEX; i <= IndexPrinter.MAX_INDEX; i++) {
            threads.put(i, new Thread(new IndexPrinter(i)));
        }
    }

    public void start(int index) {
        validate(index);
        Thread thread = this.threads.get(index);
        if(thread.isAlive()) {
            thread.resume();
        }
        else  {
            thread.start();
        }
    }

    public void start(int firstIndex, int finalIndex) {
        validate(firstIndex, finalIndex);
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

    public boolean validate(int index) {
        return this.threads.containsKey(index);
    }
    public boolean validate(int firstIndex, int finalIndex) {
        for(int index = firstIndex; index <= finalIndex; index++) {
            if(!this.threads.containsKey(index)) {
                return false;
            }
        }
        return true;
    }

    public void suspend(int index) {
        validate(index);
        this.threads.get(index).suspend();
    }

    public void suspend(int firstIndex, int finalIndex) {
        validate(firstIndex, finalIndex);
        for(int index = firstIndex; index <= finalIndex; index++) {
            this.threads.get(index).suspend();
        }
    }

    public static void main(String[] args) {
        try {
            IndexPrinterHandler handler = new IndexPrinterHandler();


            int first, second;
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
                        System.out.println("Enter first index");
                        first = scanner.nextInt();
                        System.out.println("Enter second index");
                        second = scanner.nextInt();
                        handler.start(first, second);
                        break;
                    case 3:
                        System.out.println("Enter thread index to suspend");
                        handler.suspend(scanner.nextInt());
                        break;
                    case 4:
                        System.out.println("Enter first index");
                        first = scanner.nextInt();
                        System.out.println("Enter second index");
                        second = scanner.nextInt();
                        handler.suspend(first, second);
                        break;
                    default:
                        System.out.println("Invalid input");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
