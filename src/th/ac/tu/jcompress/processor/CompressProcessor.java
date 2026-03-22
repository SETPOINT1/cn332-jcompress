package th.ac.tu.jcompress.processor;

import th.ac.tu.jcompress.core.FileObserver;
import th.ac.tu.jcompress.strategy.CompressStrategy;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class CompressProcessor implements FileObserver {
    private OutputStream out;
    private String outputPath;

    public CompressProcessor(String inputPath, CompressStrategy strategy) {
        try {
            this.outputPath = inputPath + ".zip";
            // Decorator Pattern: หุ้ม FileOutputStream ด้วยท่อบีบอัด
            FileOutputStream fos = new FileOutputStream(outputPath);
            this.out = strategy.wrapStream(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(byte[] data, int bytesRead) {
        try {
            if (out != null) out.write(data, 0, bytesRead);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onComplete() {
        try {
            if (out != null) {
                out.flush();
                out.close();
                System.out.println("[Compress] สร้างไฟล์บีบอัดสำเร็จ: " + outputPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}