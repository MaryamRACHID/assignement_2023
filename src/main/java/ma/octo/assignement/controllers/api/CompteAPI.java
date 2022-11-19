package ma.octo.assignement.controllers.api;

import ma.octo.assignement.dto.CompteDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static ma.octo.assignement.utils.Constants.APP_ROOT;

public interface CompteAPI {
    @PostMapping(value = APP_ROOT + "/compte/creer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CompteDto save(CompteDto compteDto);

    @GetMapping(value = APP_ROOT + "/compte/ListeDesCompte", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CompteDto> loadAllCompte();

    @GetMapping(value = APP_ROOT + "/compte/{nrCompte}", produces = MediaType.APPLICATION_JSON_VALUE)
    CompteDto findByNrCompte(String nrCompte);

    @GetMapping(value = APP_ROOT + "/compte/{rib}", produces = MediaType.APPLICATION_JSON_VALUE)
    CompteDto findByRib(String rib);

    @GetMapping(value = APP_ROOT + "/compte/{idCompte}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CompteDto> findComptesByUtilisateurId(Long idCompte);

    void delete(Long id);

}
