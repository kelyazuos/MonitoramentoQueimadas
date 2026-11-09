
import javax.swing.*;
import java.awt.*;

public class App {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame janela = new JFrame(
                "Monitoramento de Queimadas no Maranhão"
            );

            janela.setSize(800, 500);
            janela.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
            );
            janela.setLocationRelativeTo(null);

            JPanel painel = new JPanel();
            painel.setLayout(new BorderLayout());
            painel.setBackground(Color.WHITE);

            JLabel titulo = new JLabel(
                "Sistema de Monitoramento de Queimadas",
                SwingConstants.CENTER
            );

            titulo.setFont(new Font(
                "Arial", Font.BOLD, 22
            ));

            titulo.setForeground(
                new Color(30, 70, 120)
            );

            painel.add(titulo, BorderLayout.NORTH);

            JButton cadastrar = new JButton(
                "Cadastrar Ocorrência"
            );

            JButton consultar = new JButton(
                "Consultar Ocorrências"
            );

            JButton editar = new JButton(
                "Editar Ocorrência"
            );

            JButton excluir = new JButton(
                "Excluir Ocorrência"
            );

            JPanel botoes = new JPanel();
            botoes.setLayout(new GridLayout(4, 1, 10, 10));
            botoes.setBorder(
                BorderFactory.createEmptyBorder(40, 150, 40, 150)
            );

            botoes.add(cadastrar);
            botoes.add(consultar);
            botoes.add(editar);
            botoes.add(excluir);

            painel.add(botoes, BorderLayout.CENTER);

            janela.add(painel);
            janela.setVisible(true);
        });
    }
}
