package ma.octo.assignement.dto;

import lombok.Builder;
import lombok.Data;
import ma.octo.assignement.models.Utilisateur;

import java.util.Date;

@Data
@Builder
public class UtilisateurDto {

    private String username;

    private String gender;

    private String lastName;

    private String firstName;

    private Date birthDate;


    public static UtilisateurDto fromEntity(Utilisateur utilisateur){

        if(utilisateur == null){
            return null;
        }
        return UtilisateurDto.builder()
                .username(utilisateur.getUsername())
                .gender(utilisateur.getGender())
                .lastName(utilisateur.getLastName())
                .firstName(utilisateur.getFirstName())
                .birthDate(utilisateur.getBirthDate())
                .build();

    }

    public static Utilisateur toEntity(UtilisateurDto utilisateurDto){

        if(utilisateurDto == null){
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUsername(utilisateurDto.getUsername());
        utilisateur.setGender(utilisateurDto.getGender());
        utilisateur.setLastName(utilisateurDto.getLastName());
        utilisateur.setFirstName(utilisateurDto.getFirstName());
        utilisateur.setBirthDate(utilisateurDto.birthDate);

        return utilisateur;

    }

}
