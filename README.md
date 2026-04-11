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

#### Cartas escudo
As cartas escudo possem um valor de escudo e um custo de energia. Elas podem ser acionadas somente se o herói possui energia maior ou igual ao custo da carta.

Quando acionadas elas recarregam a barra de escudo do heroi, assim o escudo irá absorver todo ou uma parecela do dano incidente.

O dano sofrido é calculado da seguinte maneira:

D = DANO DO INIMIGO - ESCUDO

| D | dano sofrido |
|-------|--------|
| > 0 | D |
| <= 0 | 0 |

### 3-Efeitos Especiais

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





