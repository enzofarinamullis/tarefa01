public class ListaInimigos {
  NoInimigo noInimigo;
  int  qntInimigos;

  public ListaInimigos(){
    this.noInimigo = null;
    qntInimigos = 0;
  }

  public void adicionarInimigo(Inimigo inimigoAdicionar){
    if(qntInimigos == 0){
      noInimigo = new NoInimigo(inimigoAdicionar);
      qntInimigos++;
    }

    else{
      NoInimigo noMovel;
      noMovel = noInimigo;
      for(int i = 1; i < qntInimigos; i++){
        noMovel = noMovel.prox;
      }
      /* achamos o ultimo elemento */
      NoInimigo proxNo = new NoInimigo(inimigoAdicionar);
      noMovel.prox = proxNo;
      qntInimigos++;
    }
  }

  public void mostrarInimigos(){
    NoInimigo noMovel = noInimigo;
    for(int i = 0; i < qntInimigos; i++){
      int indice = i + 1;
      System.out.println(indice + " - " + noMovel.inimigo.nome + " HP = " + noMovel.inimigo.vida + " DMG = " + noMovel.inimigo.dano);
      noMovel = noMovel.prox;
    }
  }

  public Inimigo buscarInimigo(int numero){
    NoInimigo noMovel;
    noMovel = noInimigo;
    int indice = numero - 1;
    for(int i = 0; i < indice; i++){
      noMovel = noMovel.prox;
    }
    /* achamos o inimigo */
    return noMovel.inimigo;
  }
}
