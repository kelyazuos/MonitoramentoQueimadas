# Sistema de Monitoramento de Queimadas no Maranhão

**Universidade Federal do Maranhão (UFMA)**  
**Disciplina:** Paradigmas de Programação  
**Professora:** Vandecia Rejane Monteiro Fernandes  
**Discentes:** Kely Souza, Gabriel Gama e Matheus Alexsander  
**Avaliação:** 3ª Avaliação — 2026

## 1. Apresentação

Projeto acadêmico desenvolvido para a disciplina de Paradigmas de Programação da Universidade Federal do Maranhão (UFMA), para obtenção da terceira nota.

O sistema consiste em uma aplicação desktop desenvolvida em Java, com interface gráfica construída utilizando a biblioteca Swing. Permite cadastrar, consultar, alterar e excluir registros de ocorrências de queimadas em municípios do Maranhão.

## 2. Objetivo

Desenvolver uma aplicação que demonstre, na prática, a utilização dos conceitos de Programação Orientada a Objetos (POO), especialmente herança, polimorfismo, abstração e encapsulamento.

O projeto também utiliza Collections para o gerenciamento dos registros e arquivos locais para o armazenamento permanente das informações.

## 3. Funcionalidades

O sistema oferece as seguintes operações:

- Cadastrar ocorrências rurais e urbanas.
- Consultar as ocorrências em uma tabela.
- Alterar os dados de registros existentes.
- Excluir registros mediante confirmação.
- Impedir o cadastro de IDs duplicados.
- Validar os dados informados pelo usuário.
- Calcular um índice didático de risco.
- Salvar e recuperar os registros em arquivo CSV.

## 4. Tecnologias utilizadas e requisitos

| Tecnologia | Especificação |
|---|---|
| Linguagem | Java 21 |
| JDK utilizado | Eclipse Temurin 21.0.13 (OpenJDK) |
| IDE | IntelliJ IDEA 2026.3 e Visual Studio Code |
| Interface gráfica | Java Swing (`javax.swing`) |
| Collections | Java Collections Framework (`ArrayList`) |
| Controle de versão | Git e GitHub |
| Persistência | Arquivo CSV |

### Requisitos de execução

Para compilar e executar o projeto, recomenda-se utilizar o **JDK 21**, com o nível de linguagem configurado para Java 21.

A aplicação utiliza exclusivamente bibliotecas padrão do Java, não sendo necessária a instalação de dependências externas.

**Configurações do projeto:**

- **Project SDK:** JDK 21.
- **Language level:** Java 21 (sem Preview).
- **Sources Root:** pasta `src`.
- **Classe principal:** `App.java`.
- **Working directory:** pasta raiz do projeto, para acesso correto ao arquivo `dados/ocorrencias.csv`.

## 5. Organização das classes

### Ocorrencia.java

Classe abstrata que representa uma ocorrência de queimada e reúne os atributos ID, município, data, temperatura e umidade.

Declara o método abstrato `calcularRisco()`, que deve ser implementado pelas subclasses.

### QueimadaRural.java

Classe que herda de `Ocorrencia` e implementa o método `calcularRisco()` utilizando uma fórmula específica para ocorrências rurais.

### QueimadaUrbana.java

Classe que herda de `Ocorrencia` e implementa o método `calcularRisco()` utilizando uma fórmula específica para ocorrências urbanas.

### GerenciadorOcorrencias.java

Responsável pelo gerenciamento dos registros, utilizando `ArrayList<Ocorrencia>` para armazenar as ocorrências durante a execução.

Implementa os métodos:

- `cadastrar()`
- `listar()`
- `buscarPorId()`
- `alterar()`
- `excluir()`

Também realiza a leitura e a gravação dos registros em arquivo CSV.

### App.java

Classe principal da aplicação, responsável pela interface gráfica desenvolvida com Java Swing e pelo acesso às funcionalidades de cadastro, consulta, edição e exclusão.

## 6. Conceitos de Programação Orientada a Objetos

### Abstração

A classe `Ocorrencia` é abstrata e define características comuns às ocorrências de queimadas.

### Encapsulamento

Os atributos são acessados por métodos específicos, como `getTemperatura()` e `getUmidade()`.

### Herança

As classes `QueimadaRural` e `QueimadaUrbana` herdam características da classe `Ocorrencia`.

### Polimorfismo

O método `calcularRisco()` possui implementações diferentes nas subclasses `QueimadaRural` e `QueimadaUrbana`.

Dessa forma, objetos de diferentes subclasses podem ser tratados por referências do tipo `Ocorrencia`, executando o comportamento correspondente ao seu tipo.

### Collections

O sistema utiliza `ArrayList<Ocorrencia>` para gerenciar dinamicamente os registros.

## 7. Persistência de dados

Os registros são armazenados no arquivo:

`dados/ocorrencias.csv`

O arquivo é criado automaticamente quando uma ocorrência é salva. Ao iniciar novamente o programa, os registros armazenados são recuperados.

O campo município é codificado em Base64 para evitar conflitos com o separador utilizado no arquivo.

A pasta `dados` não é enviada ao GitHub, conforme a configuração do `.gitignore`, mas está incluída no arquivo ZIP.

## 8. Cálculo do índice de risco

O sistema utiliza fórmulas didáticas distintas para as ocorrências rurais e urbanas.

**Queimada rural:**

`R = (temperatura × 1,5) − (umidade × 0,5)`

**Queimada urbana:**

`R = (temperatura × 1,2) − (umidade × 0,3)`

Os resultados são limitados ao intervalo de 0 a 100.

Essas fórmulas têm finalidade exclusivamente acadêmica e não representam um modelo científico validado de previsão de queimadas.

## 9. Testes realizados

Foram realizados testes manuais para verificar:

- Cadastro de ocorrências rurais e urbanas.
- Consulta dos registros cadastrados.
- Alteração dos dados de uma ocorrência.
- Exclusão de registros.
- Identificação de IDs duplicados.
- Recuperação dos dados após reiniciar o programa.
- Abertura e execução do projeto no IntelliJ IDEA a partir de uma cópia extraída do ZIP.

## 10. Considerações finais

O desenvolvimento do sistema permitiu aplicar conceitos fundamentais da Programação Orientada a Objetos em uma aplicação Java com interface gráfica.
A utilização de herança e polimorfismo possibilitou a especialização dos tipos de ocorrência, enquanto o uso de Collections facilitou o gerenciamento dos registros.

A persistência em arquivo permitiu manter as informações entre diferentes execuções do programa.
