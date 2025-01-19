package thread.start;

public class BadThreadMain {

    public static void main(String[] args) {

        // Main Thread
        System.out.println(Thread.currentThread().getName() + " : main() start");

        HelloThread helloThread = new HelloThread();
        System.out.println(Thread.currentThread().getName() + " : start() 호출 전");
        // 반드시 run 치지 마라 -> main Thread가 run을 직접 실행하게 됨
        helloThread.run();
        System.out.println(Thread.currentThread().getName() + " : start() 호출 후");

        System.out.println(Thread.currentThread().getName() + " : main() end");
    }
}
