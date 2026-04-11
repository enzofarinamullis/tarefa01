package sistematurnos;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dados.Dados;
import dados.Inimigo;
import dados.ListaInimigos;

/**
 * Interface gráfica textual para exibição da batalha no terminal.
 * 
 * <p>Esta classe é responsável por renderizar os inimigos em formato ASCII art
 * durante as batalhas, proporcionando uma experiência visual mais imersiva
 * mesmo em ambiente de terminal.</p>
 * 
 * <p>Características principais:</p>
 * <ul>
 *   <li>Exibe inimigos em arte ASCII 8x16 pixels</li>
 *   <li>Suporte para exibição de até 2 inimigos por linha</li>
 *   <li>Exibe estatísticas (nome, vida, escudo) abaixo de cada inimigo</li>
 *   <li>Formatação alinhada para múltiplos inimigos</li>
 * </ul>
 * @see Dados
 * @see Inimigo
 */
public class InterfaceBatalha {
  
  /**
   * Quantidade total de inimigos na batalha atual.
   */
  private int qntInimigos;
  
  /**
   * Número máximo de inimigos exibidos por linha (fixo em 2).
   * (Campo reservado para futura implementação de responsividade)
   */
  private int inimigoPorLinha;
  
  /**
   * Contador de linhas processadas durante a impressão.
   */
  private int numLinha;
  
  /**
   * Scanner para leitura dos arquivos de arte ASCII.
   */
  private Scanner leitor;
  
  /**
   * Dados centrais do jogo contendo a lista de inimigos.
   */
  private Dados dados;
  
  /**
   * Construtor que inicializa a interface com os dados do jogo.
   * 
   * <p>Configura a exibição para mostrar no máximo 2 inimigos por linha,
   * o que proporciona um tamanho adequado para visualização no terminal.</p>
   * 
   * @param dados objeto contendo as informações centrais do jogo
   */
  public InterfaceBatalha(Dados dados) {
    this.dados = dados;
    this.qntInimigos = dados.listaInimigos.getTamanho();
    this.inimigoPorLinha = 2;
    this.numLinha = 0;
  }
  
  /**
   * Carrega o arquivo de arte ASCII de um inimigo.
   * 
   * @param inimigo inimigo cuja arte será carregada
   * @return Scanner configurado para ler o arquivo, ou {@code null} se o arquivo não for encontrado
   */
  private Scanner carregaLeitor(Inimigo inimigo) {
    String caminho = inimigo.getCaminho();
    if (caminho == null || caminho.isEmpty()) {
      System.out.println("Caminho do arquivo ASCII não configurado para: " + inimigo.getNome());
      return null;
    }
    
    InputStream entrada = getClass().getResourceAsStream(caminho);
    if(entrada == null) {
      System.out.println("Arquivo não encontrado: " + caminho);
      return null;
    }
    return new Scanner(entrada);
  }
  
  /**
   * Imprime a arte ASCII de um único inimigo (8 linhas).
   * 
   * @param inimigo inimigo a ser exibido
   */
  protected void imprimeUmInimigo(Inimigo inimigo) {
    Scanner scanner = carregaLeitor(inimigo);
    if (scanner == null) {
      System.out.println("Não foi possível carregar a arte do inimigo: " + inimigo.getNome());
      return;
    }
    
    String linha;
    for(int i = 0; i < 8; i++) {
      if(scanner.hasNextLine()) {
        linha = scanner.nextLine();
        System.out.println(linha);
      }
      numLinha++;
    }
    scanner.close(); // Fecha o scanner para evitar vazamento de recurso
  }
  
  /**
   * Carrega todas as linhas da arte ASCII de um inimigo em uma lista.
   * 
   * @param inimigo inimigo cuja arte será carregada
   * @return lista contendo todas as linhas da arte ASCII, ou lista vazia se o arquivo não for encontrado
   */
  private List<String> carregaASCII(Inimigo inimigo) {
    List<String> ASCII = new ArrayList<>();
    Scanner scanner = carregaLeitor(inimigo);
    
    if (scanner == null) {
      return ASCII; // Retorna lista vazia
    }
    
    while(scanner.hasNextLine()) {
      ASCII.add(scanner.nextLine());
    }
    scanner.close(); // Fecha o scanner para evitar vazamento de recurso
    return ASCII;
  }
  
