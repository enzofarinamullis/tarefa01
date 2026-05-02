package sistematurnos.lojas;

import dados.Dados;
import sistematurnos.Evento;
import usaveis.cartas.Carta;
import usaveis.pilhas.PilhaCompra;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        System.out.println(i + noAtual.getNome() + " - " + noAtual.getDescricao());
      }
    }
  }

  private boolean validaEscolha(int escolha){
    if(escolha >= 0 && escolha < nosDisponiveis.size()){
      return true;
    }
    else return escolha == -1;
  }

  private boolean verificaQuantidadeDinheiro(NoLoja noEscolhido){
    return dados.heroi.getQntDinheiro() >= noEscolhido.getCusto();
  }

  public boolean iniciar(){
    Scanner teclado = new Scanner(System.in);
    Carta cartaComprada = null;
    NoLoja noEscolhido = null;
    while (true) {
      imprimirCartasParaComprar();
      System.out.println();
      System.out.println("Sair: -1");

      int escolha = teclado.nextInt();
      while (!validaEscolha(escolha)) {
        escolha = teclado.nextInt();
      }

      /* Verificamos se o jogador não deseja sair */
      if (escolha == -1) {
        return true;
      }
      else {
        noEscolhido = nosDisponiveis.get(escolha);
      }

      /* caso o jogador possua dinheiro */
      if (verificaQuantidadeDinheiro(noEscolhido)) {
        cartaComprada = noEscolhido.getCarta();
        PilhaCompra pilhaCompra = dados.heroi.getPilhaCompra();
        pilhaCompra.pilha.add(cartaComprada);

        /* diminuimos o dinheiro do heroi */
        dados.heroi.setQntDinheiro(dados.heroi.getQntDinheiro() - noEscolhido.getCusto());
      }
      else {
        System.out.println("Você não tem saldo suficiente para comprar esta carta");
      }
    }

  }

}
