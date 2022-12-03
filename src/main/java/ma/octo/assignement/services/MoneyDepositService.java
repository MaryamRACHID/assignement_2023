package ma.octo.assignement.services;

import ma.octo.assignement.dto.MoneyDepositDto;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.models.Compte;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface MoneyDepositService {

//    MoneyDepositDto save(MoneyDepositDto moneyDepositDto) throws TransactionException;

    MoneyDepositDto findById(Long id);

    MoneyDepositDto findBynomEmetteur(String nomEmetteur);

    MoneyDepositDto findByNrCompteBeneficiaire(String nrCompte);

    List<MoneyDepositDto> loadAllMoneyDeposit();

    void delete(Long id);

    MoneyDepositDto createTransaction(MoneyDepositDto moneyDepositDto) throws TransactionException;

    void save(String emetteurName, Compte compteBeneficiaire, BigDecimal montantTransfer, String motifTransfer, Date date) throws TransactionException;
}
