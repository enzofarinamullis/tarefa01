// dinâmica de ataque:
// 1-Declara o ataque
// 2-roda d20 + bonus
// 3-se romper o escudo é aplicado o efeito
//obs: só pode um ataque por turno

class CartaDano{
  public String nome;
  public int custoEnergia;
  public int forca;

  public CartaDano(String nome, int custoEnergia, int forca) {
    this.nome = nome;
    this.custoEnergia = custoEnergia;
    this.forca = forca;
  }
  public boolean temEnergia(Heroi heroi, int custoCarta){
    return energiaHeroi >= custoCarta;
  }

  public void calcularDano() {

  }


  public void usar(Inimigo inimigo){
    inimigo.receberDano(this.dano);
  }


  


}
