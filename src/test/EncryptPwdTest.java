package test;

import main.java.util.EncryptionUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.junit.Before;
import org.junit.Test;

public class EncryptPwdTest {
    private SecretKey key;
    private IvParameterSpec iv;

    @Before
    public void setUp() throws Exception {
        key = EncryptionUtil.generateKey(256);
        iv = EncryptionUtil.generateIv();
    }

    @Test
    public void testEncryptionPwd() throws Exception {
        String originalString = "TestString";
        String encryptedString = EncryptionUtil.encrypt(originalString, key, iv);
        String decryptedString = EncryptionUtil.decrypt(encryptedString, key, iv);

        assertNotEquals("Original and encrypted strings should not be equal.", originalString, encryptedString);
        assertEquals("Decrypted string should match the original string.", originalString, decryptedString);
    }
}
