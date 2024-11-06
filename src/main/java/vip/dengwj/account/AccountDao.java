package vip.dengwj.account;

public interface AccountDao {
    int insert(Account account);
    int update(Account account);
    int delete(Account account);
    Account select(String cardNo);
}
