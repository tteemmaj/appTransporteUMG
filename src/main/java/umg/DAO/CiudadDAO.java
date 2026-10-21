package umg.DAO;
import umg.modelo.Ciudad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;


public class CiudadDAO {
    
    public void insertar(Ciudad ciudad) throws SQLException {
        String sql = "INSERT INTO Ciudad (nombre) VALUES(?)";
        
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
             
            ps.setString(1, ciudad.getNombre());
            
            int filas = ps.executeUpdate();
            
            if (filas == 0) {
                throw new SQLException("No se pudo insertar el registro.");
            }
        }
    }
    public void actualizar(Ciudad ciudad) throws SQLException {
        String sql = "UPDATE Ciudad SET Nombre=? WHERE idCiudad = ?;";
        
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
             
            ps.setString(1, ciudad.getNombre());
            ps.setInt(2, ciudad.getIdCiudad());
            
            int filas = ps.executeUpdate();
            
            if (filas == 0) {
                throw new SQLException("No se pudo insertar el registro."); 
            }
        }
    }
    public void eliminar (int id) throws SQLException {
        String sql = "DELETE from Ciudad where idCiudad=?";
        
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
             
            ps.setInt(1, id);
            
            int filas = ps.executeUpdate();
            
            if (filas == 0) {
                throw new SQLException("No se pudo insertar el registro.");
            }
        }
    }
    public List<Ciudad> listar() throws SQLException {
        List<Ciudad> ciudadlista = new ArrayList<>();
        String sql = "select idCiudad, Nombre from Ciudad;";
        
        try (Connection cn = ConexionDB.abrir(); 
                PreparedStatement ps = cn.prepareStatement(sql); 
                ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Ciudad ciudad = new Ciudad();
                // Asignar los valores obtenidos de MySQL
                
                ciudad.setIdCiudad(rs.getInt("idCiudad"));
                ciudad.setNombre(rs.getString("Nombre"));
                
                
                ciudadlista.add(ciudad);
            }
        }
        return ciudadlista;
    }
}