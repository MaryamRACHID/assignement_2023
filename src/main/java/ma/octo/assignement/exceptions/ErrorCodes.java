/*
 * @author Maryam RACHID
 * Software Engineer from the National School Of Applied Science
 */

package ma.octo.assignement.exceptions;

public enum ErrorCodes {

    TRANSFER_NOT_FOUND(1000),
    TRANSFER_NOT_VALID(1001),
    DEPOSIT_NOT_FOUND(2000),
    DEPOSIT_NOT_VALID(2001),
    UTILISATEUR_NOT_FOUND(3000),
    UTILISATEUR_NOT_VALID(3001),
    COMPTE_NOT_FOUND(3000),
    COMPTE_NOT_VALID(3001),
    ;

    private int code;

    ErrorCodes(int code){
        this.code = code;
    }

    public int getCode(){
        return this.code;
    }

    public void setCode(int code){
        this.code = code;
    }

}
