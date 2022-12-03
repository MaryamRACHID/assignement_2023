/*
 * @author Maryam RACHID
 * Software Engineer from the National School Of Applied Science
 */

package ma.octo.assignement.validators;

import ma.octo.assignement.utils.Constants;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TransferValidator {

    public static List<String> errors = new ArrayList<>();
    public static BigDecimal totalMontantTransfer = new BigDecimal(0);



    public static List<String> Validate(TransferDto transferDto) throws TransactionException, SoldeDisponibleInsuffisantException {


        if(transferDto == null){
            errors.add("Ce transfert n'est pas reconnu");
            return errors;
        }

        if (!StringUtils.hasLength(transferDto.getNrCompteEmetteur())) {
            errors.add("Veuillez renseigner le Numéro de compte");
        }

        if (!StringUtils.hasLength(transferDto.getNrCompteBeneficiaire())) {
            errors.add("Veuillez renseigner le Numéro de compte ");
        }

        if (transferDto.getMontantTransfer() == null) {
            errors.add("Veuillez renseigner le montant ");
            throw new TransactionException("Veuillez renseigner le montant à transferer");

        } else { validateMontantValue(transferDto.getMontantTransfer());}

        if (transferDto.getDateExecution() == null) {
            errors.add("Veuillez renseigner la date");
        }

        if (!StringUtils.hasLength(transferDto.getMotifTransfer())) {
            errors.add("Veuillez renseigner le motif de transfer");
        }


        return errors;
    }

    public static void validateMontantValue(BigDecimal montant) throws TransactionException {

        if(montant.compareTo(Constants.MONTANT_TRANSFER_MINIMAL) == -1){
            errors.add("Montant minimal de transfer non atteint");
            throw new TransactionException("Montant minimal de transfer non atteint");
        }
        if (montant.compareTo(Constants.MONTANT_TRANSFER_MAXIMAL) == 1){
            errors.add("Montant maximal de transfer dépassé");
            throw new TransactionException("Montant maximal de transfer dépassé");}
    }

    public static Boolean validateSoldeValue(BigDecimal solde, BigDecimal montant) throws SoldeDisponibleInsuffisantException {
        if(montant.compareTo(solde) == 1){
            errors.add("Votre solde est insuffisant");
            throw new SoldeDisponibleInsuffisantException("Votre solde est insuffisant");
        }
        return true;
    }


    public static Boolean validateTransferPerJour(List<TransferDto> allTransferDate) throws TransactionException {

        if(allTransferDate.isEmpty()){
            errors.add("Aucun transfer!");
            return false;
        }

        for( TransferDto elm : allTransferDate) {
            totalMontantTransfer = totalMontantTransfer.add(elm.getMontantTransfer());
            System.out.println(totalMontantTransfer);
            System.out.println(elm.getMontantTransfer());
        }

        if(totalMontantTransfer.compareTo(Constants.MONTANT_TRANSFER_DAY_MAXIMAL) == 1){
            errors.add("Vous avez depasser le maximum que vous pouvez transferer par jour");
            throw new TransactionException("Vous avez depasser le maximum que vous pouvez transferer par jour");
        }
        return true;
    }


}
