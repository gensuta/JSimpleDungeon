import org.jasypt.util.text.AES256TextEncryptor;

public class EncryptionHandler {

    AES256TextEncryptor textEncryptor = new AES256TextEncryptor();

    public EncryptionHandler(String myEncryptionPassword){

        textEncryptor.setPassword(myEncryptionPassword);
    }

    public String encrypt(String myText)
    {
        return textEncryptor.encrypt(myText);
    }


    public String decrypt(String myEncryptedText)
    {
        return textEncryptor.decrypt(myEncryptedText);
    }

}
