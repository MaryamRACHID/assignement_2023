package ma.octo.assignement.controllers;

import ma.octo.assignement.dto.CompteDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.services.CompteService;
import ma.octo.assignement.controllers.api.CompteAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompteController implements CompteAPI {

    private CompteService compteService;

    @Autowired
    CompteController(CompteService compteService){ this.compteService = compteService;}


    @Override
    public CompteDto save(CompteDto compteDto) throws CompteNonExistantException { return compteService.save(compteDto); }

    @Override
    public List<CompteDto> loadAllCompte() {
        return compteService.loadAllCompte();
    }

    @Override
    public CompteDto findByNrCompte(String nrCompte) {
        return compteService.findByNrCompte(nrCompte);
    }

    @Override
    public CompteDto findByRib(String rib) {
        return compteService.findByRib(rib);
    }

    @Override
    public List<CompteDto> findComptesByUtilisateurId(Long idCompte) {
        return compteService.findComptesByUtilisateurId(idCompte);
    }

    @Override
    public void delete(Long id) { compteService.delete(id); }
}
