package ma.octo.assignement.controllers;

import ma.octo.assignement.dto.UtilisateurDto;
import ma.octo.assignement.services.CompteService;
import ma.octo.assignement.services.UtilisateurService;
import ma.octo.assignement.controllers.api.UtilisateurAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtilisateurController implements UtilisateurAPI {

    UtilisateurService utilisateurService;
    CompteService compteService;

    @Autowired
    UtilisateurController(UtilisateurService utilisateurService, CompteService compteService){
        this.utilisateurService = utilisateurService;
        this.compteService = compteService;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        return utilisateurService.save(utilisateurDto);
    }

    @Override
    public UtilisateurDto findById(Long id) {
        return utilisateurService.findById(id);
    }

    @Override
    public List<UtilisateurDto> loadAllUtilisateur() {
        return utilisateurService.loadAllUtilisateur();
    }


    @Override
    public void delete(Long id) {
        utilisateurService.delete(id);
    }
}
