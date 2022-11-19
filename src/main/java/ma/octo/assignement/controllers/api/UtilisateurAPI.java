package ma.octo.assignement.controllers.api;

import ma.octo.assignement.dto.UtilisateurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ma.octo.assignement.utils.Constants.APP_ROOT;

public interface UtilisateurAPI {

    @PostMapping(value = APP_ROOT + "/utilisateur/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto save(@RequestBody UtilisateurDto utilisateurDto);

    @GetMapping(value = APP_ROOT + "/utilisateur/{idutilisateur}", produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findById(@PathVariable("idutilisateur") Long id);

    @GetMapping(value = APP_ROOT + "/utilisateur/ListeDesUtilisateur", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UtilisateurDto> loadAllUtilisateur();

    @DeleteMapping(value = APP_ROOT + "/utilisateur/delete/{idutilisateur}")
    void delete(@PathVariable("idutilisateur") Long id);


}
