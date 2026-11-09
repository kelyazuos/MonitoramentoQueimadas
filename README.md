
# Sistema de Monitoramento de Queimadas no Maranhão

## 1. Apresentação

Projeto acadêmico desenvolvido para a disciplina de
Paradigmas de Programação, da Universidade Federal
do Maranhão (UFMA), como parte da 3ª avaliação.

O sistema consiste em uma aplicação desktop
desenvolvida em Java, com interface gráfica
construída utilizando a biblioteca Swing.

A aplicação permite cadastrar, consultar, alterar
e excluir registros de ocorrências de queimadas
em municípios do Maranhão.

## 2. Objetivo

Desenvolver uma aplicação que demonstre, na prática,
a utilização dos conceitos de Programação Orientada
a Objetos (POO), especialmente herança, polimorfismo,
abstração e encapsulamento.

O projeto também utiliza Collections para o
gerenciamento dos registros e arquivos locais
para o armazenamento permanente das informações.

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

## 4. Tecnologias utilizadas

- Linguagem Java (JDK 21).
- Biblioteca Swing (javax.swing).
- Java Collections Framework (ArrayList).
- Manipulação de arquivos com java.nio.file.
- Visual Studio Code.
- Git e GitHub.

## 5. Organização das classes

### Ocorrencia.java

Classe abstrata que representa uma ocorrência
de queimada.

Contém os atributos:

- ID
- Município
- Data
- Temperatura
- Umidade

Declara o método abstrato calcularRisco(),
que deve ser implementado pelas subclasses.

### QueimadaRural.java

Classe que herda de Ocorrencia.

Implementa calcularRisco() utilizando uma
fórmula específica para ocorrências rurais.

### QueimadaUrbana.java

Classe que herda de Ocorrencia.

Implementa calcularRisco() utilizando uma
fórmula específica para ocorrências urbanas.

### GerenciadorOcorrencias.java

Responsável pelo gerenciamento dos registros.

Utiliza ArrayList<Ocorrencia> para armazenar
as ocorrências durante a execução.

Implementa os métodos:

- cadastrar()
- listar()
- buscarPorId()
- alterar()
- excluir()

Também realiza a leitura e a gravação
dos registros em arquivo CSV.

### App.java

Classe principal da aplicação.

Implementa a interface gráfica com Java Swing
e permite ao usuário acessar as funcionalidades
de cadastro, consulta, edição e exclusão.

## 6. Conceitos de Programação Orientada a Objetos

### Abstração

A classe Ocorrencia é abstrata e define
características comuns às ocorrências de queimadas.

### Encapsulamento

Os atributos são acessados por métodos específicos,
como getTemperatura() e getUmidade().

### Herança

As classes QueimadaRural e QueimadaUrbana
herdam características da classe Ocorrencia.

### Polimorfismo

O método calcularRisco() possui implementações
diferentes nas subclasses QueimadaRural e
QueimadaUrbana.

Dessa forma, objetos de diferentes subclasses
podem ser tratados por referências do tipo
Ocorrencia, executando o comportamento
correspondente ao seu tipo.

### Collections

O sistema utiliza ArrayList<Ocorrencia>
para gerenciar dinamicamente os registros.

## 7. Persistência de dados

Os registros são armazenados no arquivo:

dados/ocorrencias.csv

O arquivo é criado automaticamente quando
uma ocorrência é salva.

Ao iniciar novamente o programa, os registros
armazenados são recuperados.

O campo município é codificado em Base64 para
evitar conflitos com o separador utilizado
no arquivo.

A pasta dados não é enviada ao GitHub,
conforme a configuração do .gitignore.

## 8. Cálculo do índice de risco

O sistema utiliza fórmulas didáticas distintas
para as ocorrências rurais e urbanas.

Queimada rural:

R = (temperatura * 1.5) - (umidade * 0.5)

Queimada urbana:

R = (temperatura * 1.2) - (umidade * 0.3)

Os resultados são limitados ao intervalo
de 0 a 100.

Essas fórmulas têm finalidade exclusivamente
acadêmica e não representam um modelo científico
validado de previsão de queimadas.

## 9. Testes realizados

Foram realizados testes manuais para verificar:

- Cadastro de ocorrências rurais e urbanas.
- Consulta dos registros cadastrados.
- Alteração dos dados de uma ocorrência.
- Exclusão de registros.
- Identificação de IDs duplicados.
- Recuperação dos dados após reiniciar o programa.

## 10. Considerações finais

O desenvolvimento do sistema permitiu aplicar
conceitos fundamentais da Programação Orientada
a Objetos em uma aplicação Java com interface
gráfica.

A utilização de herança e polimorfismo
possibilitou a especialização dos tipos de
ocorrência, enquanto o uso de Collections
facilitou o gerenciamento dos registros.

A persistência em arquivo permitiu manter
as informações entre diferentes execuções
do programa.


# Universidade Federal do Maranhão (UFMA)
# Disciplina: Paradigmas de Programação
# Discentes: Kely Souza, Gabriel Gama, Matheus Alexsander
# Projeto: Sistema de Monitoramento de Queimadas no Maranhão
