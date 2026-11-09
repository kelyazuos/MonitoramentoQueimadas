
public class QueimadaUrbana extends Ocorrencia {

    public QueimadaUrbana(int id, String municipio,
                          String data, double temperatura,
                          double umidade) {

        super(id, municipio, data, temperatura, umidade);
    }

    @Override
    public double calcularRisco() {

        double risco = (getTemperatura() * 1.2)
                     - (getUmidade() * 0.3);

        return Math.max(0, Math.min(100, risco));
    }
}
