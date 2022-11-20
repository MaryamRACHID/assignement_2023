package ma.octo.assignement.validators;

import ma.octo.assignement.utils.Constants;
import ma.octo.assignement.dto.MoneyDepositDto;
import ma.octo.assignement.exceptions.TransactionException;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MoneyDepositValidator {

    public static List<String> errors = new ArrayList<>();

    public static List<String> Validate(MoneyDepositDto moneyDepositDto) throws TransactionException {


        if(moneyDepositDto == null){
            errors.add("Ce Deposit n'est pas reconnu");
            return errors;
        }

        if (!StringUtils.hasLength(moneyDepositDto.getNomEmetteur())) {
            errors.add("Veuillez renseigner le Nom de l'emetteur");
        }


        if (!StringUtils.hasLength(moneyDepositDto.getNrCompteBeneficiaire())) {
            errors.add("Veuillez renseigner le Numéro de compte");
        }

        if (moneyDepositDto.getMontant() == null) {
            errors.add("Veuillez renseigner le montant");
            throw new TransactionException("Veuillez renseigner le montant à deposer");

        } else{ validateMontantValue(moneyDepositDto.getMontant());}

        if (moneyDepositDto.getDateExecution() == null) {
            errors.add("Veuillez renseigner la date");
        }

        if (!StringUtils.hasLength(moneyDepositDto.getMotifDeposit())) {
            errors.add("Veuillez renseigner le motif de deposit");
        }

        return errors;
    }


    public static void validateMontantValue(BigDecimal montant) throws TransactionException {

        if(montant.intValue() < Constants.MONTANT_DEPOSIT_MINIMAL){
            errors.add("Montant minimal non atteint");
            throw new TransactionException("Montant minimal non atteint");
        }
        if (montant.intValue() >Constants.MONTANT_DEPOSIT_MAXIMAL){
            errors.add("Montant maximal dépassé");
            throw new TransactionException("Montant maximal dépassé");}
    }

}
