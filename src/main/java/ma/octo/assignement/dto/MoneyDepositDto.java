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

  private CompteDto compteBeneficiaire;

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
            .compteBeneficiaire(CompteDto.fromEntity(moneyDeposit.getCompteBeneficiaire()))
            .nrCompteBeneficiaire(CompteDto.fromEntity(moneyDeposit.getCompteBeneficiaire()).getNrCompte())
            .dateExecution(moneyDeposit.getDateExecution())
            .motifDeposit(moneyDeposit.getMotifDeposit())
            .build();

  }

  public static MoneyDeposit toEntity(MoneyDepositDto moneyDepositDto){

    if(moneyDepositDto == null){
      return null;
    }
    MoneyDeposit moneyDeposit = new MoneyDeposit();
    moneyDeposit.setNomEmetteur(moneyDepositDto.getNomEmetteur());
    moneyDeposit.setMontant(moneyDepositDto.getMontant());
    moneyDeposit.setCompteBeneficiaire(CompteDto.toEntity(moneyDepositDto.getCompteBeneficiaire()));
    moneyDeposit.setDateExecution(moneyDeposit.getDateExecution());

    return moneyDeposit;

  }


}
