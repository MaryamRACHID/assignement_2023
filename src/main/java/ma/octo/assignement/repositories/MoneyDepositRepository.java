package ma.octo.assignement.repositories;

import ma.octo.assignement.models.Compte;
import ma.octo.assignement.models.MoneyDeposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoneyDepositRepository extends JpaRepository<MoneyDeposit, Long> {

    Optional<MoneyDeposit> findByCompteBeneficiaire(Compte compte);

    Optional<MoneyDeposit> findByNomEmetteur(String nomEmetteur);

}
