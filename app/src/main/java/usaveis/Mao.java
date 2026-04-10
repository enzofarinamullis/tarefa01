package usaveis;
import constantes.Cores;
import usaveis.cartas.Carta;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a mão de cartas do jogador.
 *
 * <p>
 *   Armazena as cartas disponíveis para o uso no turno atual,
 *   permitindo listagem e acesso às cartas.
 * </p>
 *
 * <p>
 *   Responsabilidades:<br>
 *    - Armazenar cartas atualmente na mão do jogador<br>
 *    - Fornecer método para exibir as cartas na mão<br>
 * </p>
 *
 * <p>
 *   Exemplo de uso:<br>
 *   Mao mao = new Mao();<br>
 *   mao.cartas.add(new CartaDano("Espada Curta", 10, 2, "Causa dano físico ao inimigo."));<br>
 *   mao.printMao();<br>
 */
public class Mao{
  public List<Carta> cartas;
  
  public Mao(){
    cartas = new ArrayList<>();
  }
  
  /**
   * Método para exibir as cartas atualmente na mão do jogador.
   */
  public void printMao(){
    if(cartas.isEmpty()){
      System.out.println("Mão vazia");
      return;
    }
    Carta atual;
    int num = 0;
    while(num != cartas.size()){
      atual = cartas.get(num);

      /* colocar funcoes printCarta em Carta */
      if(atual.isDano()){
        atual.info(num);
      }
      else if(atual.isEscudo()){
        atual.info(num);
      }
      num++;
    }
  }
}

