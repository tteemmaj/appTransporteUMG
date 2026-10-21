
package umg.modelo;


public class Camionero {
    int idCamionero;
    String nombre;
    String apellido;
    String direccion;
    double salario;
    String poblacion;
    String dpi;
    String telefono;

    public Camionero() {
    }

    public Camionero(int idCamionero, String nombre, String apellido, String direccion, double salario, String poblacion, String dpi, String telefono) {
        this.idCamionero = idCamionero;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.salario = salario;
        this.poblacion = poblacion;
        this.dpi = dpi;
        this.telefono = telefono;
    }

    public int getIdCamionero() {
        return idCamionero;
    }

    public void setIdCamionero(int idCamionero) {
        this.idCamionero = idCamionero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Camionero{" + "idCamionero=" + idCamionero + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion + ", salario=" + salario + ", poblacion=" + poblacion + ", dpi=" + dpi + ", telefono=" + telefono + '}';
    }
    
    
    
    

    
    
    
    
}
