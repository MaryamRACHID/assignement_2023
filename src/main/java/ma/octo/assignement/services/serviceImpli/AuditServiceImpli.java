package ma.octo.assignement.services.serviceImpli;

import lombok.extern.slf4j.Slf4j;
import ma.octo.assignement.models.Audit;
import ma.octo.assignement.utils.EventType;
import ma.octo.assignement.dto.MoneyDepositDto;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.repositories.AuditRepository;
import ma.octo.assignement.services.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class AuditServiceImpli implements AuditService {

    private AuditRepository auditRepository;

    @Autowired
    public AuditServiceImpli(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    public void audit(Object event, EventType eventType) {

        log.info("Audit de l'événement :", eventType);
        Audit audit = new Audit();
        audit.setEventType(eventType);

        if (eventType == EventType.TRANSFER){
            audit.setMessage(auditTransfer((TransferDto) event));
        }

        if ( eventType == EventType.DEPOSIT){
            audit.setMessage(auditMoneyDeposit((MoneyDepositDto) event));
        }

        auditRepository.save(audit);

    }

    public String auditTransfer(TransferDto transferDto){

        String message = "Audit de l'événement : Transfert"+
                " | Montant :"+transferDto.getMontantTransfer()+" DH | Beneficiaire : "+
                //transferDto.getCompteBeneficiaire().getUtilisateurDto().getFirstName()+" "
                //+transferDto.getCompteBeneficiaire().getUtilisateurDto().getLastName()+
                " | Motif :"+transferDto.getMotifTransfer();

        return message;

    }

    public String auditMoneyDeposit(MoneyDepositDto moneyDepositDto){

        String message = "Audit de l'événement : Deposit"+
                "Emetteur :"+moneyDepositDto.getNomEmetteur()+" "+
                " | Montant :"+moneyDepositDto.getMontant()+" DH | Beneficiaire : "+
                //moneyDepositDto.getCompteBeneficiaire().getUtilisateurDto().getFirstName()+" "
                //+moneyDepositDto.getCompteBeneficiaire().getUtilisateurDto().getLastName()+
                " | Motif :"+moneyDepositDto.getMotifDeposit();

        return message;

    }


}
