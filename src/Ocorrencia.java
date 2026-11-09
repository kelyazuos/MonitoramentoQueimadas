
public abstract class Ocorrencia {

    private int id;
    private String municipio;
    private String data;
    private double temperatura;
    private double umidade;

    public Ocorrencia(int id, String municipio,
                      String data, double temperatura,
                      double umidade) {

        this.id = id;
        this.municipio = municipio;
        this.data = data;
        this.temperatura = temperatura;
        this.umidade = umidade;
    }

    public int getId() {
        return id;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getData() {
        return data;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public double getUmidade() {
        return umidade;
    }

    public abstract double calcularRisco();
}
