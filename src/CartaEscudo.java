public class CartaEscudo{
  /* Atributos */
  public String nome;
  public int escudo;

  /* Construtor */
  public CartaEscudo(String nome, int escudo, int custoEnergia){
    this.escudo = escudo;
  }

  public void usar(Heroi heroi){
      heroi.ganharEscudo(this.escudo);
      System.out.println("O " + heroi.nome + " ganhou" + escudo + "de escudo.");
  }
}
