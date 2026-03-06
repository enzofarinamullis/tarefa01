class Inimigo {
  public String nome;
  public int vida;
  public int escudo;
  public int dano;

  /* Construtor */
  public Inimigo(String nome, int vida, int escudo) {
    this.nome = nome;
    this.vida = vida;
    this.escudo = escudo;
  }

  void recebe_dano(int dano) {
        
    if (escudo > 0){
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
    }
    else{
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
