package dados;
import java.util.ArrayList;
import java.util.List;
public class ListaInimigos {
  private List<Inimigo> inimigos;
  
  public ListaInimigos(){
    inimigos = new ArrayList<>();
  }
  
  public void adicionarInimigo(Inimigo inimigo){
    inimigos.add(inimigo);
  }
  
  public void mostrarInimigos(){
    int indice = 0;
    Inimigo atual;
    for(int i = 0; i < inimigos.size(); i++){
      indice = i + 1;
      System.out.print(indice + " ");
      atual = inimigos.get(i);
      atual.printStats();
    }
  }

  public void printInimigosSemIndice(){
    Inimigo atual;
    for(int i = 0; i < inimigos.size(); i++){
      atual = inimigos.get(i);
      atual.printStats();
    }
  }

  public Inimigo buscarInimigo(int numero){
    int indice = numero - 1;
    return inimigos.get(indice);
  }

  public void removerInimigo(Inimigo inimigoRemover){
    inimigos.remove(inimigoRemover);
  }
  
  public int getTamanho(){
    return inimigos.size();
  }
}
