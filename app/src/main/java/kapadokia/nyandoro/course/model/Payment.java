package kapadokia.nyandoro.course.model;

public class Payment {
    private String userCreds;
    private String total_payment;
    private String amount_paid;
    private String balance;

    public Payment() {
    }

    public Payment(String userCreds, String total_payment, String amount_paid, String balance) {
        this.userCreds = userCreds;
        this.total_payment = total_payment;
        this.amount_paid = amount_paid;
        this.balance = balance;
    }

    public String getUserCreds() {
        return userCreds;
    }

    public void setUserCreds(String userCreds) {
        this.userCreds = userCreds;
    }

    public String getTotal_payment() {
        return total_payment;
    }

    public void setTotal_payment(String total_payment) {
        this.total_payment = total_payment;
    }

    public String getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(String amount_paid) {
        this.amount_paid = amount_paid;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
