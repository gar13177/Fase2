
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kevin
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Reader reader;
        Parser parser;
        try{
            reader = new Reader("prueba.txt");
            parser = new Parser(reader);
            String str = "Archivo de evaluacion: prueba.txt\n";
            str += "Para identificar errores revisar archivo: log.txt";
            System.out.println("Valido: "+parser.Cocol());
            System.out.println(str);
            
        } catch (IOException e){
            System.out.println(e.toString());
        }
        
        
    }
    
}
