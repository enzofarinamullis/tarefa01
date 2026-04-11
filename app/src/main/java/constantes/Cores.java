package constantes;

/**
 * Classe centralizada para gerenciamento de cores e formatação no terminal.
 * 
 * <p>Esta classe fornece códigos de escape ANSI para colorir a saída no terminal,
 * além de métodos utilitários para imprimir texto e números com cores.</p>
 * 
 * <p>Os códigos ANSI seguem o padrão <code>\u001B[38;5;Xm</code> onde X é um valor
 * entre 0 e 255 da paleta de 256 cores do terminal, ou os códigos ANSI básicos
 * (30-37) para cores padrão.</p>
 * 
 * <p><b>Paletas de cores disponíveis:</b></p>
 * <ul>
 *   <li><b>Cores básicas ANSI:</b> Preto, Vermelho, Verde, Amarelo, Azul, Roxo, Ciano, Branco, Laranja</li>
 *   <li><b>Cabeçalho:</b> Cores para elementos de deque</li>
 *   <li><b>Título "Sangue":</b> 4 tons de vermelho/sangue (códigos 52, 88, 124, 160)</li>
 *   <li><b>Título "Cimento":</b> 3 tons de cinza/cimento (códigos 236, 237, 238)</li>
 *   <li><b>Título "Musgo":</b> 4 tons de verde/musgo (códigos 22, 28, 65, 29)</li>
 *   <li><b>Título "Runas":</b> 4 tons de azul/runas (códigos 4, 24, 27, 63)</li>
 *   <li><b>Animação de Fogo:</b> 8 cores para efeitos de chamas</li>
 *   <li><b>Animação de Chuva:</b> 12 cores para efeitos de chuva e água</li>
 * </ul>
 * 
 * <p><b>Exemplo de uso:</b></p>
 * <pre>
 * Cores.cprint(Cores.ANSI_CYAN, "Texto em ciano");
 * Cores.cprintln(Cores.ANSI_GREEN, "Texto em verde com quebra de linha");
 * Cores.cprintInt(Cores.ANSI_RED, 42);
 * </pre>
 */
public class Cores {
  
  /* ==================== CORES BÁSICAS ANSI ==================== */
  
  /**
   * Reseta a cor atual para a cor padrão do terminal.
   * <p><b>Nota:</b> Este código (229) é um bege claro, não o reset ANSI padrão.
   * Para o reset verdadeiro, use "\u001B[0m".</p>
   */
  public static final String ANSI_RESET = "\u001B[38;5;229m";
  
  /**
   * Cor preta ANSI padrão (código 30).
   */
  public static final String ANSI_BLACK = "\u001B[30m";
  
  /**
   * Cor vermelha ANSI padrão (código 31).
   */
  public static final String ANSI_RED = "\u001B[31m";
  
  /**
   * Cor verde ANSI padrão (código 32).
   */
  public static final String ANSI_GREEN = "\u001B[32m";
  
  /**
   * Cor amarela ANSI padrão (código 33).
   */
  public static final String ANSI_YELLOW = "\u001B[33m";
  
  /**
   * Cor azul ANSI padrão (código 34).
   */
  public static final String ANSI_BLUE = "\u001B[34m";
  
  /**
   * Cor roxa/roxa ANSI padrão (código 35).
   */
  public static final String ANSI_PURPLE = "\u001B[35m";
  
  /**
   * Cor ciano ANSI padrão (código 36).
   */
  public static final String ANSI_CYAN = "\u001B[36m";
  
  /**
   * Cor branca ANSI padrão (código 37).
   */
  public static final String ANSI_WHITE = "\u001B[37m";
  
  /**
   * Cor laranja ANSI da paleta estendida (código 208).
   */
  public static final String ANSI_ORANGE = "\u001B[38;5;208m";
  
  /* ==================== CORES DE CABEÇALHO ==================== */
  
