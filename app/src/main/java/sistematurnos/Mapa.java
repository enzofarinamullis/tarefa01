package sistematurnos;
import javax.swing.tree.DefaultMutableTreeNode;
import sistematurnos.batalhas.*;
import dados.Dados;

public class Mapa {
  DefaultMutableTreeNode inicio;
  
  public Mapa(Dados dados){
    inicio = new DefaultMutableTreeNode("Batalhas");
    
    /* Criamos as batalhas que iremos utilizar */
    Batalha b1 = new BatalhaNSlimes(dados, 1);
    Batalha b2 = new BatalhaNSlimes(dados, 2);
    Batalha b3 = new BatalhaNSlimes(dados, 3);
    Batalha b4 = new BatalhaLesmasESlimes(dados, 1, 1);
    Batalha b5 = new BatalhaLesmasESlimes(dados, 2, 1);
    Batalha b6 = new BatalhaLesmasESlimes(dados, 1, 3);
    
    DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(b1);
    DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(b2);
    DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(b3);
    DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(b4);
    DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(b5);
    DefaultMutableTreeNode n6 = new DefaultMutableTreeNode(b6);
    
    /* A partir do no inicial, so temos um caminho a seguir */
    inicio.add(n1);
    
    n1.add(n2);
    
    n2.add(n3);
    n2.add(n4);
    
    n3.add(n5);
    
    n4.add(n5);
  }
  
  
}
