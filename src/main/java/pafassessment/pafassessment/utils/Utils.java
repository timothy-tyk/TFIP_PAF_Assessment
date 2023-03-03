package pafassessment.pafassessment.utils;

import java.util.ArrayList;
import java.util.List;

import pafassessment.pafassessment.model.Account;
import pafassessment.pafassessment.model.CheckResult;
import pafassessment.pafassessment.model.Transfer;

public class Utils {

  public static CheckResult TransactionCheck(Transfer trf, List<String> accountIds, Account fromAccount){
    CheckResult res = new CheckResult();
    List<String> errors = new ArrayList<String>();
    Boolean condition0 = accountIds.contains(trf.getFromId()) && accountIds.contains(trf.getToId());
    Boolean condition1 = trf.getFromId().length()==10 && trf.getToId().length()==10;
    Boolean condition2 = !trf.getFromId().equals(trf.getToId());
    Boolean condition3and4 = trf.getAmount()!=null && trf.getAmount()>=10;
    Boolean condition5 = trf.getAmount()!=null && fromAccount.getBalance()>=trf.getAmount();
    if(!condition0) errors.add("Accounts have to exist in database!");
    if(!condition1) errors.add("Account Ids have to be 10 characters!");
    if(!condition2) errors.add("From and To accounts cannot be the same!");
    if(!condition3and4) errors.add("Minimum Transfer Amount is $10");
    if(!condition5) errors.add("Insufficient funds!");

    res.setRes(condition0 && condition1 && condition2 && condition3and4 &&condition5);
    res.setErrors(errors);

    return res;
    
  }
}
