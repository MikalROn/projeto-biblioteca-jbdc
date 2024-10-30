# Instruções para Rodar o Projeto

O projeto foi desenvolvido e testado em ambiente windows 11

## Requisitos

Certifique-se de ter os seguintes pré-requisitos instalados:

- Java JDK 8 ou superior
- Maven
- Docker Compose

## Banco de dados

Para iniciar o banco de dados basta usar o comando:

```bash
docker-compose up -d
```

docker ja inicia o banco de dados rodando os scripts .sql na pasta docs por padrão

- Em caso de não usar docker segue as credencias usadas no sistema
```java
public class GerenciaDados {
    private final String URL = "jdbc:mysql://localhost:3306/biblioteca";
    private final String USER = "user";
    private final String SENHA = "password";
}
```

###  Modelagem do Banco de dados

Todos os arquivos de dados encontão-se na pasta docs (Documentação)


## Construindo o Projeto

Para construir o projeto, navegue até o diretório do projeto e execute o seguinte comando:

```bash
mvn clean install
```

## Executando o Projeto
Para executar o aplicativo, use o seguinte comando, substituindo com.seupacote.Main pela classe principal do seu projeto:

```bash
java -jar target/projeto-biblioteca-jdbc-1.0-SNAPSHOT.jar

```

