package ma.octo.assignement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import ma.octo.assignement.models.Transfer;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class TransferDto {


  private String nrCompteEmetteur;

  private String nrCompteBeneficiaire;

  private BigDecimal montantTransfer;

  private Date dateExecution;

  private String motifTransfer;

  public static TransferDto fromEntity(Transfer transfer){

    if(transfer == null){
      return null;
    }
    return TransferDto.builder()

            .nrCompteEmetteur(CompteDto.fromEntity(transfer.getCompteEmetteur()).getNrCompte())

            .nrCompteBeneficiaire(CompteDto.fromEntity(transfer.getCompteBeneficiaire()).getNrCompte())
            .montantTransfer(transfer.getMontantTransfer())
            .dateExecution(transfer.getDateExecution())
            .motifTransfer(transfer.getMotifTransfer())
            .build();

  }



}
