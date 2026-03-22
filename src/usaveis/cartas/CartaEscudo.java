package usaveis.cartas;
import dados.Heroi;
import dados.Inimigo;
import java.util.ArrayList;

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

  public void usar(Inimigo inimigo, Heroi heroi){
      heroi.ganharEscudo(this.escudo);
      heroi.setaEnergia(heroi.getEnergia() - custoEnergia);
      for (int i = 0; i < efeitos.size(); i++) {
        efeitos.get(i).aplicar(heroi);
      }
  }
}