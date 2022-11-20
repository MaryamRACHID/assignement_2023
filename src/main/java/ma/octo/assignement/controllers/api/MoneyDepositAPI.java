package ma.octo.assignement.controllers.api;


import ma.octo.assignement.dto.MoneyDepositDto;
import ma.octo.assignement.exceptions.TransactionException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ma.octo.assignement.utils.Constants.APP_ROOT;

public interface MoneyDepositAPI {

    @GetMapping(value = APP_ROOT + "/deposit/{idDeposit}", produces = MediaType.APPLICATION_JSON_VALUE)
    MoneyDepositDto findById(@PathVariable("idDeposit") Long id);

    @GetMapping(value = APP_ROOT + "/deposit/Emetteur/{nomEmetteur}", produces = MediaType.APPLICATION_JSON_VALUE)
    MoneyDepositDto findBynomEmetteur(@PathVariable("nomEmetteur") String nomEmetteur);

    @GetMapping(value = APP_ROOT + "/deposit/Beneficiaire/{nrCompte}", produces = MediaType.APPLICATION_JSON_VALUE)
    MoneyDepositDto findByNrCompteBeneficiaire(@PathVariable("nrCompte") String nrCompte);

    @GetMapping(value = APP_ROOT + "/deposit/listDesDeposit", produces = MediaType.APPLICATION_JSON_VALUE)
    List<MoneyDepositDto> loadAllTransfer();

    @DeleteMapping(value = APP_ROOT + "/deposit/supprimer/{idDeposit}")
    void delete(@PathVariable("idDeposit") Long id);

    @PostMapping(value = APP_ROOT + "/deposit/executerDeposit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    void createTransaction(@RequestBody MoneyDepositDto transferDto) throws TransactionException;


}
