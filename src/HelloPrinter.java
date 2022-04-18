public class HelloPrinter implements Runnable {

    public void run() {
        System.out.println("Hello world!");
    }

    public static void main(String[] args) {
        try {
            Thread helloThread = new Thread(new HelloPrinter());
            helloThread.start();
            helloThread.join();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
