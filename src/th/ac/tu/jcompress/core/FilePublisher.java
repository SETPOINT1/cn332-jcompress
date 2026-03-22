package th.ac.tu.jcompress.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilePublisher {
    private List<FileObserver> observers = new ArrayList<>();

    public void attach(FileObserver observer) { 
        observers.add(observer); 
    }

    public void readFile(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            System.out.println("กำลังอ่านไฟล์: " + filePath);
            
            while ((bytesRead = fis.read(buffer)) != -1) {
                // แจ้งเตือน Observer ทุกตัวพร้อมๆ กัน
                for (FileObserver observer : observers) {
                    observer.update(buffer, bytesRead);
                }
            }
            
            // อ่านจบแล้ว สั่งปิดการทำงาน
            for (FileObserver observer : observers) {
                observer.onComplete();
            }
        } catch (IOException e) {
            System.err.println("ไม่สามารถอ่านไฟล์ได้: " + e.getMessage());
        }
    }
}