package sistematurnos.batalhas;

import dados.Dados;
import dados.Inimigo;
import dados.inimigos.Slime;
import dados.inimigos.lesmaVenenosa;
import sistematurnos.Batalha;

public class BatalhaLesmasESlimes extends Batalha {
  int qntLesmas;
  int qntSlimes;
  public BatalhaLesmasESlimes(Dados dados, int qntLesmas, int qntSlimes){
    super(dados, "Batalha contra Lesmas e Slimes");
    this.qntLesmas = qntLesmas;
    this.qntSlimes = qntSlimes;
  }
  
  protected void adicionarInimigos(){
    Inimigo inimigo;
    for(int i = 0; i < qntLesmas; i++){
      inimigo = new lesmaVenenosa();
      dados.listaInimigos.adicionarInimigo(inimigo);
    }
    for(int i = 0; i < qntSlimes; i++){
      inimigo = new Slime();
      dados.listaInimigos.adicionarInimigo(inimigo);
    }
  }
}
