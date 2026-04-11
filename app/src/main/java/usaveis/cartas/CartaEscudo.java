package usaveis.cartas;
import dados.Heroi;
import dados.Inimigo;
import java.util.ArrayList;

/**
 * Representa uma carta de defesa que concede escudo ao herói.
 *
 * <p>
 *   Ao ser utilizada, a carta aumenta o escudo do herói e pode aplicar
 *   efeitos adicionais, quando existem.
 * </p>
 *
 * <p>
 *   O uso da carta consome energia do herói.
 * </p>
 *
 * <p>
 *   Exemplo de uso:<br>
 *   CartaEscudo escudo = new CartaEscudo("Barreira Mágica", 5, 2, "Concede um escudo mágico que absorve dano.");<br
 *   escudo.usar(null, heroi);<br>
 */
public class CartaEscudo extends Carta{

  public CartaEscudo(String nome, int escudo, int custoEnergia, String descricao){
    setNome(nome);
    this.escudo = escudo;
    this.custoEnergia = custoEnergia;
    this.ehDano = false;
    this.ehEscudo = true;
    this.descricao = descricao;
    this.efeitos = new ArrayList<>();
  }
  
  /**
   * Usa a carta de escudo, concedendo escudo ao herói e aplicando efeitos adicionais, se houver.
   * O uso da carta consome energia do herói.
   * <p>
   *   Se o herói não tiver energia suficiente, a carta não será usada e uma
   *   mensagem de aviso será exibida.
   * </p>
   * @param inimigo - O inimigo alvo da carta (pode ser null se a carta não afetar o inimigo diretamente)
   * @param heroi - O herói que usará a carta e receberá o escudo
   */
  public void usar(Inimigo inimigo, Heroi heroi) {
    if (heroi.getEnergia() >= custoEnergia) {
      heroi.ganharEscudo(this.escudo);
      heroi.setaEnergia(heroi.getEnergia() - custoEnergia);
    } else {
      System.out.println("Energia insuficiente para usar " + getNome() + "!");
    }
  }
}