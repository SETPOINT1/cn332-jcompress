package th.ac.tu.jcompress.strategy;

public interface ChecksumStrategy {
    void update(byte[] data, int bytesRead);
    String getResult();
}