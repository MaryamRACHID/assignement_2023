/*
 * @author Maryam RACHID
 * Software Engineer from the National School Of Applied Science
 */

package ma.octo.assignement.validators;

import ma.octo.assignement.dto.CompteDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CompteValidator {

    public static List<String> validate(CompteDto compteDto) throws CompteNonExistantException {

        List<String> errors = new ArrayList<>();

        if(compteDto == null){
            //TODO
            errors.add("Compte non trouvé");
            throw new CompteNonExistantException("Compte non trouvé");

        }

        if (!StringUtils.hasLength(compteDto.getNrCompte())){
            errors.add("Veuillez renseigner le numero du compte");

        }

        //TODO

        return errors;

    }
}
