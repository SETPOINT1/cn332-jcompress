package th.ac.tu.jcompress.strategy;

import java.io.OutputStream;

public interface EncryptStrategy {
    OutputStream wrapStream(OutputStream out) throws Exception;
}