package usaveis;
import dados.Heroi;
import dados.Inimigo;

public class CartaEscudo extends Cartas{

  public CartaEscudo(String nome, int escudo, int custoEnergia, String descricao){
    setNome(nome);
    this.escudo = escudo;
    this.custoEnergia = custoEnergia;
    this.ehDano = false;
    this.ehEscudo = true;
    this.descricao = descricao;
  }

  public void usar(Inimigo inimigo, Heroi heroi){
      heroi.ganharEscudo(this.escudo);
      heroi.setaEnergia(heroi.getEnergia() - custoEnergia);
  }
}