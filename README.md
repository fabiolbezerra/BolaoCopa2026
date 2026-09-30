# Bolão dos Palpiteiros da UFRA

Aplicação para gerenciamento de Bolão da Copa do Mundo de 2026, baseada em uma aplicação web. 
Desenvolvido como projeto para a comunidade da Universidade Federal Rural da Amazônia (UFRA), 
com o objetivo de proporcionar uma competição saudável entre os palpiteiros durante a Copa do 
Mundo de 2026.

## 📣 Apresentação

A Copa do Mundo da FIFA mobiliza comunidades em um movimento espontâneo de 
integração e entretenimento, comumente desdobrado em "bolões" informais geridos 
por papéis ou planilhas. Na universidade, contudo, essas abordagens analógicas 
carecem de escalabilidade, segurança e integridade de dados para conectar centenas 
de estudantes, docentes e técnicos. Nesse contexto, o evento esportivo surge como 
um catalisador para integrar a comunidade acadêmica por meio de uma plataforma web 
gratuita e segura.

A concepção do projeto emergiu na disciplina de Programação Orientada a Objetos (POO), 
ao observar-se o elevado engajamento da turma no cumprimento dos desafios práticos. A 
convergência entre alunos motivados e a proximidade da Copa do Mundo de 2026 viabilizou 
um convite aberto para a criação voluntária da aplicação "Bolão dos Palpiteiros da UFRA". 
O desafio aliou o engajamento esportivo ao aprendizado prático de Engenharia de Software, 
oferecendo motivação tripla aos discentes: domínio de uma _stack web_, enriquecimento do 
repertório intelectual e satisfação de conceber um produto real para uso da comunidade . 
Assim, o projeto funcionou como um laboratório prático para impulsionar o crescimento 
profissional de alunos matriculados na primeira metade do curso de Bacharelado em Sistemas 
de Informação.

## ⚡ Tecnologias Utilizadas

* **Java 17** - Linguagem principal do projeto.
* **JSF 2.3.9** - Framework utilizado para construção da interface web.
* **PrimeFaces 8.0** - Biblioteca para construção dos componentes da interface gráfica.
* **Spring Security 3.0.5** - Framework utilizado para autenticação e controle de acesso.
* **EclipseLink 2.7.12 / JPA** - Framework utilizado para persistência e mapeamento objeto-relacional.
* **MySQL 8.0.33** - Banco de dados relacional utilizado para persistência dos dados.
* **JDBC** - Java Database Connectivity para comunicação com o banco de dados.
* **Maven** - Gerenciador de dependências e ferramentas de compilação.

## 🚀 Funcionalidades da Aplicação

O sistema atende de forma integral aos principais requisitos solicitados:

* **Cadastro de Usuários**: Formulário para criação e gerenciamento de usuários.
* **Gerenciamento de Bolões**: Criação, listagem e gerenciamento de bolões.
* **Cadastro de Partidas**: Cadastro e gerenciamento das partidas da Copa do Mundo.
* **Cadastro de Seleções**: Cadastro e gerenciamento das seleções participantes.
* **Cadastro de Grupos**: Organização das seleções nos grupos da competição.
* **Cadastro de Fases**: Gerenciamento das fases da Copa do Mundo.
* **Registro de Palpites**: Permite aos usuários registrar seus palpites para as partidas.
* **Ranking**: Exibição da classificação dos participantes de acordo com a pontuação obtida.
* **Estatísticas**: Exibição de estatísticas relacionadas aos usuários e aos palpites.
* **Persistência de Dados**: Persistência das informações utilizando banco de dados MySQL.


## 📄 Artigo Científico

