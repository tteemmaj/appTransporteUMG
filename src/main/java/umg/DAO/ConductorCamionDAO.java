
package umg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import umg.modelo.Camion;
import umg.modelo.Camionero;
import umg.modelo.ConductorCamion;

public class ConductorCamionDAO {

    public void insertar(ConductorCamion cc) throws SQLException {
        String sql = "INSERT INTO ConductorCamion (idCamioneroCamion, idCamionero, idCamion, Fecha) VALUES (?, ?, ?, ?);";

        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, cc.getIdConductorCamion());
            
            if (cc.getConductor() != null) {
                ps.setInt(2, cc.getConductor().getIdCamionero());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }

            if (cc.getCamion() != null) {
                ps.setInt(3, cc.getCamion().getIdCamion());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }

            ps.setString(4, cc.getFecha());

            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo insertar el registro de asignación.");
            }
        }
    }

    public void actualizar(ConductorCamion cc) throws SQLException {
        String sql = "UPDATE ConductorCamion SET idCamionero = ?, idCamion = ?, Fecha = ? WHERE idCamioneroCamion = ?";

        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            if (cc.getConductor() != null) {
                ps.setInt(1, cc.getConductor().getIdCamionero());
            } else {
                ps.setNull(1, java.sql.Types.INTEGER);
            }

            if (cc.getCamion() != null) {
                ps.setInt(2, cc.getCamion().getIdCamion());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }

            ps.setString(3, cc.getFecha());
            ps.setInt(4, cc.getIdConductorCamion());

            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se encontró el registro para actualizar.");
            }
        }
    }

    public void eliminar(int idConductorCamion) throws SQLException {
        String sql = "DELETE FROM ConductorCamion WHERE idCamioneroCamion = ?";

        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idConductorCamion);

            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se encontró el registro a eliminar.");
            }
        }
    }

    public List<ConductorCamion> obtenerTodos() throws SQLException {
        List<ConductorCamion> lista = new ArrayList<>();
        String sql = "SELECT * FROM ConductorCamion";

        CamioneroDAO camioneroDAO = new CamioneroDAO();
        CamionDAO camionDAO = new CamionDAO();

        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ConductorCamion cc = new ConductorCamion();
                cc.setIdConductorCamion(rs.getInt("idCamioneroCamion"));
                cc.setFecha(rs.getString("Fecha"));

                // Mapear y asociar el objeto Camionero
                int idCamionero = rs.getInt("idCamionero");
                if (!rs.wasNull()) {
                    Camionero c = camioneroDAO.buscarPorId(idCamionero);
                    cc.setConductor(c);
                }

                // Mapear y asociar el objeto Camion
                int idCamion = rs.getInt("idCamion");
                if (!rs.wasNull()) {
                    Camion camion = camionDAO.buscarPorId(idCamion);
                    cc.setCamion(camion);
                }

                lista.add(cc);
            }
        }
        return lista;
    }

    public ConductorCamion buscarPorId(int idConductorCamion) throws SQLException {
        String sql = "SELECT * FROM ConductorCamion WHERE idCamioneroCamion = ?";
        ConductorCamion cc = null;

        CamioneroDAO camioneroDAO = new CamioneroDAO();
        CamionDAO camionDAO = new CamionDAO();

        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idConductorCamion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cc = new ConductorCamion();
                    cc.setIdConductorCamion(rs.getInt("idCamioneroCamion"));
                    cc.setFecha(rs.getString("Fecha"));

                    int idCamionero = rs.getInt("idCamionero");
                    if (!rs.wasNull()) {
                        cc.setConductor(camioneroDAO.buscarPorId(idCamionero));
                    }

                    int idCamion = rs.getInt("idCamion");
                    if (!rs.wasNull()) {
                        cc.setCamion(camionDAO.buscarPorId(idCamion));
                    }
                }
            }
        }
        return cc;
    }
}