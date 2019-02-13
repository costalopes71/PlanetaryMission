# PlanetaryMission

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
- As URLs que a sonda manda de volta para a "Terra" são fotos de Rovers da Nasa que estão realmente na superfície de Marte. Para tanto é consumida uma API da Nasa que pode ser encontrada [aqui](https://api.nasa.gov/index.html#getting-started).
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
- Listar planetas: `http://localhost:8080/PlanetaryMission/rest/planet` (GET)
- Listar sondas: `http://localhost:8080/PlanetaryMission/rest/probe`  (GET)
- Cadastrar sonda `http://localhost:8080/PlanetaryMission/rest/probe` (POST)
  Obs: para cadastrar sonda no corpo do HTTP basta enviar uma String contendo o nome da sonda. Ex: `Elo7MaiorLegal Probe`
- Procurar sonda `http://localhost:8080/PlanetaryMission/rest/probe/{id da sonda a procurar}` (GET)
- Pousar sonda `http://localhost:8080/PlanetaryMission/rest/probe/land` (POST)
  Obs: para pousar a sonda, enviar um JSON no corpo do HTTP como segue:
  
  `
  { 
      "equipmentId" : 1928912,
      "planetId" : 1001,
      "positionX" : 5,
      "positionY" : 5
  }
  `
- Mover a sonda `http://localhost:8080/PlanetaryMission/rest/probe/move` (POST)

  `
  {
    "equipmentId" : 2378237,
    "movement" : "R|R|M|M"
  }
  `
  
  Obs: o atributo movement é uma string onde os movimentos devem ser separados por pipe `|`.
  Movimentos possíveis: R (direita), L (esquerda), M (a frente).
  
## E como testa!? oO
Basta executar a suíte de testes `br.com.elo7.planetarymission.model.test.PlanetaryMissionTestSuite`

## Tech/Framework utilizado
- Java 8
- Webservices RESTFFULL
- Jersey e Jackson
- Maven
- Service Jersey consumindo API
