package thread.control;

import util.ThreadUtils;

import static util.ThreadUtils.sleep;

public class CheckedExceptionMain {

    public static void main(String[] args) throws Exception {
        throw new Exception();
    }

    static class CheckedRunnable implements Runnable {

        @Override
        public void run() /*throws Exception*/ {
            // 부모 메서드가 체크 에외를 던지지 않는 경우, 재정의된 자식 메서드도 체크 예외를 던질 수는 없다.
            // 자식 메서드는 부모 메서드가 던질 수 있는 체크 예외의 하위 타입만 던질 수 있다.
//            throw new Exception(); // overridden method does not throw java.lang.Exception

            sleep(1000);
        }
    }
}
