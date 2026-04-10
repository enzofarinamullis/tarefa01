package usaveis.escudos;

import usaveis.cartas.CartaEscudo;
/**
 * Representa a carta de escudo "Escudo Pequeno" no jogo.
 * <p>
 * O Escudo Pequeno é um item de defesa minimalista que oferece proteção
 * básica com custo reduzido. Apesar de seu tamanho modesto e aparência
 * questionável ("estranhamente pequeno"), este escudo cumpre seu papel
 * quando o recurso de mana está escasso.
 * </p>
 * 
 * <p><b>Características da carta:</b></p>
 * <ul>
 *   <li><b>Defesa Base:</b> 1 ponto</li>
 *   <li><b>Custo de Mana:</b> 1 ponto</li>
 *   <li><b>Efeitos Especiais:</b> Nenhum</li>
 * </ul>
 * 
 * <p><b>Quando usar:</b></p>
 * <ul>
 *   <li>🛡️ Quando você está com pouca mana no início do jogo</li>
 *   <li>🛡️ Quando precisa de qualquer proteção, mesmo que mínima</li>
 *   <li>🛡️ Como um escudo temporário até conseguir algo melhor</li>
 * </ul>
 * 
 * <p><b>Nota de design:</b> O Escudo Pequeno é a contraparte defensiva da
 * Espada Curta - ambos são itens de entrada com baixo custo e baixo
 * benefício, perfeitos para personagens iniciantes ou situações de
 * poucos recursos.</p>
 * 
 * <p><b>Curiosidade:</b> Dizem que este escudo é tão pequeno que mal cobre
 * o punho do usuário. Alguns guerreiros o utilizam mais como bracelete
 * do que como escudo propriamente dito.</p>
 * @see usaveis.cartas.CartaEscudo
 */

public class escudoPequeno extends CartaEscudo {
  public escudoPequeno() {
    super("Escudo Pequeno", 1, 1, "Um escudo estranhamente pequeno");
  }
}
