package com.jbrown.core.util;
 
import java.security.spec.KeySpec;
import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;

public class BrownCrypter {
	    private static final String UNICODE_FORMAT = "UTF8";
	    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	    private KeySpec myKeySpec;
	    private SecretKeyFactory mySecretKeyFactory;
	    private Cipher cipher;
	    private byte[] keyAsBytes;
	    private String myEncryptionKey;
	    private String myEncryptionScheme;
	    private SecretKey key;
	    private BrownEncoder encoder;
	 
	    public BrownCrypter(String encryption24CharKey) throws Exception
	    {
	        myEncryptionKey = encryption24CharKey;
	        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
	        keyAsBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
	        myKeySpec = new DESedeKeySpec(keyAsBytes);
	        mySecretKeyFactory = SecretKeyFactory.getInstance(myEncryptionScheme);
	        cipher = Cipher.getInstance(myEncryptionScheme);
	        key = mySecretKeyFactory.generateSecret(myKeySpec);
	        encoder = new BrownEncoder();
	    }
	  
	    /**
	     * Method To Encrypt The String
	     */
	    public String encrypt(String unencryptedString) {
	        String encryptedString = null;
 
	        try {
	            cipher.init(Cipher.ENCRYPT_MODE, key);
	            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
	            byte[] encryptedText = cipher.doFinal(plainText);
	            encryptedString = Base64.encodeBase64String(encryptedText);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return encoder.encode(encryptedString);
	    }
	    
	    /**
	     * Method To Decrypt An Ecrypted String
	     */
	    public String decrypt(String encryptedString) {
	    	encryptedString = encoder.decode(encryptedString);
	        String decryptedText=null;
	        try {
	            cipher.init(Cipher.DECRYPT_MODE, key);
	            byte[] encryptedText =  Base64.decodeBase64(encryptedString);
	            byte[] plainText = cipher.doFinal(encryptedText);
	            decryptedText= bytes2String(plainText);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return decryptedText;
	    }
	    
	    /**
	     * Returns String From An Array Of Bytes
	     */
	    private static String bytes2String(byte[] bytes) {
	        StringBuffer stringBuffer = new StringBuffer();
	        for (int i = 0; i < bytes.length; i++) {
	            stringBuffer.append((char) bytes[i]);
	        }
	        return stringBuffer.toString();
	    }
	 
	    /**
	     * Testing The DESede Encryption And Decryption Technique
	     */
	    public static void main(String args []) throws Exception
	    {
	    	BrownCrypter myEncryptor= new BrownCrypter("sssssssxsdfddMyKeyssdhaa");
	 
	        String stringToEncrypt = "Java Test";
	        String encrypted = myEncryptor.encrypt(stringToEncrypt);
	        String decrypted = myEncryptor.decrypt(encrypted);

		    System.out.println("encrypted=" + encrypted + ", decrypted="
				+ decrypted);
	    }
}