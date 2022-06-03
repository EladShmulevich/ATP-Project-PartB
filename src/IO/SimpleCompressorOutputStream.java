package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream {
    OutputStream out;
    int zeroOneCounter;
    int previousBinary;


    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
        zeroOneCounter = 0;
        previousBinary = 0;
    }

    public void write(int b) throws IOException {
        out.write(b);
    }


    /**
     * write to out into byte array
     *
     * @param b byte array of bytes
     */
    public void write(byte[] b) throws IOException {
        for (int i = 0; i < 12; i++) {
            out.write(b[i]);
        }
        for (int i = 12; i < b.length; i++) {
            handleBytes(b[i]);
        }
        if (zeroOneCounter != 0)
            out.write(zeroOneCounter);
    }


    private void handleBytes(int currentBinary) throws IOException {
        if (previousBinary == currentBinary) {
            zeroOneCounter++;
            if (zeroOneCounter == 255) {
                out.write((byte) 255);
                zeroOneCounter = 0;
            }
        } else {
            previousBinary = currentBinary;
            out.write((byte) zeroOneCounter);
            zeroOneCounter = 1;
        }
    }
}
