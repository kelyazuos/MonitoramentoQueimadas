
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.format.DateTimeParseException;

public class App {

    private static final GerenciadorOcorrencias gerenciador =
            new GerenciadorOcorrencias();

    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame janela = new JFrame(
                "Monitoramento de Queimadas no Maranhão"
            );

            janela.setSize(800, 500);
            janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            janela.setLocationRelativeTo(null);

            JPanel painel = new JPanel(new BorderLayout());
            painel.setBackground(Color.WHITE);

            JLabel titulo = new JLabel(
                "Sistema de Monitoramento de Queimadas",
                SwingConstants.CENTER
            );

            titulo.setFont(new Font("Arial", Font.BOLD, 22));
            titulo.setForeground(new Color(30, 70, 120));

            painel.add(titulo, BorderLayout.NORTH);

            JButton cadastrar =
                    new JButton("Cadastrar Ocorrência");

            JButton consultar =
                    new JButton("Consultar Ocorrências");

            JButton editar =
                    new JButton("Editar Ocorrência");

            JButton excluir =
                    new JButton("Excluir Ocorrência");

            JPanel botoes =
                    new JPanel(new GridLayout(4, 1, 10, 10));

            botoes.setBorder(
                BorderFactory.createEmptyBorder(
                    40, 150, 40, 150
                )
            );

            botoes.add(cadastrar);
            botoes.add(consultar);
            botoes.add(editar);
            botoes.add(excluir);

            painel.add(botoes, BorderLayout.CENTER);

            cadastrar.addActionListener(
                e -> abrirCadastro(janela)
            );

            consultar.addActionListener(
                e -> abrirConsulta(janela)
            );

            editar.addActionListener(
                e -> abrirEdicao(janela)
            );

            excluir.addActionListener(
                e -> abrirExclusao(janela)
            );

