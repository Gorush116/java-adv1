package thread.start;

public class HelloRunnableMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " : main() start");

        HelloRunnable helloRunnable = new HelloRunnable(); // 인터페이스 구현
        Thread thread = new Thread(helloRunnable);
        thread.start(); // 작업이 분리됨

        System.out.println(Thread.currentThread().getName() + " : main() end");
    }
}
