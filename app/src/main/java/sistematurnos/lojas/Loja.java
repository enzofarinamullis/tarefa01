package sistematurnos.lojas;

import anim.Animacao;
import anim.Shopping;
import anim.dialogos.falas.Dialogo;
import constantes.Cores;
import dados.Dados;
import sistematurnos.Evento;
import sistematurnos.interfaces.LojaStrategy;
import usaveis.cartas.Carta;
import usaveis.pilhas.PilhaCompra;
import utilitarios.PrintTerminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Loja extends Evento {
  protected int qntItens = 3;
  protected List<NoLoja> nosDisponiveis = new ArrayList<>();
  private LojaStrategy strategy;

  public Loja(Dados dados, String nome, LojaStrategy strategy){
    super(dados, nome, Tipo.LOJA);
    this.strategy = strategy;
    strategy.adicionarNos(nosDisponiveis, dados);
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
        System.out.println(i + ": -" + Cores.ANSI_GREEN + " $ " +
            noAtual.getCusto() + Cores.ANSI_CYAN +
            " - " + noAtual.getNome() + Cores.ANSI_RESET +
            " - " + noAtual.getDescricao());
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

  /*
   * Padrão de design utilizado Strategy Pattern
   * Baseado na escolha do jogador, decidimos se deve haver uma compra
   * e dependendo da escolha que o jogador fizes, decidimos se este
   * compra a carta ou sai da loja.
   * ref: https://refactoring.guru/design-patterns/strategy
   */
  public boolean executarEvento(){
    Scanner teclado = new Scanner(System.in);
    Animacao imagemShopping = new Shopping();

    try{
      Thread.sleep(1000);
    }
    catch (Exception e){}
    PrintTerminal.limparTerminal();
    imagemShopping.run();

    Carta cartaComprada = null;
    NoLoja noEscolhido = null;
    try{
      Thread.sleep(1000);
    }
    catch (Exception e){}

    while (true) {
      System.out.println("Saldo disponível: " + Cores.ANSI_GREEN + "$ " +
          dados.heroi.getQntDinheiro() + Cores.ANSI_RESET);
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
        adicionarCompra(cartaComprada);

        /* diminuimos o dinheiro do heroi */
        dados.heroi.setQntDinheiro(dados.heroi.getQntDinheiro() - noEscolhido.getCusto());

        /* Removemos o item da loja */
        removerItemLoja(noEscolhido);
        cartaComprada = null;
      }
      else {
        System.out.println("Você não tem saldo suficiente para comprar esta carta");
      }
    }

  }

  private void removerItemLoja(NoLoja noEscolhido){
    nosDisponiveis.remove(noEscolhido);
  }

}
