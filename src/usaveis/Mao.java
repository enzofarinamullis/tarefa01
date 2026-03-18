package usaveis;
import usaveis.Cartas;
import constantes.Cores;

import java.util.ArrayList;
import java.util.List;

public class Mao{
  public List<Cartas> cartas;
  
  public Mao(){
    cartas = new ArrayList<>();
  }


  public void printMao(){
    if(cartas.size() == 0){
      System.out.println("Mão vazia");
      return;
    }
    Cartas atual;
    int num = 0;
    while(num != cartas.size()){
      atual = cartas.get(num);
      Cores.cprintInt(Cores.ANSI_BLUE, num);
      Cores.cprint(Cores.ANSI_BLUE, " - ");
      System.out.print(atual.nome + "\n");

      /* colocar funcoes printCarta em Cartas */
      if(atual.ehDano){
        Cores.cprint(Cores.ANSI_RED, "> ");
        System.out.print("Dano Base: ");
        Cores.cprintIntn(Cores.ANSI_RED, atual.nivel);
      }
      else if(atual.ehEscudo){
        Cores.cprint(Cores.ANSI_BLUE, "> ");
        System.out.print("Escudo Base: ");
        Cores.cprintIntn(Cores.ANSI_BLUE, atual.escudo);
      }
      Cores.cprint(Cores.ANSI_GREEN, "> ");
      System.out.print("Custo: ");
      Cores.cprintIntn(Cores.ANSI_GREEN, atual.custoEnergia);
      num++;
    }
  }
}

