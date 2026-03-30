package usaveis.escudos;

import usaveis.cartas.CartaEscudo;
import usaveis.cartas.efeitos.Cura;

public class escudoDaInstropeccao extends CartaEscudo {
  public escudoDaInstropeccao() {
    super("Escudo da Instrospecção", 5,5, "Escudo de uma Cura Profunda" );
    Cura curaII = new Cura("Cura II", 1, 10, 5);
    adicionarEfeito(curaII);
  }
}
