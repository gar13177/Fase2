
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
        CodeGenerator code;
        try{
            reader = new Reader("prueba.txt");
            parser = new Parser(reader);
            String str = "Archivo de evaluacion: prueba.txt\n";
            str += "Para identificar errores revisar archivo: log.txt";
            boolean succ = parser.Cocol();
            System.out.println("Valido: "+succ);
            System.out.println(str);
            
            if (succ){// lectura con exito
                System.out.println("Exito");
                reader = new Reader("entrada.txt");//archivo de entrada
                code = new CodeGenerator(reader,parser.getTokens());
                System.out.println("Valido: "+code.TryCode());
                
                /**
                 * por el momento solo tengo conjuntos
                 * el parser tendria que devolver un listado de conjuntos
                 * luego la lectura solo es recorrer caracter por caracter
                 * revisar a que conjunto pertenece cada uno
                 */
            }
            
        } catch (IOException e){
            System.out.println(e.toString());
        }
        
        
    }
    
}
