package ma.octo.assignement.controllers.api;

import ma.octo.assignement.dto.CompteDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static ma.octo.assignement.utils.Constants.APP_ROOT;

public interface CompteAPI {
    @PostMapping(value = APP_ROOT + "/compte/creer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CompteDto save(@RequestBody CompteDto compteDto) throws CompteNonExistantException;

    @GetMapping(value = APP_ROOT + "/compte/ListeDesCompte", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CompteDto> loadAllCompte();

    @GetMapping(value = APP_ROOT + "/compte/numCompte/{nrCompte}", produces = MediaType.APPLICATION_JSON_VALUE)
    CompteDto findByNrCompte(@PathVariable("nrCompte") String nrCompte);

    @GetMapping(value = APP_ROOT + "/compte/ribCompte/{rib}", produces = MediaType.APPLICATION_JSON_VALUE)
    CompteDto findByRib(@PathVariable("rip") String rib);

    @GetMapping(value = APP_ROOT + "/compte/id/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CompteDto> findComptesByUtilisateurId(@PathVariable("idUser")Long idUser);

    @GetMapping(value = APP_ROOT + "/compte/supprimer/{idCompte}", produces = MediaType.APPLICATION_JSON_VALUE)
    void delete(@PathVariable("idCompte") Long id);

}
