package cenas;
import dados.Dados;
import dados.Inimigo;
import dados.ListaInimigos;

public class Slime extends Cena {
  
  public Slime(Dados dados) {
    this.leitor = carregaLeitor("src/imagemSlime.txt");
    this.nome = "Slime Selvagem";
    Inimigo slime = new Inimigo("Slime Selvagem", 30, 10, 1);
    dados.listaInimigos.adicionarInimigo(slime);
  }

 /* Renderiza a Cena */
  @Override
  public void renderizaCena(){
    imprimeArquivo();
  }
  
  /* Atualiza a Cena */
  public void atualizaCena(){
  }
}