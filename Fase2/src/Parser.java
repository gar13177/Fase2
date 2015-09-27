

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
    private String errorLog = "";
    
    private Reader reader;
    
    public Parser(Reader reader){
        this.reader = reader;
    }
    
    public boolean Cocol(){
        boolean val = true;
        if (!reader.Read().equals("COMPILER")){
            //error
            errorLog += "COMPILER expected in position: "+reader.getIndex()+"\n";
            val = false;
        }//read COMPILER
        
        String ident = "";
        int indextemp = reader.getIndex();
        if (!isIdent()){//si no sigue ident
            errorLog += "ident expected in position: "+reader.getIndex()+"\n";
            val = false;
        }else{//como es ident
            reader.setIndex(indextemp);
            ident = reader.Read();
        }
        
        
        
        //run ScannerSpecification
        if (!ScannerSpecification()){
            //error
            errorLog += "ScannerSpecification failure in position: "+reader.getIndex()+"\n";
            val = false;
        }
        
        if (!reader.Read().equals("END")){
            //error
            errorLog += "END expected in position: "+reader.getIndex()+"\n";
            val = false;
        }//Read "END"
        
        
        if (!reader.Read().equals(ident)){
            //error
            errorLog += "ident mismatch in position: "+reader.getIndex()+"\n";
            val = false;
        }
        
        if (!reader.Read().equals(".")){
            //error
            errorLog += ". expected in position: "+reader.getIndex()+"\n";
            val = false;
        }//Read .
        
        errorLog += "Accepted: "+val;
        new Printer(errorLog, "log.txt");
        
        return val;
    }
    
    public boolean ScannerSpecification(){
        boolean val = true;
        int indextemp;
        indextemp = reader.getIndex();//por si no hay CHARACTERS
        if (!reader.Read().equals("CHARACTERS")){
            reader.setIndex(indextemp);//no esta characters, entonces no hay
        }else{//si esta 
            indextemp = reader.getIndex();//guardo el siguiente indice
            while (SetDecl()){//mientras se lea un SetDecl
                indextemp = reader.getIndex();//se obtiene el inidice nuevo
                
            }
            //reader.setIndex(indextemp);//cuando se termina de leer SetDecl
            //se guarda ultimo indice valido
        }
        reader.setIndex(indextemp);
        
        
        indextemp = reader.getIndex();//por si no hay KEYWORDS
        if (!reader.Read().equals("KEYWORDS")){
            reader.setIndex(indextemp);//no esta keywords, entonces no hay
        }else{//si esta 
            indextemp = reader.getIndex();//guardo el siguiente indice
            while (KeywordDecl()){//mientras se lea un SetDecl
                indextemp = reader.getIndex();//se obtiene el inidice nuevo
            }
            //reader.setIndex(indextemp);//cuando se termina de leer SetDecl
            //se guarda ultimo indice valido
        }
        reader.setIndex(indextemp);
        
        return val;
    }
    
    public boolean SetDecl(){
        if (!isIdent()){//busca que sea ident
            return false;
        }//suponemos que esta bien ident
        
        if (!reader.Read().equals("=")){
            return false;//no es SetDecl
        }//Read =
        
        
        if (!Set()){//no existe set
            return false; //no era de este tipo
        }
        
        if (!reader.Read().equals(".")){
            return false;//no es SetDecl
        }//Read .
        
        return true;
    }
    
    public boolean Set(){
        if (!BasicSet()){//no es Set
            return false;
        }
        
        int indextemp;
        boolean bool = true;
        do{
            indextemp = reader.getIndex();//guardamos el index anterior
            String temp = reader.Read();
            
            if (!temp.equals("+") && !temp.equals("-")){//no es Set
                break; //ya no es set, no es necesario seguir
            }  
            bool = BasicSet();//determina si sigue      
        }while (bool);
        
        reader.setIndex(indextemp);
        
        return true;
    }
    
    public boolean BasicSet(){
        boolean result;
        int tempindex = reader.getIndex();//guardo index por si no es String
        result = isString();
        if (!result){//no es String
            reader.setIndex(tempindex);
            result = isIdent();
        }else{//es string
            return true;
        }
        //PROBLEMA PORQUE CHR ES ident
        //--------------------------
        //---------------------------
        if (!result){//no es ident
            reader.setIndex(tempindex);
            result = Char();
        }else {//es ident
            return true;
        }
        
        if (!result){//no es char
            reader.setIndex(tempindex);
            
            return false;//no es alguno de los 3
        }else{//si es char
            tempindex = reader.getIndex(); //ya hay un char valido
            result = reader.Read().equals("..");
            result = result && Char();
        }
        
        
        if(!result){// no continua algo despues de char pero ya hay char
            reader.setIndex(tempindex);   
        }

        return true;
    }
    
    public boolean Char(){
        
        boolean result;
        int tempindex = reader.getIndex();
        
        if (isChar()){//es char
            return true;
        }else{//no es char
            
            reader.setIndex(tempindex);
            result = reader.Read().equals("CHR");
            
            result = result && reader.Read().equals("(");//sigue un parentesis
            result = result && isNumber();
            result = result && reader.Read().equals(")");//sigue un parentesis
        }
        
        if (!result){//no se cumple
            return false;
        }
                
        return true;
    }
    
    public boolean isChar(){
        String temp = reader.Read();
        
        if (temp.charAt(0)!= '\'' || temp.charAt(temp.length()-1) != '\'' )
            return false; //no es char
        
        if (temp.length()!=3)
            return false; //es mas largo que char
        
        return true;
    }
    
    public boolean KeywordDecl(){
        if (!isIdent()){//no es ident lo que sigue
            return false;//no es KeywordDecl
        }
        
        if (!reader.Read().equals("=")){//no hay =
            return false;
        }
        
        if (!isString()){//lo que sigue no es un string
            return false;
        }
        
        if (!reader.Read().equals(".")){//no hay .
            return false;
        }       
            
        return true;
    }
    
    public void WhiteSpaceDecl(){
        //Read "IGNORE"
        Set();
    }

    public boolean isString(){
        String temp = reader.Read();//guardo lectura
        boolean result = temp.charAt(0)== '"' && temp.charAt(temp.length()-1)== '"';
        return result;        
    }
    
    public boolean isNumber() {
        String temp = reader.Read();
        boolean result = digit.contains(""+temp.charAt(0)); //primer caracter numero
        
        for (int i = 0; i < temp.length(); i++){
            result = result && digit.contains(""+temp.charAt(i));
        }
        return result;
    }
    
    public boolean isIdent(){
        String temp = reader.Read();
        if (temp.equals("CHR")) return false;//caso desesperado
        boolean result = letterU.contains(""+temp.charAt(0)) || letterL.contains(""+temp.charAt(0));
        
        for (int i = 0; i < temp.length(); i++){
            result = result && (letterU.contains(""+temp.charAt(0)) || letterL.contains(""+temp.charAt(0)) || digit.contains(""+temp.charAt(i)));
        }
        return result;
    }
    
}
