package dados.inimigos;
import dados.Inimigo;
import usaveis.cartas.efeitos.Envenenamento; 

public class lesmaVenenosa extends Inimigo {
  public lesmaVenenosa() {
    super("Lesma Venenosa", 10, 3, 1, "eu sou uma lesma muito cruel");
    this.ASCII = "/ASCIIInimigos/lesmaVenenosa.txt";
    Envenenamento veneno = new Envenenamento("Veneno da Lesma", 1, 1, 2);
    this.listaEfeitos.add(veneno);
  }
}
