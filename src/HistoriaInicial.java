import java.util.Scanner;

public class HistoriaInicial extends Cena{
  
  public HistoriaInicial(Dados dados){
    super("Historia Inicial", dados);
    this.leitor = new Scanner(System.in);
  }

  @Override
  public void carregaCena(){
    System.out.println("Alan: Olá novato");
    System.out.println("Novato: oi? eu?");
    System.out.println("1 - meu nome eh");
    System.out.println("2 - nao te conheco");
  }

  @Override
  public void atualizaCena(){
    int escolha = leitor.nextInt();
    if(escolha == 1){
      System.out.println("Alan: ola!");
    }
  }
}
