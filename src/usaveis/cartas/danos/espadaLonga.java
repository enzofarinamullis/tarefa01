package usaveis.cartas.danos;
import usaveis.cartas.CartaDano;
import usaveis.cartas.efeitos.Sangramento;

public class espadaLonga extends CartaDano {
  public espadaLonga() {
    super("Espada Longa", 3, 3, "Uma espada estranhamente longa");
    Sangramento sangramentoI = new Sangramento("Sangramento I", 2, 3, 1);
    adicionarEfeito(sangramentoI);
  }
}
