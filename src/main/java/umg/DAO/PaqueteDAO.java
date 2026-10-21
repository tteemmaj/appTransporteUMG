package umg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import umg.modelo.Camionero;
import umg.modelo.Ciudad;
import umg.modelo.Paquete;

public class PaqueteDAO {

    public void insertar(Paquete paquete) throws SQLException {
        String sql = "INSERT INTO Paquete (idPaquete, Descripcion, Destinatario, DireccionEnvio, idCamionero, idCiudad, Peso) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, paquete.getIdPaquete());
            ps.setString(2, paquete.getDescripcion());
            ps.setString(3, paquete.getDestinatario());
            ps.setString(4, paquete.getDireccion());

            if (paquete.getCamionero() != null) {
                ps.setInt(5, paquete.getCamionero().getIdCamionero());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }

            if (paquete.getCiudad() != null) {
                ps.setInt(6, paquete.getCiudad().getIdCiudad());
            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }

            if (paquete.getPeso() != null) {
                ps.setDouble(7, paquete.getPeso());
            } else {
                ps.setNull(7, java.sql.Types.DOUBLE);
            }

            int filas = ps.executeUpdate();

            if (filas == 0) {
                throw new SQLException("No se pudo insertar el registro.");
            }
        }
    }

    public void actualizar(Paquete paquete) throws SQLException {
        String sql = "UPDATE Paquete SET Descripcion=?, Destinatario=?, DireccionEnvio=?, idCamionero=?, idCiudad=?, Peso=? WHERE idPaquete=?";

        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, paquete.getDescripcion());
            ps.setString(2, paquete.getDestinatario());
            ps.setString(3, paquete.getDireccion());

            if (paquete.getCamionero() != null) {
                ps.setInt(4, paquete.getCamionero().getIdCamionero());
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }

            if (paquete.getCiudad() != null) {
                ps.setInt(5, paquete.getCiudad().getIdCiudad());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }

            if (paquete.getPeso() != null) {
                ps.setDouble(6, paquete.getPeso());
            } else {
                ps.setNull(6, java.sql.Types.DOUBLE);
            }

            ps.setInt(7, paquete.getIdPaquete());

            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se encontró el registro para actualizar.");
            }
        }
    }

    public void eliminar(int idPaquete) throws SQLException {
        String sql = "DELETE FROM Paquete WHERE idPaquete=?";

        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idPaquete);

            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se encontró el registro a eliminar.");
            }
        }
    }

    public List<Paquete> obtenerTodos() throws SQLException {
        List<Paquete> lista = new ArrayList<>();
        String sql = "SELECT * FROM Paquete";

        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Paquete p = new Paquete();
                p.setIdPaquete(rs.getInt("idPaquete"));
                p.setDescripcion(rs.getString("Descripcion"));
                p.setDestinatario(rs.getString("Destinatario"));
                p.setDireccion(rs.getString("DireccionEnvio"));
                p.setPeso(rs.getObject("Peso") != null ? rs.getDouble("Peso") : null);

                int idCamionero = rs.getInt("idCamionero");
                if (!rs.wasNull()) {
                    Camionero camionero = new Camionero();
                    camionero.setIdCamionero(idCamionero);
                    p.setCamionero(camionero);
                }

                int idCiudad = rs.getInt("idCiudad");
                if (!rs.wasNull()) {
                    Ciudad ciudad = new Ciudad();
                    ciudad.setIdCiudad(idCiudad);
                    p.setCiudad(ciudad);
                }

                lista.add(p);
            }
        }
        return lista;
    }

    public Paquete buscarPorId(int idPaquete) throws SQLException {
        String sql = "SELECT * FROM Paquete WHERE idPaquete = ?";
        Paquete p = null;

        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idPaquete);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = new Paquete();
                    p.setIdPaquete(rs.getInt("idPaquete"));
                    p.setDescripcion(rs.getString("Descripcion"));
                    p.setDestinatario(rs.getString("Destinatario"));
                    p.setDireccion(rs.getString("DireccionEnvio"));
                    p.setPeso(rs.getObject("Peso") != null ? rs.getDouble("Peso") : null);

                    int idCamionero = rs.getInt("idCamionero");
                    if (!rs.wasNull()) {
                        Camionero camionero = new Camionero();
                        camionero.setIdCamionero(idCamionero);
                        p.setCamionero(camionero);
                    }

                    int idCiudad = rs.getInt("idCiudad");
                    if (!rs.wasNull()) {
                        Ciudad ciudad = new Ciudad();
                        ciudad.setIdCiudad(idCiudad);
                        p.setCiudad(ciudad);
                    }
                }
            }
        }
        return p;
    }
}