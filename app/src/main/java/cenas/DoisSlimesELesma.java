package cenas;

import dados.Dados;
import dados.Inimigo;

/**
 * Representa a cena de batalha contra dois Slimes Selvagens e uma Lesma Venenosa.
 * 
 * <p>Esta cena configura o primeiro encontro de combate do jogo, onde o herói
 * enfrenta múltiplos inimigos simultaneamente.</p>
 * 
 * <p>Composição dos inimigos:</p>
 * <ul>
 *   <li>2x {@link dados.inimigos.Slime} - Inimigos básicos com habilidades padrão</li>
 *   <li>1x {@link dados.inimigos.lesmaVenenosa} - Inimigo especial com efeito de veneno</li>
 * </ul>
 * 
 * <p>Esta classe estende {@link Cena} e implementa a configuração específica
 * desta batalha, adicionando os inimigos à lista de inimigos do jogo.</p>
 * @see Cena
 * @see dados.Dados
 * @see dados.Inimigo
 * @see dados.inimigos.Slime
 * @see dados.inimigos.lesmaVenenosa
 */
public class DoisSlimesELesma extends Cena {
  
  /**
   * Construtor que inicializa a cena com dois Slimes e uma Lesma Venenosa.
   * 
   * <p>Configura o nome da cena e adiciona os inimigos à lista de inimigos
   * contida no objeto {@link Dados}.</p>
   * 
   * <p><b>Nota:</b> A adição dos dois Slimes está atualmente comentada.
   * Para ativar a batalha completa, descomente as linhas correspondentes.</p>
   * 
   * @param dados objeto central do jogo contendo a lista de inimigos
   */
  public DoisSlimesELesma(Dados dados) {
    this.nome = "Dois Slimes Selvagens e uma Lesma Venenosa";
    this.dados = dados;
    
    // Código para adicionar os Slimes (atualmente comentado)
    for(int i = 0; i < 2; i++) {
      // Inimigo slime = new dados.inimigos.Slime();
      // dados.listaInimigos.adicionarInimigo(slime);
    }
    
    // Adiciona a Lesma Venenosa à lista de inimigos
    Inimigo lesma = new dados.inimigos.lesmaVenenosa();
    dados.listaInimigos.adicionarInimigo(lesma);
  }

  /**
   * Atualiza o estado da cena.
   * 
   * <p>Este método é chamado periodicamente para atualizar a lógica da cena.
   * Atualmente não realiza nenhuma operação específica, mas pode ser
   * estendido no futuro para animações ou transições.</p>
   * 
   * <p>Possíveis usos futuros:</p>
   * <ul>
   *   <li>Animação de entrada dos inimigos</li>
   *   <li>Diálogo introdutório da batalha</li>
   *   <li>Efeitos visuais no ambiente</li>
   * </ul>
   */
  @Override
  public void atualizaCena() {
    // Implementação futura para animações e transições
  }
}