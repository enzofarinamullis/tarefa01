package sistematurnos.fogueira;

import dados.Dados;

public class FogueiraBasica extends Fogueira{
  public FogueiraBasica(Dados dados, String nome){
    super(dados, nome);
  }
  protected int getVidaRecuperar(){
    return 10;
  }
}
