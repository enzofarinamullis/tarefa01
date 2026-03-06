class Heroi{
  /* Atributos */
  public String nome;
  public int vida;
  public int escudo;
  public int dano;

  /* Construtor */
  public Heroi(String nome, int vida, int escudo, int dano) {
    this.nome = nome;
    this.vida = vida;
    this.escudo = escudo;
    this.dano = dano;
  }

  void recebe_dano(int dano){
      
    if(escudo > 0) {
      int dano_no_escudo = Math.min(escudo, dano);
      escudo -= dano_no_escudo;
      dano -= dano_no_escudo;
      System.out.println("O escudo absorveu" + dano_no_escudo + "de escudo.");
    }

    System.out.println(nome + "recebeu" + dano + "de dano.");
    vida -= dano;

    if(vida <= 0){
      System.out.println(nome + "morreu !");
      vida = 0;
    }
    else{
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