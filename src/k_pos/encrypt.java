package k_pos;

import org.mindrot.jbcrypt.BCrypt;

public class encrypt {   
    private String Encrypted_Password = "";
    
    public boolean VerifyPassword(String Password , String Encrypted_Password) {
        return BCrypt.checkpw(Password, Encrypted_Password);
    }
    
    public String GetEncryptedPassword(String Password){
        Encrypted_Password = BCrypt.hashpw(Password, BCrypt.gensalt());
        return Encrypted_Password ;
    }
}