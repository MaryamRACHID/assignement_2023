package ma.octo.assignement.controllers.api;


import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static ma.octo.assignement.utils.Constants.APP_ROOT;

public interface TransferAPI {

    @GetMapping(value = APP_ROOT + "/transfer/{idTransfer}", produces = MediaType.APPLICATION_JSON_VALUE)
    TransferDto findById(@PathVariable("idTransfer") Long id);

    @GetMapping(value = APP_ROOT + "/transfer/Emetteur/{nrCompte}", produces = MediaType.APPLICATION_JSON_VALUE)
    TransferDto findByNrCompteEmetteur(@PathVariable("nrCompte") String nrCompte);

    @GetMapping(value = APP_ROOT + "/transfer/Beneficiaire/{nrCompte}", produces = MediaType.APPLICATION_JSON_VALUE)
    TransferDto findByNrCompteBeneficiaire(@PathVariable("nrCompte") String nrCompte);

    @GetMapping(value = APP_ROOT + "/transfer/listDesTransfer", produces = MediaType.APPLICATION_JSON_VALUE)
    List<TransferDto> loadAllTransfer();

    @DeleteMapping(value = APP_ROOT + "/transfer/supprimer/{idTransfer}")
    void delete(@PathVariable("idTransfer") Long id);

    @PostMapping(value = APP_ROOT + "/transfer/executerTransfers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    TransferDto createTransaction(@RequestBody TransferDto transferDto) throws TransactionException, SoldeDisponibleInsuffisantException, CompteNonExistantException;

}
