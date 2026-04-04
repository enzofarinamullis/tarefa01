package constantes;
public class Cores {
  /* sistema de cores */
  public static final String ANSI_RESET = "\u001B[38;5;229m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";
  public static final String ANSI_ORANGE = "\u001B[38;5;208m";
  
  /* cores de cabecalho */
  public static final String COR_DEQUE_CABECALHO = "\u001B[33m";
  public static final String COR_DEQUE_NUM = "\u001B[36m";

  /* cores titulo */
  public static final String COR_SANGUE_1 = "\u001B[38;5;52m";
  public static final String COR_SANGUE_2 = "\u001B[38;5;88m";
  public static final String COR_SANGUE_3 = "\u001B[38;5;124m";
  public static final String COR_SANGUE_4 = "\u001B[38;5;160m";

  public static final String COR_CIMENTO_1 = "\u001B[38;5;236m";
  public static final String COR_CIMENTO_2 = "\u001B[38;5;237m";
  public static final String COR_CIMENTO_3 = "\u001B[38;5;238m";

  public static final String COR_MUSGO_1 = "\u001B[38;5;22m";
  public static final String COR_MUSGO_2 = "\u001B[38;5;28m";
  public static final String COR_MUSGO_3 = "\u001B[38;5;65m";
  public static final String COR_MUSGO_4 = "\u001B[38;5;29m";

  public static final String COR_RUNA_1 = "\u001B[38;5;4m";
  public static final String COR_RUNA_2 = "\u001B[38;5;24m";
  public static final String COR_RUNA_3 = "\u001B[38;5;27m";
  public static final String COR_RUNA_4 = "\u001B[38;5;63m";

  /* Cores fogo */
  public static final String cor03071e = "\u001B[38;5;17m";
  public static final String cor1f1b1c = "\u001B[38;5;236m";
  public static final String cor5b373a = "\u001B[38;5;174m";
  public static final String cor9d0208 = "\u001B[38;5;196m";
  public static final String core85d04 = "\u001B[38;5;202m";
  public static final String cordc2f02 = "\u001B[38;5;166m";
  public static final String corf48c06 = "\u001B[38;5;214m";
  public static final String corfaa307 = "\u001B[38;5;220m";
  
  /* Cores chuva */
  public static final String cor1e90ff = "\u001B[38;5;110m";
  public static final String corb3dbff = "\u001B[38;5;111m";
  public static final String cor4169e1 = "\u001B[38;5;105m";
  public static final String cor483d8b = "\u001B[38;5;97m";
  public static final String cor0000cd = "\u001B[38;5;20m";
  public static final String cor191970 = "\u001B[38;5;56m";
  public static final String corcce7ff = "\u001B[38;5;189m";
  public static final String cor87cefa = "\u001B[38;5;153m";
  public static final String cor6495ed = "\u001B[38;5;105m";
  public static final String cor000080 = "\u001B[38;5;17m";
  public static final String cor00bfff = "\u001B[38;5;39m";
  public static final String cor87ceeb = "\u001B[38;5;81m";
  
  
  public static void cprintln(String cor, String texto){
    System.out.println(cor + texto + Cores.ANSI_RESET);
  }

  public static void cprintn(String cor, String texto){
    System.out.print(cor + texto + Cores.ANSI_RESET + "\n");
  }

  public static void cprint(String cor, String texto){
    System.out.print(cor + texto + Cores.ANSI_RESET);
  }

  public static void cprintInt(String cor, int inteiro){
    System.out.print(cor + inteiro + Cores.ANSI_RESET);
  }
  
   public static void cprintIntln(String cor, int inteiro){
    System.out.println(cor + inteiro + Cores.ANSI_RESET);
  } 

  public static void cprintIntn(String cor, int inteiro){
    System.out.print(cor + inteiro + Cores.ANSI_RESET + "\n");
  }
  
  
}
