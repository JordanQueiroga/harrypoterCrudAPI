# 📝Sobre

Foi criado um CRUD para a criação de personagens de Harry Potter com as opções de:

- Criar um novo personagem
- Ler um único ou vários personagens podendo ser filtrado pela casa
- Atualizar um único personagem
- Deletar um único personagem

Além de se conectar com uma API externa para validar se a casa que o usuário informou é válida

## 👨‍💻 Tecnologias utilizadas:

:heavy_check_mark: Java 8 <br/>
:heavy_check_mark: Maven <br/>
:heavy_check_mark: Spring Boot <br/>
:heavy_check_mark: Spring JPA <br/>
:heavy_check_mark: Spring boot dev tools<br/>
:heavy_check_mark: Spring boot starter cache<br/>
:heavy_check_mark: Swagger<br/>
:heavy_check_mark: JUnit 4<br/>
:heavy_check_mark: MySql (5.5.0)<br/>
:heavy_check_mark: H2 database<br/>
:heavy_check_mark: Redis<br/>

## 🚀 Como rodar o projeto

Antes de iniciarmos a nossa aplicação, precisamos primeiro dos nossos bancos de dados, **MySql v5.5.0** e **Redis**, você pode escolher em instalar esses bancos em sua máquina ou instalar o **Docker** e executar alguns comandos do Docker os comandos:

Para inicializar o banco de dados MySql v5.5.0:

```
docker run --name jordan_harry_potter_mysql -e MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 -d mysql:5.5.40
```

Para inicializar o banco de dados Redis:

```
docker run --name jordan_harry_potter_redis -p 6379:6379 -d -t redis:alpine
```

Pronto!!! Com os dois DB em execução você precisará agora do **Maven** instalado na sua máquina para gerarmos o nosso build executando o comando:

```
mvn clean install
```

Com isso ele irá gerar o nosso arquivo **.jar** dentro da pasta **target**. Com o java instalado em sua máquina execute-o.
Finalmente nossa aplicação está rodando,para testarmos se realmente está rodando vamos entrar na documentação interativa da API gerado pelo **Swagger** clicando [aqui](http://localhost:8080/swagger-ui.html#/).

Se deu tudo certo, você deverá estar testando minha aplicação agora em uma tela como esta. :tada: :tada: :tada:

![swegger](https://user-images.githubusercontent.com/22600632/90342304-8a57d580-dfdd-11ea-8c0f-0923f0c0bd08.png)
