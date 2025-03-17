package thread.executor.test;

import java.util.concurrent.ExecutionException;

public class NewOrderServiceTestMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String orderNo = "Order#1234"; // 에서 주문 번호
        newOrderService newOrderService = new newOrderService();
        newOrderService.order(orderNo);
    }
}
