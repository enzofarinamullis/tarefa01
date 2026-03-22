package usaveis;
import constantes.Cores;
import usaveis.cartas.Carta;

import java.util.ArrayList;
import java.util.List;

public class Mao{
  public List<Carta> cartas;
  
  public Mao(){
    cartas = new ArrayList<>();
  }


  public void printMao(){
    if(cartas.isEmpty()){
      System.out.println("Mão vazia");
      return;
    }
    Carta atual;
    int num = 0;
    while(num != cartas.size()){
      atual = cartas.get(num);
      Cores.cprintInt(Cores.ANSI_BLUE, num);
      Cores.cprint(Cores.ANSI_BLUE, " - ");
      System.out.print(atual.getNome() + "\n");

      /* colocar funcoes printCarta em Carta */
      if(atual.isDano()){
        Cores.cprint(Cores.ANSI_RED, "> ");
        System.out.print("Dano Base: ");
        Cores.cprintIntn(Cores.ANSI_RED, atual.nivel);
      }
      else if(atual.isEscudo()){
        Cores.cprint(Cores.ANSI_BLUE, "> ");
        System.out.print("Escudo Base: ");
        Cores.cprintIntn(Cores.ANSI_BLUE, atual.escudo);
      }
      Cores.cprint(Cores.ANSI_GREEN, "> ");
      System.out.print("Custo: ");
      Cores.cprintIntn(Cores.ANSI_GREEN, atual.getCustoEnergia());
      num++;
    }
  }
}

