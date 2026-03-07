import java.util.Scanner;


public class Interface{
  public static void main(String[] args) {
    
    Scanner teclado = new Scanner(System.in);

    System.out.println("Escolha seu nome de herói: ");
    String nome = teclado.nextLine();
    
    Heroi heroi = new Heroi(nome, 20, 10, 20);
    System.out.println(heroi.nome + " " + heroi.energia);
  }
}
