package pafassessment.pafassessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import pafassessment.pafassessment.model.Account;
import pafassessment.pafassessment.model.CheckResult;
import pafassessment.pafassessment.model.Transfer;
import pafassessment.pafassessment.service.FundsTransferService;
import pafassessment.pafassessment.service.LogAuditService;

@Controller
@RequestMapping(path="/")
public class FundsTransferController {
  @Autowired
  FundsTransferService ftSvc;

  @Autowired
  LogAuditService logSvc;

  @GetMapping()
  public String showInputPage(Model model){
    List<Account> accounts = ftSvc.getAllAccounts();
    model.addAttribute("accounts", accounts);
    model.addAttribute("transfer", new Transfer());
    return "landing";
  }

  @PostMapping(path="/transfer", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String postTransfer(@Valid Transfer transfer, BindingResult bindResult, Model model){
    CheckResult res = ftSvc.transferBetweenAccounts(transfer);
    if(bindResult.hasErrors() || res.getErrors().size()>0){
      List<Account> accounts = ftSvc.getAllAccounts();
      model.addAttribute("txErrors", res.getErrors());
      model.addAttribute("accounts", accounts);
      return "landing";
    }
    logSvc.logToRedis(transfer);
    Account fromAcc = ftSvc.getAccountInfo(transfer.getFromId());
    Account toAcc = ftSvc.getAccountInfo(transfer.getToId());
    model.addAttribute("transfer", transfer);
    model.addAttribute("fromName", fromAcc.getName());
    model.addAttribute("toName", toAcc.getName());
    return "transferdetails";
  }
}
