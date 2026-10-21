package umg.UI;
import java.awt.Component;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import umg.DAO.CamioneroDAO;
import umg.DAO.CiudadDAO;
import umg.modelo.Camionero;
import umg.modelo.Ciudad;
import umg.DAO.PaqueteDAO;
import umg.modelo.Paquete;

public class frmPaquete extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frmPaquete.class.getName());

    public frmPaquete() {
        initComponents();
        cbCamionero.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Camionero) {
                    Camionero c = (Camionero) value;
                    setText(c.getNombre() + " " + c.getApellido());
                }
                return this;
            }
        });

        // --- 2. CONFIGURACIÓN DEL RENDERIZADOR PARA CIUDADES ---
        cbCiudad.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Ciudad) {
                    Ciudad ciu = (Ciudad) value;
                    setText(ciu.getNombre()); // Muestra solo el Nombre de la Ciudad
                }
                return this;
            }
        });

        // --- 3. CARGAR LOS DATOS EN LOS DESPLEGABLES ---
        cargarDesplegables();
        cargarPaquetes(); // Carga la tabla al abrir la interfaz
        limpiarCampos();
        
    }
    private void cargarPaquetes() {
    DefaultTableModel modelo = (DefaultTableModel) tblPaquete.getModel();
    modelo.setRowCount(0); // Limpia las filas previas

    PaqueteDAO dao = new PaqueteDAO();
    CamioneroDAO camioneroDAO = new CamioneroDAO();
    CiudadDAO ciudadDAO = new CiudadDAO();

    try {
        List<Paquete> lista = dao.obtenerTodos();

        for (Paquete p : lista) {
            // Buscar nombre del Camionero
            String nombreCamionero = "Sin asignar";
            if (p.getCamionero() != null) {
                Camionero c = camioneroDAO.buscarPorId(p.getCamionero().getIdCamionero());
                if (c != null) {
                    nombreCamionero = c.getNombre() + " " + c.getApellido();
                }
            }

            // Buscar nombre de la Ciudad
            String nombreCiudad = "Sin asignar";
            if (p.getCiudad() != null) {
                List<Ciudad> ciudades = ciudadDAO.listar();
                for (Ciudad ciu : ciudades) {
                    if (ciu.getIdCiudad() == p.getCiudad().getIdCiudad()) {
                        nombreCiudad = ciu.getNombre();
                        break;
                    }
                }
            }

            // Agregar la fila a la tabla
            modelo.addRow(new Object[]{
                p.getIdPaquete(),
                p.getDescripcion(),
                p.getDestinatario(),
                p.getPeso(),
                p.getDireccion(),
                nombreCamionero,
                nombreCiudad
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar paquetes: " + e.getMessage());
    }
    }
    
    private void limpiarCampos() {
    txtId.setText("");
    txtDescripcion.setText("");
    txtDestinatario.setText("");
    txtPeso.setText("");
    txtDireccion.setText("");
    if (cbCamionero.getItemCount() > 0) {
        cbCamionero.setSelectedIndex(0);
    }
    if (cbCiudad.getItemCount() > 0) {
        cbCiudad.setSelectedIndex(0);
    }
    txtId.requestFocus();
}

    
    public void cargarDesplegables() {
        try {
            // Cargar Camioneros
            CamioneroDAO camioneroDAO = new CamioneroDAO();
            List<Camionero> listaCamioneros = camioneroDAO.obtenerTodos();
            cbCamionero.removeAllItems();
            for (Camionero c : listaCamioneros) {
                cbCamionero.addItem(c);
            }

            // Cargar Ciudades
            CiudadDAO ciudadDAO = new CiudadDAO();
            List<Ciudad> listaCiudades = ciudadDAO.listar();
            cbCiudad.removeAllItems();
            for (Ciudad ciu : listaCiudades) {
                cbCiudad.addItem(ciu);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos en los desplegables: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new java.awt.Panel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtDestinatario = new javax.swing.JTextField();
        txtPeso = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        btnInsertar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnBuscarPorId = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        cbCamionero = new javax.swing.JComboBox<>();
        cbCiudad = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        panel2 = new java.awt.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPaquete = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ID:");

        jLabel2.setText("Descripcion:");

        jLabel3.setText("Destinatario:");

        jLabel4.setText("Peso:");

        jLabel5.setText("Dirección:");
        jLabel5.setToolTipText("");

        jLabel6.setText("Camionero:");

        jLabel7.setText("Ciudad:");

        btnInsertar.setText("Insertar");
        btnInsertar.addActionListener(this::btnInsertarActionPerformed);

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        btnBuscarPorId.setText("BuscarPorId");
        btnBuscarPorId.addActionListener(this::btnBuscarPorIdActionPerformed);

        btnListar.setText("Listar");
        btnListar.addActionListener(this::btnListarActionPerformed);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        cbCamionero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbCiudad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCiudad.addActionListener(this::cbCiudadActionPerformed);

        jLabel8.setFont(new java.awt.Font("Adwaita Sans", 3, 18)); // NOI18N
        jLabel8.setText("Paquete");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(73, 73, 73)
                            .addComponent(txtId))
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(txtDescripcion))
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7))
                            .addGap(18, 18, 18)
                            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDestinatario, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                                .addComponent(txtPeso)
                                .addComponent(txtDireccion)
                                .addComponent(cbCamionero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbCiudad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(btnInsertar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarPorId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnListar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(btnLimpiar)))
                .addContainerGap())
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbCamionero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsertar)
                    .addComponent(btnActualizar)
                    .addComponent(btnBuscarPorId)
                    .addComponent(btnListar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        tblPaquete.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descripcion", "Destinatario", "Peso", "Direccion", "Camionero", "Ciudad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPaquete.setColumnSelectionAllowed(true);
        tblPaquete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPaqueteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPaquete);
        tblPaquete.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCiudadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCiudadActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        if (txtId.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Selecciona o ingresa el ID del paquete que deseas actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        Camionero camioneroSel = (Camionero) cbCamionero.getSelectedItem();
        Ciudad ciudadSel = (Ciudad) cbCiudad.getSelectedItem();

        Paquete p = new Paquete();
        p.setIdPaquete(Integer.parseInt(txtId.getText().trim()));
        p.setDescripcion(txtDescripcion.getText().trim());
        p.setDestinatario(txtDestinatario.getText().trim());
        p.setDireccion(txtDireccion.getText().trim());

        String pesoStr = txtPeso.getText().trim();
        p.setPeso(pesoStr.isEmpty() ? null : Double.parseDouble(pesoStr));

        p.setCamionero(camioneroSel);
        p.setCiudad(ciudadSel);

        PaqueteDAO dao = new PaqueteDAO();
        dao.actualizar(p);

        cargarPaquetes();
        limpiarCampos();
        JOptionPane.showMessageDialog(this, "Paquete actualizado correctamente.");

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Verifica que el ID y el Peso sean valores numéricos válidos.", "Error de formato", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException exp) {
        JOptionPane.showMessageDialog(this, "Error al actualizar en la Base de Datos: " + exp.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarActionPerformed
        // TODO add your handling code here:
        try {
            // Validar campos obligatorios
            if (txtId.getText().trim().isEmpty() || txtDescripcion.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El ID y la Descripción son obligatorios.");
                return;
            }

            // Obtener elementos seleccionados
            Camionero camioneroSel = (Camionero) cbCamionero.getSelectedItem();
            Ciudad ciudadSel = (Ciudad) cbCiudad.getSelectedItem();

            if (camioneroSel == null || ciudadSel == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un camionero y una ciudad.");
                return;
            }

            // Construir el objeto Paquete
            Paquete p = new Paquete();
            p.setIdPaquete(Integer.parseInt(txtId.getText().trim()));
            p.setDescripcion(txtDescripcion.getText().trim());
            p.setDestinatario(txtDestinatario.getText().trim());
            p.setDireccion(txtDireccion.getText().trim());

            String pesoStr = txtPeso.getText().trim();
            p.setPeso(pesoStr.isEmpty() ? null : Double.parseDouble(pesoStr));

            p.setCamionero(camioneroSel);
            p.setCiudad(ciudadSel);

            // Guardar en la BD
            PaqueteDAO dao = new PaqueteDAO();
            dao.insertar(p);

            JOptionPane.showMessageDialog(this, "¡Paquete registrado exitosamente!");
            btnLimpiarActionPerformed(evt);
            btnListarActionPerformed(evt);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID y el Peso deben ser numéricos válidos.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error en la base de datos: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnInsertarActionPerformed

    private void btnBuscarPorIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPorIdActionPerformed
        // TODO add your handling code here:
        if (txtId.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ingresa un ID en el campo correspondiente para buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        int idBuscado = Integer.parseInt(txtId.getText().trim());
        PaqueteDAO dao = new PaqueteDAO();
        Paquete p = dao.buscarPorId(idBuscado);

        if (p != null) {
            txtDescripcion.setText(p.getDescripcion() != null ? p.getDescripcion() : "");
            txtDestinatario.setText(p.getDestinatario() != null ? p.getDestinatario() : "");
            txtDireccion.setText(p.getDireccion() != null ? p.getDireccion() : "");
            txtPeso.setText(p.getPeso() != null ? String.valueOf(p.getPeso()) : "");

            // Seleccionar el Camionero en el ComboBox según el ID obtenido
            if (p.getCamionero() != null) {
                for (int i = 0; i < cbCamionero.getItemCount(); i++) {
                    Object item = cbCamionero.getItemAt(i);
                    if (item instanceof Camionero && ((Camionero) item).getIdCamionero() == p.getCamionero().getIdCamionero()) {
                        cbCamionero.setSelectedIndex(i);
                        break;
                    }
                }
            }

            // Seleccionar la Ciudad en el ComboBox según el ID obtenido
            if (p.getCiudad() != null) {
                for (int i = 0; i < cbCiudad.getItemCount(); i++) {
                    Object item = cbCiudad.getItemAt(i);
                    if (item instanceof Ciudad && ((Ciudad) item).getIdCiudad() == p.getCiudad().getIdCiudad()) {
                        cbCiudad.setSelectedIndex(i);
                        break;
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró ningún paquete con ese ID.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "El ID debe ser un número entero válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException exp) {
        JOptionPane.showMessageDialog(this, "Error en la Base de Datos: " + exp.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnBuscarPorIdActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if (txtId.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Selecciona o ingresa el ID del paquete que deseas eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar este paquete?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

    if (confirmacion == JOptionPane.YES_OPTION) {
        try {
            int id = Integer.parseInt(txtId.getText().trim());

            PaqueteDAO dao = new PaqueteDAO();
            dao.eliminar(id);

            cargarPaquetes();
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Paquete eliminado correctamente.");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException exp) {
            JOptionPane.showMessageDialog(this, "Error al eliminar en la Base de Datos: " + exp.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        cargarPaquetes();
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tblPaqueteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPaqueteMouseClicked
        // TODO add your handling code here:
        int fila = tblPaquete.getSelectedRow();
    if (fila >= 0) {
        try {
            // 1. Asignar los campos de texto directamente con los datos de la fila
            txtId.setText(tblPaquete.getValueAt(fila, 0).toString());
            txtDescripcion.setText(tblPaquete.getValueAt(fila, 1) != null ? tblPaquete.getValueAt(fila, 1).toString() : "");
            txtDestinatario.setText(tblPaquete.getValueAt(fila, 2) != null ? tblPaquete.getValueAt(fila, 2).toString() : "");
            txtPeso.setText(tblPaquete.getValueAt(fila, 3) != null ? tblPaquete.getValueAt(fila, 3).toString() : "");
            txtDireccion.setText(tblPaquete.getValueAt(fila, 4) != null ? tblPaquete.getValueAt(fila, 4).toString() : "");

            // 2. Buscar las llaves foráneas en la BD para seleccionar los elementos correspondientes en los JComboBox
            int idPaquete = Integer.parseInt(txtId.getText().trim());
            PaqueteDAO dao = new PaqueteDAO();
            Paquete p = dao.buscarPorId(idPaquete);

            if (p != null) {
                // Seleccionar el Camionero en el ComboBox
                if (p.getCamionero() != null) {
                    for (int i = 0; i < cbCamionero.getItemCount(); i++) {
                        Object item = cbCamionero.getItemAt(i);
                        if (item instanceof Camionero && ((Camionero) item).getIdCamionero() == p.getCamionero().getIdCamionero()) {
                            cbCamionero.setSelectedIndex(i);
                            break;
                        }
                    }
                }

                // Seleccionar la Ciudad en el ComboBox
                if (p.getCiudad() != null) {
                    for (int i = 0; i < cbCiudad.getItemCount(); i++) {
                        Object item = cbCiudad.getItemAt(i);
                        if (item instanceof Ciudad && ((Ciudad) item).getIdCiudad() == p.getCiudad().getIdCiudad()) {
                            cbCiudad.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al seleccionar el paquete: " + e.getMessage());
        }
    }
    }//GEN-LAST:event_tblPaqueteMouseClicked

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        // TODO add your handling code here:
        cargarPaquetes();
        limpiarCampos();
    }//GEN-LAST:event_btnListarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new frmPaquete().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscarPorId;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnListar;
    private javax.swing.JComboBox<Object> cbCamionero;
    private javax.swing.JComboBox<Object> cbCiudad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private javax.swing.JTable tblPaquete;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDestinatario;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtPeso;
    // End of variables declaration//GEN-END:variables
}
