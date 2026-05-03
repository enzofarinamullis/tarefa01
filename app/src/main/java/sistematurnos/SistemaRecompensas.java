package sistematurnos;

import dados.Dados;
import sistematurnos.interfaces.RecompensaTemplate;

public class SistemaRecompensas {
  RecompensaTemplate recompensa;
  Dados dados;

  public SistemaRecompensas(Dados dados, RecompensaTemplate recompensa){
    this.dados = dados;
    this.recompensa = recompensa;
  }

  protected void gerarRecompensa(){
    dados.heroi.setQntDinheiro(dados.heroi.getQntDinheiro() + recompensa.getDinheiro());
    System.out.println("Como recompensa da sua vitório, você ganhou: " +
        recompensa.getDinheiro());
  }
}