Como resultado do processo de concepção, engenharia e avaliação deste projeto, a equipe 
produziu um [artigo científico](ERIN_2026_Bolao.pdf). O trabalho relata a 
experiência vivenciada no desenvolvimento da plataforma e foi aceito e publicado 
nos anais da **Escola Regional de Informática Norte (ERIN 2026)**, integrando a 
lista oficial de [artigos aceitos do evento](https://erin2.sbc.org.br/2026/artigos-aceitos).

### Apresentando um Motor Pedagógico de Desenvolvimento Intelectual em Engenharia de Software

No ensino de Sistemas de Informação, a transição da teoria para a prática é um 
desafio recorrente. Para mitigar essa lacuna, adotou-se o desenvolvimento 
voluntário da aplicação web "Bolão dos Palpiteiros da UFRA" para a Copa do Mundo 
de 2026. Este relato de experiência descreve a vivência de quatro discentes do 
terceiro semestre em uma universidade pública. A imersão durou 45 dias de 
desenvolvimento e 30 de operação, abordando o ciclo de vida completo do software 
com mentoria docente e apoio de IA Generativa. Os resultados evidenciam entregas 
tangíveis (28 histórias de usuário, 66 issues, 14 entidades e 201 commits), 
avanço no domínio da stack tecnológica (Java, JSF, JPA, MySQL, Git) e consolidação 
do pensamento orientado a objetos, ou seja, um desenvolvimento de competências em 
engenharia de software.


## 📁 Estrutura do Projeto

```text
BolaoCopa2026/
├── .mvn/                           # Arquivos do Maven Wrapper.
│   └── wrapper/
├── src/
│   ├── docs/                       # Scripts e documentação do banco de dados.
│   │   ├── criacao.sql
│   │   ├── inserts.sql
│   │   ├── usuarios.sql
│   │   ├── palpiteiros.sql
│   │   └── bolao-copa-ER.mwb
│   │
│   ├── main/
│   │   ├── java/
│   │   │   └── br/
│   │   │       └── edu/
│   │   │           └── ufra/
│   │   │               ├── bean/                 # Beans da aplicação.
│   │   │               │   ├── converter/
│   │   │               │   ├── validator/
│   │   │               │   └── *.java
│   │   │               │
│   │   │               ├── dao/                  # Acesso aos dados.
│   │   │               │   ├── GenericDAO.java
│   │   │               │   ├── InterfaceDAO.java
│   │   │               │   └── *.java
│   │   │               │
│   │   │               ├── entidade/             # Entidades do domínio.
│   │   │               │   ├── tipo/
│   │   │               │   ├── comparador/
│   │   │               │   └── *.java
│   │   │               │
│   │   │               ├── processamento/        # Processamento dos resultados.
│   │   │               │   ├── ManipuladorBuilder.java
│   │   │               │   ├── ManipuladorResultadoAbstrato.java
│   │   │               │   └── *.java
│   │   │               │
│   │   │               ├── rn/                    # Regras de negócio.
│   │   │               │   ├── estatistica/
│   │   │               │   ├── email/
│   │   │               │   └── *.java
│   │   │               │
│   │   │               ├── seguranca/             # Autenticação e segurança.
│   │   │               │   ├── AuthenticationHandler.java
│   │   │               │   └── LoginHandler.java
│   │   │               │
│   │   │               └── util/                  # Classes auxiliares.
│   │   │
│   │   ├── resources/
│   │   │   └── META-INF/
│   │   │       └── persistence.xml                # Configuração da persistência.
│   │   │
│   │   └── webapp/                                # Interface web da aplicação.
│   │       ├── administrador/
│   │       ├── palpiteiro/
│   │       ├── resources/
│   │       ├── WEB-INF/
│   │       ├── index.xhtml
│   │       └── login.xhtml
│   │
│   └── test/                                      # Testes da aplicação.
│
├── mvnw                                            # Maven Wrapper.
├── pom.xml                                         # Dependências e configuração do Maven.
└── README.md                                       # Documentação do projeto.
```

## 🛠️ Como Executar

Existem algumas maneiras de executar este projeto. Escolha a que for mais conveniente para você.

### 1. Executando localmente (própria máquina)

Se preferir rodar o projeto em sua própria máquina, siga estes passos:

#### Pré-requisitos

* `Git`: Para clonar o repositório
* `Java JDK 17 ou superior`: Para compilar e executar o código.
* `MySQL 8.0`: Para persistência dos dados.
* `Apache Tomcat`: Para executar a aplicação web.

#### Passos para Execução Local

1. **Clonar o Repositório:** Abra o terminal na sua máquina e execute o comando abaixo para clonar o projeto:

```bash
git clone https://github.com/fabiolbezerra/BolaoCopa2026.git
cd BolaoCopa2026
```

2. Antes de iniciar a aplicação, prepare a base de dados executando os scripts SQL que estão dentro dos arquivos .sql localizados no diretório `./src/docs/`.
A execução pode ser feita pelo terminal, pelo MySQL Workbench ou pela ferramenta de banco da sua IDE, obrigatoriamente nesta ordem:

   1. [`criação.sql`](./src/docs/criacao.sql)
   2. [`inserts.sql`](./src/docs/inserts.sql) 
   3. [`palpiteiros.sql`](./src/docs/palpiteiros.sql) 
   4. [`usuarios.sql`](./src/docs/usuarios.sql)

3. Execute a Aplicação: O projeto inclui um **Maven Wrapper**, então você não precisa instalá-lo. Para compilar a aplicação, use o comando:

```bash
./mvnw clean package
```
> [!NOTE]
> **Para usuários Linux / macOS:**  
> Caso o terminal retorne o erro `Permissão negada`, conceda permissão de execução ao script antes de compilar:
> ```bash
> chmod +x mvnw
> ./mvnw clean package
> ```

Após a compilação, o arquivo `.war` será gerado na pasta `./target/`.

O arquivo gerado será:

```text
bolao-copa-2026-1.0.war
```

Esse arquivo deverá ser implantado no servidor Apache Tomcat.


## ⚖️ Licença e Proprietários

Copyright © 2026 Antônio Henrique Costa, Fábio Bezerra, Gabriel Chaves, Luis Paulo Neto e Murillo Santo. 

Registrado no Instituto Nacional da Propriedade Industrial (INPI) sob o nº `[Inserir o número do processo do INPI após o protocolo]`.

Este projeto é um software livre e está licenciado sob a Licença MIT - consulte o arquivo [LICENSE](LICENSE) para mais detalhes.


## 🤝 Colaboradores

Um agradecimento a todas as pessoas que contribuíram com este projeto:

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/HenriqueCavallero" title="Github de Antonio Henrique Cavalléro Pontes Costa">
        <img src="https://avatars.githubusercontent.com/u/213304769?v=4" width="100px; height=100px" alt="Foto de Antonio Henrique Cavalléro Pontes Costa no GitHub"/><br>
        <sub>
          <b>Antonio Henrique Costa</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/fabiolbezerra" title="Github de Fábio de Lima Bezerra">
        <img src="https://avatars.githubusercontent.com/u/107626312?v=4" width="100px; height=100px" alt="Foto de Fábio de Lima Bezerra no GitHub"/><br>
        <sub>
          <b>Fábio Bezerra</b>
        </sub>
      </a>
    </td>     
    <td align="center">
      <a href="https://github.com/Gab9082" title="Github de Gabriel da Silva Chaves">
        <img src="https://avatars.githubusercontent.com/u/230392743?v=4" width="100px; height=100px" alt="Foto de Gabriel da Silva Chaves no GitHub"/><br>
        <sub>
          <b>Gabriel Chaves</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/pauloglorianeto-cmyk" title="Github de Luis Paulo da Silva Glória Neto">
        <img src="https://avatars.githubusercontent.com/u/230487034?v=4" width="100px; height=100px" alt="Foto de Luis Paulo da Silva Glória Neto no GitHub"/><br>
      <sub>
        <b>Luis Paulo Neto</b>
      </sub>
      </a>
    </td>
      <td align="center">
      <a href="https://github.com/murillofes" title="Github de Murillo Freire Espirito Santo">
        <img src="https://avatars.githubusercontent.com/u/139729137?v=4" width="100px; height=100px" alt="Foto de Murillo Freire Espirito Santo no GitHub"/><br>
        <sub>
          <b>Murillo Santo</b>
        </sub>
      </a>
    </td>
  </tr>
</table>

---

<div align="center">

**Obrigado pela visita!**

</div>
