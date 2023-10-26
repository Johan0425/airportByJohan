/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package views.traveler;

import controllers.TravelerController;
import exceptions.UserAlreadyRegisteredException;
import exceptions.UsernameInUseException;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import model.Employee;
import model.Traveler;
import view.main.MainView;

/**
 *
 * @author joanp
 */
public class RegisterView extends javax.swing.JInternalFrame {

    private final TravelerController controller;
    private final MainView view;

    /**
     * Creates new form RegisterView
     *
     * @param view
     */
    public RegisterView(MainView view) {
        initComponents();
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
        setTitle("Registro de usuario");
        setSize(1200, 750);
        controller = new TravelerController();
        this.view = view;
        hideWarnings();
    }

    private void editableFields() {
        txtName.setEditable(true);
        txtUsername.setEditable(true);
        txtPassword.setEditable(true);
    }

    private void noEditableFields() {
        txtName.setEditable(false);
        txtUsername.setEditable(false);
        txtPassword.setEditable(false);
    }

    private void cleanFields() {
        txtId.setText("");
        txtName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
    }

    private void cleanFillFields() {
        txtName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
    }

    private void fillFields(String id) {
        Employee employee = controller.searchEmployee(id);
        if (employee != null) {
            txtName.setText(employee.getFullname());
            txtUsername.setText(employee.getUsername());
            txtPassword.setText(employee.getPassword());

        }
    }

