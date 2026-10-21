
package umg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import umg.modelo.Camion;

public class CamionDAO {

    public void insertar(Camion camion) throws SQLException {
        String sql = "INSERT INTO Camion (idCamion, Modelo, Tipo, Potencia, NoPlaca) VALUES (?, ?, ?, ?, ?);";

        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, camion.getIdCamion());
            ps.setString(2, camion.getModelo());
            ps.setString(3, camion.getTipo());
            
            if (camion.getPotencia() != null) {
                ps.setDouble(4, camion.getPotencia());
            } else {
                ps.setNull(4, java.sql.Types.DOUBLE);
            }
            
            ps.setString(5, camion.getNoPlaca());

            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo insertar el registro del camión.");
            }
        }
    }

    public void actualizar(Camion camion) throws SQLException {
        String sql = "UPDATE Camion SET Modelo = ?, Tipo = ?, Potencia = ?, NoPlaca = ? WHERE idCamion = ?";

        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, camion.getModelo());
            ps.setString(2, camion.getTipo());
            
            if (camion.getPotencia() != null) {
                ps.setDouble(3, camion.getPotencia());
            } else {
                ps.setNull(3, java.sql.Types.DOUBLE);
            }
            
            ps.setString(4, camion.getNoPlaca());
            ps.setInt(5, camion.getIdCamion());

            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se encontró el camión para actualizar.");
            }
        }
    }

    public void eliminar(int idCamion) throws SQLException {
        String sql = "DELETE FROM Camion WHERE idCamion = ?";

        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idCamion);

            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se encontró el camión a eliminar.");
            }
        }
    }

    public List<Camion> obtenerTodos() throws SQLException {
        List<Camion> lista = new ArrayList<>();
        String sql = "SELECT * FROM Camion";

        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Camion c = new Camion();
                c.setIdCamion(rs.getInt("idCamion"));
                c.setModelo(rs.getString("Modelo"));
                c.setTipo(rs.getString("Tipo"));
                c.setPotencia(rs.getObject("Potencia") != null ? rs.getDouble("Potencia") : null);
                c.setNoPlaca(rs.getString("NoPlaca"));

                lista.add(c);
            }
        }
        return lista;
    }

    public Camion buscarPorId(int idCamion) throws SQLException {
        String sql = "SELECT * FROM Camion WHERE idCamion = ?";
        Camion c = null;

        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idCamion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c = new Camion();
                    c.setIdCamion(rs.getInt("idCamion"));
                    c.setModelo(rs.getString("Modelo"));
                    c.setTipo(rs.getString("Tipo"));
                    c.setPotencia(rs.getObject("Potencia") != null ? rs.getDouble("Potencia") : null);
                    c.setNoPlaca(rs.getString("NoPlaca"));
                }
            }
        }
        return c;
    }
}