  /** Cor padrão para cabeçalhos de deque (amarelo). */
  public static final String COR_DEQUE_CABECALHO = "\u001B[33m";
  
  /** Cor padrão para números em deques (ciano). */
  public static final String COR_DEQUE_NUM = "\u001B[36m";

  /* ==================== CORES DO TÍTULO "SANGUE" ==================== */
  
  /** Tom mais escuro de sangue (código 52 - marrom escuro). */
  public static final String COR_SANGUE_1 = "\u001B[38;5;52m";
  
  /** Tom médio-escuro de sangue (código 88 - vermelho escuro). */
  public static final String COR_SANGUE_2 = "\u001B[38;5;88m";
  
  /** Tom médio de sangue (código 124 - vermelho forte). */
  public static final String COR_SANGUE_3 = "\u001B[38;5;124m";
  
  /** Tom mais claro de sangue (código 160 - vermelho vivo). */
  public static final String COR_SANGUE_4 = "\u001B[38;5;160m";

  /* ==================== CORES DO TÍTULO "CIMENTO" ==================== */
  
  /** Tom mais escuro de cimento (código 236 - cinza muito escuro). */
  public static final String COR_CIMENTO_1 = "\u001B[38;5;236m";
  
  /** Tom médio de cimento (código 237 - cinza escuro). */
  public static final String COR_CIMENTO_2 = "\u001B[38;5;237m";
  
  /** Tom mais claro de cimento (código 238 - cinza médio). */
  public static final String COR_CIMENTO_3 = "\u001B[38;5;238m";

  /* ==================== CORES DO TÍTULO "MUSGO" ==================== */
  
  /** Tom mais escuro de musgo (código 22 - verde floresta). */
  public static final String COR_MUSGO_1 = "\u001B[38;5;22m";
  
  /** Tom médio-escuro de musgo (código 28 - verde grama). */
  public static final String COR_MUSGO_2 = "\u001B[38;5;28m";
  
  /** Tom médio de musgo (código 65 - verde musgo). */
  public static final String COR_MUSGO_3 = "\u001B[38;5;65m";
  
  /** Tom mais claro de musgo (código 29 - verde água escuro). */
  public static final String COR_MUSGO_4 = "\u001B[38;5;29m";

  /* ==================== CORES DO TÍTULO "RUNAS" ==================== */
  
  /** Tom mais escuro de runa (código 4 - azul escuro). */
  public static final String COR_RUNA_1 = "\u001B[38;5;4m";
  
  /** Tom médio-escuro de runa (código 24 - azul médio escuro). */
  public static final String COR_RUNA_2 = "\u001B[38;5;24m";
  
  /** Tom médio de runa (código 27 - azul forte). */
  public static final String COR_RUNA_3 = "\u001B[38;5;27m";
  
  /** Tom mais claro de runa (código 63 - azul claro). */
  public static final String COR_RUNA_4 = "\u001B[38;5;63m";

  /* ==================== CORES PARA ANIMAÇÃO DE FOGO ==================== */
  
  /** Fundo escuro para contraste do fogo (código 17 - azul marinho escuro). */
  public static final String cor03071e = "\u001B[38;5;17m";
  
  /** Tom cinza escuro para brasas (código 236). */
  public static final String cor1f1b1c = "\u001B[38;5;236m";
  
  /** Tom marrom-avermelhado para fogo baixo (código 174). */
  public static final String cor5b373a = "\u001B[38;5;174m";
  
  /** Tom vermelho intenso para chamas (código 196). */
  public static final String cor9d0208 = "\u001B[38;5;196m";
  
  /** Tom laranja-escuro para chamas (código 202). */
  public static final String core85d04 = "\u001B[38;5;202m";
  
  /** Tom laranja médio para chamas (código 166). */
  public static final String cordc2f02 = "\u001B[38;5;166m";
  
  /** Tom laranja-claro para pontas da chama (código 214). */
  public static final String corf48c06 = "\u001B[38;5;214m";
  
