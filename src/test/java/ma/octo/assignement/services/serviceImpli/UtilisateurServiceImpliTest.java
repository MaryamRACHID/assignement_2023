package ma.octo.assignement.services.serviceImpli;

import ma.octo.assignement.dto.CompteDto;
import ma.octo.assignement.dto.UtilisateurDto;
import ma.octo.assignement.exceptions.ErrorCodes;
import ma.octo.assignement.exceptions.InvalidEntityException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.models.Compte;
import ma.octo.assignement.models.Utilisateur;
import ma.octo.assignement.repositories.UtilisateurRepository;
import ma.octo.assignement.services.UtilisateurService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class UtilisateurServiceImpliTest {

    private UtilisateurService utilisateurService;
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    UtilisateurServiceImpliTest(UtilisateurService utilisateurService, UtilisateurRepository utilisateurRepository){
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurService = utilisateurService;
    }

    @Test
    void saveFailed() {
        Utilisateur utilisateur1 = new Utilisateur();
        utilisateur1.setId(22L);
        utilisateur1.setFirstName("Maryam");
        utilisateur1.setLastName("RACHID");
        utilisateur1.setGender("Femme");

        Exception exception = assertThrows(InvalidEntityException.class,()->utilisateurService.save(UtilisateurDto.fromEntity(utilisateur1)));
        assertThat("L'utilisateur est null").isEqualTo(exception.getMessage());

    }

    @Test
    void loadAllUtilisateur() {
        Utilisateur utilisateur1 = new Utilisateur();
        utilisateur1.setUsername("user1");
        utilisateur1.setLastName("last1");
        utilisateur1.setFirstName("first1");
        utilisateur1.setGender("Male");

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setUsername("user2");
        utilisateur2.setLastName("last2");
        utilisateur2.setFirstName("first2");
        utilisateur2.setGender("Female");

        Utilisateur utilisateur3 = new Utilisateur();
        utilisateur3.setFirstName("Maryam");
        utilisateur3.setLastName("RACHID");
        utilisateur3.setGender("Femme");
        utilisateur3.setUsername("Mary");
        utilisateur3.setBirthDate(Date.from(Instant.now()));


        Utilisateur utilisateur4 = new Utilisateur();
        utilisateur4.setFirstName("Aya");
        utilisateur4.setLastName("RACHID");
        utilisateur4.setGender("Femme");
        utilisateur4.setUsername("Aya");
        utilisateur4.setBirthDate(Date.from(Instant.now()));

        utilisateurService.save(UtilisateurDto.fromEntity(utilisateur3));
        utilisateurService.save(UtilisateurDto.fromEntity(utilisateur4));

        List<UtilisateurDto> utilisateurListTrouvée = utilisateurService.loadAllUtilisateur();
        List<UtilisateurDto> utilisateurList = new ArrayList<>();
        utilisateurList.add(UtilisateurDto.fromEntity(utilisateur1));
        utilisateurList.add(UtilisateurDto.fromEntity(utilisateur2));
        utilisateurList.add(UtilisateurDto.fromEntity(utilisateur3));
        utilisateurList.add(UtilisateurDto.fromEntity(utilisateur4));

        assertThat(utilisateurListTrouvée).isEqualTo(utilisateurList);

    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }

}