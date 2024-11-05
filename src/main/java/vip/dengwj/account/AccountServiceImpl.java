package vip.dengwj.account;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountServiceImpl {
    public void transfer(String fromNo, String password, String toNo, double amount) {
        // 验证 fromNo 是否存在
        AccountDaoImpl accountDao = new AccountDaoImpl();
        Account select = accountDao.select(fromNo);

        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            // 设置当前事务的自动提交为手动提交。开启事务
            connection.setAutoCommit(false);

            if (select == null) {
                // 不存在
                throw new RuntimeException("fromNo 不存在");
                //System.out.println("fromNo 不存在");
                //return;
            }
            // 验证 fromNo 的密码是否正确
            if (!select.getPassword().equals(password)) {
                System.out.println("fromNo 的密码不正确");
                return;
            }
            // 验证余额是否充足
            Double balance = select.getBalance();
            if (balance < amount) {
                System.out.println("转账的余额不足");
                return;
            }
            // 验证 toNo 是否存在
            Account select1 = accountDao.select(toNo);
            if (select1 == null) {
                System.out.println("toNo 不存在");
                return;
            }
            // 减少 fromNo 的余额
            select.setBalance(select.getBalance() - amount);
            int update = accountDao.update(select);
            if (update != 1) {
                System.out.println("fromNo 的余额减少失败");
                return;
            }

            // 出现错误
            //int i = 10 /0;

            // 增加 toNo 的余额
            select1.setBalance(select1.getBalance() + amount);
            int update1 = accountDao.update(select1);
            if (update1 == 1) {
                System.out.println("toNo 的余额增加成功");
            }
            System.out.println("转账成功");
            // 提交事务
            connection.commit();
        } catch (Exception e) {
            System.out.println("转账失败");
            try {
                // 回滚事务
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(null, null, connection);
        }
    }
}
