package usaveis;
import dados.Inimigo;
import dados.Heroi;

public class CartaDano extends Cartas{

  public CartaDano(String nome, int custoEnergia, int nivel){
    this.nome = nome;
    this.custoEnergia = custoEnergia;
    this.nivel = nivel;
    this.ehDano = true;
    this.ehEscudo = false;
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
    else if (precisao >= 6 && precisao >= 10) {
      return potencia*2;
    }
    else if (precisao >= 11 && precisao <= 15) {
      return potencia*3;
    }
    else if (precisao >= 16 && precisao <= 19) {
      return potencia*4;
    }
    else {
      return potencia*6;
    }
  }


  /* Usar carta no inimigo ja pronta */
  @Override
  public void usar(Inimigo inimigo, Heroi heroi){
    if (heroi.temEnergia(custoEnergia)) {
      heroi.energia -= custoEnergia;
      int dano = calcularDano();
      inimigo.receberDano(dano);  
      System.out.println(dano + " causado em " + inimigo.nome + "!");
    }
    else{
      System.out.println("Energia Insuficiente!");
    }
  } 
} 