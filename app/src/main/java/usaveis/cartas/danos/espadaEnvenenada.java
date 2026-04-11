package usaveis.cartas.danos;

import usaveis.cartas.CartaDano;
import usaveis.cartas.efeitos.Envenenamento;

/**
 * Representa a carta de dano "Espada Envenenada" no jogo.
 * <p>
 *   A Espada Envenenada é uma carta de dano que causa 5 pontos de dano
 *   com um custo de 5 pontos de mana.
 *   </p>
 *   <p>
 *   Seu efeito adicional é o Envenenamento V, que causa pontos de dano
 *   baseados no nível 5, durante 5 turnos, durante 5 turnos.
 *   </p>
 *   @see usaveis.cartas.CartaDano
 *   @see usaveis.cartas.efeitos.Envenenamento
 */
public class espadaEnvenenada extends CartaDano{
  
  public espadaEnvenenada() {
    super("Espada Envenenada", 5,5, "Espada banhada em veneno de rato" );
    Envenenamento envenenamentoV = new Envenenamento("Envenenamento V", 5, 5, 5);
    adicionarEfeito(envenenamentoV);
  }
}

