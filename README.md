# spring-boot-rest-teste-texo-it
API RESTful para consulta da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards.

## Requisitos
Para execução do projeto, é necessário instalação do JDK 8.
O passo-a-passo abaixo foi feito com base no VsCode.

## Configurações
- Para alterar as configurações do banco de dados com URL, usuário, senha e url do console, e ativar/desativar o console, abra o arquivo application.properties. As opções do banco H2 e as propriedades do datasource são exibidas como abaixo:
```sh
    # H2
    spring.h2.console.enabled=true
    spring.h2.console.path=/h2
    
    # Datasource
    spring.datasource.url=jdbc:h2:mem:testdb
    spring.datasource.username=sa
    spring.datasource.password=
    spring.datasource.driver-class-name=org.h2.Driver
    spring.jpa.hibernate.ddl-auto=update
```

## Para executar o projeto
Para executar o projeto, nenhuma instalação externa é necessária. Ao ser iniciada, a aplicação cria o banco de dados e o popula com os dados do arquivo movielist.csv, que se encontra em *src/main/resources/*.
1. Clone o repositório ou faça download;
2. Abra o VSCode-> Arquivo-> Abrir pasta (para importar o projeto)
3. Para iniciar a aplicação clique na classe SpringMainApplication.java com o botão direito do mouse, vá até a opção *Run java*.

## EndPoint
Foi criado um endPoint que retorna o resultado esperado, conforme solicitado nos requisitos: http://localhost:8080/worstMovie

## Testes
1. Para executar os testes, entre no menu testes do VsCode e execute o spring-boot-rest-teste-texo-it, isso fará com que o teste de integração seja executado.
2. Para alterar o cenário de testes, entre na classe SpringApplicationTests.java e coloque o nome do produtor esperado.
