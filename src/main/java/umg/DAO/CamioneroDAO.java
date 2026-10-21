
package umg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import umg.modelo.Camionero;

public class CamioneroDAO {
    public void insertar(Camionero camionero) throws SQLException {
        String sql = "INSERT INTO Camionero (idCamionero, Nombre, Apellido, Telefono, Direccion, Salario, Poblacion, Dpi)  VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setInt(1, camionero.getIdCamionero());
            ps.setString(2, camionero.getNombre());
            ps.setString(3, camionero.getApellido());
            ps.setString(4, camionero.getTelefono());
            ps.setString(5, camionero.getDireccion());
            ps.setDouble(6, camionero.getSalario());
            ps.setString(7, camionero.getPoblacion());
            ps.setString(8, camionero.getDpi());
            
       
            int filas = ps.executeUpdate();
            
            if (filas == 0) {
                throw new SQLException("No se pudo insertar el registro.");
            }
        }
    }
    public void actualizar(Camionero camionero) throws SQLException {
        String sql = "UPDATE Camionero SET Nombre=?, Apellido=?, Direccion=?, Salario=?, Poblacion=?, Dpi=?, Telefono=? WHERE idCamionero=?";
        
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setString(1, camionero.getNombre());
            ps.setString(2, camionero.getApellido());
            ps.setString(3, camionero.getDireccion());
            ps.setDouble(4, camionero.getSalario());
            ps.setString(5, camionero.getPoblacion());
            ps.setString(6, camionero.getDpi());
            ps.setString(7, camionero.getTelefono());
            ps.setInt(8, camionero.getIdCamionero());

            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se encontró el registro para actualizar.");
            }
        }
    }
    public void eliminar(int idCamionero) throws SQLException {
        String sql = "DELETE FROM Camionero WHERE idCamionero=?";
        
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setInt(1, idCamionero);

            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se encontró el registro a eliminar.");
            }
        }
    }
    
    public List<Camionero> obtenerTodos() throws SQLException {
    List<Camionero> lista = new ArrayList<>();
    String sql = "SELECT * FROM Camionero";

    try (Connection cn = ConexionDB.abrir();
         PreparedStatement ps = cn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Camionero c = new Camionero();
            c.setIdCamionero(rs.getInt("idCamionero"));
            c.setNombre(rs.getString("Nombre"));
            c.setApellido(rs.getString("Apellido")); // <--- Cambiado a "Apellido" (singular)
            c.setDireccion(rs.getString("Direccion"));
            c.setSalario(rs.getDouble("Salario"));
            c.setPoblacion(rs.getString("Poblacion"));
            c.setDpi(rs.getString("Dpi"));
            c.setTelefono(rs.getString("Telefono"));

            lista.add(c);
        }
    }
    return lista;
    }
    public Camionero buscarPorId(int idCamionero) throws SQLException {
    String sql = "SELECT * FROM Camionero WHERE idCamionero = ?";
    Camionero c = null;

    try (Connection cn = ConexionDB.abrir();
         PreparedStatement ps = cn.prepareStatement(sql)) {
        
        ps.setInt(1, idCamionero);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                c = new Camionero();
                c.setIdCamionero(rs.getInt("idCamionero"));
                c.setNombre(rs.getString("Nombre"));
                c.setApellido(rs.getString("Apellido")); // <--- Cambiado a "Apellido" (singular)
                c.setDireccion(rs.getString("Direccion"));
                c.setSalario(rs.getDouble("Salario"));
                c.setPoblacion(rs.getString("Poblacion"));
                c.setDpi(rs.getString("Dpi"));
                c.setTelefono(rs.getString("Telefono"));
            }
        }
    }
    return c;
    }
    
}
