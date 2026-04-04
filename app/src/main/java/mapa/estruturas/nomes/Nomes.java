package mapa.estruturas.nomes;
import java.util.List;
import java.util.Random;

public abstract class Nomes {
  public List<String> nomes;
  public String escolheNome(){
    int qntNomes = nomes.size();
    Random aleatorio = new Random();
    
    /* buscamos um numero aleatorio dentro do limite da lista */
    int indice = aleatorio.nextInt(qntNomes);
    String nome = nomes.get(indice);
    
    /* Retornamos o nome para que ele sempre seja unico */
    nomes.remove(indice);
    return nome;
  }
}
