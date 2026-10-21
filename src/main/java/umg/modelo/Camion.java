package umg.modelo;

public class Camion {
    private int idCamion;
    private String modelo;
    private String tipo;
    private Double potencia;
    private String noPlaca;

    public Camion() {
    }

    public Camion(int idCamion, String modelo, String tipo, Double potencia, String noPlaca) {
        this.idCamion = idCamion;
        this.modelo = modelo;
        this.tipo = tipo;
        this.potencia = potencia;
        this.noPlaca = noPlaca;
    }

    public int getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(int idCamion) {
        this.idCamion = idCamion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPotencia() {
        return potencia;
    }

    public void setPotencia(Double potencia) {
        this.potencia = potencia;
    }

    public String getNoPlaca() {
        return noPlaca;
    }

    public void setNoPlaca(String noPlaca) {
        this.noPlaca = noPlaca;
    }

    @Override
    public String toString() {
        return "Camion{" + "idCamion=" + idCamion + ", modelo=" + modelo + ", tipo=" + tipo + ", potencia=" + potencia + ", noPlaca=" + noPlaca + '}';
    }
    
    
    
    
}
