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

 /* Carregamento da Cena */
  @Override
  public void carregaCena(){
    imprimeArquivo();
  }

  public void atualizaCena(){
  }
}