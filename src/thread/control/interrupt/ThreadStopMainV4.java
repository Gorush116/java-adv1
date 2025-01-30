package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV4 {

    public static void main(String[] args) {

        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(100);
        log("작업 중단 지시 thread.interrupt()");
        thread.interrupt();
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        // 여러 스레드에서 공유하는 값에 사용하는 키워드(추후 상세히 설명)
        volatile boolean runFlag = true;

        @Override
        public void run() {

            // 인터럽트 상태 변경 O(인터럽트 상태라면 true를 반환하고 해당 스레드 인터럽트 상태 false로 변환
            while(!Thread.interrupted()) {
                log("작업중");
            }

            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());
            try {
                log("자원 정리");
                Thread.sleep(1000);
                log("자원 종료");
            } catch (InterruptedException e) {
                log("자원 정리 실패 - 자원 정리 중 인터럽트 발생");
                log("work 스레드 인터럽트 상태3 = " + Thread.currentThread().isInterrupted());
                throw new RuntimeException(e);
            }
            log("작업 종료");
        }
    }
}
