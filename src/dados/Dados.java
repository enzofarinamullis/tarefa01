package dados;
public class Dados {
  /* Nesta classe de dados, salvaremos todos os dados necessarios */
  /* para uma Cena, ou seja... qntInimigos, e o proprio Heroi que */
  /* estara em todas as cenas */

  // para adicionar os inimigos, podemos trabalhar com uma lista!
  // onde cada inimigo sera um elemento da lista!
  public Heroi heroi;
  public ListaInimigos listaInimigos;
  
  public int frame;

  public Dados(Heroi heroi){
    this.heroi = heroi;
    this.listaInimigos = null;
    this.frame = 0;
  }

}
