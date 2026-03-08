public class WindowManager{
  /* O WindowManager servira apenas para trocarmos entre as cenas */
  Cena cenaAtual;
  /* Construtor */

  public WindowManager(Cena cenaAtual){
    this.cenaAtual = cenaAtual;
  }

  public void trocarCena(Cena atual, Cena cenaProx){
    cenaAtual = cenaProx;
  }
}