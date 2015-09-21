
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
public class Parser {
    
    private String letterU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String letterL = "abcdefghijklmnopqrstuvwxyz";
    private String digit = "1234567890";
    
    private Reader reader;
    
    public Parser(Reader reader){
        this.reader = reader;
    }
    
    public boolean Cocol(){
        boolean val = true;
        if (!reader.Read().equals("COMPILER")){
            //error
            val = false;
        }//read COMPILER
        
        String ident = reader.Read();//suponemos que ident esta bien
        
        //run ScannerSpecification
        if (!ScannerSpecification()){
            //error
            val = false;
        }
        
        if (!reader.Read().equals("END")){
            //error
            val = false;
        }//Read "END"
        
        if (!reader.Read().equals(ident)){
            //error
            val = false;
        }
        
        if (!reader.Read().equals(".")){
            //error
            val = false;
        }//Read .
        
        return val;
    }
    
    public void ident(){
        //first letter == letter
    }
    
    public boolean ScannerSpecification(){
        boolean val = true;
        //Read "CHARACTERS"
        SetDecl();
        //Read "KEYWORDS"
        KeywordDecl();
        WhiteSpaceDecl();
        
        return val;
    }
    
    public void SetDecl(){
        ident();
        //Reconocer '='
        Set();
        //Reconocer '.'        
    }
    
    public void Set(){
        //BasicSet()
    }
    
    public void KeywordDecl(){
        ident();
        //Reconocer '='
        //try{
            string();
        //}catch (IOException e){
            //throw e;
        //}
        
        //Reconocer '.'
    }
    
    public void WhiteSpaceDecl(){
        //Read "IGNORE"
        Set();
    }
    
  
    
    
    public void string(){
        //Reconocer '"'
        anyButQuote();
        //Reconocer '"'
    }
    
    public void anyButQuote(){
        //read next while != '"'
    }
    
    public void number() throws IOException{
        String eval = "";
        
        try{
            String first = ""+eval.charAt(0);
            
            //se verifica que el primer caracter sea numero
            if (!digit.contains(first)){
                throw new IOException("Number esperado pero se ha encontrado otra cosa");
            }else{
                //se verifica que el resto sea numero
                for (int i = 0; i<eval.length(); i++){
                    if (!digit.contains(""+eval.charAt(i))){
                        throw new IOException("Number esperado pero se ha encontrado otra cosa");
                    }
                }
            }
        }catch (IOException e){
            //Exception no existe el numero
            throw e;
        }
        
    }
    
   
    
    
    
}
