package ma.octo.assignement.controllers;

import lombok.extern.slf4j.Slf4j;
import ma.octo.assignement.controllers.api.MoneyDepositAPI;
import ma.octo.assignement.dto.MoneyDepositDto;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.services.MoneyDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class MoneyDepositController implements MoneyDepositAPI {


    private MoneyDepositService moneyDepositService;

    @Autowired
    public MoneyDepositController(MoneyDepositService moneyDepositService){
        this.moneyDepositService = moneyDepositService;
    }

    @Override
    public MoneyDepositDto findById(Long id) { return moneyDepositService.findById(id); }

    @Override
    public MoneyDepositDto findBynomEmetteur(String nomEmetteur) {
        return moneyDepositService.findBynomEmetteur(nomEmetteur);
    }

    @Override
    public MoneyDepositDto findByNrCompteBeneficiaire(String nrCompte) {
        return moneyDepositService.findByNrCompteBeneficiaire(nrCompte);
    }

    @Override
    public List<MoneyDepositDto> loadAllTransfer() {
        return moneyDepositService.loadAllMoneyDeposit();
    }

    @Override
    public void delete(Long id) { moneyDepositService.delete(id); }

    @Override
    public void createTransaction(MoneyDepositDto moneyDepositDto) throws TransactionException {
        moneyDepositService.createTransaction(moneyDepositDto);
    }
}
