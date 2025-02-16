package thread.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV6 implements BankAccount {

    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV6(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작 : " + getClass().getSimpleName());

        try {
            if (!lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                log("[진입 실패] 이미 처리중인 작업이 있습니다.");
                return false;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // == 임계 영역 시작 ==
        try {
            log("[검증 시작]  출금액 : " + amount + ", 잔액 : " + balance);
            if (amount > balance) {
                log("[검증 실패]  출금액 : " + amount + ", 잔액 : " + balance);
                return false;
            }
            // 검증 : 잔고가 출금액보다 많으면, 진행
            log("[검증 완료]  출금액 : " + amount + ", 잔액 : " + balance);
            sleep(1000); // 출금에 걸리는 시간으로 가정
            balance = balance - amount;
            log("[출금 완료]  출금액 : " + amount + ", 잔액 : " + balance);

            // == 임계 영역 종료 ==
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock(); // ReentrantLock 이용하여 lock 해제
        }

        log("거래 종료 : " + getClass().getSimpleName());
        return true;
    }

    @Override
    public synchronized int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock(); // ReentrantLock 이용하여 lock 해제
        }
    }
}
