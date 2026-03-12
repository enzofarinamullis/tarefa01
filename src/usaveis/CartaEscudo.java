package usaveis;
import dados.Heroi;

public class CartaEscudo extends Cartas{

  public CartaEscudo(String nome, int escudo, int custoEnergia){
    this.nome = nome;
    this.escudo = escudo;
    this.custoEnergia = custoEnergia;
    this.ehDano = false;
    this.ehEscudo = true;
  }

  public void usar(Heroi heroi){
      heroi.ganharEscudo(this.escudo);
      System.out.println("O " + heroi.nome + " ganhou" + escudo + " de escudo.");
  }
}