  /**
   * Imprime dois inimigos lado a lado no terminal.
   * 
   * <p>As artes ASCII de ambos os inimigos são combinadas linha por linha,
   * permitindo a exibição simultânea dos dois.</p>
   * 
   * @param inimigo1 primeiro inimigo (à esquerda)
   * @param inimigo2 segundo inimigo (à direita)
   */
  protected void imprimeDoisInimigos(Inimigo inimigo1, Inimigo inimigo2) {
    System.out.println();
    List<String> ASCIIInimigo1 = carregaASCII(inimigo1);
    List<String> ASCIIInimigo2 = carregaASCII(inimigo2);
    
    int numLinhas = 8;
    
    for(int i = 0; i < numLinhas; i++) {
      String linha1 = i < ASCIIInimigo1.size() ? ASCIIInimigo1.get(i) : "";
      String linha2 = i < ASCIIInimigo2.size() ? ASCIIInimigo2.get(i) : "";
      
      System.out.println(linha1 + linha2);
    }
  }
  
  /**
   * Imprime todos os inimigos da batalha no terminal.
   * 
   * <p>O método organiza os inimigos em grupos de até 2 por linha,
   * exibindo tanto a arte ASCII quanto as estatísticas (nome, vida, escudo).</p>
   */
  protected void imprimeTodosInimigos() {
    ListaInimigos listaInimigos = dados.listaInimigos;
    int inimigosRestante = listaInimigos.getTamanho();
    
    if (inimigosRestante == 0) {
      System.out.println("Nenhum inimigo para exibir.");
      return;
    }
    
    for(int i = 0; i < listaInimigos.getTamanho(); i += 2) {
      if(inimigosRestante == 1) {
        Inimigo inimigo1 = listaInimigos.buscarInimigo(i + 1);
        imprimeUmInimigo(inimigo1);
        printaHeaderUmInimigo(inimigo1);
      } else {
        Inimigo inimigo1 = listaInimigos.buscarInimigo(i + 1);
        Inimigo inimigo2 = listaInimigos.buscarInimigo(i + 2);
        imprimeDoisInimigos(inimigo1, inimigo2);
        printaHeaderDoisInimigos(inimigo1, inimigo2);
      }
      inimigosRestante = inimigosRestante - 2;
    }
    System.out.println();
  }
  
  /**
   * Exibe as estatísticas de um único inimigo abaixo de sua arte ASCII.
   * 
   * @param inimigo inimigo cujas estatísticas serão exibidas
   */
  private void printaHeaderUmInimigo(Inimigo inimigo) {
    int vida = inimigo.getVida();
    int escudo = inimigo.getEscudo();
    String nome = inimigo.getNome();
    
    System.out.println("Nome: " + nome);
    System.out.println("Vida: " + vida);
    System.out.println("Escudo: " + escudo);
  }
  
  /**
   * Imprime um número específico de espaços em branco.
   * Utilizado para alinhamento das estatísticas na exibição lado a lado.
   * 
   * @param qnt quantidade de espaços a serem impressos
   */
  private void imprimeEspacos(int qnt) {
    for(int i = 0; i < qnt; i++) {
      System.out.print(" ");
    }
  }
  
  /**
   * Exibe as estatísticas de dois inimigos lado a lado com formatação alinhada.
   * 
   * <p>As estatísticas exibidas incluem nome, vida e escudo para ambos os inimigos,
   * com alinhamento calculado dinamicamente baseado no tamanho dos textos e números.</p>
   * 
   * @param inimigo1 primeiro inimigo (à esquerda)
   * @param inimigo2 segundo inimigo (à direita)
   */
  private void printaHeaderDoisInimigos(Inimigo inimigo1, Inimigo inimigo2) {
    String nome1 = inimigo1.getNome();
    String nome2 = inimigo2.getNome();
    
    int vida1 = inimigo1.getVida();
    int vida2 = inimigo2.getVida();
    
    int escudo1 = inimigo1.getEscudo();
    int escudo2 = inimigo2.getEscudo();
    
    System.out.print("Nome: ");
    imprimeEspacos(10);
    System.out.print("Nome:\n");
    
    int qntEspacos = 16 - nome1.length();
    System.out.print(nome1);
    imprimeEspacos(Math.max(qntEspacos, 1));
    System.out.print(nome2 + "\n");
    
    System.out.print("Vida: ");
    imprimeEspacos(10);
    System.out.print("Vida:\n");
    
    int digitosVida1 = String.valueOf(vida1).length();
    System.out.print(vida1);
    imprimeEspacos(Math.max(16 - digitosVida1, 1));
    System.out.print(vida2 + "\n");
    
    System.out.print("Escudo: ");
    imprimeEspacos(8);
    System.out.print("Escudo:\n");
    
    int digitosEscudo = String.valueOf(escudo1).length();
    System.out.print(escudo1);
    imprimeEspacos(Math.max(16 - digitosEscudo, 1));
    System.out.print(escudo2 + "\n");
  }
}