package ma.octo.assignement.services;

import ma.octo.assignement.dto.CompteDto;
import ma.octo.assignement.dto.UtilisateurDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.models.Utilisateur;

import java.util.List;

public interface CompteService {

    CompteDto save(CompteDto compteDto) throws CompteNonExistantException;
    List<CompteDto> loadAllCompte();
    CompteDto findByNrCompte(String nrCompte);
    CompteDto findByRib(String rib);
    List<CompteDto> findComptesByUtilisateurId(Long utilisateurId);
    void delete(Long id);


}
