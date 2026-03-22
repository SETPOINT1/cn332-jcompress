package th.ac.tu.jcompress.strategy;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.OutputStream;

public class DESAlgorithm implements EncryptStrategy {
    @Override
    public OutputStream wrapStream(OutputStream out) throws Exception {
        // สร้าง Key สำหรับ DES แบบอัตโนมัติ (สำหรับงานจำลอง)
        KeyGenerator keygen = KeyGenerator.getInstance("DES");
        SecretKey key = keygen.generateKey();
        
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        
        return new CipherOutputStream(out, cipher);
    }
}