package pafassessment.pafassessment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pafassessment.pafassessment.exception.FundsTransferException;
import pafassessment.pafassessment.model.Account;
import pafassessment.pafassessment.model.CheckResult;
import pafassessment.pafassessment.model.Transfer;
import pafassessment.pafassessment.repository.AccountsRepository;
import pafassessment.pafassessment.utils.Utils;

@Service
public class FundsTransferService {
  @Autowired
  AccountsRepository accRepo;

  public List<Account> getAllAccounts(){
    return accRepo.getAllAccounts();
  }

  public Account getAccountInfo(String accountId){
    return accRepo.getAccountInfo(accountId);
  }
  

  @Transactional(rollbackFor=FundsTransferException.class)
  public CheckResult transferBetweenAccounts(Transfer trf){
    List<String> accountIds = accRepo.getAllAccounts().stream()
                                    .map(v -> v.getAccountId())
                                    .toList();
    Account fromAccount = getAccountInfo(trf.getFromId());
    CheckResult res = Utils.TransactionCheck(trf, accountIds, fromAccount);
    if(res.getRes()){
      accRepo.transferBetweenAccounts(trf);
    }
    return res;
  }
}
