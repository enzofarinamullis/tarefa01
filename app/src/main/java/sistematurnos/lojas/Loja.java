package sistematurnos.lojas;

import dados.Dados;
import sistematurnos.Evento;
import usaveis.cartas.Carta;
import usaveis.pilhas.PilhaCompra;

import java.util.ArrayList;
import java.util.List;

public abstract class Loja extends Evento {
  protected int qntItens = 3;
  protected List<NoLoja> nosDisponiveis = new ArrayList<>();

  public Loja(Dados dados, String nome){
    super(dados, nome, Tipo.LOJA);
  }

  private void adicionarCompra(Carta cartaComprada){
    PilhaCompra pilhaCompra = dados.heroi.getPilhaCompra();
    pilhaCompra.pilha.add(cartaComprada);
  }

  private void imprimirCartasParaComprar(){
    if(!nosDisponiveis.isEmpty()){
      NoLoja noAtual;
      Carta cartaAtual;
      for(int i = 0; i < nosDisponiveis.size(); i++){
        noAtual = nosDisponiveis.get(i);
        System.out.println((i + 1) + noAtual.getNome() + " - " + noAtual.getDescricao());
      }
    }
  }

  public boolean iniciar(){

  }

}
