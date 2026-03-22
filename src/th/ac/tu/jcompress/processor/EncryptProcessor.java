package th.ac.tu.jcompress.processor;

import th.ac.tu.jcompress.core.FileObserver;
import th.ac.tu.jcompress.strategy.EncryptStrategy;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class EncryptProcessor implements FileObserver {
    private OutputStream out;
    private String outputPath;

    public EncryptProcessor(String inputPath, EncryptStrategy strategy) {
        try {
            this.outputPath = inputPath + ".enc";
            // Decorator Pattern: หุ้ม FileOutputStream ด้วยท่อเข้ารหัส
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
                System.out.println("[Encrypt] สร้างไฟล์เข้ารหัสสำเร็จ: " + outputPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}