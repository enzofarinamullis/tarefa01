package cenas;

import constantes.Cores;
import dados.Dados;

/**
 * Representa a cena do logotipo do jogo "Sangue e Runas".
 * 
 * <p>Esta classe é responsável por exibir a animação do logotipo do jogo
 * no início da aplicação, utilizando arte ASCII com efeitos de cores
 * e transições entre diferentes paletas de cores.</p>
 * 
 * <p>O logotipo possui 3 frames de animação, cada um com uma paleta
 * de cores diferente:</p>
 * <ul>
 *   <li><b>Frame 0:</b> Paleta de sangue (tons avermelhados)</li>
 *   <li><b>Frame 1:</b> Paleta de musgo (tons esverdeados)</li>
 *   <li><b>Frame 2:</b> Paleta de runas (tons dourados/amarronzados)</li>
 * </ul>
 * 
 * <p>O arquivo de animação é carregado de "/animacoes/Cabecalho2.txt" e
 * utiliza caracteres especiais para mapear diferentes elementos visuais
 * (blocos, caracteres de arte ASCII) que são substituídos por cores
 * específicas durante a renderização.</p>
 * @see Cena
 * @see constantes.Cores
 */
public class Logo extends Cena {

  /**
   * Construtor que inicializa a cena do logotipo.
   * 
   * <p>Configura o nome da cena como "LOGO" e carrega o arquivo de animação
   * localizado em "/animacoes/Cabecalho2.txt".</p>
   * 
   * @param dados objeto central do jogo contendo informações do herói e estado geral
   */
  public Logo(Dados dados) {
    this.dados = dados;
    this.nome = "LOGO";
    this.leitor = carregaLeitor("/animacoes/Cabecalho2.txt");
  }

  /**
   * Renderiza a cena do logotipo, exibindo a animação do título.
   * 
   * <p>Este método invoca {@link #imprimeArquivo()} que processa e exibe
   * a animação completa com todos os frames e efeitos de cor.</p>
   */
  @Override
  public void renderizaCena() {
    imprimeArquivo();
  }

  /**
   * Atualiza o estado da cena após a exibição do logotipo.
   * 
   * <p>Exibe uma mensagem de boas-vindas personalizada com o nome do herói
   * e o título do jogo "Sangue e Runas" utilizando cores especiais.</p>
   */
  @Override
  public void atualizaCena() {
    System.out.println("Bem vindo, " + dados.heroi.getNome() + ", ao mundo de " +
      Cores.COR_SANGUE_2 + "Sangue" + Cores.COR_MUSGO_1 + " e " +
      Cores.COR_RUNA_1 + "Runas" + Cores.ANSI_RESET + "!");
  }

