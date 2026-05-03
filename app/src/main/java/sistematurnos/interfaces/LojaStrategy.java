package sistematurnos.interfaces;

import dados.Dados;
import sistematurnos.lojas.NoLoja;
import usaveis.cartas.CartaDano;

import java.util.List;

public interface LojaStrategy{
  void adicionarNos(List<NoLoja> nosDisponiveis, Dados dados);
}

