package umg.modelo;

public class Paquete {
    private int idPaquete;
    private String descripcion;
    private String destinatario;
    private Double peso;
    private String Direccion;
    private Camionero camionero;
    private Ciudad ciudad;

    public Paquete() {
    }

    public Paquete(int idPaquete, String descripcion, String destinatario, Double peso, String Direccion, Camionero camionero, Ciudad ciudad) {
        this.idPaquete = idPaquete;
        this.descripcion = descripcion;
        this.destinatario = destinatario;
        this.peso = peso;
        this.Direccion = Direccion;
        this.camionero = camionero;
        this.ciudad = ciudad;
    }

    public int getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public Camionero getCamionero() {
        return camionero;
    }

    public void setCamionero(Camionero camionero) {
        this.camionero = camionero;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Paquete{" + "idPaquete=" + idPaquete + ", descripcion=" + descripcion + ", destinatario=" + destinatario + ", peso=" + peso + ", Direccion=" + Direccion + ", camionero=" + camionero + ", ciudad=" + ciudad + '}';
    }
    
    
    
}
