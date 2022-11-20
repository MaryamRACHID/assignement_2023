package ma.octo.assignement.services.serviceImpli;

import lombok.extern.slf4j.Slf4j;
import ma.octo.assignement.models.Utilisateur;
import ma.octo.assignement.dto.UtilisateurDto;
import ma.octo.assignement.exceptions.EntityNotFoundException;
import ma.octo.assignement.exceptions.ErrorCodes;
import ma.octo.assignement.exceptions.InvalidEntityException;
import ma.octo.assignement.repositories.CompteRepository;
import ma.octo.assignement.repositories.UtilisateurRepository;
import ma.octo.assignement.services.UtilisateurService;
import ma.octo.assignement.validators.UtilisateurValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
@Transactional
public class UtilisateurServiceImpli implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;
    private CompteRepository compteRepository;

    @Autowired
    public UtilisateurServiceImpli(UtilisateurRepository utilisateurRepository, CompteRepository compteRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.compteRepository = compteRepository;
    }


    @Override
    public List<UtilisateurDto> loadAllUtilisateur() {
        return utilisateurRepository.findAll().stream().map(UtilisateurDto::fromEntity).collect(Collectors.toList());
    }


    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {

        List<String> errors = UtilisateurValidator.validate(utilisateurDto);
        if(!errors.isEmpty()){
            log.error("L'utilisateur est null", utilisateurDto);
            throw new InvalidEntityException("L'utilisateur est null", ErrorCodes.UTILISATEUR_NOT_VALID);
        }

        return UtilisateurDto.fromEntity(utilisateurRepository.save(UtilisateurDto.toEntity(utilisateurDto)));

    }

    @Override
    public UtilisateurDto findById(Long id) {
        if( id == null){
            log.error("L'Id n'existe pas !");
            return null;
        }

        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        return Optional.of(UtilisateurDto.fromEntity(utilisateur.get())).orElseThrow(() -> new EntityNotFoundException
                ("L'utilisateur ayant l'ID : "+id+" n'existe pas !",ErrorCodes.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        if( id == null){
            log.error("L'ID n'existe pas !");
        }
        utilisateurRepository.deleteById(id);
    }


}
