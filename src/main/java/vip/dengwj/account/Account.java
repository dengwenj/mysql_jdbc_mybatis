package vip.dengwj.account;

public class Account {
    private String cardNo;
    private String password;
    private String name;
    private Double balance;

    public Account() {
    }

    public Account(String cardNo, String password, String name, Double balance) {
        this.cardNo = cardNo;
        this.password = password;
        this.name = name;
        this.balance = balance;
    }

    /**
     * 获取
     * @return cardNo
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * 设置
     * @param cardNo
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return balance
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * 设置
     * @param balance
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String toString() {
        return "Account{cardNo = " + cardNo + ", password = " + password + ", name = " + name + ", balance = " + balance + "}";
    }
}
