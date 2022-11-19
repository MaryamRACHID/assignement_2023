/*
 * @author Maryam RACHID
 * Software Engineer from the National School Of Applied Science
 */

package ma.octo.assignement.validators;

import ma.octo.assignement.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<String> validate(UtilisateurDto utilisateurDto){

        List<String> errors = new ArrayList<>();

        if(utilisateurDto == null){
            errors.add("Veuillez renseigner le nom de l'utilisateur'");
            errors.add("Veuillez renseigner le prenom de l'utilisateur'");
            errors.add("Veuillez renseigner l'email de l'utilisateur'");
            errors.add("Veuillez renseigner un mot de passe pour l'utilisateur'");
            errors.add("Veuillez renseigner l'adresse de l'utilisateur'");
            return errors;
        }

        if (!StringUtils.hasLength(utilisateurDto.getLastName())){
            errors.add("Veuillez renseigner le nom de l'utilisateur'");
        }

        if (!StringUtils.hasLength(utilisateurDto.getFirstName())){
            errors.add("Veuillez renseigner le prenom de l'utilisateur'");
        }

        if (!StringUtils.hasLength(utilisateurDto.getUsername())){
            errors.add("Veuillez renseigner un mot d'utilisateur'");
        }

        if (!StringUtils.hasLength(utilisateurDto.getGender())){
            errors.add("Veuillez renseigner le genre pour l'utilisateur'");
        }


        if(utilisateurDto.getBirthDate() == null){
            errors.add("Veuillez renseigner une date de naissance pour l'utilisateur'");
        }

        return errors;

    }
}
