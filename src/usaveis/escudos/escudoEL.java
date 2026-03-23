package usaveis.escudos;
import usaveis.cartas.CartaEscudo;
import usaveis.cartas.efeitos.Cura;

public class escudoEL extends CartaEscudo {
  public escudoEL() {
    super("Escudo Estranhamente Largo", 4, 4, "Um escudo comicamente largo");
    Cura curaIII = new Cura("Cura III", 3, 5, 1);
  }
}