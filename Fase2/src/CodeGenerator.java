
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
public class CodeGenerator {
    
    private Reader reader;
    private ArrayList<ArrayList> tokens;
    private String errorLog = "";
    private String found = "";
    
    
    //constructor: scanner y tokens
    public CodeGenerator(Reader reader, ArrayList tokens){
        this.reader = reader;
        this.tokens = tokens;
    }
    
    public boolean TryCode(){
        String temp = "";
        do{
            temp = reader.Read();
            //System.out.println(temp);
            AllPosibilities(temp);
            
        }while(temp.length()!=0);
        System.out.println(found);
        return true;
    }
    
    public void AllPosibilities(String str){
        char[] charac = str.toCharArray();
        //System.out.println(String.valueOf(charac));
        for (int length = 0; length < charac.length; length ++){//para cada tamanio posible de string
            for (int j = 0;  j<charac.length-length; j++){//para todo j hasta longitud -i
                //i te indica de que tamaño es el string
                //j te indica en donde debe empezar el string
                //read from j to j + length
                
                char[] temp = CharPortion(charac, j, j+length);//ya tengo el string a evaluar
                //System.out.println(String.valueOf(temp));
                Evaluate(temp);
                
            }
            
        }
        
    }
    
    public char[] CharPortion(char[] charc, int init, int end){
        String temp = "";
        while (init <= end){
            temp += charc[init++];
        }
        
        return temp.toCharArray();
    }
    
    
    public void Evaluate(char[] charc){
        //charac tiene todos los elementos a evaluar
        
        for (ArrayList arr: tokens){//evaluamos cada conjunto
            String eval = (String)arr.get(1);//eval es el conjunto
            if (eval.contains("¬")){//eval es un keyword
                String temp = String.valueOf(charc);
                eval = eval.replace("¬", "");
                if (eval.equals(temp)){//si lo contiene es key word
                    found += "<"+arr.get(0)+","+temp+"> ";
                }
            }else{//debe pertencer a un conjunto
                if (isCompare(charc,eval)){//si todo pertenece almismo
                    found += "<"+arr.get(0)+","+String.valueOf(charc)+"> ";
                }
                
            }
        }
        
    }   
    
    public boolean isCompare(char[] charac, String eval){
        boolean result = true;
        
        for (char ch: charac){//para cada caracter
            result = result && eval.contains(""+ch);//eval debe contener todos
        }
        
        return result;
    }
    
    
}
