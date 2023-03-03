package pafassessment.pafassessment.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pafassessment.pafassessment.model.Account;
import pafassessment.pafassessment.model.Transfer;

@Repository
public class AccountsRepository {
  @Autowired
  JdbcTemplate jdbcTemplate;

  public List<Account> getAllAccounts(){
    List<Account> accounts = jdbcTemplate.query(Queries.getAllAccountsSQL, BeanPropertyRowMapper.newInstance(Account.class));
    return accounts;
  }

  public Account getAccountInfo(String accountId){
    Account acc = jdbcTemplate.queryForObject(Queries.getAccountInfoSQL, BeanPropertyRowMapper.newInstance(Account.class), accountId);
    return acc;
  }

  public void transferBetweenAccounts(Transfer transfer){
    Account fromAccount = getAccountInfo(transfer.getFromId());
    Account toAccount = getAccountInfo(transfer.getToId());
    Float amount = transfer.getAmount();
    jdbcTemplate.update(Queries.transferFrom, fromAccount.getBalance()-amount, fromAccount.getAccountId() );
    jdbcTemplate.update(Queries.transferTo, toAccount
    .getBalance()+amount, toAccount.getAccountId());
  }
}
