package th.ac.tu.jcompress.core;

public interface FileObserver {
    void update(byte[] data, int bytesRead);
    void onComplete();
}