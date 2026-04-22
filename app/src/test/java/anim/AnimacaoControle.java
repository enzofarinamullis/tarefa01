package anim;

import java.util.List;

public class AnimacaoControle extends Animacao{
  public List<String> linhasImpressas;
  
  /* Criamos uma animacao controle para verificarmos se a animacao esta imprimindo as linhas corretamente */
  public AnimacaoControle(String caminho) {
    super(caminho);
  }
  
  /* queremos uma forma de verificar se a animacao esta imprimindo as linhas corretamente */
  @Override
  public void imprimeLinha(String linha) {
    linhasImpressas.add(linha);
  }
}
