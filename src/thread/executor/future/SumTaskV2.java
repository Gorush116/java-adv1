package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SumTaskV2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        ExecutorService es = Executors.newFixedThreadPool(2);

        Future<Integer> future1 = es.submit(task1); // 블로킹 아님
        Future<Integer> future2 = es.submit(task2); // 블로킹 아님

        // 동시 요청하여 대기 시간을 최소화
        
        Integer sum1 = future1.get(); // 블로킹
        Integer sum2 = future2.get(); // 블로킹

        log("task1.result = " + sum1);
        log("task2.result = " + sum2);

        int sumAll = sum1 + sum2;
        log("task1 + task2 = " + sumAll);

        log("end");
        es.close();
    }

    private static class SumTask implements Callable<Integer> {

        int startValue;
        int endValue;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public Integer call() throws InterruptedException {
            log("작업 시작");
            Thread.sleep(2000);
            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }
            log("작업 완료. sum = " + sum);
            return sum;
        }
    }
}
