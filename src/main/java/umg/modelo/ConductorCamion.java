package umg.modelo;

public class ConductorCamion {
    private int idConductorCamion;
    private Camionero conductor;
    private Camion camion;
    private String fecha;

    public ConductorCamion() {
    }

    public ConductorCamion(int idConductorCamion, Camionero conductor, Camion camion, String fecha) {
        this.idConductorCamion = idConductorCamion;
        this.conductor = conductor;
        this.camion = camion;
        this.fecha = fecha;
    }

    public int getIdConductorCamion() {
        return idConductorCamion;
    }

    public void setIdConductorCamion(int idConductorCamion) {
        this.idConductorCamion = idConductorCamion;
    }

    public Camionero getConductor() {
        return conductor;
    }

    public void setConductor(Camionero conductor) {
        this.conductor = conductor;
    }

    public Camion getCamion() {
        return camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "ConductorCamion{" + "idConductorCamion=" + idConductorCamion + ", conductor=" + conductor + ", camion=" + camion + ", fecha=" + fecha + '}';
    }
    
    
    
    
    
    
    
    
    
    
    
}
