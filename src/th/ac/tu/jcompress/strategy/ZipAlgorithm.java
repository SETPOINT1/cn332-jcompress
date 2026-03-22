package th.ac.tu.jcompress.strategy;

import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipAlgorithm implements CompressStrategy {
    private String fileName;

    public ZipAlgorithm(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public OutputStream wrapStream(OutputStream out) throws Exception {
        ZipOutputStream zos = new ZipOutputStream(out);
        zos.putNextEntry(new ZipEntry(fileName));
        return zos;
    }
}