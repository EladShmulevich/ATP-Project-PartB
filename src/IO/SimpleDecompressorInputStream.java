package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {
    InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    /**
     * @param b the buffer into which the data is read.
     * @return the end of the byteArray index; decompress it to the original byteArray
     */
    @Override
    public int read(byte[] b) {
        int byteIndex = 0;
        while (byteIndex < 12) {
            b[byteIndex] = (byte) read();
            byteIndex++;
        }
        int nextBinary = 0;
        int i;
        while ((i = read()) != -1) {
            for (; i > 0; i--) {
                b[byteIndex] = (byte) nextBinary;
                byteIndex++;
            }
            nextBinary = ((nextBinary == 1) ? 0 : 1);
        }
        return byteIndex;
    }


    @Override
    public int read() {
        try {
            return in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
