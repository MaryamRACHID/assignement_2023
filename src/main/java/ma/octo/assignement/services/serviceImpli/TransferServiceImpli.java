package ma.octo.assignement.services.serviceImpli;

import lombok.extern.slf4j.Slf4j;
import ma.octo.assignement.models.Compte;
import ma.octo.assignement.models.Transfer;
import ma.octo.assignement.utils.EventType;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.*;
import ma.octo.assignement.repositories.CompteRepository;
import ma.octo.assignement.repositories.TransferRepository;
import ma.octo.assignement.services.AuditService;
import ma.octo.assignement.services.TransferService;
import ma.octo.assignement.validators.TransferValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransferServiceImpli implements TransferService {

    private TransferRepository transferRepository;
    private CompteRepository compteRepository;

    private AuditService auditService;

    @Autowired
    public TransferServiceImpli(TransferRepository transferRepository, CompteRepository compteRepository,
                                AuditService auditService) {
        this.transferRepository = transferRepository;
        this.compteRepository = compteRepository;
        this.auditService = auditService;
    }

    @Override
    public TransferDto findById(Long id) {
        if( id == null){
            log.error("L'ID est null");
            return null;
        }
        Optional<Transfer> transfer = transferRepository.findById(id);

        return Optional.of(TransferDto.fromEntity(transfer.get())).orElseThrow(() -> new EntityNotFoundException
                ("Aucun Transfer avec l'ID"+id+"n'a été trouvé", ErrorCodes.DEPOSIT_NOT_FOUND));
    }

    @Override
    public TransferDto findByNrCompteEmetteur(String nrCompte) {
        if(nrCompte == null){
            log.error("Le numero Compte est null");
            return null;
        }

        Compte compte = compteRepository.findByNrCompte(nrCompte);
        Optional<Transfer> transfer = transferRepository.findByCompteEmetteur(compte);

        return Optional.of(TransferDto.fromEntity(transfer.get())).orElseThrow(() -> new EntityNotFoundException
                ("Aucun Transfer avec le Nr compte d'Emmuteur : "+nrCompte+" n'a été trouvé", ErrorCodes.TRANSFER_NOT_FOUND));
    }

    @Override
    public TransferDto findByNrCompteBeneficiaire(String nrCompte) {
        if(nrCompte == null){
            log.error("Le numero Compte est null");
            return null;
        }

        Compte compte = compteRepository.findByNrCompte(nrCompte);
        Optional<Transfer> transfer = transferRepository.findByCompteBeneficiaire(compte);

        return Optional.of(TransferDto.fromEntity(transfer.get())).orElseThrow(() -> new EntityNotFoundException
                ("Aucun Transfer avec le Nr compte du Beneficiaire: "+nrCompte+" n'a été trouvé", ErrorCodes.TRANSFER_NOT_FOUND));
    }

    @Override
    public List<TransferDto> loadAllTransfer() {
        return transferRepository.findAll().stream().map(TransferDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if( id == null){
        log.error("L'ID est null");
        return; }

        transferRepository.deleteById(id);
    }

    @Override
    public TransferDto createTransaction(TransferDto transferDto) throws TransactionException, SoldeDisponibleInsuffisantException {

        Compte compteEmetteur = compteRepository.findByNrCompte(transferDto.getNrCompteEmetteur());
        Compte compteBeneficiaire = compteRepository.findByNrCompte(transferDto.getNrCompteBeneficiaire());

        List<String> errors = TransferValidator.Validate(transferDto);
        if(!errors.isEmpty()){
            log.error("Transfer n'est pas valide", transferDto);
            throw new InvalidEntityException("Transfer n'est pas valide", ErrorCodes.TRANSFER_NOT_VALID, errors);
        }

        compteEmetteur.setSolde(compteEmetteur.getSolde().subtract(transferDto.getMontantTransfer()));
        compteBeneficiaire.setSolde(compteBeneficiaire.getSolde().add(transferDto.getMontantTransfer()));

        compteRepository.save(compteEmetteur);
        compteRepository.save(compteBeneficiaire);
        auditService.audit(transferDto, EventType.TRANSFER);
        save(transferDto);




        return transferDto;

    }

    @Override
    public TransferDto save(TransferDto transferDto) throws TransactionException {
        return TransferDto.fromEntity(transferRepository.save(TransferDto.toEntity(transferDto)));
    }


}
