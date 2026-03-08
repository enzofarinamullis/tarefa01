public class CartaEscudo{
  /* Atributos */
  public String nome;
  public int escudo;
  public int custoEnergia;

  /* Construtor */
  public CartaEscudo(String nome, int escudo, int custoEnergia){
    this.escudo = escudo;
    this.custoEnergia = custoEnergia;
  }

  /* Métodos */
  public void calcularEscudo(D20 d20){
    this.escudo = this.escudo + d20.rolarDado();
  }

  public void usar(Heroi heroi){
    if(heroi.temEnergia(this.custoEnergia)){
      heroi.ganharEscudo(this.escudo);
    }
  }
}