  /**
   * Processa e exibe o arquivo de animação do logotipo.
   * 
   * <p>Este método implementa a lógica completa de renderização da animação,
   * com suporte a múltiplos frames (0, 1 e 2) e mapeamento de caracteres
   * para diferentes cores conforme a paleta de cada frame.</p>
   * 
   * <p><b>Formato do arquivo de animação:</b></p>
   * <ul>
   *   <li>O caractere ';' no arquivo indica a mudança para o próximo frame</li>
   *   <li>Os números '1', '2', '3', '4' representam diferentes intensidades ou elementos</li>
   *   <li>Caracteres especiais (█, ▄, ▀, ▌, ▐, ▓, ▒, ░) são usados para compor a arte ASCII</li>
   *   <li>Qualquer outro caractere é exibido em verde</li>
   * </ul>
   * 
   * <p><b>Paletas de cores por frame:</b></p>
   * <ul>
   *   <li>Frame 0: Cores de sangue (COR_SANGUE_1, COR_SANGUE_2, COR_SANGUE_3, COR_SANGUE_4)</li>
   *   <li>Frame 1: Cores de musgo (COR_MUSGO_1, COR_MUSGO_3, COR_MUSGO_4)</li>
   *   <li>Frame 2: Cores de runas (COR_RUNA_1, COR_RUNA_3, COR_RUNA_4, COR_CIMENTO_1, COR_CIMENTO_2, COR_CIMENTO_3)</li>
   * </ul>
   * 
   * @see constantes.Cores
   */
  @Override
  public void imprimeArquivo() {
    String linha = "-";
    int comprimento;
    
    if(leitor.hasNextLine()) {
      linha = leitor.nextLine();
    }
    
    for(; leitor.hasNextLine();) {
      while(leitor.hasNextLine()) {
        if(linha.equals(";")) {
          frame++;
          linha = leitor.nextLine();
        }

        comprimento = linha.length();
        
        for(int i = 0; i < comprimento; i++) {
          if(frame == 0) {
            if(linha.charAt(i) == '1') {
              System.out.print(Cores.COR_SANGUE_1 + "█" + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '2') {
              System.out.print(Cores.COR_SANGUE_2 + "█" + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '3') {
              System.out.print(Cores.COR_CIMENTO_1 + '█' + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '4') {
              System.out.print(Cores.COR_CIMENTO_2 + '█' + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '█') {
              System.out.print(Cores.COR_CIMENTO_3 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '▄' || linha.charAt(i) == '▀' || linha.charAt(i) == '▐' || linha.charAt(i) == '▌') {
              System.out.print(Cores.COR_SANGUE_1 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '▓') {
              System.out.print(Cores.COR_SANGUE_2 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '▒') {
              System.out.print(Cores.COR_SANGUE_3 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '░') {
              System.out.print(Cores.COR_SANGUE_4 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else {
              System.out.print(Cores.ANSI_GREEN + linha.charAt(i) + Cores.ANSI_RESET);
            }
          }

          if(frame == 1) {
            if(linha.charAt(i) == '1') {
              System.out.print(Cores.COR_MUSGO_1 + "█" + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '2') {
              System.out.print(Cores.COR_MUSGO_1 + "▓" + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '3') {
              System.out.print(Cores.COR_CIMENTO_1 + '█' + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '4') {
              System.out.print(Cores.COR_CIMENTO_2 + '█' + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '█') {
              System.out.print(Cores.COR_CIMENTO_3 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '▄' || linha.charAt(i) == '▀' || linha.charAt(i) == '▐' || linha.charAt(i) == '▌') {
              System.out.print(Cores.COR_MUSGO_1 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '▓') {
              System.out.print(Cores.COR_MUSGO_1 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '▒') {
              System.out.print(Cores.COR_MUSGO_3 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '░') {
              System.out.print(Cores.COR_MUSGO_4 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else {
              System.out.print(Cores.ANSI_GREEN + linha.charAt(i) + Cores.ANSI_RESET);
            }
          }

          if(frame == 2) {
            if(linha.charAt(i) == '1') {
              System.out.print(Cores.COR_RUNA_1 + "█" + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '2') {
              System.out.print(Cores.COR_MUSGO_1 + "█" + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '3') {
              System.out.print(Cores.COR_CIMENTO_1 + "█" + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '4') {
              System.out.print(Cores.COR_CIMENTO_2 + "█" + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '█') {
              System.out.print(Cores.COR_CIMENTO_3 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '▄' || linha.charAt(i) == '▀' || linha.charAt(i) == '▐' || linha.charAt(i) == '▌') {
              System.out.print(Cores.COR_CIMENTO_2 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '▓') {
              System.out.print(Cores.COR_RUNA_1 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '▒') {
              System.out.print(Cores.COR_RUNA_3 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else if(linha.charAt(i) == '░') {
              System.out.print(Cores.COR_RUNA_4 + linha.charAt(i) + Cores.ANSI_RESET);
            }
            else {
              System.out.print(Cores.ANSI_GREEN + linha.charAt(i) + Cores.ANSI_RESET);
            }
          }
        }
        linha = leitor.nextLine();
        System.out.print("\n");
      }
    }
  }
}