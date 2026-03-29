package usaveis.cartas.danos;

import usaveis.cartas.CartaDano;
import usaveis.cartas.efeitos.Envenenamento;

public class espadaEnvenenada extends CartaDano{
  
  public espadaEnvenenada() {
    super("Espada Envenenada", 5,5, "Espada banhada em veneno de rato" );
    Envenenamento envenenamentoV = new Envenenamento("Envenenamento V", 5, 10, 5);
    adicionarEfeito(envenenamentoV);
  }
}

