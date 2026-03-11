public class CenaInicial extends Cena {

  /* Init */
  CenaInicial(Dados dados){
    super("Cena Inicial", dados);
  }

  /* Carrega a Cena */
  @Override
  public void carregaCena(){
    imprimeArquivo("animacoes/CabecalhoInicial.txt");
  }

  /* Atualiza a Cena */
  @Override
  public void atualizaCena(){
    System.out.println("Bem vindo, " + dados.heroi.nome + ", ao mundo de Sangue e Runas!");
  }
}