/**
 * Implementacion de clase Token extraida de:
 * http://www.ssw.uni-linz.ac.at/Coco/
 * Segun Manual de CocoR el Scanner debe leer Tokens con los siguientes atributos
 * 
 */

/**
 *
 * @author Kevin
 */
public class Token {
    protected int kind;    //Tipo de token
    protected int pos;     //Posicion de token en bytes del archivo (inicia en 0)
    protected int charPos; //Posicion de token en caracteres (inicia en 0)
    protected int col;     //columna del token (inicia en 1)
    protected int line;    //linea del token (inicia en 1)
    protected String val;  //valor del token
    protected Token next;  //referencia a siguiente token
    
    public Token(){
        
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getCharPos() {
        return charPos;
    }

    public void setCharPos(int charPos) {
        this.charPos = charPos;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Token getNext() {
        return next;
    }

    public void setNext(Token next) {
        this.next = next;
    }
}
