# Instruções e Regras do jogo
## Sumário
1. [Instruções do Jogo](#instruções-do-jogo)
2. [Mecânica das Cartas](#mecânica-das-cartas)
3. [Mecânica de Combate](#mecânica-de-combate)
4. [Catálogo de Cartas](#catálogo-de-cartas)
5. [Eventos do Jogo](#eventos-do-jogo)
6. [Como Compilar e Executar](#como-compilar-e-executar)

---

## Instruções do jogo
### Visão geral:
O jogo é um RPG de turnos onde você controla um herói que enfrenta inimigos em batalhas, compra cartas em lojas e descansa em fogueiras. O progresso se dá através de um mapa em formato de grafo, onde cada vértice representa um evento diferente. O objetivo é avançar pelo mapa, derrotando inimigos e fortalecendo os heróis com cartas, até completar todos os desafios.

### 1-Entidades:
#### Diagrama UML das entidades:
📁 O diagrama UML das entidades está disponível em: https://drive.google.com/file/d/1hplqrjE_NpR0gHMIkE3rnphIrEFVonWV/view?usp=sharing
#### Herói
A classe Heroi representa o personagem controlado pelo jogador no jogo. É uma classe que gerencia tudo sobre o herói: sua vida, energia, dinheiro, cartas e recompensas. Ela estende a classe Entidade, ou seja, herda características básicas como vida, escudo e energia.

O herói possui vida, que representa quantos pontos de vida ele tem restantes. Ele começa com 5 pontos de vida. Se a vida chegar a zero, o herói morre e o jogo acaba. O herói também possui escudo, que é uma proteção que absorve parte do dano sofrido. Ele começa com zero de escudo. A energia é o recurso usado para jogar cartas durante as batalhas. O herói começa com 20 de energia e tem um limite máximo de 20. Ele também tem um limite de energia, que é o máximo que sua energia pode atingir.

O herói possui uma mão, que é onde ficam as cartas que ele pode jogar no momento atual da batalha. A mão começa vazia. Ele também tem uma pilha de compra, que é o baralho do herói, de onde ele compra novas cartas durante o jogo. Este baralho é criado e automaticamente embaralhado quando o herói é criado. A pilha de descarte é onde vão as cartas que já foram usadas ou descartadas.

O herói também possui uma quantidade de dinheiro, que começa com 10 unidades, e pode ser usado para comprar cartas nas lojas. O sistema de recompensas é responsável por dar prêmios ao herói após vencer batalhas. Por fim, ele tem um contador de quantas recompensas já foram dadas, que começa em zero.

O método de recompensas é chamado quando o herói vence uma batalha e deve receber uma recompensa. O tipo de recompensa muda conforme quantas recompensas já foram dadas anteriormente.

Se o herói ainda não recebeu nenhuma recompensa, ou seja, o contador está em zero, ele recebe uma recompensa básica. Depois de receber, o contador aumenta para um.

Se o herói já recebeu uma recompensa, ou seja, o contador está em um, ele recebe uma recompensa média. Depois de receber, o contador aumenta para dois.

Se o herói já recebeu duas ou mais recompensas, ou seja, o contador é maior ou igual a dois, ele recebe uma recompensa grande. O contador continua aumentando a cada vez que o método é chamado.

Depois de definir qual tipo de recompensa será dada, o método chama gerarRecompensa do sistema de recompensas para efetivamente dar a recompensa ao herói.
#### Inimigos:
A classe Inimigo representa os adversários que o herói enfrenta durante as batalhas do jogo. Assim como o herói, o inimigo também estende a classe Entidade, ou seja, ele possui vida, escudo, energia e dano. O inimigo tem comportamentos específicos como anunciar sua presença e aplicar efeitos no herói.


### 2-Mecanica das cartas:
#### Diagrama UML das cartas:
📁 O diagrama UML das cartas está disponível em: https://drive.google.com/file/d/1VQ-yuuNAlYXiD1cJ8nLoSqjtWQNzAfd0/view?usp=sharing
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

#### Cartas escudo
As cartas escudo possem um valor de escudo e um custo de energia. Elas podem ser acionadas somente se o herói possui energia maior ou igual ao custo da carta.

Quando acionadas elas recarregam a barra de escudo do heroi, assim o escudo irá absorver todo ou uma parecela do dano incidente.

O dano sofrido é calculado da seguinte maneira:

D = DANO DO INIMIGO - ESCUDO

| D | dano sofrido |
|-------|--------|
| > 0 | D |
| <= 0 | 0 |

### Efeitos Especiais
#### Diagrama UML dos efeitos:
📁 O diagrama UML dos efeitos: https://drive.google.com/file/d/1RN73PInMLR4mRCJlKV9xVB3ipik_vbGS/view?usp=sharing

Algumas cartas possuem **efeitos de status** que persistem por múltiplos turnos.

#### Sangramento

O efeito de sangramento causa dano contínuo ao alvo ao longo do tempo.

| Atributo | Descrição |
|----------|-----------|
| **Dano por tick** | Quantidade de dano causada por turno |
| **Duração** | Número de turnos que o efeito persiste |
| **Custo** | Energia necessária para aplicar o efeito |
| **Alcance**| QUantos inimigos sofrem o dano|

O dano do sangramento é aplicado **no início de cada turno do afetado** e **ignora armadura/escudo**.

#### Envenenamento

Similar ao sangramento, o envenenamento causa dano contínuo ao longo do tempo.

| Atributo | Descrição |
|----------|-----------|
| **Dano por tick** | Quantidade de dano causada por turno |
| **Duração** | Número de turnos que o efeito persiste |
| **Custo** | Energia necessária para aplicar o efeito |
| **Alcance**| Quantos inimigos sofrem o dano|


**Diferença mecânica:** Enquanto sangramento é dano físico, envenenamento é dano mágico/natureza — alguns inimigos podem ser imunes a um ou outro.

### 3- Mecânica de combate
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

---

## 🗡️ Catálogo de Cartas

### Espada Curta
| Atributo | Valor |
|----------|-------|
| **Tipo** | Dano |
| **Nível** | 1 |
| **Custo** | 1 |
| **Dano base** | 1 |
| **Efeito** | Nenhum |
| **Descrição** | "Uma espada curta até de mais" |

---

### Espada Média
| Atributo | Valor |
|----------|-------|
| **Tipo** | Dano |
| **Nível** | 3 |
| **Custo** | 3 |
| **Dano base** | 3 |
| **Efeito** | Nenhum |
| **Descrição** | "Uma espada na média" |

---

### Espada Longa
| Atributo | Valor |
|----------|-------|
| **Tipo** | Dano |
| **Nível** | 3 |
| **Custo** | 3 |
| **Dano base** | 3 |
| **Efeito** | Sangramento I |
| **Descrição** | "Uma espada estranhamente longa" |

#### Efeito: Sangramento I
| Atributo | Valor |
|----------|-------|
| Dano por tick | 2 |
| Duração | 3 turnos |
| Custo | 1 |
| Dano total do efeito | 6 |
|Alcance | 1|

---

### Espada Estranhamente Longa
| Atributo | Valor |
|----------|-------|
| **Tipo** | Dano |
| **Nível** | 4 |
| **Custo** | 4 |
| **Dano base** | 4 |
| **Efeito** | Sangramento III |
| **Descrição** | "Uma espada comicamente longa" |

#### Efeito: Sangramento III
| Atributo | Valor |
|----------|-------|
| Dano por tick | 3 |
| Duração | 5 turnos |
| Custo | 3 |
| Dano total do efeito | 15 |
|Alcance | 3|
---

### Espada Envenenada
| Atributo | Valor |
|----------|-------|
| **Tipo** | Dano |
| **Nível** | 5 |
| **Custo** | 5 |
| **Dano base** | 5 |
| **Efeito** | Envenenamento V |
| **Descrição** | "Espada banhada em veneno de rato" |

#### Efeito: Envenenamento V
| Atributo | Valor |
|----------|-------|
| Dano por tick | 5 |
| Duração | 10 turnos |
| Custo | 5 |
| Dano total do efeito | 50 |
|Alcance | 5|
---

### Espada da Lua Sangrenta
| Atributo | Valor |
|----------|-------|
| **Tipo** | Dano |
| **Nível** | 5 |
| **Custo** | 5 |
| **Dano base** | 5 |
| **Efeito** | Sangramento V |
| **Descrição** | "Espada com magia sangrenta" |

#### Efeito: Sangramento V
| Atributo | Valor |
|----------|-------|
| Dano por tick | 5 |
| Duração | 10 turnos |
| Custo | 5 |
| Dano total do efeito | 50 |
|Alcance | 5|

---

### Tabela Resumo das Espadas

| Carta | Nível | Custo | Dano Base | Efeito | Dano Total* |
|-------|-------|-------|-----------|--------|--------------|
| Espada Curta | 1 | 1 | 1 | Nenhum | 1 |
| Espada Média | 3 | 3 | 3 | Nenhum | 3 |
| Espada Longa | 3 | 3 | 3 | Sangramento I (6) | 9 |
| Espada Estranhamente Longa | 4 | 4 | 4 | Sangramento III (15) | 19 |
| Espada Envenenada | 5 | 5 | 5 | Envenenamento V (50) | 55 |
| Espada da Lua Sangrenta | 5 | 5 | 5 | Sangramento V (50) | 55 |

*Dano total considerando dano base + dano total do efeito

---
### Eventos:
O jogo funciona com sistema de mapa em forma de um grafo direcionado, onde cada vértice corresponde a um evento.

Há 3 tipos de evento: Batalha, fogueira e loja. 

#### Batalhas:
A batalha é um sistema de combate por turnos onde o jogador e os inimigos se alternam realizando ações até que um dos lados seja derrotado. O jogador age primeiro, escolhendo entre usar uma carta ou tentar fugir. Após a ação do jogador, chega a vez dos inimigos: cada um deles que ainda estiver vivo ataca o jogador. Esse ciclo se repete até que algo aconteça: o jogador pode derrotar todos os inimigos e vencer, pode ter seu personagem morrido e perder, ou pode conseguir fugir da batalha.
#### Loja:
A classe Loja representa um estabelecimento comercial dentro do jogo, onde o jogador pode gastar seu dinheiro para comprar cartas que auxiliarão em sua jornada.
##### Mecânica de compra:
1. Verifica saldo
2. Cria cópia da carta comprada
3. Adiciona à pilha de compras do herói
4. Diminui o dinheiro do herói
5. Remove o item da lista da loja
6. Retorna ao menu de compras
#### Design Pattern: Strategy
A loja utiliza um padrão de projeto Strategy para definir quais cartas estaram disponíveis. As estratégias são LojaInicial e LojaSecunda. As lojas iniciais possuem itens mais básicos e baratos enquanto a loja secunda possuem itens mais caros e mais poderosos.
### Fogueira:
A fogueira epresenta um ponto de descanso dentro do jogo, onde o jogador pode recuperar pontos de vida antes de continuar sua jornada. É um evento seguro que nunca causa game over.
#### Design Pattern: Template Method Pattern
A fogueira usa o padrão de projeto Template Method para definir diferentes comportamentos de recuperação. Assim evitando repetição de código e muda o comportamento sem mudar a classe.

## Instruções para compilar e executar o programa
### Para baixar o projeto:
git clone https://github.com/enzofarinamullis/tarefa01.git
### Compilar e executar:
./gradlew build

java -jar app/build/libs/app.jar
### Compilar e executar com makefile:
make

### Documentação do código:
xsg-open docs\index.html 

está na pasta docs.