            janela.add(painel);
            janela.setVisible(true);
        });
    }

    // =====================================
    // FORMULARIO DE OCORRENCIA
    // =====================================

    private static Ocorrencia mostrarFormulario(
            JFrame janela, Ocorrencia existente) {

        JTextField campoId = new JTextField();
        JTextField campoMunicipio = new JTextField();
        JTextField campoData = new JTextField();
        JTextField campoTemperatura = new JTextField();
        JTextField campoUmidade = new JTextField();

        JComboBox<String> campoTipo = new JComboBox<>(
            new String[]{"Rural", "Urbana"}
        );

        if (existente != null) {

            campoId.setText(
                String.valueOf(existente.getId())
            );

            campoId.setEditable(false);

            campoMunicipio.setText(
                existente.getMunicipio()
            );

            campoData.setText(
                existente.getData()
            );

            campoTemperatura.setText(
                String.valueOf(existente.getTemperatura())
            );

            campoUmidade.setText(
                String.valueOf(existente.getUmidade())
            );

            campoTipo.setSelectedItem(
                existente instanceof QueimadaRural
                    ? "Rural" : "Urbana"
            );
        }

        JPanel formulario =
                new JPanel(new GridLayout(6, 2, 10, 10));

        formulario.add(new JLabel("ID:"));
        formulario.add(campoId);

        formulario.add(new JLabel("Município:"));
        formulario.add(campoMunicipio);

        formulario.add(new JLabel("Data (DD/MM/AAAA):"));
        formulario.add(campoData);

        formulario.add(new JLabel("Temperatura (°C):"));
        formulario.add(campoTemperatura);

        formulario.add(new JLabel("Umidade (%):"));
        formulario.add(campoUmidade);

        formulario.add(new JLabel("Tipo de queimada:"));
        formulario.add(campoTipo);

        String titulo = existente == null
                ? "Cadastrar Ocorrência"
                : "Editar Ocorrência";

        while (true) {

            int opcao = JOptionPane.showConfirmDialog(
                janela,
                formulario,
                titulo,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );

            if (opcao != JOptionPane.OK_OPTION) {
                return null;
            }

            try {

                int id = Integer.parseInt(
                    campoId.getText().trim()
                );

                String municipio =
                    campoMunicipio.getText().trim();

                String data =
                    campoData.getText().trim();

                double temperatura = Double.parseDouble(
                    campoTemperatura.getText()
                        .trim().replace(",", ".")
                );

                double umidade = Double.parseDouble(
                    campoUmidade.getText()
                        .trim().replace(",", ".")
                );

                if (id <= 0 || municipio.isEmpty()
                        || !Double.isFinite(temperatura)
                        || !Double.isFinite(umidade)
                        || umidade < 0 || umidade > 100) {

                    JOptionPane.showMessageDialog(
                        janela,
                        "Verifique os dados informados."
                    );
                    continue;
                }

                try {
                    LocalDate.parse(data, FORMATO_DATA);
                } catch (DateTimeParseException erro) {

                    JOptionPane.showMessageDialog(
                        janela,
                        "Data inválida. Use DD/MM/AAAA."
                    );
                    continue;
                }

                if (campoTipo.getSelectedItem()
                        .equals("Rural")) {

                    return new QueimadaRural(
                        id, municipio, data,
                        temperatura, umidade
                    );

                } else {

                    return new QueimadaUrbana(
                        id, municipio, data,
                        temperatura, umidade
                    );
                }

            } catch (NumberFormatException erro) {

                JOptionPane.showMessageDialog(
                    janela,
                    "ID, temperatura e umidade "
                    + "devem ser números válidos."
                );
            }
        }
    }

    // =====================================
    // CADASTRAR OCORRENCIA
    // =====================================

    private static void abrirCadastro(JFrame janela) {

        Ocorrencia ocorrencia =
                mostrarFormulario(janela, null);

        if (ocorrencia == null) {
            return;
        }

        boolean sucesso =
                gerenciador.cadastrar(ocorrencia);

        if (sucesso) {

            JOptionPane.showMessageDialog(
                janela,
                "Ocorrência cadastrada com sucesso!\n"
                + "Índice didático de risco: "
                + String.format(
                    "%.2f", ocorrencia.calcularRisco()
                )
            );

        } else {

            JOptionPane.showMessageDialog(
                janela,
                "Já existe uma ocorrência com esse ID."
            );
        }
    }

    // =====================================
    // CONSULTAR OCORRENCIAS
    // =====================================

    private static void abrirConsulta(JFrame janela) {

        ArrayList<Ocorrencia> lista =
                gerenciador.listar();

        if (lista.isEmpty()) {

            JOptionPane.showMessageDialog(
                janela,
                "Nenhuma ocorrência cadastrada."
            );
            return;
        }

        String[] colunas = {
            "ID", "Município", "Data",
            "Temperatura", "Umidade",
            "Tipo", "Risco"
        };

        Object[][] dados =
                new Object[lista.size()][7];

        for (int i = 0; i < lista.size(); i++) {

            Ocorrencia o = lista.get(i);

            dados[i][0] = o.getId();
            dados[i][1] = o.getMunicipio();
            dados[i][2] = o.getData();
            dados[i][3] = o.getTemperatura();
            dados[i][4] = o.getUmidade();

            dados[i][5] =
                o instanceof QueimadaRural
                    ? "Rural" : "Urbana";

            dados[i][6] = String.format(
                "%.2f", o.calcularRisco()
            );
        }

        JTable tabela = new JTable(dados, colunas);
        tabela.setEnabled(false);
        tabela.setRowHeight(25);
        tabela.setAutoCreateRowSorter(true);

        JScrollPane rolagem =
                new JScrollPane(tabela);

        rolagem.setPreferredSize(
            new Dimension(750, 300)
        );

        JOptionPane.showMessageDialog(
            janela,
            rolagem,
            "Ocorrências Cadastradas",
            JOptionPane.PLAIN_MESSAGE
        );
    }

    // =====================================
    // EDITAR OCORRENCIA
    // =====================================

    private static void abrirEdicao(JFrame janela) {

        String entrada = JOptionPane.showInputDialog(
            janela,
            "Digite o ID da ocorrência que deseja editar:"
        );

        if (entrada == null) {
            return;
        }

        try {

            int id = Integer.parseInt(entrada.trim());

            Ocorrencia existente =
                    gerenciador.buscarPorId(id);

            if (existente == null) {

                JOptionPane.showMessageDialog(
                    janela,
                    "Ocorrência não encontrada."
                );
                return;
            }

            Ocorrencia nova =
                    mostrarFormulario(janela, existente);

            if (nova == null) {
                return;
            }

            boolean sucesso =
                    gerenciador.alterar(id, nova);

            if (sucesso) {

                JOptionPane.showMessageDialog(
                    janela,
                    "Ocorrência atualizada com sucesso!"
                );

            } else {

                JOptionPane.showMessageDialog(
                    janela,
                    "Não foi possível atualizar a ocorrência."
                );
            }

        } catch (NumberFormatException erro) {

            JOptionPane.showMessageDialog(
                janela,
                "Digite um ID numérico válido."
            );
        }
    }

    // =====================================
    // EXCLUIR OCORRENCIA
    // =====================================

    private static void abrirExclusao(JFrame janela) {

        String entrada = JOptionPane.showInputDialog(
            janela,
            "Digite o ID da ocorrência que deseja excluir:"
        );

        if (entrada == null) {
            return;
        }

        try {

            int id = Integer.parseInt(entrada.trim());

            Ocorrencia existente =
                    gerenciador.buscarPorId(id);

            if (existente == null) {

                JOptionPane.showMessageDialog(
                    janela,
                    "Ocorrência não encontrada."
                );
                return;
            }

            int confirmacao =
                    JOptionPane.showConfirmDialog(
                        janela,
                        "Deseja realmente excluir a ocorrência "
                        + id + " de "
                        + existente.getMunicipio() + "?",
                        "Confirmar Exclusão",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                    );

            if (confirmacao != JOptionPane.YES_OPTION) {
                return;
            }

            boolean sucesso = gerenciador.excluir(id);

            if (sucesso) {

                JOptionPane.showMessageDialog(
                    janela,
                    "Ocorrência excluída com sucesso!"
                );

            } else {

                JOptionPane.showMessageDialog(
                    janela,
                    "Não foi possível excluir a ocorrência."
                );
            }

        } catch (NumberFormatException erro) {

            JOptionPane.showMessageDialog(
                janela,
                "Digite um ID numérico válido."
            );
        }
    }
}
