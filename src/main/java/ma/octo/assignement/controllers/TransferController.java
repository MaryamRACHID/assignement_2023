package ma.octo.assignement.controllers;

import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.services.TransferService;
import ma.octo.assignement.controllers.api.TransferAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TransferController implements TransferAPI {

    private TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    public TransferDto findById(Long id){ return  transferService.findById(id); }

    public TransferDto findByNrCompteEmetteur(String nrCompte){ return  transferService.findByNrCompteEmetteur(nrCompte); }

    public TransferDto findByNrCompteBeneficiaire(String nrCompte){ return  transferService.findByNrCompteBeneficiaire(nrCompte); }

    public List<TransferDto> loadAllTransfer(){ return transferService.loadAllTransfer(); }

    public void delete(Long id){ transferService.delete(id); }

    public void createTransaction(TransferDto transferDto) throws TransactionException, SoldeDisponibleInsuffisantException, CompteNonExistantException {
        transferService.createTransaction(transferDto);
    }
}
