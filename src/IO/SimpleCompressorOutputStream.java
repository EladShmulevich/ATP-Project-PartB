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

//    @Override
//    public void write(int b) throws IOException {
//        try {
//            out.write(b);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//
//
//    public void write(byte[] b) throws IOException {
//        byte zeroOneCounter, i = 0;
//        ArrayList<Byte> myLIst = new ArrayList<Byte>();
//        while (i < b.length) {
//            zeroOneCounter = 0;
//            while (b[i] == previous) {
//                if (zeroOneCounter == 255) {
//                    myLIst.add((byte)zeroOneCounter);
//                    zeroOneCounter = 0;
//                }
//                zeroOneCounter++;
//                i++;
//            }
//            if(previous == 0)
//                previous = 1;
//            else{
//                previous = 0;
//            }
//            myLIst.add((byte)zeroOneCounter);
//        }
//
//      for(byte comp: myLIst){
//          out.write(comp);
//      }
//
//    }


//    public void write(byte[] b) throws IOException {
//        try{
////            byte counter = 0;
////            byte previous = b[12];
////            if (previous == 0){
////                counter = 1;
////            }
//
//            for(int i = 0; i < 12; i++){
//                out.write(b[i]);
//            }
//            for(int i = 13; i<b.length; i++){
//                if(b[i] == previous){
//                    counter++;
//                }
//                else{
//                    out.write(counter);
//                    previous = b[i];
//                    counter =1;
//                }
//            }
//            out.write(counter);
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//
//    }

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
