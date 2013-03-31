package pl.akiba.frontend.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Logger;

import org.apache.commons.codec.binary.Base64;
import org.springframework.social.InternalServerErrorException;
import org.springframework.stereotype.Component;

@Component("tokensGenerator")
public class TokensGenerator {
    private SecureRandom prng;
    
    Logger logger = Logger.getLogger(TokensGenerator.class.toString());
    
    public TokensGenerator() {
        try {
            //Initialize SecureRandom
            //This is a lengthy operation, to be done only upon
            //initialization of the application
            
            prng = SecureRandom.getInstance("SHA1PRNG");
            logger.info("Secure Random instantiated");
            
        } catch (NoSuchAlgorithmException e) {
            logger.severe(e.toString());
        }
    }
    
    public String generateToken() {
        String randomNum = new Integer( prng.nextInt() ).toString();

        //get its digest
        MessageDigest sha;
        try {
            sha = MessageDigest.getInstance("SHA-1");
            byte[] result =  sha.digest( randomNum.getBytes() );

            String token = Base64.encodeBase64String(result);
            logger.info("Generated token=" + token);
            return token;
        } catch (NoSuchAlgorithmException e) {
            logger.severe(e.toString());
            throw new InternalServerErrorException(e.toString());
        }
 
    }

}
