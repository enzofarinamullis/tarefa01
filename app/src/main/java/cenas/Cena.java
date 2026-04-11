package cenas;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

import dados.Dados;

/**
 * Classe abstrata que representa uma cena do jogo.
 * 
 * <p>Uma cena é uma unidade visual e interativa do jogo, como um menu,
 * uma tela de batalha, um diálogo ou uma animação. Esta classe fornece
 * a estrutura base para todas as cenas do sistema.</p>
 * 
 * <p>Responsabilidades principais:</p>
 * <ul>
 *   <li>Gerenciar o ciclo de vida da cena (carregamento, atualização, renderização)</li>
 *   <li>Fornecer métodos para leitura de arquivos de texto (como arte ASCII)</li>
 *   <li>Manter referência aos dados centrais do jogo</li>
 *   <li>Controlar o frame atual da animação da cena</li>
 * </ul>
 * 
 * <p>O ciclo de vida de uma cena geralmente segue:</p>
 * <ol>
 *   <li>Instanciação da cena (construtor)</li>
 *   <li>Chamada a {@link #renderizaCena()} para exibir a cena</li>
 *   <li>Atualizações periódicas via {@link #atualizaCena()}</li>
 *   <li>O descarregamento (unload) é gerenciado automaticamente pelo GC do Java</li>
 * </ol>
 */
public abstract class Cena {
  
  /**
   * Nome identificador da cena.
   */
  String nome;
  
  /**
   * Dados centrais do jogo, contendo referências ao herói, inimigos, etc.
   */
  Dados dados;
  
  /**
   * Arquivo associado à cena (pode conter arte ASCII ou configurações).
   */
  File file;
  
  /**
   * Scanner para leitura de arquivos de texto da cena.
   */
  Scanner leitor;
  
  /**
   * Número do frame atual da animação da cena.
   */
  int frame;

  /**
   * Construtor padrão que inicializa a cena.
   * 
   * <p>Serve como método base para carregar a cena.
   * As subclasses devem estender este construtor para
   * inicializar seus recursos específicos.</p>
   */
  public Cena() {
  }

  /**
   * Atualiza o estado da cena.
   * 
   * <p>Este método deve ser chamado periodicamente para atualizar
   * a lógica da cena, como animações, movimentação de elementos
   * ou verificação de condições de transição.</p>
   * 
   * <p>Por padrão, não realiza nenhuma operação. As subclasses
   * devem sobrescrever este método conforme necessário.</p>
   */
  public void atualizaCena() {}
  
  /**
   * Renderiza (desenha) a cena na tela.
   * 
   * <p>Este método é responsável por exibir visualmente a cena
   * no terminal, incluindo arte ASCII, textos, menus, etc.</p>
   * 
   * <p>Por padrão, não realiza nenhuma operação. As subclasses
   * devem sobrescrever este método obrigatoriamente.</p>
   */
  public void renderizaCena() {}
  
  /**
   * Carrega um scanner para leitura de um arquivo a partir do classpath.
   * 
   * <p>Utiliza {@link Class#getResourceAsStream(String)} para acessar
   * arquivos dentro do JAR ou diretório de recursos do projeto.</p>
   * 
   * @param caminho caminho relativo do arquivo dentro do classpath
   * @return Scanner configurado para leitura do arquivo, ou {@code null}
   *         se o arquivo não for encontrado
   */
  public Scanner carregaLeitor(String caminho) {
    InputStream entrada = getClass().getResourceAsStream(caminho);
    if(entrada == null) {
      System.out.println("Arquivo nao encontrado");
      return null;
    }
    
    return new Scanner(entrada);
  }
  
  /**
   * Imprime o conteúdo do arquivo carregado no scanner, linha por linha.
   * 
   * <p>Este método processa o arquivo de forma especial, utilizando
   * vírgula (",") como delimitador de seções ou pausas na impressão.
   * A cada vírgula encontrada, o frame da cena é incrementado.</p>
   * 
   * <p>Comportamento do método:</p>
   * <ul>
   *   <li>Lê e imprime linhas até encontrar uma linha contendo apenas ","</li>
   *   <li>Ao encontrar ",", avança para a próxima linha e incrementa o frame</li>
   *   <li>Continua a impressão até o final do arquivo</li>
   * </ul>
   * 
   * <p><b>Nota:</b> Este método depende que o {@link #leitor} já esteja
   * inicializado e apontando para um arquivo válido.</p>
   * 
   * @see #carregaLeitor(String)
   */
  public void imprimeArquivo() {
    String linha = "-";
    if(leitor.hasNextLine()) {
      linha = leitor.nextLine();
    }
    for(; leitor.hasNextLine();) {
      while(!linha.equals(",") && leitor.hasNextLine()) {
        System.out.println(linha);
        linha = leitor.nextLine();
      }
      /* caso o leitor achou o final da linha */
      if(linha.equals(",")) {
        linha = leitor.nextLine();
      }
      this.dados.frame++;
    }
  }
}