
public class QueimadaRural extends Ocorrencia {

    public QueimadaRural(int id, String municipio,
                         String data, double temperatura,
                         double umidade) {

        super(id, municipio, data, temperatura, umidade);
    }

    @Override
    public double calcularRisco() {

        double risco = (getTemperatura() * 1.5)
                     - (getUmidade() * 0.5);

        return Math.max(0, Math.min(100, risco));
    }
}
