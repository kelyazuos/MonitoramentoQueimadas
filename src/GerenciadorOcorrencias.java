
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.Base64;

public class GerenciadorOcorrencias {

    private ArrayList<Ocorrencia> ocorrencias;

    private static final Path ARQUIVO =
            Paths.get("dados", "ocorrencias.csv");

    public GerenciadorOcorrencias() {
        ocorrencias = new ArrayList<>();
        carregarDados();
    }

    // CADASTRAR
    public boolean cadastrar(Ocorrencia ocorrencia) {

        if (ocorrencia == null ||
            buscarPorId(ocorrencia.getId()) != null) {
            return false;
        }

        ocorrencias.add(ocorrencia);
        salvarDados();
        return true;
    }

    // LISTAR
    public ArrayList<Ocorrencia> listar() {
        return new ArrayList<>(ocorrencias);
    }

    // BUSCAR POR ID
    public Ocorrencia buscarPorId(int id) {

        for (Ocorrencia o : ocorrencias) {
            if (o.getId() == id) {
                return o;
            }
        }

        return null;
    }

    // ALTERAR
    public boolean alterar(int id, Ocorrencia nova) {

        if (nova == null || nova.getId() != id) {
            return false;
        }

        for (int i = 0; i < ocorrencias.size(); i++) {

            if (ocorrencias.get(i).getId() == id) {
                ocorrencias.set(i, nova);
                salvarDados();
                return true;
            }
        }

        return false;
    }

    // EXCLUIR
    public boolean excluir(int id) {

        boolean removido = ocorrencias.removeIf(
            ocorrencia -> ocorrencia.getId() == id
        );

        if (removido) {
            salvarDados();
        }

        return removido;
    }

    // SALVAR DADOS NO ARQUIVO
    private void salvarDados() {

        try {

            Files.createDirectories(ARQUIVO.getParent());

            ArrayList<String> linhas = new ArrayList<>();

            for (Ocorrencia o : ocorrencias) {

                String tipo = o instanceof QueimadaRural
                        ? "Rural" : "Urbana";

                // Base64 evita conflitos com o separador CSV.
                String municipio = Base64.getEncoder()
                    .encodeToString(
                        o.getMunicipio().getBytes(
                            StandardCharsets.UTF_8
                        )
                    );

                String linha = o.getId() + ";"
                        + municipio + ";"
                        + o.getData() + ";"
                        + o.getTemperatura() + ";"
                        + o.getUmidade() + ";"
                        + tipo;

                linhas.add(linha);
            }

            Files.write(
                ARQUIVO,
                linhas,
                StandardCharsets.UTF_8
            );

        } catch (IOException erro) {

            throw new IllegalStateException(
                "Erro ao salvar ocorrências: "
                + erro.getMessage(), erro
            );
        }
    }

    // CARREGAR DADOS DO ARQUIVO
    private void carregarDados() {

        if (!Files.exists(ARQUIVO)) {
            return;
        }

        try {

            for (String linha : Files.readAllLines(
                    ARQUIVO, StandardCharsets.UTF_8)) {

                if (linha.isBlank()) {
                    continue;
                }

                String[] partes = linha.split(";", -1);

                if (partes.length != 6) {
                    throw new IllegalArgumentException(
                        "Registro com quantidade incorreta de campos."
                    );
                }

                int id = Integer.parseInt(partes[0]);

                String municipio = new String(
                    Base64.getDecoder().decode(partes[1]),
                    StandardCharsets.UTF_8
                );

                String data = partes[2];
                double temperatura =
                        Double.parseDouble(partes[3]);

                double umidade =
                        Double.parseDouble(partes[4]);

                String tipo = partes[5];

                Ocorrencia ocorrencia;

                if (tipo.equals("Rural")) {

                    ocorrencia = new QueimadaRural(
                        id, municipio, data,
                        temperatura, umidade
                    );

                } else if (tipo.equals("Urbana")) {

                    ocorrencia = new QueimadaUrbana(
                        id, municipio, data,
                        temperatura, umidade
                    );

                } else {

                    throw new IllegalArgumentException(
                        "Tipo de ocorrência desconhecido: " + tipo
                    );
                }

                if (buscarPorId(id) != null) {
                    throw new IllegalArgumentException(
                        "ID duplicado no arquivo: " + id
                    );
                }

                ocorrencias.add(ocorrencia);
            }

        } catch (IOException | IllegalArgumentException erro) {

            throw new IllegalStateException(
                "Erro ao carregar ocorrências: "
                + erro.getMessage(), erro
            );
        }
    }
}
