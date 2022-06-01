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
        try{
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
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
