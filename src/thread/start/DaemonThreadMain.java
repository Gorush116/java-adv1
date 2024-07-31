package thread.start;

public class DaemonThreadMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " : main() start");
        DaemonThread daemonThread = new DaemonThread();
        
        // 데몬 쓰레드 여부는 start 전에 결정해야 함
        daemonThread.setDaemon(true); // 데몬 쓰레드 여부
        daemonThread.start();

        System.out.println(Thread.currentThread().getName() + " : main() end");
    }

    static class DaemonThread extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " : run()");
            // 데몬 쓰레드는 throws 가 안됨(try/catch만 가능)
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " : run() end");
        }
    }
}
