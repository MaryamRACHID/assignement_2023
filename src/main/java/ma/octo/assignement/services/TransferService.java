package ma.octo.assignement.services;

import ma.octo.assignement.dto.CompteDto;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.models.Compte;
import ma.octo.assignement.models.Transfer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransferService {

    TransferDto findById(Long id);

    TransferDto findByNrCompteEmetteur(String nrCompte);

    TransferDto findByNrCompteBeneficiaire(String nrCompte);

    List<TransferDto> loadAllTransfer();

    void delete(Long id);

    List<TransferDto> findBydateExecutionAndCompteEmetteur(Date date, String NrCompteEmetteur);


    TransferDto createTransaction(TransferDto transferDto) throws TransactionException, SoldeDisponibleInsuffisantException, CompteNonExistantException;

   void save(Compte compteEmetteur, Compte compteBeneficiaire, BigDecimal montantTransfer, String motifTransfer, Date date) throws TransactionException;
}
