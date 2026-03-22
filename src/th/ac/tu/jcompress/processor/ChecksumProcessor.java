package th.ac.tu.jcompress.processor;

import th.ac.tu.jcompress.core.FileObserver;
import th.ac.tu.jcompress.strategy.ChecksumStrategy;

public class ChecksumProcessor implements FileObserver {
    private ChecksumStrategy strategy;

    public ChecksumProcessor(ChecksumStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void update(byte[] data, int bytesRead) {
        strategy.update(data, bytesRead);
    }

    @Override
    public void onComplete() {
        System.out.println("[Checksum] ผลลัพธ์: " + strategy.getResult());
    }
}