package th.ac.tu.jcompress.strategy;

import java.io.OutputStream;

public interface CompressStrategy {
    // ส่งผ่าน OutputStream เพื่อให้ Algorithm หุ้มท่อ (Decorator)
    OutputStream wrapStream(OutputStream out) throws Exception;
}