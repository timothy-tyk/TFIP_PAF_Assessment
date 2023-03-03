package pafassessment.pafassessment.repository;

public class Queries {
  public static final String getAllAccountsSQL="SELECT * FROM accounts";
  public static final String getAccountInfoSQL="SELECT * FROM accounts WHERE account_id=?";
  public static final String transferFrom="UPDATE accounts SET balance = ? WHERE account_id=?";
  public static final String transferTo="UPDATE accounts SET balance=? WHERE account_id=?";
}
