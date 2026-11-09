# Sistema de Monitoramento de Queimadas no Maranhão

## 1. Descrição do projeto

O Sistema de Monitoramento de Queimadas no Maranhão é uma aplicação desktop desenvolvida em Java, com o objetivo de registrar, organizar e consultar informações relacionadas a ocorrências de queimadas em municípios do estado do Maranhão.

O projeto possui finalidade acadêmica e busca aplicar conceitos de Programação Orientada a Objetos (POO), utilizando uma interface gráfica para facilitar a interação com o usuário.

## 2. Objetivos

**Objetivo geral**

Desenvolver um sistema em Java para gerenciamento de registros de ocorrências de queimadas no Maranhão.

**Objetivos específicos**

- Implementar uma interface gráfica utilizando Java Swing.
- Permitir o cadastro de ocorrências de queimadas.
- Consultar os registros cadastrados.
- Editar informações das ocorrências.
- Excluir registros.
- Aplicar os conceitos de encapsulamento, herança, polimorfismo e abstração.
- Implementar uma classificação didática de risco baseada em informações das ocorrências.

## 3. Tecnologias utilizadas

- Java JDK 21
- Java Swing
- Visual Studio Code
- Extension Pack for Java
- Git e GitHub

## 4. Estrutura do projeto

```text
MonitoramentoQueimadas/
├── .vscode/
├── bin/
├── lib/
├── src/
│   ├── App.java
│   ├── Ocorrencia.java
│   ├── QueimadaRural.java
│   ├── QueimadaUrbana.java
│   └── GerenciadorOcorrencias.java
├── .gitignore
└── README.md
```

## 5. Funcionalidades previstas

| Funcionalidade | Descrição |
|---|---|
| Cadastrar | Registrar novas ocorrências de queimadas |
| Consultar | Visualizar ocorrências cadastradas |
| Editar | Atualizar informações dos registros |
| Excluir | Remover ocorrências |
| Classificar risco | Calcular um indicador didático de risco |

## 6. Programação Orientada a Objetos

O projeto utiliza os seguintes conceitos:

- **Encapsulamento:** proteção dos atributos das classes por meio de modificadores de acesso e métodos de consulta.
- **Herança:** especialização da classe `Ocorrencia` em `QueimadaRural` e `QueimadaUrbana`.
- **Polimorfismo:** implementação do método `calcularRisco()` de diferentes maneiras nas subclasses.
- **Abstração:** utilização de uma classe abstrata para representar características comuns das ocorrências.

## 7. Interface gráfica

A interface é desenvolvida utilizando Java Swing, com uma janela principal e botões destinados às operações de gerenciamento das ocorrências.

## 8. Status do desenvolvimento

**Em desenvolvimento.**

Etapas iniciadas:

- [x] Configuração do ambiente Java
- [x] Criação do projeto no VS Code
- [x] Desenvolvimento da janela gráfica inicial
- [x] Estruturação das classes de domínio e gerenciamento
- [x] Inicialização do repositório Git
- [ ] Integração das operações de cadastro, consulta, edição e exclusão à interface
- [ ] Testes das funcionalidades
- [ ] Implementação e validação da persistência de dados
- [ ] Documentação final

## 9. Execução

Para executar o projeto durante o desenvolvimento:

1. Instale o JDK 21.
2. Abra a pasta do projeto no VS Code.
3. Instale a extensão Extension Pack for Java.
4. Abra o arquivo `src/App.java`.
5. Execute a classe principal utilizando a opção Run Java.

## 10. Observações

O sistema é um protótipo acadêmico. A classificação de risco prevista tem caráter demonstrativo e não substitui dados oficiais de monitoramento ambiental ou modelos científicos validados.
