package IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SimpleDecompressorInputStream extends InputStream {
    InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

//    @Override
//    public int read() throws IOException {
//        try{
//            return in.read();
//        }catch(IOException ex){
//            ex.printStackTrace();
//        }
//        return 0; //(-1?)
//    }
//
//    @Override
//    public int read(byte[] b) throws IOException {
//        try{
//            int lenByteArr = 12;
//            for(int i=12; i<b.length; i++){
//                lenByteArr += b[i];
//            }
//            byte[] byteArr = new byte[lenByteArr];
//            for(int i=0; i<12; i++){
//                byteArr[i] = b[i];
//            }
//            for (int i = 12; i < b.length; i++){
//                for(int j = 0; j<b[i]; j++){
//                    if (i % 2 == 0){
//                        b[i+j] = 0;
//                    }
//                    else{
//                        b[i+j] = 0;
//                    }
//                }
//            }
//
//            return in.read();
//        }catch(IOException ex){
//            ex.printStackTrace();
//        }
//        return 0;
//    }

    /**
     *
     * @param b   the buffer into which the data is read.
     * @return the end of the byteArray index; decompress it to the original byteArray
     * @throws IOException
     */
    @Override
    public int read(byte[] b) throws IOException{
        int byteIndex =0;
        while (byteIndex < 12){
            b[byteIndex]=(byte)read();
            byteIndex++;
        }

        int nextBinary = 0;
        int i;
        while ((i=read())!=-1) {
            for(;i>0;i--){
                b[byteIndex]=(byte)nextBinary;
                byteIndex++;
            }
            nextBinary = ((nextBinary == 1) ? 0 : 1);
        }
        return byteIndex;
    }


    @Override
    public int read() throws IOException{
        try {
            return in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
