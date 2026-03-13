# Instruções e Regras do jogo

## Instruções do jogo
### 1-Mecanica das cartas:
#### Cartas dano
As cartas dano possuem um determinado nível (váriando de 1 a 5 ) e um custo energia. Elas podem ser acionadas somente se o herói possui energia maior ou igual ao custo da carta.

Ao serem acionadas é rolado dois dados para calcular o dano:

1- Dado de precisão (D20), indicando o quão preciso foi o ataque.

2- Dado de potência (Dx) , indicando o quão potênte foi o ataque.

O nível da carta determina o número lados do dado de potência, conforme a tabela:
| nível | número de lados |
|------|-----|
|1|4|
|2|6|
|3|8|
|4|10|
|5|12|

O dado de precisão estabelece um multiplicador para o dado de potência conforme a seguinte tabela:

| Valor no D20 | Modificador |
|-------------|-------------|
| 1     | 0 (Errou o ataque)   |
| entre 2 a 5  | 1 (acertou de raspão)     |
| entre 6 e 10 | 2 (acertou o golpe) |
| entre 11 e 15 | 4 (acertou um bom golpe) |
| entre 16 e 19 | 16 (acertou ponto vital) |
| 20 | 64 (acertou ponto crítico)|

O dano causado é calculado pela fórmula:

DANO = DADO DE POTÊNCIA X MODIFICADOR
#### cartas escudo
As cartas escudo possem um valor de escudo e um custo de energia. Elas podem ser acionadas somente se o herói possui energia maior ou igual ao custo da carta.

Quando acionadas elas recarregam a barra de escudo do heroi, assim o escudo irá absorver todo ou uma parecela do dano incidente.

O dano sofrido é calculado da seguinte maneira:

D = DANO DO INIMIGO - ESCUDO

| D | dano sofrido |
|-------|--------|
| > 0 | D |
| <= 0 | 0 |




### 2- Mecânica de combate
#### Turno do herói
O combate funciona com um sistema de turnos no qual o herói tem a ação inicial. Durante os turnos as ações são tomadas de acordo com o número que o usuário digitar no terminal conforme a seguinte tabela:

|Ações | número |
|------|-------|
|Usar carta dano |     1    |
|Usar carta escudo | 2|
|Tentar fugir | 3|
|Passar turno | 0|

O turno termina se o herói não possui energia ou se ele passar o turno.
#### Turno do inimgo
O inimgo possui dano fixo ele sempre ataca no turno

Caso houver mais de um inimigo, um é sorteado aletóriamente para atacar. E apenas um ataca por turno. Quando um inimigo ataca é encerrado o fim do turno, dando sequencia para o turno do protagonista.
#### Fim do combate
O combate termina se o herói fugir, ou se o herói ou todos os inimigos morrerem.
## Instruções para compilar e executar o programa
### Para baixar o projeto:
git clone https://github.com/enzofarinamullis/tarefa01.git
### Executar e compilar:
cd tarefa01
javac -d bin $(find src -name "*.java")
java -cp bin App