    private void signUp() {
        if (hasEmptyFields()) {
            JOptionPane.showMessageDialog(null, "Ingrsese todos los datos");
            return;
        }

        String id = txtId.getText();
        String name = txtName.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        Employee employee = controller.searchEmployee(id);
        Traveler traveler = new Traveler(id, name, username, password);

        if (employee != null) {
            int option = JOptionPane.showConfirmDialog(this, "La cédula ingresada ya está registrada como empleado del aeropuerto. "
                    + "¿Desea registrarse como viajero?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                controller.addEmployeeAsTraveler(traveler);
                JOptionPane.showMessageDialog(null, "Registro exitoso");
                returnToLogin();
                return;
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo hacer");
        }
        try {
            controller.addTraveler(traveler);
            JOptionPane.showMessageDialog(null, "Registro exitoso");
            returnToLogin();
        } catch (UserAlreadyRegisteredException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            returnToLogin();
        } catch (UsernameInUseException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void validateFields() {
        String id = txtId.getText();

        Traveler traveler = controller.searchTraveler(id);
        Employee employee = controller.searchEmployee(id);

        boolean enableBtnAddTraveler = true;

        if (!id.isEmpty() && employee != null) {
            lblEmployeeFound.setVisible(true);
            fillFields(id);
            noEditableFields();
        } else {
            lblEmployeeFound.setVisible(false);
            editableFields();
            cleanFillFields();
        }

        if (traveler != null) {
            idWarning.setVisible(true);
            enableBtnAddTraveler = false;
        } else {
            idWarning.setVisible(false);
        }

        btnSingUp.setEnabled(enableBtnAddTraveler);
    }

    private void validateUsername() {
        String username = txtUsername.getText();

        boolean usernameInUse = controller.isUsernameInUse(username);

        boolean enableBtnAddTraveler = true;

        if (!username.isEmpty() && usernameInUse) {
            usernameWarning.setVisible(true);
            enableBtnAddTraveler = false;
        } else {
            usernameWarning.setVisible(false);

        }
        btnSingUp.setEnabled(enableBtnAddTraveler);

    }

    private boolean hasEmptyFields() {
        return (txtId.getText().isEmpty() || txtName.getText().isEmpty() || txtUsername.getText().isEmpty()
                || txtPassword.getText().isEmpty());
    }

    private void returnToLogin() {
        view.validateDesktop();
        view.openLoginView();
    }

    private void hideWarnings() {
        idWarning.setVisible(false);
        usernameWarning.setVisible(false);
        lblEmployeeFound.setVisible(false);
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
        btnSingUp = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        txtId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        idWarning = new javax.swing.JLabel();
        usernameWarning = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblEmployeeFound = new javax.swing.JLabel();

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        btnSingUp.setBackground(new java.awt.Color(255, 255, 255));
        btnSingUp.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnSingUp.setForeground(new java.awt.Color(0, 102, 153));
        btnSingUp.setText("CREAR");
        btnSingUp.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 2, true));
        btnSingUp.setContentAreaFilled(false);
        btnSingUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSingUpActionPerformed(evt);
            }
        });

        txtId.setBorder(null);
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setText("Nombre:");

        txtName.setBorder(null);
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNameKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setText("Usuario:");

        txtUsername.setBorder(null);
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsernameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsernameKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 153));
        jLabel6.setText("Contraseña:");

        txtPassword.setBorder(null);
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPasswordKeyTyped(evt);
            }
        });

        idWarning.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 10)); // NOI18N
        idWarning.setForeground(new java.awt.Color(204, 0, 0));
        idWarning.setText("NO PUEDE TENER DOS CUENTAS");

        usernameWarning.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 10)); // NOI18N
        usernameWarning.setForeground(new java.awt.Color(204, 0, 0));
        usernameWarning.setText("USUARIO EN USO");

        btnClose.setBackground(new java.awt.Color(255, 255, 255));
        btnClose.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnClose.setForeground(new java.awt.Color(0, 102, 153));
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/close.png"))); // NOI18N
        btnClose.setBorder(null);
        btnClose.setContentAreaFilled(false);
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 153));
        jLabel4.setText("ID:");

        lblEmployeeFound.setBackground(new java.awt.Color(0, 102, 153));
        lblEmployeeFound.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 10)); // NOI18N
        lblEmployeeFound.setForeground(new java.awt.Color(0, 102, 153));
        lblEmployeeFound.setText("EMPLEADO YA REGISTRADO");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(51, 1130, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addGap(26, 26, 26))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(369, 369, 369)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(101, 101, 101)))
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(idWarning)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator1)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblEmployeeFound))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(usernameWarning)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator5)
                                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator2)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator6)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(490, 490, 490)
                        .addComponent(btnSingUp, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(465, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(idWarning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblEmployeeFound)
                .addGap(41, 41, 41)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(usernameWarning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139)
                .addComponent(btnSingUp, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(269, Short.MAX_VALUE))
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

    private void btnSingUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSingUpActionPerformed
        signUp();
    }//GEN-LAST:event_btnSingUpActionPerformed

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        validateFields();
    }//GEN-LAST:event_txtIdKeyReleased

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        String id = txtId.getText();
        char c = evt.getKeyChar();

        if (!Character.isDigit(c) || id.length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtIdKeyTyped

    private void txtNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyTyped
        String name = txtName.getText();
        char c = evt.getKeyChar();

        if (Character.isDigit(c) || name.length() == 50) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNameKeyTyped

    private void txtUsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyReleased
        validateUsername();
    }//GEN-LAST:event_txtUsernameKeyReleased

    private void txtUsernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyTyped
        String username = txtUsername.getText().trim();
        char c = evt.getKeyChar();
        if (!Character.isLetterOrDigit(c) || username.length() == 20) {
            evt.consume();
        }
    }//GEN-LAST:event_txtUsernameKeyTyped

    private void txtPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyTyped
        String pass = txtPassword.getText().trim();
        char c = evt.getKeyChar();
        if (!Character.isLetterOrDigit(c) || pass.length() == 20) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPasswordKeyTyped

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        returnToLogin();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            signUp();
        }
    }//GEN-LAST:event_txtPasswordKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSingUp;
    private javax.swing.JLabel idWarning;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lblEmployeeFound;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JLabel usernameWarning;
    // End of variables declaration//GEN-END:variables
}
