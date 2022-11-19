package ma.octo.assignement.dto;

import lombok.Builder;
import lombok.Data;
import ma.octo.assignement.models.Transfer;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class TransferDto {

  private CompteDto compteEmetteur;

  private String nrCompteEmetteur;

  private CompteDto compteBeneficiaire;

  private String nrCompteBeneficiaire;

  private BigDecimal montantTransfer;

  private Date dateExecution;

  private String motifTransfer;

  public static TransferDto fromEntity(Transfer transfer){

    if(transfer == null){
      return null;
    }
    return TransferDto.builder()
            .compteEmetteur(CompteDto.fromEntity(transfer.getCompteEmetteur()))
            .nrCompteEmetteur(CompteDto.fromEntity(transfer.getCompteEmetteur()).getNrCompte())
            .compteBeneficiaire(CompteDto.fromEntity(transfer.getCompteBeneficiaire()))
            .nrCompteBeneficiaire(CompteDto.fromEntity(transfer.getCompteBeneficiaire()).getNrCompte())
            .montantTransfer(transfer.getMontantTransfer())
            .dateExecution(transfer.getDateExecution())
            .motifTransfer(transfer.getMotifTransfer())
            .build();

  }

  public static Transfer toEntity(TransferDto transferDto){

    if(transferDto == null){
      return null;
    }
    Transfer transfer = new Transfer();
    transfer.setCompteEmetteur(CompteDto.toEntity(transferDto.getCompteEmetteur()));
    transfer.setCompteBeneficiaire(CompteDto.toEntity(transferDto.getCompteBeneficiaire()));
    transfer.setMontantTransfer(transferDto.getMontantTransfer());
    transfer.setDateExecution(transferDto.getDateExecution());
    transfer.setMotifTransfer(transferDto.getMotifTransfer());

    return transfer;

  }


}
