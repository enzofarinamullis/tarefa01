package usaveis;
import constantes.Cores;
import dados.Heroi;

public class CartaEscudo extends Cartas{

  public CartaEscudo(String nome, int escudo, int custoEnergia){
    this.nome = nome;
    this.escudo = escudo;
    this.custoEnergia = custoEnergia;
    this.ehDano = false;
    this.ehEscudo = true;
  }

  @Override
  public void usarEscudo(Heroi heroi){
      heroi.ganharEscudo(this.escudo);
      heroi.energia = heroi.energia - custoEnergia;
  }
}