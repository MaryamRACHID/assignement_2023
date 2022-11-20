package ma.octo.assignement.dto;

import lombok.Builder;
import lombok.Data;
import ma.octo.assignement.models.Compte;


import java.math.BigDecimal;

@Data
@Builder
public class CompteDto {

    private String nrCompte;

    private BigDecimal solde;

    private String rib;

    //private Long idUtilisateur;

    private UtilisateurDto utilisateurDto;


    public static CompteDto fromEntity(Compte compte){

        if(compte == null){
            return null;
        }
        return CompteDto.builder()
                .nrCompte(compte.getNrCompte())
                .solde(compte.getSolde())
                .rib(compte.getRib())
                //.utilisateurDto(UtilisateurDto.fromEntity(compte.getUtilisateur()))
                .build();

    }

    public static Compte toEntity(CompteDto compteDto){

        if(compteDto == null){
            return null;
        }
        Compte compte = new Compte();
        compte.setNrCompte(compteDto.getNrCompte());
        compte.setSolde(compteDto.getSolde());
        compte.setRib(compteDto.getRib());
        //compte.setUtilisateur(UtilisateurDto.toEntity(compteDto.utilisateurDto));
        return compte;

    }

}
