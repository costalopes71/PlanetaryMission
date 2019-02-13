# PlanetaryMission

*******
Tabela de conteúdo 
 1. [Introdução](#introdução)
 2. [Regras de negócio](#regras-de-negócio)
 3. [Highlights do projeto](#highlights-do-projeto)
 4. [Pré-requisitos](#pré-requisitos)
 5. [Como executar?](#como-executar)
 6. [Endpoints](#endpoints)
 7. [E como testa!?](#e-como-testa-oo)
 8. [Tech/Framework utilizado](#techframework-utilizado)
 

*******

## Introdução
Projeto tem por finalidade reproduzir a idéia de uma sonda espacial pousando em um planeta e podendo mover-se na superfície.
Um típico exemplo seria um Rover da Nasa movendo-se na superfície de Marte a partir de comandos enviados da Terra. É exatamente este exemplo que o projeto tenta reproduzir.

O projeto é um web service, basicamente, ele representa o Universo com seus planetas e as sondas espaciais. O webservice não depende de banco de dados, exatamente para que não haja nenhuma dependência que não seja executar o projeto. 

Permite: listar os planetas existentes no universo, listar as sondas cadastradas, cadastrar uma sonda, pesquisar uma sonda, pousar uma sonda em um planeta, mover a sonda pela superfície do planeta.

## Regras de negócio 
- uma mesma sonda não pode pousar duas vezes
- uma sonda não pode pousar em um planeta inexistente
- uma sonda não pode pousar além da superfície do planeta
- uma sonda não pode pousar em uma posição da superfície já ocupada
- um sonda não pode colidar com outra sonda durante seu trajeto.
- uma mesma sonda não pode ser registrada duas vezes

## Highlights do projeto
- As URLs que a sonda manda de volta para a "Terra" são fotos de Rovers da Nasa que estão realmente na superfície de Marte e elas são MUITO legais!!! XD . Para tanto é consumida uma API da Nasa que pode ser encontrada [aqui](https://api.nasa.gov/index.html#getting-started).
- A possibilidade de simular comandos enviados da "Terra" para uma sonda no espaço e a resposta dessa sonda.
- Um exemplo adolescente de divisão entre camadas (DAO, BO e Model) e exemplo de webservices REST usando as APIs Jersey e Jackson.

## Pré-requisitos
Não existe nenhuma configuração especial ou pré-requisito para o projeto rodar!!! Basta executar de uma das maneiras explicadas na seção abaixo.

## Como executar?
- Usando DOCKER
Abra o prompt de comando, power shell ou bash e execute o comando:

`docker pull costalopes/planetarymissionws` -> para obter a imagem

`docker run -d -p 8080:8080 costalopes/planetarymissionws` -> obter a imagem e executar

Obs: a imagem nada mais é do que uma imagem de um Tomcat 9 com o arquivo war deployado nele, a porta do container exposta para o host é a 8080. =)

- Usando ECLIPSE
Basta clonar o projeto ou baixar o arquivo WAR que esta na seção [release](https://github.com/costalopes71/PlanetaryMission/releases) e importá-lo no eclipse.

- Diretamente no TOMCAT
Basta copiar o arquivo planetarymission.war para a pasta webapps do seu Tomcat e iniciar o Tomcat! =D (autodeploy flag deve estar com o valor true) 

## Endpoints

| AÇÃO | URL | VERBO HTTP |
| ------|-----|-----|
listar planetas | `http://localhost:8080/PlanetaryMission/rest/planet` | GET |
listar sondas | `http://localhost:8080/PlanetaryMission/rest/probe` | GET |
cadastrar sonda | `http://localhost:8080/PlanetaryMission/rest/probe` | POST |
procurar sonda | `http://localhost:8080/PlanetaryMission/rest/probe/{id da sonda a procurar}`| GET |
pousar sonda | `http://localhost:8080/PlanetaryMission/rest/probe/land` | POST |
mover sonda | `http://localhost:8080/PlanetaryMission/rest/probe/move` | POST |


| AÇÃO | REQUEST | RESPONSE |
| ------|-----|-----|
listar planetas | \- | `{ "planetId" = 1001, "name" = "Mars" }` |
cadastrar sonda | um texto no body do http. Ex: `BR29 Probe`| mensagem texto de sucesso ou erro |
pousar sonda | `{ "equipmentId" : 1928912, "planetId" : 1001, "positionX" : 5, "positionY" : 5 }` | objeto sonda em json |
mover sonda | `{ "equipmentId" : 2378237, "movement" : "R|R|M|M" }` | mensagem texto de sucesso ou erro |

Obs: o atributo movement é uma string onde os movimentos devem ser separados por pipe `|`.
Movimentos possíveis: R (direita), L (esquerda), M (a frente).
  
## E como testa!? oO
Basta executar a suíte de testes `br.com.elo7.planetarymission.model.test.PlanetaryMissionTestSuite`

## Tech/Framework utilizado
- Java 8
- Webservices RESTFFULL
- Jersey e Jackson
- Maven
- JUnit 4
- Service Jersey consumindo API
