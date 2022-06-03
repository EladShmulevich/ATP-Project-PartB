package IO;

import java.io.IOException;
import java.io.OutputStream;


public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;

    public MyCompressorOutputStream(OutputStream os) {

        this.out = os;
    }


    @Override
    public void write(int b) {
        try {
            out.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(byte[] b) throws IOException {
        for (int i = 0; i < 12; i++) {
            out.write(b[i]);
        }
        for (int i = 12; i < b.length; i++) {
            if (i + 7 < b.length) {
                handleByteNum(b, i);
                i += 7;
            } else {
                handleFixNum(b, i);
            }
        }
    }

    public void handleByteNum(byte[] b, int i) throws IOException {
        int divider = 128;
        int num = 0;
        while (divider > 0) {
            num += b[i] * divider;
            divider = divider / 2;
            i++;
        }
        out.write(num);
    }

    public void handleFixNum(byte[] b, int index) throws IOException {
        int complete = 8 - (b.length - index);
        int divider = (int) Math.pow(2, complete);
        divider = 128 / divider;
        int num = 0;
        while (divider > 0) {
            num += b[index] * divider;
            divider = divider / 2;
            index++;
        }
        out.write(num);
    }
}
