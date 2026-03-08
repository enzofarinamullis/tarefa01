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
  
  /* Implementacao simples de um calculo de dano */
  public int calcularDano(){
    D20 d20 = new D20();
    int valorDado = d20.rolarDado();

    if(valorDado > 3 && valorDado < 20){
      return valorDado * this.forca;
    }
    else if(valorDado == 20){
      return valorDado/5 * forca;
    }
    else{
      return 0;
    }
  }

  /* Usar carta no inimigo ja pronta */
  public void usar(Inimigo inimigo, Heroi heroi){
    if (heroi.temEnergia(custoEnergia)) {
      inimigo.receberDano(calcularDano());  
    }
  }


  


}
