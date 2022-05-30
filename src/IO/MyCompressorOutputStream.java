package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;

    public MyCompressorOutputStream(OutputStream os) {
        this.out = os;
    }

    @Override
    public void write(int b) throws IOException {
        try{
            out.write(b);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void write(byte[] b) throws IOException {

    }
}