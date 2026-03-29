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

      /* colocar funcoes printCarta em Carta */
      if(atual.isDano()){
        atual.info(num);
      }
      else if(atual.isEscudo()){
        atual.info(num);
      }
      num++;
    }
  }
}

