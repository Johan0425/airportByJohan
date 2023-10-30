/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package views.adminAirlines;

import controllers.AirplaneController;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import model.Airline;
import model.Airplane;

/**
 *
 * @author joanp
 */
public class RegisterAirplane extends javax.swing.JInternalFrame {

    private final AirplaneController controller;
    private final AirlineAdminTasks viewTasks;
    private final Airline airline;
    private final Airplanes view;

    /**
     * Creates new form RegisterAirplane
     *
     * @param viewTasks
     * @param airline
     * @param view
     */
    public RegisterAirplane(AirlineAdminTasks viewTasks, Airline airline, Airplanes view) {
        initComponents();
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
        setResizable(false);
        setSize(1200, 700);
        controller = new AirplaneController(airline);
        this.view = view;
        this.viewTasks = viewTasks;
        this.airline = airline;
        hideWarning();
    }

    private void hideWarning() {
        lblWarningRows.setVisible(false);
    }

    private void cleanFields() {
        txtModel.setText("");
        txtRows.setText("");
        cbxNumCols.setSelectedIndex(0);
    }

    private boolean hasEmptyFields() {
        return (txtModel.getText().isEmpty() || txtRows.getText().isEmpty() || cbxNumCols.getSelectedIndex() == 0);
    }

    private boolean hasEmptyFieldsToPreview() {
        return (txtRows.getText().isEmpty() || cbxNumCols.getSelectedIndex() == 0);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        txtModel = new javax.swing.JTextField();
        txtRows = new javax.swing.JTextField();
        cbxNumCols = new javax.swing.JComboBox<>();
        btnPreviewAirplane = new javax.swing.JButton();
        lblWarningRows = new javax.swing.JLabel();

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setText("Modelo:");

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setText("Grupos de:");

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 153));
        jLabel3.setText("Número de filas:");

        btnAdd.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(0, 102, 153));
        btnAdd.setText("GUARDAR");
        btnAdd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 2));
        btnAdd.setContentAreaFilled(false);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        txtModel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        txtModel.setBorder(null);

        txtRows.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        txtRows.setBorder(null);
        txtRows.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRowsKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRowsKeyTyped(evt);
            }
        });

        cbxNumCols.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        cbxNumCols.setForeground(new java.awt.Color(0, 102, 153));
        cbxNumCols.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opción:", "2", "3" }));

        btnPreviewAirplane.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnPreviewAirplane.setForeground(new java.awt.Color(0, 102, 153));
        btnPreviewAirplane.setText("PREVISUALIZAR");
        btnPreviewAirplane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 2));
        btnPreviewAirplane.setContentAreaFilled(false);
        btnPreviewAirplane.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviewAirplaneActionPerformed(evt);
            }
        });

        lblWarningRows.setBackground(new java.awt.Color(204, 0, 0));
        lblWarningRows.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        lblWarningRows.setForeground(new java.awt.Color(255, 0, 0));
        lblWarningRows.setText("Deben ser mas de 5 o menor a 11 filas");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(351, 351, 351)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPreviewAirplane))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtModel)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                                    .addComponent(txtRows, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                                    .addComponent(cbxNumCols, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(47, 47, 47))))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(483, 483, 483)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblWarningRows)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(395, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(lblWarningRows)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(txtRows, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxNumCols, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPreviewAirplane, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(192, Short.MAX_VALUE))
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

    private void txtRowsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRowsKeyTyped
        String rows = txtRows.getText();
        char c = evt.getKeyChar();

        if (!Character.isDigit(c) || rows.length() == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_txtRowsKeyTyped

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (hasEmptyFields()) {
            JOptionPane.showMessageDialog(null, "Ingrese todos los datos");
            return;
        }

        String model = txtModel.getText();
        int rows = Integer.parseInt(txtRows.getText());
        int cols = Integer.parseInt(cbxNumCols.getSelectedItem().toString());

        Airplane airplane = new Airplane(airline, rows, cols, model);
        controller.addAirplane(airplane);
        JOptionPane.showMessageDialog(null, "Registro exitoso");
        cleanFields();
        view.fillTable();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnPreviewAirplaneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviewAirplaneActionPerformed
        if (hasEmptyFieldsToPreview()) {
            JOptionPane.showMessageDialog(null, "Para previsualizar debe de tener el numero de filas y grupos que quiere en el avión");
            return;

        }
        int row = Integer.parseInt(txtRows.getText());
        int groupSeats = Integer.parseInt(cbxNumCols.getSelectedItem().toString());

        viewTasks.validateDesktop();
        viewTasks.openPreviewPlane(row, groupSeats, airline, view);

    }//GEN-LAST:event_btnPreviewAirplaneActionPerformed

    private void txtRowsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRowsKeyReleased
        String text = txtRows.getText();
        try {
            int value = Integer.parseInt(text);
            if (value < 5) {
                txtRows.setText("5");
            } else if (value > 11) {
                txtRows.setText("11");
            }
        } catch (NumberFormatException e) {
            lblWarningRows.setVisible(true);
        }
    }//GEN-LAST:event_txtRowsKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnPreviewAirplane;
    private javax.swing.JComboBox<String> cbxNumCols;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblWarningRows;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField txtModel;
    private javax.swing.JTextField txtRows;
    // End of variables declaration//GEN-END:variables
}
