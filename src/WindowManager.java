public class WindowManager{
  /* O WindowManager servira apenas para trocarmos entre as cenas */
  Cena cenaAtual;
  /* Construtor */

  public WindowManager(Cena cenaAtual){
    this.cenaAtual = cenaAtual;
  }

  public void limpaTerminal(){
    for(int i = 0; i < 100; i++){
      System.out.println("\n");
    }
  }

  public void trocarCena(Cena cenaProx){
    limpaTerminal();
    this.cenaAtual = cenaProx;
  }
}