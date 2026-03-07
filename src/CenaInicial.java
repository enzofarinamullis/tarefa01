import java.util.Scanner;

public class CenaInicial extends Cena {

  /* Init */
  CenaInicial(Dados dados){
    super("Cena Inicial", dados);
  }

  /* Carrega a Cena */
  @Override
  public void carregaCena(){

    /* Lemos o nome do Heroi */
    Scanner teclado = new Scanner(System.in);
    System.out.println("Escolha seu nome de herói: ");
    String nome = teclado.nextLine();

    /* Como o Heroi ja foi inicializado na Main */
    /* podemos, agora, salvar o seu nome */
    this.dados.heroi.nome = nome;
  }

  /* Atualiza a Cena */
  @Override
  public void atualizaCena(){
    System.out.println("Bem vindo, " + this.dados.heroi.nome + ", ao mundo de Valhalla!");
  }
}