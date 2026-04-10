package usaveis.cartas.danos;

import usaveis.cartas.CartaDano;
/**
 * Representa a carta de dano "Espada Média" no jogo.
 * <p>
 * A Espada Média é a definição de uma arma mediana e equilibrada. 
 * Não é muito curta nem muito longa, não causa muito dano nem pouco,
 * não é cara nem barata. É a escolha perfeita para jogadores que
 * não querem se preocupar com decisões complexas ou estratégias
 * elaboradas.
 * </p>
 * 
 * <p><b>Características da carta:</b></p>
 * <ul>
 *   <li><b>Dano Base:</b> 3 pontos</li>
 *   <li><b>Custo de Mana:</b> 3 pontos</li>
 *   <li><b>Efeitos Especiais:</b> Nenhum</li>
 * </ul>
 * 
 * <p><b>Filosofia de design:</b>
 * Esta carta existe para representar a arma mais genérica possível.
 * É o equivalente em armas a comer arroz sem tempero - faz o trabalho,
 * mas ninguém vai lembrar que estava ali. Perfeita para personagens
 * sem personalidade ou para momentos em que você simplesmente não quer
 * pensar.</p>
 * 
 * <p><b>Curiosidade:</b> Apesar do nome, não existe consenso histórico
 * sobre o que seria uma "espada média" - longas são para guerreiros,
 * curtas para ladinos, médias são para pessoas indecisas.</p>
 * 
 * @see usaveis.cartas.CartaDano
 */
public class espadaMedia extends CartaDano  {
  public espadaMedia() {
    super("Espada média", 3, 3, "Uma espada na média");
  }
}
