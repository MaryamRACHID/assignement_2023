package ma.octo.assignement.services.serviceImpli;

import lombok.extern.slf4j.Slf4j;
import ma.octo.assignement.models.Compte;
import ma.octo.assignement.models.MoneyDeposit;
import ma.octo.assignement.repositories.CompteRepository;
import ma.octo.assignement.utils.EventType;
import ma.octo.assignement.dto.MoneyDepositDto;
import ma.octo.assignement.exceptions.EntityNotFoundException;
import ma.octo.assignement.exceptions.ErrorCodes;
import ma.octo.assignement.exceptions.InvalidEntityException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repositories.MoneyDepositRepository;
import ma.octo.assignement.repositories.UtilisateurRepository;
import ma.octo.assignement.services.AuditService;
import ma.octo.assignement.services.MoneyDepositService;
import ma.octo.assignement.validators.MoneyDepositValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MoneyDepositServiceImpli implements MoneyDepositService {

    private MoneyDepositRepository moneyDepositRepository;
    private CompteRepository compteRepository;
    private UtilisateurRepository utilisateurRepository;

    private AuditService auditService;

    @Autowired
    public MoneyDepositServiceImpli(MoneyDepositRepository moneyDepositRepository, CompteRepository compteRepository,
                                    UtilisateurRepository utilisateurRepository, AuditService auditService) {
        this.moneyDepositRepository = moneyDepositRepository;
        this.compteRepository = compteRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.auditService = auditService;
    }

    @Override
    public MoneyDepositDto findById(Long id) {
        if( id == null){
            log.error("L'ID est null");
            return null;
        }
        Optional<MoneyDeposit> moneyDeposit = moneyDepositRepository.findById(id);

        return Optional.of(MoneyDepositDto.fromEntity(moneyDeposit.get())).orElseThrow(() -> new EntityNotFoundException
                ("Aucun deposit avec l'ID"+id+"n'a été trouvé", ErrorCodes.TRANSFER_NOT_FOUND));
    }

    @Override
    public MoneyDepositDto findBynomEmetteur(String nomEmetteur) {
        if(nomEmetteur == null){
            log.error("Nom emetteur est null");
            return null;
        }
        Optional<MoneyDeposit> moneyDeposit = moneyDepositRepository.findByNomEmetteur(nomEmetteur);

        return Optional.of(MoneyDepositDto.fromEntity(moneyDeposit.get())).orElseThrow(() -> new EntityNotFoundException
                ("Aucun Deposit de la part de l'emetteur: "+nomEmetteur+" n'a été trouvé", ErrorCodes.DEPOSIT_NOT_FOUND));

    }

    @Override
    public MoneyDepositDto findByNrCompteBeneficiaire(String nrCompte) {
        if(nrCompte == null){
            log.error("Le numero Compte est null");
            return null;
        }

        Compte compte = compteRepository.findByNrCompte(nrCompte);
        Optional<MoneyDeposit> moneyDeposit = moneyDepositRepository.findByCompteBeneficiaire(compte);

        return Optional.of(MoneyDepositDto.fromEntity(moneyDeposit.get())).orElseThrow(() -> new EntityNotFoundException
                ("Aucun Deposit avec le Nr compte de beneficiaire : "+nrCompte+" n'a été trouvé", ErrorCodes.DEPOSIT_NOT_FOUND));

    }


    @Override
    public List<MoneyDepositDto> loadAllMoneyDeposit() {
        return moneyDepositRepository.findAll().stream().map(MoneyDepositDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if( id == null){
            log.error("L'ID est null");
            return; }

        moneyDepositRepository.deleteById(id);

    }

    @Override
    public MoneyDepositDto createTransaction(MoneyDepositDto moneyDepositDto) throws TransactionException {

        Compte compteBeneficiaire = compteRepository.findByNrCompte(moneyDepositDto.getNrCompteBeneficiaire());

        List<String> errors = MoneyDepositValidator.Validate(moneyDepositDto);
        if(!errors.isEmpty()){
            log.error("Deposit n'est pas valide", moneyDepositDto);
            throw new InvalidEntityException("Deposit n'est pas valide", ErrorCodes.DEPOSIT_NOT_VALID, errors);
        }

        compteBeneficiaire.setSolde(compteBeneficiaire.getSolde().add(moneyDepositDto.getMontant()));

        compteRepository.save(compteBeneficiaire);
        auditService.audit(moneyDepositDto, EventType.DEPOSIT);
        save(moneyDepositDto);

        return moneyDepositDto;
    }

    @Override
    public MoneyDepositDto save(MoneyDepositDto moneyDepositDto) throws TransactionException {
        return MoneyDepositDto.fromEntity(moneyDepositRepository.save(MoneyDepositDto.toEntity(moneyDepositDto)));
    }


}
