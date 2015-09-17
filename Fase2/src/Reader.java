
import java.io.IOException;
import java.io.RandomAccessFile;

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
    private RandomAccessFile file;
    private int lengthF;
    
    public Reader(String name){
        try{
            file = new RandomAccessFile(name, "r");
            lengthF = (int) file.length();
            //suponemos que el archivo no esta vacio
        }catch (IOException e){
            throw new RuntimeException("No se puede abrir " + name);
        }
    }
    
    //cierre de archivo
    public void Close(){
        if (file!=null){
            try{
                file.close();
                file= null;
            }catch (IOException e){
                throw new RuntimeException(e.getMessage());
            }
        }
    }
    
    
}
