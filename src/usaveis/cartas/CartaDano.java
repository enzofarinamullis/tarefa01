package usaveis.cartas;
import dados.Heroi;
import dados.Inimigo;
import java.util.ArrayList;
import usaveis.D.Potencia;
import usaveis.D.Precisao;


public class CartaDano extends Carta{

  public CartaDano(String nome, int custoEnergia, int nivel, String descricao){
    setNome(nome);
    this.custoEnergia = custoEnergia;
    this.nivel = nivel;
    this.ehDano = true;
    this.ehEscudo = false;
    this.descricao = descricao;
    this.efeitos = new ArrayList<>();
  }
  
  public int calcularDano(){
    Precisao d20 = new Precisao();
    Potencia dx = new Potencia(nivel);
    int precisao = d20.rolarDado();
    if (precisao == 1) {
      System.out.println("Errou o ataque!");
      return 0;
    }
    int potencia = dx.rolarDado();
    if (precisao >= 2 && precisao <= 5) {
      return potencia;
    }
    else if (precisao >= 6 && precisao <= 10) {
      return potencia * 2;
    }
    else if (precisao >= 11 && precisao <= 15) {
      return potencia * 4;
    }
    else if (precisao >= 16 && precisao <= 19) {
      return potencia * 16;
    }
    else {
      return potencia * 64;
    }
  }


  /* Usar carta no inimigo ja pronta */
  public void usar(Inimigo inimigo, Heroi heroi){
    if (heroi.temEnergia(custoEnergia)) {
      heroi.setaEnergia(heroi.getEnergia() - custoEnergia);
      int dano = calcularDano();
      int danoRecebido = dano - inimigo.getEscudo();
      inimigo.receberDano(dano);
      if (danoRecebido > 0) {
        System.out.println(dano + " causado em " + inimigo.getNome() + "!");
        if (!efeitos.isEmpty()) {
          for (int i = 0; i < efeitos.size(); i++) {
            efeitos.get(i).aplicar(inimigo);
          }
        }
      }
      else {
        System.out.println("0 causado em " + inimigo.getNome() + "!");
      }
    }
    else{
      System.out.println("Energia Insuficiente!");
    }
  } 
}

