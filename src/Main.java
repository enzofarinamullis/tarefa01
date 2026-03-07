public class Main {
  public static void main(String[] args) throws Exception {
    /* Implementei inicialmente a ideia do WindowManager */
    /* Pessoalemente explicarei melhor como ele funciona */
    /* Mas resumindo, toda Cena, terá 4 métodos (funções) principais */

    /* 1- Init --> o init será responsável por iniciarmos os dados da cena */
    /* isso será feito automaticamente quando chamarmos Cena cena = new CenaInicial(dados) */
    /* voce pode ver isso na linha 26 */

    /* 2- carregaCena --> ou seja, literalmente desenhamos a cena (carregamos ela) */

    /* 3- atualizaCena --> será responsável por atualizar a cena (exemplo movimento dos inimigos) */

    /* 4- liberaCena --> isso o Java já fará de graça para a gente, sem termos que nos preocupar */
    /* Apenas limpamos a tela do terminal (já implementado) */


    /* Inicializamos nosso Heroi com atributos iniciais, mas o nome vazio */
    Heroi heroi = new Heroi(null, 20, 10, 1, 10); // aqui ainda esta o dano ( retirar mais tarde )
                                                                                  // dano estara nas cartas

    /* Inicializamos nossos Dados vazio */
    Dados dados = new Dados(heroi, 0);
    /* Inicializamos nossa cena como sendo a CenaInicial */
    Cena cena = new CenaInicial(dados); // estou usando heranca para as cenas
    
    /* Inicializamos nosso windowManager */
    WindowManager windowManager = new WindowManager(cena);
    
    /* Carregamos nossa cena inicial */
    cena.carregaCena();

    /* so para efeitos de teste */
    cena.atualizaCena(); // amanha irei implementar a sistema de FramesPerSeconds para que o 
                         // nosso jogo tenha atualizacoes constantes
  }

  /* Explicarei melhor tudo pessoalmente, mas basicamente, nao faremos a "jogatina" na main */
  /* A Main será responsável apenas por carregar e atualizar nosso jogo! */
}
