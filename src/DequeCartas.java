public class DequeCartas {
  String tipo;
  int qntCartas;
  NoCartas no;
  
  public DequeCartas(String tipo){
    this.tipo = tipo;
    this.no = new NoCartas(tipo);
    this.no.prox = null;
    this.qntCartas = 0;
  }

  public void adicionaCartaDano(CartaDano cartaDano){
    NoCartas noMovel;
    /* iniciamos no no raiz */
    noMovel = this.no;
    for(int i = 0; i < this.qntCartas; i++){
      noMovel = noMovel.prox;
    }
    NoCartas noAdicionar = new NoCartas(this.tipo);
    /* atribuimos a carta a o no */
    noAdicionar.atribuiCartaDano(cartaDano); 
    /* apontamos as referencias para termos uma lista ligada */
    noMovel.prox = noAdicionar;
    noAdicionar.ant = noMovel;
  }

  public void adicionaCartaEscudo(CartaEscudo cartaEscudo){
    NoCartas noMovel;
    noMovel = this.no;
    for(int i = 0; i < this.qntCartas; i++){
      noMovel = noMovel.prox;
    }
    NoCartas noAdicionar = new NoCartas(this.tipo);
    /* atribuimos a carta ao no */
    noAdicionar.atribuiCartaEscudo(cartaEscudo);
    /* apontamos as referencias */
    noMovel.prox = noAdicionar;
    noAdicionar.ant = noMovel;
    /* apontamos o primeiro no.ant para o ultimo adicionado */
    no.ant = noAdicionar;
    /* e o ultimo.prox pro primeiro */
    noAdicionar.prox = no;

  }

  public void removeCarta(int numero){
    NoCartas noMovel;
    if(numero < 1 && numero > qntCartas){
      System.out.println("Carta Inválida");
      return;
    }

    noMovel = this.no;
    for(int i = 1; i < numero; i++){
      noMovel = no.prox;
    }

    /* estamos no Nó que queremos remover */
    /* apontamos as referencias */
    /* ant */
    noMovel.ant.prox = noMovel.prox;
    noMovel.prox.ant = noMovel.ant;

    /* removemos o no */
    this.qntCartas--;
  }

  public void mostrarCartaEscudo(){
    NoCartas noMovel = no;
    CartaEscudo carta;
    for(int i = 0; i < qntCartas; i++){
      carta = noMovel.cartaEscudo;
      System.out.println(carta.nome + ": SHD: " + carta.escudo + " ENG: " + carta.custoEnergia);
      noMovel = noMovel.prox;
    }
  }

  public void mostrarCartaDano(){
    NoCartas noMovel = no;
    CartaDano carta;
    for(int i = 0; i < qntCartas; i++){
      carta = noMovel.cartaDano;
      System.out.println(carta.nome + ": DMG: " + carta.nivel + " ENG: " + carta.custoEnergia);
      noMovel = noMovel.prox;
    }
  }

  public CartaDano selecionarCartaDano(int numero){
    NoCartas noMovel = this.no;
    int indice = numero - 1;
    for(int i = 0; i < indice; i++){
      noMovel = no.prox;
    }
    return noMovel.cartaDano; 
  }

  public CartaEscudo selecionarCartaEscudo(int numero){
    NoCartas noMovel = this.no;
    int indice = numero - 1;
    for(int i = 0; i < indice; i++){
      noMovel = no.prox;
    }
    return noMovel.cartaEscudo;
  }
}
