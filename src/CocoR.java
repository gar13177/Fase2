/* CocoR.java */
/* Generated By:JavaCC: Do not edit this line. CocoR.java */
public class CocoR implements CocoRConstants {

  public static void main(String args[]) {
    CocoR parser;
    if (args.length == 0) {
      System.out.println("IDL Parser Version 0.1:  Reading from standard input . . .");
      parser = new CocoR(System.in);
    } else if (args.length == 1) {
      System.out.println("IDL Parser Version 0.1:  Reading from file " + args[0] + " . . .");
      try {
        parser = new CocoR(new java.io.FileInputStream(args[0]));
      } catch (java.io.FileNotFoundException e) {
        System.out.println("IDL Parser Version 0.1:  File " + args[0] + " not found.");
        return;
      }
    } else {
      System.out.println("IDL Parser Version 0.1:  Usage is one of:");
      System.out.println("         java CocoR < inputfile");
      System.out.println("OR");
      System.out.println("         java CocoR inputfile");
      return;
    }
    try {
      parser.CocoL();
      System.out.println("IDL Parser Version 0.1:  IDL file parsed successfully.");
    } catch (ParseException e) {
      System.out.println("IDL Parser Version 0.1:  Encountered errors during parse.");
    }
  }

/* Production 1 */
  static final public 
void CocoL() throws ParseException {
    jj_consume_token(8);
    ident();
    ScannerSpecification();
    jj_consume_token(9);
    ident();
    jj_consume_token(10);
  }

/* Production 2 */
  static final public 
void ScannerSpecification() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 11:{
      jj_consume_token(11);
      SetDecl();
      break;
      }
    default:
      jj_la1[0] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 12:{
      jj_consume_token(12);
      KeywordDecl();
      break;
      }
    default:
      jj_la1[1] = jj_gen;
      ;
    }
  }

/* Production 3 */
  static final public 
void SetDecl() throws ParseException {
    ident();
    jj_consume_token(13);
    Set();
    jj_consume_token(10);
  }

/* Production 4 */
  static final public 
void Set() throws ParseException {
    BasicSet();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 14:
      case 15:{
        ;
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 14:{
        jj_consume_token(14);
        break;
        }
      case 15:{
        jj_consume_token(15);
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      BasicSet();
    }
  }

/* Production 5 */
  static final public 
void BasicSet() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 20:{
      string();
      break;
      }
    case LETTER:{
      ident();
      break;
      }
    case 17:
    case CHAR:{
      Char();
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 16:{
        jj_consume_token(16);
        Char();
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        ;
      }
      break;
      }
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

/* Production 6 */
  static final public 
void Char() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case CHAR:{
      jj_consume_token(CHAR);
      break;
      }
    case 17:{
      jj_consume_token(17);
      jj_consume_token(18);
      number();
      jj_consume_token(19);
      break;
      }
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

/* Production 7 */
  static final public 
void KeywordDecl() throws ParseException {
    ident();
    jj_consume_token(13);
    string();
    jj_consume_token(10);
  }

/* Definitions of complex regular expressions follow */
  static final public 
void ident() throws ParseException {
    jj_consume_token(LETTER);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LETTER:
      case DIGIT:{
        ;
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        break label_2;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LETTER:{
        jj_consume_token(LETTER);
        break;
        }
      case DIGIT:{
        jj_consume_token(DIGIT);
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  static final public void number() throws ParseException {
    jj_consume_token(DIGIT);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case DIGIT:{
        ;
        break;
        }
      default:
        jj_la1[9] = jj_gen;
        break label_3;
      }
      jj_consume_token(DIGIT);
    }
  }

  static final public void string() throws ParseException {
    jj_consume_token(20);
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LETTER:
      case DIGIT:{
        ;
        break;
        }
      default:
        jj_la1[10] = jj_gen;
        break label_4;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LETTER:{
        jj_consume_token(LETTER);
        break;
        }
      case DIGIT:{
        jj_consume_token(DIGIT);
        break;
        }
      default:
        jj_la1[11] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    jj_consume_token(20);
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public CocoRTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[12];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x800,0x1000,0xc000,0xc000,0x10000,0xb20000,0x820000,0x600000,0x600000,0x400000,0x600000,0x600000,};
   }

  /** Constructor with InputStream. */
  public CocoR(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public CocoR(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new CocoRTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public CocoR(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new CocoRTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public CocoR(CocoRTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(CocoRTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[24];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 12; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 24; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
