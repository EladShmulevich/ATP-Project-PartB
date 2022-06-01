package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

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
        try{
            for(int i=0; i < 12; i++){
                out.write(b[i]);
            }
            byte counter = 0;
            byte previous = b[12];
            if (previous == 0){
                 counter = 1;
            }
            for(int i = 13; i<b.length; i++){
                if(b[i] == previous){
                    counter++;
                }
                else{
                    out.write(counter);
                    previous = b[i];
                    counter =1;
                }
            }
            out.write(counter);
            out.flush();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
