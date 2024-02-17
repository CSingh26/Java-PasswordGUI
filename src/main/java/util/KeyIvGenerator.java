package main.java.util;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class KeyIvGenerator {

    public static void main(String[] args) throws Exception {
        // Generate Key and IV
        SecretKey key = EncryptionUtil.generateKey(256);
        IvParameterSpec iv = EncryptionUtil.generateIv();

        // Convert to Base64 for easy text storage
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        String base64Iv = Base64.getEncoder().encodeToString(iv.getIV());

        // Store in files
        Files.write(Paths.get("encryptionKey.txt"), base64Key.getBytes());
        Files.write(Paths.get("encryptionIv.txt"), base64Iv.getBytes());
    }
}
