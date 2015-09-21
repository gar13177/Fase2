
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kevin
 */
public class Reader {
    
    private int length = 0;
    private int index = 0;
    private char[] charArray;
    
    public Reader(String namePath) throws IOException{
        String theString = "";

        File file = new File(namePath);
        Scanner scanner = new Scanner(file);
        theString = scanner.nextLine();
        while (scanner.hasNextLine()) {
               theString = theString + "\n" + scanner.nextLine();
        }
        scanner.close();
        
        charArray = theString.toCharArray();
        //System.out.println(charArray);
        length = charArray.length;
    }
    
    public String Read(){
        if (index < length){ 
            //problema throw new IOException("Fuera de limites");
            String _read = "";
            int t = (int)charArray[index];

            if (t == 10 || t == 32) ReadBlank();//salto de linea o espacio
            if (charArray[index] == '"') return ReadString();
            while ((int)charArray[index] != 10 && (int)charArray[index] != 32){
                _read += charArray[index];
                //System.out.println(_read);
                index += 1;
                if (index >= length) break;
            }

            return _read;
        }
        return "";
    }
    
    //salta espacios en blanco
    public void ReadBlank(){
        while ((int)charArray[index] == 10 || (int)charArray[index] == 32){
            //System.out.println((int) charArray[index]);
            index += 1;
            if (index >= length) break;
        } 
    }
    
    public String ReadString(){
        index += 1;//se salta la comilla
        String _read = "";
        while (charArray[index] != '"'){
            _read += charArray[index];
            index += 1;
        }
        index += 1;//se salta la ultima comilla
        return _read;
    }
    
    public int getLength(){
        return length;
    }
    
    
    
}
