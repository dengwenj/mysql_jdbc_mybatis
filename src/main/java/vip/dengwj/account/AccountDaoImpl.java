package vip.dengwj.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDaoImpl implements AccountDao {
    public int insert(Account account) {
        return 0;
    }
    public int delete(Account account) {
        return 0;
    }

    public int update(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "update account1 set password = ?, name =?, balance = ? where cardNo = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getName());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.setString(4, account.getCardNo());
            int res = preparedStatement.executeUpdate();
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, preparedStatement, null);
        }
    }

    // 查询单个
    public Account select(String cardNo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Account account = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from account1 where cardNo = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cardNo);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                account = new Account();
                account.setCardNo(resultSet.getString("cardNo"));
                account.setName(resultSet.getString("name"));
                account.setBalance(resultSet.getDouble("balance"));
                account.setPassword(resultSet.getString("password"));
            }
            return account;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(resultSet, preparedStatement, null);
        }
    }
}
