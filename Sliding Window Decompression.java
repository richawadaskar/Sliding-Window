import java.io.*;
import java.util.ArrayList;

public class P4_Wadaskar_Richa_SlidingWindowDecompression
{
    public static void main(String[] args){
		//specialChar can be replaced with the special character chosen to compress a file. 
        char specialChar = 7;
        ArrayList<Character> fullFile = new ArrayList<Character>();
        ArrayList<Character> output = new ArrayList<Character>();
        FileReader inFile = null;
        FileWriter outFile = null;
        int positionWindow = 0;
        int lengthChars = 0;
        int windowSize = 30;
        
        try {
			//catsupply_output.txt can be replaced with any text file. 
            inFile = new FileReader("catsupply_output.txt");
            outFile = new FileWriter(new File("outputFileCatSupply.txt"));
            while(inFile.ready()) {
                char c = (char)inFile.read();
                fullFile.add(c);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        for(int i = 0; i < fullFile.size(); i++){
            char a = (char)fullFile.get(i);
            if(a == specialChar){
                positionWindow = (int)fullFile.get(i+1);
                lengthChars = (int)fullFile.get(i+2);
                int moveBack = windowSize - positionWindow;
                String repeat = findRepeats(moveBack, lengthChars, output);
                for(int m = 0; m < repeat.length(); m++){
                    output.add(repeat.charAt(m));
                }
                i+=2;
            } else {
                output.add(fullFile.get(i));
            }
        }
        System.out.println(output);
        try{
             for(int k = 0; k < output.size(); k++){
                outFile.write(output.get(k));
             }
             outFile.close();
        } catch (IOException e) {
             System.out.println(e.getMessage());
        }
    }
    public static String findRepeats(int moveBack, int lengthChars, ArrayList<Character> output){
        String A = "";
        for(int j = output.size() - moveBack; j < output.size() - moveBack + lengthChars; j++){
            A += (char)output.get(j);
        }
        return A;
    }
}
