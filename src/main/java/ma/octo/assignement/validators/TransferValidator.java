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
import java.util.List;

public class TransferValidator {

    public static List<String> errors = new ArrayList<>();


    public static List<String> Validate(TransferDto transferDto) throws TransactionException, SoldeDisponibleInsuffisantException {


        if(transferDto == null){
            errors.add("Ce transfert n'est pas reconnu");
            return errors;
        }

        /*org.springframework.util.StringUtils
        if (transferDto.getCompteEmetteur() == null){
            errors.add("Erreur liée au Compte de l'émetteur, essayer à nouveau");
        }
 if (transferDto.getCompteBeneficiaire() == null){
            errors.add("Erreur liée au Compte de beneficiaire, essayer à nouveau");
        }

*/

        if (!StringUtils.hasLength(transferDto.getNrCompteEmetteur())) {
            errors.add("Veuillez renseigner le Numéro de compte");
        }

        if (!StringUtils.hasLength(transferDto.getNrCompteBeneficiaire())) {
            errors.add("Veuillez renseigner le Numéro de compte ");
        }

        if (transferDto.getMontantTransfer() == null) {
            errors.add("Veuillez renseigner le montant ");
            throw new TransactionException("Veuillez renseigner le montant à transferer");

        } else{ validateMontantValue(transferDto.getMontantTransfer());}

        if (transferDto.getDateExecution() == null) {
            errors.add("Veuillez renseigner la date");
        }

        if (!StringUtils.hasLength(transferDto.getMotifTransfer())) {
            errors.add("Veuillez renseigner le motif de transfer");
        }

        /*if(transferDto.getCompteEmetteur() != null && transferDto.getCompteBeneficiaire() != null) {
            validateSoldeValue(transferDto.getCompteEmetteur().getSolde(), transferDto.getMontantTransfer());
        }*/

        return errors;
    }

    public static void validateMontantValue(BigDecimal montant) throws TransactionException {

        if(montant.intValue() < Constants.MONTANT_TRANSFER_MINIMAL){
            errors.add("Montant minimal de transfer non atteint");
            throw new TransactionException("Montant minimal de transfer non atteint");
        }
        if (montant.intValue() >Constants.MONTANT_TRANSFER_MAXIMAL){
            errors.add("Montant maximal de transfer dépassé");
            throw new TransactionException("Montant maximal de transfer dépassé");}
    }

    public static Boolean validateSoldeValue(BigDecimal solde, BigDecimal montant) throws SoldeDisponibleInsuffisantException {
        if(montant.intValue() > solde.intValue()){
            errors.add("Votre solde est insuffisant");
            throw new SoldeDisponibleInsuffisantException("Votre solde est insuffisant");
        }
        return true;
    }


}
