package sistematurnos;

import dados.Dados;
import dados.Heroi;
import sistematurnos.interfaces.RecompensaTemplate;

public class SistemaRecompensas {
  RecompensaTemplate recompensa;
  Heroi heroi;

  public SistemaRecompensas(Heroi heroi, RecompensaTemplate recompensa){
    this.heroi = heroi;
    this.recompensa = recompensa;
  }

  public void gerarRecompensa(){
    heroi.setQntDinheiro(heroi.getQntDinheiro() + recompensa.getDinheiro());
    System.out.println("Como recompensa da sua vitório, você ganhou: " +
        recompensa.getDinheiro());
  }
}
