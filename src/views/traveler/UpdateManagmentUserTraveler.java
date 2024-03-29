/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package views.traveler;

import controllers.TravelerController;
import exceptions.NumberAlreadyInUse;
import exceptions.UserAlreadyRegisteredException;
import exceptions.UsernameInUseException;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import model.Traveler;
import view.main.MainView;

/**
 *
 * @author joanp
 */
public class UpdateManagmentUserTraveler extends javax.swing.JInternalFrame {

    private final TravelerController controller;
    private final Traveler userTraveler;
    private final MainView view;

    /**
     * Creates new form UpdateManagmentUserTraveler
     *
     * @param traveler
     * @param view
     */
    public UpdateManagmentUserTraveler(Traveler traveler, MainView view) {
        initComponents();
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
        setTitle("Registro de usuario");
        setSize(1200, 750);
        controller = new TravelerController();
        this.userTraveler = traveler;
        this.view = view;
        hideWarnings();
        showTravelerData();
    }

    private void showTravelerData() {
        txtId.setText(userTraveler.getId());
        txtName.setText(userTraveler.getFullname());
        txtUsername.setText(userTraveler.getUsername());
        txtPassword.setText(userTraveler.getPassword());
    }

    private void updateTraveler() {
        if (hasEmptyFields()) {
            JOptionPane.showMessageDialog(null, "Ingrsese todos los datos");
            return;
        }

        String id = txtId.getText();
        String name = txtName.getText();
        int age = Integer.parseInt(txtAge.getText());
        String phoneNumber = txtPhoneNumber.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        try {
            Traveler traveler = new Traveler(id, name, age, phoneNumber, username, password);
            controller.updateTraveler(traveler);

            JOptionPane.showMessageDialog(null, "Actualización exitosa");
            returnToLogin();
        } catch (UserAlreadyRegisteredException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            returnToLogin();
        } catch (UsernameInUseException | NumberAlreadyInUse ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void validateFields() {
        String id = txtId.getText();
        String username = txtUsername.getText();

        Traveler traveler = controller.searchTraveler(id);
        boolean usernameInUse = controller.isUsernameInUse(username);

        boolean enableBtnAddTraveler;

        if (!id.isEmpty() && traveler != null) {
            idWarning.setVisible(true);
            enableBtnAddTraveler = false;
        } else if (!username.isEmpty() && usernameInUse) {
            usernameWarning.setVisible(true);
            enableBtnAddTraveler = false;
        } else {
            idWarning.setVisible(false);
            usernameWarning.setVisible(false);
            enableBtnAddTraveler = true;
        }

        btnUpdate.setEnabled(enableBtnAddTraveler);

    }
    
     private void validatePhoneNumberTraveler() {
        String phoneNumber = txtPhoneNumber.getText();

        boolean phoneNumberInUse = controller.isPhoneNumberInUse(phoneNumber);

        boolean enableBtnAddTraveler;

        if (!phoneNumber.isEmpty() && phoneNumberInUse) {
            phoneNumberWarning.setVisible(true);
            enableBtnAddTraveler = false;
        } else {
            phoneNumberWarning.setVisible(false);
            enableBtnAddTraveler = true;

        }
        btnUpdate.setEnabled(enableBtnAddTraveler);
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
        phoneNumberWarning.setVisible(false);
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
        btnUpdate = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        idWarning = new javax.swing.JLabel();
        usernameWarning = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        phoneNumberWarning = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        btnUpdate.setBackground(new java.awt.Color(255, 255, 255));
        btnUpdate.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(0, 102, 153));
        btnUpdate.setText("ACTUALIZAR");
        btnUpdate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 2, true));
        btnUpdate.setContentAreaFilled(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
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

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 153));
        jLabel3.setText("Edad:");

        txtAge.setBorder(null);
        txtAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgeActionPerformed(evt);
            }
        });
        txtAge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAgeKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 153));
        jLabel7.setText("Telefono:");

        txtPhoneNumber.setBorder(null);
        txtPhoneNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPhoneNumberKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPhoneNumberKeyTyped(evt);
            }
        });

        phoneNumberWarning.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 10)); // NOI18N
        phoneNumberWarning.setForeground(new java.awt.Color(204, 0, 0));
        phoneNumberWarning.setText("TELEFONO EN USO");

        jSeparator2.setForeground(new java.awt.Color(0, 102, 153));

        jSeparator3.setForeground(new java.awt.Color(0, 102, 153));

        jSeparator4.setForeground(new java.awt.Color(0, 102, 153));

        jSeparator5.setForeground(new java.awt.Color(0, 102, 153));

        jSeparator6.setForeground(new java.awt.Color(0, 102, 153));

        jSeparator7.setForeground(new java.awt.Color(0, 102, 153));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/redo.png"))); // NOI18N
        jButton2.setContentAreaFilled(false);

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/undo.png"))); // NOI18N
        jButton3.setContentAreaFilled(false);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(498, 498, 498)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(371, 371, 371)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addGap(45, 45, 45)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator6)
                        .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(idWarning, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                        .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                        .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                        .addComponent(txtPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                        .addComponent(txtAge, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                        .addComponent(phoneNumberWarning)
                        .addComponent(usernameWarning)
                        .addComponent(jSeparator2)
                        .addComponent(jSeparator3)
                        .addComponent(jSeparator4)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator7)))
                .addGap(369, 378, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addGap(26, 26, 26))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(idWarning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(phoneNumberWarning)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(usernameWarning)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(15, 15, 15))))
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

    private void txtPhoneNumberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneNumberKeyTyped
        String phoneNumber = txtPhoneNumber.getText().trim();
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || phoneNumber.length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPhoneNumberKeyTyped

    private void txtPhoneNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneNumberKeyReleased
        validatePhoneNumberTraveler();
    }//GEN-LAST:event_txtPhoneNumberKeyReleased

    private void txtAgeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAgeKeyTyped
        String age = txtAge.getText().trim();
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || age.length() == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_txtAgeKeyTyped

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
        view.openLoginView();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void txtPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyTyped
        String pass = txtPassword.getText().trim();
        char c = evt.getKeyChar();
        if (!Character.isLetterOrDigit(c) || pass.length() == 20) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPasswordKeyTyped

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            updateTraveler();
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void txtUsernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyTyped
        String username = txtUsername.getText().trim();
        char c = evt.getKeyChar();
        if (!Character.isLetterOrDigit(c) || username.length() == 20) {
            evt.consume();
        }
    }//GEN-LAST:event_txtUsernameKeyTyped

    private void txtUsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyReleased
        validateFields();
    }//GEN-LAST:event_txtUsernameKeyReleased

    private void txtNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyTyped
        String name = txtName.getText();
        char c = evt.getKeyChar();

        if (Character.isDigit(c) || name.length() == 50) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNameKeyTyped

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        String id = txtId.getText();
        char c = evt.getKeyChar();

        if (!Character.isDigit(c) || id.length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtIdKeyTyped

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        validateFields();
    }//GEN-LAST:event_txtIdKeyReleased

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        updateTraveler();
        view.validateDesktop();
        view.openLoginView();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel idWarning;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel phoneNumberWarning;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JLabel usernameWarning;
    // End of variables declaration//GEN-END:variables
}
