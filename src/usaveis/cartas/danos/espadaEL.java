package usaveis.cartas.danos;
import usaveis.cartas.CartaDano;
import usaveis.cartas.efeitos.Sangramento;


public class espadaEL extends CartaDano {
  public espadaEL() {
    super("Espada Estranhamente Longa", 4, 4, 
    "Uma espada comicamente longa");
    Sangramento sangramentoIII = new Sangramento("Sangramento III", 3, 5, 3);
    adicionarEfeito(sangramentoIII);
  }
}
