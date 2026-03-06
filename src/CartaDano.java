<<<<<<< HEAD
// dinâmica de ataque:
// 1-Declara o ataque
// 2-roda d20 + bonus
// 3-se romper o escudo é aplicado o efeito
//obs: só pode um ataque por turno

class Carta_de_dano{
=======
public class CartaDano{
>>>>>>> refs/remotes/origin/main
  public String nome;
  public int custoEnergia;
  public int dano;

  public CartaDano(String nome, int custoEnergia) {
    this.nome = nome;
    this.custoEnergia = custoEnergia;
  }

  public void usar(Inimigo inimigo){
    inimigo.receberDano(this.dano);
  }


}
