package ma.octo.assignement.repositories;

import ma.octo.assignement.models.Compte;
import ma.octo.assignement.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

    Optional<Transfer> findByCompteEmetteur(Compte compte);
    Optional<Transfer> findByCompteBeneficiaire(Compte compte);

}
