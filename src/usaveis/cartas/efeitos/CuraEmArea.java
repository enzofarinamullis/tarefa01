package usaveis.cartas.efeitos;

import dados.Aliado;
import dados.Entidade;
import dados.Heroi;
import dados.Inimigo;
import dados.ListaInimigos;
import dados.ListaAliados;
import usaveis.cartas.Efeito;

import java.util.ArrayList;
import java.util.List;
import constantes.Cores;

public class CuraEmArea extends Efeito {
  public enum tiposArea{
    ALIADOS,
    INIMIGOS,
    TODOS,
    EXCETO_QUEM_USOU
  }
  private tiposArea tipo;
  private Entidade usuario;

  public CuraEmArea(String nome, int duracao, int intensidade, tiposArea tipo_Area) {
    super(nome, duracao, intensidade);
    this.tipo = tipo_Area;
  }
  public void setUsuario(Entidade entidade) {
    this.usuario = entidade;
  }

  public void aplicar() {
    int cura = getIntensidade()*3;
    System.out.println();
  
      switch (tipo) {
        case ALIADOS:
          Cores.cprintn(Cores.ANSI_GREEN, "ALIADOS!");
          break;
        case INIMIGOS:
          Cores.cprintn(Cores.ANSI_RED, "INIMIGOS!");
          break;
        case TODOS:
          Cores.cprintn(Cores.ANSI_YELLOW, "TODOS!");
          break;
        case EXCETO_QUEM_USOU:
          Cores.cprintn(Cores.ANSI_YELLOW, "ALIADOS (exceto " + usuario.getNome() + ")!");
          break;
    }
    
  }
}
