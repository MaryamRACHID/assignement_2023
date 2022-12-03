package ma.octo.assignement.dto;

import lombok.Builder;
import lombok.Data;
import ma.octo.assignement.models.MoneyDeposit;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class MoneyDepositDto {

  private String nomEmetteur;

  private BigDecimal montant;

  private String nrCompteBeneficiaire;

  private Date dateExecution;

  private String motifDeposit;


  public static MoneyDepositDto fromEntity(MoneyDeposit moneyDeposit){

    if(moneyDeposit == null){
      return null;
    }
    return MoneyDepositDto.builder()
            .nomEmetteur(moneyDeposit.getNomEmetteur())
            .montant(moneyDeposit.getMontant())
            .nrCompteBeneficiaire(CompteDto.fromEntity(moneyDeposit.getCompteBeneficiaire()).getNrCompte())
            .dateExecution(moneyDeposit.getDateExecution())
            .motifDeposit(moneyDeposit.getMotifDeposit())
            .build();
  }


}
