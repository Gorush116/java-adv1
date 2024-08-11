package thread.control;

import thread.start.HelloRunnable;

import static util.MyLogger.log;

public class ThreadInfoMain {

    public static void main(String[] args) {
        // main 스레드
        Thread mainThread = Thread.currentThread();
        log("mainThread = " + mainThread);
        log("mainThread.threadId() = " + mainThread.threadId());
        log("mainThread.threadName() = " + mainThread.getName());
        log("mainThread.getPriority() = " + mainThread.getPriority()); // 우선순위 1~10(기본값 5), OS가 알아서 잘 하기 때문에 거의 조정할 일 없음
        log("mainThread.getThreadGroup() = " + mainThread.getThreadGroup());
        log("mainThread.getState() = " + mainThread.getState());

        // myThread 스레드
        // 스레드 이름 : 디버깅이나 로깅 목적으로 유용함
        Thread myThread = new Thread(new HelloRunnable(), "myThread");
        log("myThread = " + myThread);
        // 스레드 ID : 스레드 고유 식별자. 이 ID는 JVM 내에서 각 스레드에 대해 유일함
        log("myThread.threadId() = " + myThread.threadId());
        // 스레드명은 중복 가능
        log("myThread.threadName() = " + myThread.getName());
        log("myThread.getPriority() = " + myThread.getPriority()); // 1~10(기본값 5)
        // 스레드 그룹 : 스레드 일괄종료, 우선순위 부여 등(직접적으로 잘 사용하지 않음)
        // 부모 스레드 : 스레드는 기본적으로 다른 스레드에 의해 생성(myThread는 main 스레드에 의해 생성)
        log("myThread.getThreadGroup() = " + myThread.getThreadGroup());
        log("myThread.getState() = " + myThread.getState());
    }
}
