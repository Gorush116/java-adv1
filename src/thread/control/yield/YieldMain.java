package thread.control.yield;

import static util.ThreadUtils.sleep;

public class YieldMain {

    static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
                // 1. empty : OS의 스레드 스케줄링을 따른다.
//                sleep(1); // 2. sleep : 특정 스레드를 잠시 쉬게 한다. (RUNNABLE -> TIMED_WAITING -> RUNNABLE)
                 Thread.yield(); // 3. yield : 다른 스레드에게 양보한다. (RUNNABLE 상태 유지)
            }
        }

    }
}
