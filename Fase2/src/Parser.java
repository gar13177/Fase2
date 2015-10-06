
import java.util.ArrayList;



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
    
    private ArrayList<ArrayList> conjunto = new ArrayList();//conjunto de tokens validos
    private ArrayList<String> token;//token temporal
    private String construct;//construct temporal para token
    
    private boolean priority = false;//valor EXCEPT KEYWORDS
    
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
        System.out.println(conjunto);
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
            token = new ArrayList();//inicializa token
            construct = "";//vaciamos temporal
            while (SetDecl()){//mientras se lea un SetDecl
                //si es SetDecl, conjunto ya tiene token
                
                construct = "";//vaciamos temporal
                token = new ArrayList();//se vacia el token
                indextemp = reader.getIndex();//se obtiene el inidice nuevo
                
            }
        }
        reader.setIndex(indextemp);
        token = new ArrayList();
        construct = "";
        
        
        indextemp = reader.getIndex();//por si no hay KEYWORDS
        if (!reader.Read().equals("KEYWORDS")){
            reader.setIndex(indextemp);//no esta keywords, entonces no hay
        }else{//si esta 
            indextemp = reader.getIndex();//guardo el siguiente indice
            token = new ArrayList();
            construct = "";
            while (KeywordDecl()){//mientras se lea un SetDecl
                construct = "";
                token = new ArrayList();
                indextemp = reader.getIndex();//se obtiene el inidice nuevo
                
            }
            //reader.setIndex(indextemp);//cuando se termina de leer SetDecl
            //se guarda ultimo indice valido
        }
        reader.setIndex(indextemp);
        
        indextemp = reader.getIndex();//por si no hay KEYWORDS
        if (!reader.Read().equals("TOKENS")){
            reader.setIndex(indextemp);//no esta keywords, entonces no hay
        }else{//si esta 
            indextemp = reader.getIndex();//guardo el siguiente indice
            token = new ArrayList();
            construct = "";
            while (TokenDecl()){//mientras se lea un TokenDecl
                construct = "";
                token = new ArrayList();
                indextemp = reader.getIndex();//se obtiene el inidice nuevo
                
            }
            //reader.setIndex(indextemp);//cuando se termina de leer SetDecl
            //se guarda ultimo indice valido
        }
        reader.setIndex(indextemp);
        
        return val;
    }
    
    //-------------------------------
    
    public boolean TokenDecl(){
        int tempindex = reader.getIndex();
        if (!isIdent()){//si no es ident el proximo pedazo, return false
            return false;
        }
        
        //como sabemos que es ident
        construct = "";
        reader.setIndex(tempindex);//retornamos lectura
        construct = reader.Read();//lo que retorna sera el ident        
        token.add(construct);//construct ya tiene guardado ident
        construct = "";//vaciamos construct
        
        tempindex = reader.getIndex();//tomamos index
        if (reader.Read().equals("=")){//existe un =
            if (TokenExpr()){//si lo que sigue es un TokenExpr
                //construct ya tiene guardado lo que sigue
                
            }else{//de lo contrario se retorna posicion
                reader.setIndex(tempindex);
            }
        }else{
            //ya debe existir ese ident
            reader.setIndex(tempindex);//retornamos posicion
        }
        
        tempindex = reader.getIndex();
        if (reader.Read().equals("EXCEPT")){
            if (reader.Read().equals("KEYWORDS")){
                construct+="°";//valor para reconocer except
            }else{
                reader.setIndex(tempindex);
            }
        }else{
            reader.setIndex(tempindex);
        }
        
        if (!reader.Read().equals(".")){//si no termina con punto
            return false;
        }
        token.add(construct);
        conjunto.add(token);
        return true;
    }
    
    public boolean TokenExpr(){
        construct = "";
        if (!TokenTerm()){
            return false;//no cumple con la primera 
        }//tokenterm ya tiene construct lo que contiene
        
        String temp = construct;//guardo construct en temp
        construct = "";
        int tempindex = reader.getIndex();
        boolean condition = true;
        do{
            tempindex = reader.getIndex();//se guarda el indice actual
            condition = reader.Read().equals("|");//se busca que haya un or
            condition = condition && TokenTerm();//si sigue de TokenTerm, esta bien
            if (condition){//si lleva un OR y token term, se agrega
                temp += "|"+construct;
            }
            construct = "";
        }while(condition);
        reader.setIndex(tempindex);//se retorna al ultimo indice valido
        construct = temp;
        
        return true;
    }
    
    public boolean TokenTerm(){
        construct = "";
        if(!TokenFactor()){//si no es token factor
            return false;//primera condicion no valida
        }//token factor tiene en construct lo que contiene
        
        int tempindex = reader.getIndex();
        String temp = construct;//guardo en temporal el construct
        construct = "";
        boolean condition = true;
        do{
            tempindex = reader.getIndex();//se guarda el indice actual
            condition = TokenFactor();//mientras sea TokenFactor
            if (condition){//si es token factor
                temp += construct;
            }
            construct = "";
        }while(condition);
        reader.setIndex(tempindex);//se retorna al ultimo indice valido
        construct = temp;
        
        return true;
    }
    
    public boolean TokenFactor(){
        
        int tempindex = reader.getIndex();
        if (Symbol()){//contruct ya tiene lo que es
            return true;
        }
        
        reader.setIndex(tempindex);//retornamos a posicion inicial
        String temp = construct;//guardo en un temporal 
        construct = "";
        if (reader.Read().equals("(")){
            if (TokenExpr()){
                if (reader.Read().equals(")")){
                    reader.setIndex(tempindex);//retorno a la primera posicion
                    temp += reader.Read()+reader.Read()+reader.Read();//agrego los dos parentesis y lo que contiene
                    construct = temp;
                    return true;
                }
            }
        }
        
        reader.setIndex(tempindex);
        if (reader.Read().equals("[")){
            if(TokenExpr()){
                if (reader.Read().equals("]")){
                    reader.setIndex(tempindex);//retorno a la primera posicion
                    temp += reader.Read()+reader.Read()+reader.Read();//agrego los dos parentesis y lo que contiene
                    construct = temp;
                    return true;
                }
            }
        }
        
        reader.setIndex(tempindex);
        if (reader.Read().equals("{")){
            if (TokenExpr()){
                if (reader.Read().equals("}")){
                    reader.setIndex(tempindex);//retorno a la primera posicion
                    temp += reader.Read()+reader.Read()+reader.Read();//agrego los dos parentesis y lo que contiene
                    construct = temp;
                    return true;
                }
            }
        }
        
        reader.setIndex(tempindex);//no estoy seguro que sea necesario
        return false;
    }
    
    public boolean Symbol(){
        int tempindex = reader.getIndex();
        if (isIdent()){
            reader.setIndex(tempindex);
            construct += reader.Read();//se agrega ident
            return true;//construct ya tiene guardado ident
        }
        
        reader.setIndex(tempindex);
        if(isString()){//construct ya tiene guardado string
            return true;
        }
        
        reader.setIndex(tempindex);
        if(isChar()){//saber
            return true;
        }
        reader.setIndex(tempindex);//no estoy seguro si es necesario
        
        return false;
    }
    
    //-------------------------------
    
    public boolean SetDecl(){
        int tempindex = reader.getIndex();
        if (!isIdent()){//busca que sea ident
            return false;
        }//suponemos que esta bien ident
        
        //como sabemos que es ident
        construct = "";
        reader.setIndex(tempindex);//retornamos lectura
        construct = reader.Read();//lo que retorna sera el ident        
        token.add(construct);//construct ya tiene guardado ident
        construct = "";//vaciamos construct
        
        if (!reader.Read().equals("=")){
            return false;//no es SetDecl
        }//Read =
        
        
        if (!Set()){//no existe set
            return false; //no era de este tipo
        }
        
        token.add(construct);//construct ya tiene guardado set
        
        if (!reader.Read().equals(".")){
            return false;//no es SetDecl
        }//Read .
        //System.out.println(token);
        conjunto.add(token);//se agrega un token correcto
        
        return true;
    }
    
    public boolean Set(){
        if (!BasicSet()){//no es Set
            return false;
        }
        //construct debe retornar el Basic Set
        String tempVal = construct; //tempVal tiene lo que sigue del basicSet
        
        int indextemp;
        boolean bool = true;
        do{
            indextemp = reader.getIndex();//guardamos el index anterior
            String temp = reader.Read();
            
            if (!temp.equals("+") && !temp.equals("-")){//no es Set
                break; //ya no es set, no es necesario seguir
            }  
            bool = BasicSet();//determina si sigue
            //construct ya tiene el BasicSet
            if (bool){//si todavia hay un basicSet, y ya paso la prueba de '+' '-'
                if (temp.equals("+")){//si es mas
                    tempVal += construct;
                }else{//solo podria pasar si es '-'
                    tempVal = tempFunc(construct,tempVal);//quitamos los caracteres comunes
                }
            }
        }while (bool);
        //una vez terminamos de leer el basicSet
        //tempVal contiene todo lo del BasicSet
        construct = tempVal;//construct tendra todo
        reader.setIndex(indextemp);
        
        return true;
    }
    
    public boolean BasicSet(){
        boolean result;
        int tempindex = reader.getIndex();//guardo index por si no es String
        
        result = isString();
        //if String, construct = string
        
        if (!result){//no es String
            construct = "";//construct vacio nuevamente
            reader.setIndex(tempindex);
            result = isIdent();
        }else{//es string
            return true;
        }
        
        /**
         * PROBLEMA porque CHR es ident
         */
        if (!result){//no es ident
            construct = "";
            reader.setIndex(tempindex);
            result = Char();
        }else {//es ident
            //como sabemos que es ident
            construct = "";
            reader.setIndex(tempindex);//retornamos lectura
            String tempIde = reader.Read();//lo que retorna sera el ident
            for (ArrayList arr : conjunto){//para cada token en conjunto
                if (arr.get(0).equals(tempIde)){//si el ident es ese
                    construct = (String)arr.get(1);//
                }
            }
            if (construct.equals("")) errorLog += "ident: "+tempIde+" not defined in position: "+reader.getIndex()+"\n";
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
        construct += temp.charAt(1);//se guarda char sin ''
        return true;
    }
    
    public boolean KeywordDecl(){
        int indextemp = reader.getIndex();
        if (!isIdent()){//no es ident lo que sigue
            return false;//no es KeywordDecl
        }
        //como es ident
        reader.setIndex(indextemp);
        construct = reader.Read();
        token.add(construct);
        construct = "";
        
        if (!reader.Read().equals("=")){//no hay =
            return false;
        }
        
        if (!isString()){//lo que sigue no es un string
            return false;
        }
        //isString devuelve construct como string
        construct = "¬"+construct;//le agrego esto para saber que es un keyword
        token.add(construct);
        
        if (!reader.Read().equals(".")){//no hay .
            return false;
        }       
        conjunto.add(token);
        return true;
    }
    
    public void WhiteSpaceDecl(){
        //Read "IGNORE"
        Set();
    }

    public boolean isString(){
        String temp = reader.Read();//guardo lectura
        boolean result = temp.charAt(0)== '"' && temp.charAt(temp.length()-1)== '"';
        if (result){
            temp = temp.replace("\"", "");//quitamos comillas
            construct = temp;
        }
        return result;        
    }
    
    public boolean isNumber() {
        String temp = reader.Read();
        boolean result = digit.contains(""+temp.charAt(0)); //primer caracter numero
        
        for (int i = 0; i < temp.length(); i++){
            result = result && digit.contains(""+temp.charAt(i));
        }
        if (result) construct += temp;//suponemos numero correcto
        return result;
    }
    
    public boolean isIdent(){
        String temp = reader.Read();
        if (temp.equals("CHR")) return false;//caso desesperado
        if (temp.equals("EXCEPT")){
            int tempindex = reader.getIndex();
            String temp2 = reader.Read();
            if (temp2.equals("KEYWORDS")){//quiere decir que hay escrito EXCEPT KEYWORDS
                reader.setIndex(tempindex);
                return false;
            }
        }
        boolean result = letterU.contains(""+temp.charAt(0)) || letterL.contains(""+temp.charAt(0));
        
        for (int i = 0; i < temp.length(); i++){
            result = result && (letterU.contains(""+temp.charAt(0)) || letterL.contains(""+temp.charAt(0)) || digit.contains(""+temp.charAt(i)));
        }
        
        return result;
    }
    
    public ArrayList getTokens(){
        return conjunto;
    }
    
    private String tempFunc(String chars, String del){
        char[] chart = chars.toCharArray();
        for (char cr: chart){
            del = del.replaceAll(""+cr, "");
        }
        return del;
    }
    
}
