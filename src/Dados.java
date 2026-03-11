public class Dados {
  /* Nesta classe de dados, salvaremos todos os dados necessarios */
  /* para uma Cena, ou seja... qntInimigos, e o proprio Heroi que */
  /* estara em todas as cenas */

  // para adicionar os inimigos, podemos trabalhar com uma lista!
  // onde cada inimigo sera um elemento da lista!
  Heroi heroi;
  ListaInimigos listaInimigos;
  
  int frame;

  public Dados(Heroi heroi){
    this.heroi = heroi;
    this.listaInimigos = null;
    this.frame = 0;
  }

}