  /** Tom amarelo para centro da chama (código 220). */
  public static final String corfaa307 = "\u001B[38;5;220m";
  
  /* ==================== CORES PARA ANIMAÇÃO DE CHUVA ==================== */
  
  /** Azul Dodger (código 110). */
  public static final String cor1e90ff = "\u001B[38;5;110m";
  
  /** Azul claro (código 111). */
  public static final String corb3dbff = "\u001B[38;5;111m";
  
  /** Azul Royal (código 105). */
  public static final String cor4169e1 = "\u001B[38;5;105m";
  
  /** Azul DarkSlate (código 97). */
  public static final String cor483d8b = "\u001B[38;5;97m";
  
  /** Azul médio (código 20). */
  public static final String cor0000cd = "\u001B[38;5;20m";
  
  /** Azul meia-noite (código 56). */
  public static final String cor191970 = "\u001B[38;5;56m";
  
  /** Azul muito claro (código 189). */
  public static final String corcce7ff = "\u001B[38;5;189m";
  
  /** Azul céu claro (código 153). */
  public static final String cor87cefa = "\u001B[38;5;153m";
  
  /** Azul centáurea (código 105). */
  public static final String cor6495ed = "\u001B[38;5;105m";
  
  /** Azul marinho (código 17). */
  public static final String cor000080 = "\u001B[38;5;17m";
  
  /** Azul profundo (código 39). */
  public static final String cor00bfff = "\u001B[38;5;39m";
  
  /** Azul céu (código 81). */
  public static final String cor87ceeb = "\u001B[38;5;81m";
  
  /* ==================== MÉTODOS UTILITÁRIOS ==================== */
  
  /**
   * Imprime uma linha de texto colorida com quebra de linha automática.
   * 
   * @param cor o código de cor ANSI a ser aplicado
   * @param texto o texto a ser impresso
   */
  public static void cprintln(String cor, String texto) {
    System.out.println(cor + texto + Cores.ANSI_RESET);
  }

  /**
   * Imprime um texto colorido e força uma quebra de linha após o texto.
   * <p>Funcionalmente similar ao {@link #cprintln(String, String)}.</p>
   * 
   * @param cor o código de cor ANSI a ser aplicado
   * @param texto o texto a ser impresso
   */
  public static void cprintn(String cor, String texto) {
    System.out.print(cor + texto + Cores.ANSI_RESET + "\n");
  }

  /**
   * Imprime um texto colorido sem quebra de linha ao final.
   * 
   * @param cor o código de cor ANSI a ser aplicado
   * @param texto o texto a ser impresso
   */
  public static void cprint(String cor, String texto) {
    System.out.print(cor + texto + Cores.ANSI_RESET);
  }

  /**
   * Imprime um número inteiro colorido sem quebra de linha ao final.
   * 
   * @param cor o código de cor ANSI a ser aplicado
   * @param inteiro o número inteiro a ser impresso
   */
  public static void cprintInt(String cor, int inteiro) {
    System.out.print(cor + inteiro + Cores.ANSI_RESET);
  }
  
  /**
   * Imprime um número inteiro colorido com quebra de linha automática.
   * 
   * @param cor o código de cor ANSI a ser aplicado
   * @param inteiro o número inteiro a ser impresso
   */
  public static void cprintIntln(String cor, int inteiro) {
    System.out.println(cor + inteiro + Cores.ANSI_RESET);
  } 

  /**
   * Imprime um número inteiro colorido e força uma quebra de linha após o número.
   * <p>Funcionalmente similar ao {@link #cprintIntln(String, int)}.</p>
   * 
   * @param cor o código de cor ANSI a ser aplicado
   * @param inteiro o número inteiro a ser impresso
   */
  public static void cprintIntn(String cor, int inteiro) {
    System.out.print(cor + inteiro + Cores.ANSI_RESET + "\n");
  }
}