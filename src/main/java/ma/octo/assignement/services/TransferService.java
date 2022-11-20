package ma.octo.assignement.services;

import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;

import java.util.List;

public interface TransferService {

    TransferDto save(TransferDto transferDto) throws TransactionException;

    TransferDto findById(Long id);

    TransferDto findByNrCompteEmetteur(String nrCompte);

    TransferDto findByNrCompteBeneficiaire(String nrCompte);

    List<TransferDto> loadAllTransfer();

    void delete(Long id);

    TransferDto createTransaction(TransferDto transferDto) throws TransactionException, SoldeDisponibleInsuffisantException, CompteNonExistantException;

}
