package vip.dengwj.account;

public class Test {
    public static void main(String[] args) {
        AccountSercice accountService = new AccountServiceImpl();
        accountService.transfer("5001", "123", "5002", 1000);
    }
}
