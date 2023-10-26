/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package views.adminAirlines;

import controllers.AirplaneController;
import static enums.AirplaneStatus.AVAILABLE;
import static enums.AirplaneStatus.BUSY;
import static enums.AirplaneStatus.DISABLED;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.Airline;
import model.Airplane;
import util.LSE;

/**
 *
 * @author joanp
 */
public class Airplanes extends javax.swing.JInternalFrame {

    private final AirlineAdminTasks view;
    private final Airline airline;
    private final AirplaneController controller;
    private TableRowSorter<DefaultTableModel> sorter;

    /**
     * Creates new form Airplanes
     *
     * @param airline
     * @param view
     */
    public Airplanes(Airline airline, AirlineAdminTasks view) {
        initComponents();
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
        setResizable(false);
        setSize(1200, 700);
        this.airline = airline;
        controller = new AirplaneController(airline);
        fillTable();
        this.view = view;
    }

    private void filter() {
        String filterText = txtSearch.getText();
        RowFilter<Object, Object> idFilter = RowFilter.regexFilter(filterText.trim(), 0);
        sorter.setRowFilter(RowFilter.orFilter(Arrays.asList(idFilter, idFilter)));
    }

    public final void fillTable() {
        DefaultTableModel model = new DefaultTableModel();

        LSE<Airplane> airplanes = controller.getAirplanes();
        model.setColumnIdentifiers(new Object[]{
            "Modelo", "Número filas", "Número columnas", "Estado"
        });

        airplanesTable.setModel(model);

        airplanesTable.setAutoCreateRowSorter(true);
        sorter = new TableRowSorter<>(model);
        airplanesTable.setRowSorter(sorter);

        for (int i = 0; i < airplanes.size(); i++) {
            Airplane airplane = airplanes.get(i);
            String status = "";

            switch (airplane.getStatus()) {
                case AVAILABLE:
                    status = "Disponible";
                    break;
                case BUSY:
                    status = "Ocupado";
                    break;
                case DISABLED:
                    status = "En mantenimiento";
                    break;
                default:
                    break;
            }

            model.addRow(new Object[]{
                airplanes.get(i).getModel(),
                airplanes.get(i).getNumRows(),
                airplanes.get(i).getNumCols(),
                status
            });
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

        mainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        airplanesTable = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        btnGestion = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setText("BUSCAR:");

        txtSearch.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        txtSearch.setBorder(null);
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(0, 102, 153));
        jSeparator1.setForeground(new java.awt.Color(0, 102, 153));

        airplanesTable.setBackground(new java.awt.Color(255, 255, 255));
        airplanesTable.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        airplanesTable.setForeground(new java.awt.Color(0, 102, 153));
        airplanesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(airplanesTable);

        btnNew.setBackground(new java.awt.Color(255, 255, 255));
        btnNew.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnNew.setForeground(new java.awt.Color(0, 102, 153));
        btnNew.setText("NUEVO");
        btnNew.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 2, true));
        btnNew.setContentAreaFilled(false);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnGestion.setBackground(new java.awt.Color(255, 255, 255));
        btnGestion.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnGestion.setForeground(new java.awt.Color(0, 102, 153));
        btnGestion.setText("GESTIONAR");
        btnGestion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 2, true));
        btnGestion.setContentAreaFilled(false);
        btnGestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(255, 255, 255));
        btnSearch.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(0, 102, 153));
        btnSearch.setText("BUSCAR");
        btnSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 2, true));
        btnSearch.setContentAreaFilled(false);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 255, 255));
        btnDelete.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 102, 153));
        btnDelete.setText("ELIMINAR");
        btnDelete.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 2, true));
        btnDelete.setContentAreaFilled(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(897, 897, 897)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(jLabel1)
                        .addGap(66, 66, 66)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 968, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(btnGestion, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGestion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        filter();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        view.validateDesktop();
        view.openRegisterAirplane(airline, this);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnGestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionActionPerformed
        int selected = airplanesTable.getSelectedRow();

        if (selected >= 0) {

            String model = airplanesTable.getModel().getValueAt(selected, 0).toString();
            Airplane airplane = controller.searchAirplane(model);

            if (airplane != null) {
                view.validateDesktop();
                view.openUpdateAirplaneView(airline, airplane, this);
            } else {
                System.out.println("es null");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un empleado de la tabla");
        }
    }//GEN-LAST:event_btnGestionActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        int selected = airplanesTable.getSelectedRow();

        if (selected >= 0) {

            String model = airplanesTable.getModel().getValueAt(selected, 0).toString();

            int answer = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el avión con modelo "
                    + model + "?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (answer == 0) {
                controller.deleteAirplane(model);
                fillTable();
                JOptionPane.showMessageDialog(null, "Avión eliminado correctamente");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un avión de la tabla");
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selected = airplanesTable.getSelectedRow();

        if (selected >= 0) {

            String model = airplanesTable.getModel().getValueAt(selected, 0).toString();

            int answer = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el avión con modelo "
                    + model + "?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (answer == 0) {
                controller.deleteAirplane(model);
                fillTable();
                JOptionPane.showMessageDialog(null, "Avión eliminado correctamente");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un avión de la tabla");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable airplanesTable;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnGestion;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
