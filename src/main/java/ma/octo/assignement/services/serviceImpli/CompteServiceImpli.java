package ma.octo.assignement.services.serviceImpli;

import lombok.extern.slf4j.Slf4j;
import ma.octo.assignement.dto.CompteDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.ErrorCodes;
import ma.octo.assignement.exceptions.InvalidEntityException;
import ma.octo.assignement.repositories.CompteRepository;
import ma.octo.assignement.services.CompteService;
import ma.octo.assignement.validators.CompteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CompteServiceImpli implements CompteService {

    CompteRepository compteRepository;

    @Autowired
    CompteServiceImpli(CompteRepository compteRepository){
        this.compteRepository = compteRepository;
    }

    @Override
    public CompteDto save(CompteDto compteDto) throws CompteNonExistantException {
        List<String> errors = CompteValidator.validate(compteDto);
        if(!errors.isEmpty()){
            log.error("Le compte est null");
            throw new InvalidEntityException("Le compte est null", ErrorCodes.UTILISATEUR_NOT_VALID);
        }
        return CompteDto.fromEntity(compteRepository.save(CompteDto.toEntity(compteDto)));

    }

    @Override
    public List<CompteDto> loadAllCompte() {
        return compteRepository.findAll().stream().map(CompteDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public CompteDto findByNrCompte(String nrCompte) {
        return CompteDto.fromEntity(compteRepository.findByNrCompte(nrCompte));
    }

    @Override
    public CompteDto findByRib(String rib) {
        return CompteDto.fromEntity(compteRepository.findByRib(rib));
    }

    @Override
    public List<CompteDto> findComptesByUtilisateurId(Long utilisateurId) {
        return compteRepository.findComptesByUtilisateurId(utilisateurId).stream()
                .map(CompteDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {

    }
}
