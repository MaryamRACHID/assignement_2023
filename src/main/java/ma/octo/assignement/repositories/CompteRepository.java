package ma.octo.assignement.repositories;

import ma.octo.assignement.models.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompteRepository extends JpaRepository<Compte, Long> {

    Compte findByNrCompte(String nrCompte);

    Compte findByRib(String rib);

    Optional<Compte> findComptesByUtilisateurId(Long id);


}
