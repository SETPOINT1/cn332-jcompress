package th.ac.tu.jcompress.strategy;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Algorithm implements ChecksumStrategy {
    private MessageDigest digest;

    public MD5Algorithm() {
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(byte[] data, int bytesRead) {
        digest.update(data, 0, bytesRead);
    }

    @Override
    public String getResult() {
        byte[] hashBytes = digest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}