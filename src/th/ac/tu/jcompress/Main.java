package th.ac.tu.jcompress;

import th.ac.tu.jcompress.core.FilePublisher;
import th.ac.tu.jcompress.processor.*;
import th.ac.tu.jcompress.strategy.*;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("วิธีใช้งาน: java Main <filename> [-zip] [-DES] [-MD5]");
            return;
        }

        String fileName = args[0];
        File file = new File(fileName);
        
        if (!file.exists()) {
            System.out.println("ไม่พบไฟล์: " + fileName);
            return;
        }

        FilePublisher publisher = new FilePublisher();

        // ตรวจสอบ arguments แล้วใส่ Observer ที่เกี่ยวข้องเข้าไป
        for (int i = 1; i < args.length; i++) {
            String arg = args[i].toUpperCase();
            switch (arg) {
                case "-ZIP":
                    publisher.attach(new CompressProcessor(fileName, new ZipAlgorithm(file.getName())));
                    break;
                case "-DES":
                    publisher.attach(new EncryptProcessor(fileName, new DESAlgorithm()));
                    break;
                case "-MD5":
                    publisher.attach(new ChecksumProcessor(new MD5Algorithm()));
                    break;
                default:
                    System.out.println("ข้ามตัวเลือกที่ไม่รู้จัก: " + arg);
            }
        }

        // เริ่มต้นอ่านไฟล์และให้ Observer ทำงาน
        publisher.readFile(fileName);
    }
}