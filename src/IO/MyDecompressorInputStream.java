package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class MyDecompressorInputStream extends InputStream {
    InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        try {
            return in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int read(byte[] byteArr) throws IOException {
        int byteIndex = 0;
        while (byteIndex < 12) {
            byteArr[byteIndex] = (byte) read();
            byteIndex++;
        }
        int i;
        int indexB = 12;
        while ((i = read()) != -1) {
            convertIntToBinary(i,indexB,byteArr);
            indexB +=8 ;
        }
        return 0;
    }
  public void convertIntToBinary(int num, int index,byte[] byteArr ) throws IOException {
        byte[] newByteArr = new byte[8];
        int counter = 7;
        while(num >0){
            newByteArr[counter] = (byte) (num % 2);
            num = (byte) (num/2);
            counter --;
        }
        while(counter > -1){
            newByteArr[counter] = 0;
            counter--;
        }
        for (int i =0; i<8; i++){
            byteArr[index] = newByteArr[i];
            index++;
        }
    }
}


