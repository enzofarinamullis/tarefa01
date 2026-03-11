public class CartaEscudo{
  /* Atributos */
  public String nome;
  public int escudo;
  public int custoEnergia;

  /* Construtor */
  public CartaEscudo(String nome, int escudo, int custoEnergia){
    this.nome = nome;
    this.escudo = escudo;
    this.custoEnergia = custoEnergia;
  }

  public void usar(Heroi heroi){
      heroi.ganharEscudo(this.escudo);
      System.out.println("O " + heroi.nome + " ganhou" + escudo + "de escudo.");
  }
}
