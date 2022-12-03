package ma.octo.assignement;

import ma.octo.assignement.models.Compte;
import ma.octo.assignement.models.Utilisateur;
import ma.octo.assignement.models.Transfer;
import ma.octo.assignement.repositories.CompteRepository;
import ma.octo.assignement.repositories.UtilisateurRepository;
import ma.octo.assignement.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class NiceBankApplication implements CommandLineRunner {
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private TransferRepository transferRepository;

	public static void main(String[] args) {
		SpringApplication.run(NiceBankApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		Utilisateur utilisateur1 = new Utilisateur();
		utilisateur1.setUsername("user1");
		utilisateur1.setLastName("last1");
		utilisateur1.setFirstName("first1");
		utilisateur1.setGender("Male");

		utilisateurRepository.save(utilisateur1);


		Utilisateur utilisateur2 = new Utilisateur();
		utilisateur2.setUsername("user2");
		utilisateur2.setLastName("last2");
		utilisateur2.setFirstName("first2");
		utilisateur2.setGender("Female");

		utilisateurRepository.save(utilisateur2);

		Compte compte1 = new Compte();
		compte1.setNrCompte("c1");
		compte1.setRib("RIB1");
		compte1.setSolde(BigDecimal.valueOf(90000L));
		compte1.setUtilisateur(utilisateur1);

		compteRepository.save(compte1);

		Compte compte2 = new Compte();
		compte2.setNrCompte("c2");
		compte2.setRib("RIB2");
		compte2.setSolde(BigDecimal.valueOf(20000L));
		compte2.setUtilisateur(utilisateur2);

		compteRepository.save(compte2);

		Transfer v = new Transfer();
		v.setMontantTransfer(BigDecimal.TEN);
		v.setCompteBeneficiaire(compte2);
		v.setCompteEmetteur(compte1);
		v.setDateExecution(new Date());
		v.setMotifTransfer("Assignment 2021");

		transferRepository.save(v);
	}
}
