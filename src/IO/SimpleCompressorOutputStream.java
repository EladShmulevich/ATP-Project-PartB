package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream {
    OutputStream out;
    int eleCounter;
    int previous;


    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
        eleCounter = 0;
        previous = 0;
    }

    @Override
    public void write(int b) throws IOException {
        

    }


    public void write(byte[] b) throws IOException {

    }
}
