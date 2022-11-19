package ma.octo.assignement.services;

import ma.octo.assignement.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    List<UtilisateurDto> loadAllUtilisateur();

    UtilisateurDto save(UtilisateurDto utilisateurDto);

    UtilisateurDto findById(Long id);

    void delete(Long id);

}
