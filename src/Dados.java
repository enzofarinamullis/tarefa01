public class Dados {
  /* Nesta classe de dados, salvaremos todos os dados necessarios */
  /* para uma Cena, ou seja... qntInimigos, e o proprio Heroi que */
  /* estara em todas as cenas */

  // para adicionar os inimigos, podemos trabalhar com uma lista!
  // onde cada inimigo sera um elemento da lista!
  Heroi heroi;
  int qntInimigos;
  int frame;

  public Dados(Heroi heroi, int qntInimigos){
    this.heroi = heroi;
    this.qntInimigos = qntInimigos;
    this.frame = 0;
  }
}
