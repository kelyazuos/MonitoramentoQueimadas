
import java.util.ArrayList;

public class GerenciadorOcorrencias {

    private ArrayList<Ocorrencia> ocorrencias;

    public GerenciadorOcorrencias() {
        ocorrencias = new ArrayList<>();
    }

    // Cadastrar uma ocorrência
    public boolean cadastrar(Ocorrencia ocorrencia) {

        if (ocorrencia == null ||
            buscarPorId(ocorrencia.getId()) != null) {
            return false;
        }

        ocorrencias.add(ocorrencia);
        return true;
    }

    // Consultar todas as ocorrências
    public ArrayList<Ocorrencia> listar() {
        return new ArrayList<>(ocorrencias);
    }

    // Buscar ocorrência pelo ID
    public Ocorrencia buscarPorId(int id) {

        for (Ocorrencia o : ocorrencias) {
            if (o.getId() == id) {
                return o;
            }
        }

        return null;
    }

    // Alterar uma ocorrência
    public boolean alterar(int id, Ocorrencia nova) {

        if (nova == null || nova.getId() != id) {
            return false;
        }

        for (int i = 0; i < ocorrencias.size(); i++) {

            if (ocorrencias.get(i).getId() == id) {
                ocorrencias.set(i, nova);
                return true;
            }
        }

        return false;
    }

    // Excluir uma ocorrência
    public boolean excluir(int id) {

        return ocorrencias.removeIf(
            ocorrencia -> ocorrencia.getId() == id
        );
    }
}
