import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

class D20 {
    private Random aleatorio;
    private List<Integer> historico_de_resultados;
    //construtor:
    public D20() {
        this.aleatorio = new Random();
        this.historico_de_resultados = new ArrayList<>();
    }
    public int rolar_dado(){
        int resultado = aleatorio.nextInt();
        historico_de_resultados.add(resultado);
        return resultado;
    }
    public int rolar_com_vantagem() {
        int dado_1 = rolar_dado();
        int dado_2 = rolar_dado();
        int resultado = Math.max(dado_1, dado_2);
    }

    public int rolar_com_desvantagem() {
        int dado_1 = rolar_dado();
        int dado_2 = rolar_dado();
        int resultado = Math.min(dado_1, dado_2);
        return resultado;
    }
    public boolean teste(int dificuldade) {
        return rolar_dado()>= dificuldade;
    }

    void critico(int resultado) {
        if (resultado == 20) {
            System.out.println("Acerto crítico");
        }
        else if (resultado == 1) {
            System.out.println("Erro crítico");

        }
    }



}

class Heroi {
    //Atributos
    public String nome;
    public int vida;
    public int escudo;

    //construtor:
    public Heroi(String nome, int vida, int escudo) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
    }

    void recebe_dano(int dano) {
        
        if (escudo > 0) {
            int dano_no_escudo = Math.min(escudo, dano);
            escudo -= dano_no_escudo;
            dano -= dano_no_escudo;
            System.out.println("O escudo absorveu" + dano_no_escudo + "de escudo.");
        }
        System.out.println(nome + "recebeu" + dano + "de dano.");
        vida -= dano;
        if (vida <= 0) {
            System.out.println(nome + "morreu !");
            vida = 0;
        } else {
            System.out.println(nome + "tem" + vida + "de vida.");
        }

       
    }

    void ganha_escudo(int bonus_de_escudo) {
        System.out.println(nome + "ganhou" + bonus_de_escudo + "de escudo.");
        escudo += bonus_de_escudo;
    }

    void ganha_vida(int bonus_de_vida) {
        System.out.println(nome + "ganhou" + bonus_de_vida + "de vida.");
        vida += bonus_de_vida;
    }

    Boolean esta_vivo() {
       if (vida > 0) {
        System.out.println(nome + "está vivo!");
        return true;
       }
       else{
        System.out.println(nome + "não está vivo!");
        return false;
       }
    }

    void status(){
        System.out.println("Status do" + nome + ":");
        System.out.println("Vida: " + vida);
        System.out.println("Escudo: " + escudo);
        System.out.println("Status: " + (vida > 0 ? "Vivo" : "Morto"));
    }

}

class Inimigo {
    public String nome;
    int vida;
    int escudo;
    //construtor:
    public Inimigo(String nome, int vida, int escudo) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
    }

    void recebe_dano(int dano) {
        
        if (escudo > 0) {
            int dano_no_escudo = Math.min(escudo, dano);
            escudo -= dano_no_escudo;
            dano -= dano_no_escudo;
            System.out.println("O escudo absorveu" + dano_no_escudo + "de escudo.");
        }
        System.out.println(nome + "recebeu" + dano + "de dano.");
        vida -= dano;
        if (vida <= 0) {
            System.out.println(nome + "morreu !");
            vida = 0;
        } else {
            System.out.println(nome + "tem" + vida + "de vida.");
        }
    }
    void atacar(int forca_do_ataque, Heroi heroi) {
        System.out.println(nome + "ataca" + heroi.nome + "!");
        System.out.println("Força do ataque:" + forca_do_ataque + "PF");
        if (forca_do_ataque > 0) {
            heroi.recebe_dano(forca_do_ataque);
        }
    }
   Boolean esta_vivo() {
       if (vida > 0) {
        System.out.println(nome + "está vivo!");
        return true;
       }
       else{
        System.out.println(nome + "não está vivo!");
        return false;
       }
    }
}


class Carta_de_dano {
    public String nome;
    int custo_de_energia;

    public Carta_de_dano(String nome, int custo_de_energia) {
        this.nome = nome;
        this.custo_de_energia = custo_de_energia;
    }

    void usar_a_carta() {

    }


}

public class tarefa_1 {
    public static void main(String[] args) {
    
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nome do heroi:");
        String nome = scanner.nextLine();
        
        Heroi heroi = new Heroi(nome, 20, 10);
      
      

    }

}