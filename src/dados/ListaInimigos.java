package dados;

public class ListaInimigos {
  public NoInimigo noInimigo;
  public int  qntInimigos;

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
    int indice = 0;
    for(int i = 0; i < qntInimigos; i++){
      indice = i + 1;
      System.out.print(indice + " ");
      noMovel.inimigo.printStats();
      noMovel = noMovel.prox;
    }
  }

  public void printInimigosSemIndice(){
    NoInimigo atual = noInimigo;
    for(int i = 0; i < qntInimigos; i++){
      atual.inimigo.printStats();
      atual = atual.prox;
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

  public void removerInimigo(Inimigo inimigoRemover){
    NoInimigo noMovel = this.noInimigo;
    NoInimigo noAnterior = null;
    for(int i = 0; i < qntInimigos; i++){
      noMovel = noMovel.prox;
      if(noMovel.inimigo == inimigoRemover){
        break;
      }
      else{
        noAnterior = noMovel;
      }
    }

    /* ant->prox -> prox */
    if(noAnterior != null){
      noAnterior.prox = noMovel.prox;
      this.qntInimigos--;
    }
    else{
      this.noInimigo = null;
      this.qntInimigos--;
    }
  }
}
