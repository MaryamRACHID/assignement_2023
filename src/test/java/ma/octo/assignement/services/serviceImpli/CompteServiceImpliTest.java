package ma.octo.assignement.services.serviceImpli;

import ma.octo.assignement.dto.CompteDto;
import ma.octo.assignement.models.Compte;
import ma.octo.assignement.models.Utilisateur;
import ma.octo.assignement.repositories.CompteRepository;
import ma.octo.assignement.repositories.UtilisateurRepository;
import ma.octo.assignement.services.CompteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class CompteServiceImpliTest {

    private CompteRepository compteRepository;

    private CompteService compteService;

    @Autowired
    CompteServiceImpliTest(CompteRepository compteRepository, CompteService compteService){
        this.compteRepository = compteRepository;
        this.compteService = compteService;
    }


    @Test
    void findByNrCompte() {
        String nrCompte = "11111111111111111111";
        Compte compteDonnée = new Compte();
        compteDonnée.setNrCompte(nrCompte);
        compteRepository.save(compteDonnée);
        Compte compteTrouvé = compteRepository.findByNrCompte(nrCompte);
        assertThat(compteTrouvé).isEqualTo(compteDonnée);
    }

    @Test
    void findByRib() {
        String rib = "Rib";
        Compte compteDonnée = new Compte();
        compteDonnée.setRib(rib);
        compteRepository.save(compteDonnée);
        Compte compteTrouvé = compteRepository.findByRib(rib);
        assertThat(compteTrouvé).isEqualTo(compteDonnée);
    }


}