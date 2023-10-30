/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package views.admin;

import controllers.AirlineEmployeeController;
import enums.Role;
import exceptions.UserAlreadyRegisteredException;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import model.Airline;
import model.Employee;
import model.Traveler;

/**
 *
 * @author joanp
 */
public class RegisterAdminAirline extends javax.swing.JInternalFrame {

    private final Airline airline;
    private final Airlines airlineView;
    private final AdminsAirline view;

    private final AirlineEmployeeController controller;
    private final AdminTasks view2;

    /**
     * Creates new form RegisterAdminAirline
     *
     * @param airline
     * @param airlineView
     * @param view
     * @param view2
     */
    public RegisterAdminAirline(Airline airline, Airlines airlineView, AdminsAirline view, AdminTasks view2) {
        initComponents();
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
        setResizable(false);
        setSize(1200, 700);
        controller = new AirlineEmployeeController(airline);
        this.airline = airline;
        this.airlineView = airlineView;
        this.view = view;
        hideWarnings();
        this.view2 = view2;
    }

    private void cleanFields() {
        txtAdminId.setText("");
        txtAdminName.setText("");
        txtAdminEmail.setText("");
        txtAdminUsername.setText("");
        txtAdminPassword.setText("");
        txtAdminSalary.setText("");
        txtAge.setText("");
    }

    private void validateFields() {
        String adminId = txtAdminId.getText();
        String adminUsername = txtAdminUsername.getText();

        Employee employee = controller.searchEmployee(adminId);
        Traveler traveler = controller.searchTraveler(adminId);

        boolean usernameInUse = controller.isUsernameInUse(adminUsername);

        boolean enableBtnAddAdmin;

        if (!adminId.isEmpty() && traveler != null) {
            lblEmployeeFound.setVisible(true);
            fillFields(adminId);
            noEditableFields();
        } else {
            lblEmployeeFound.setVisible(false);
            editableFields();
            cleanFillFields();
        }

        if (!adminId.isEmpty() && employee != null) {
            idAdminWarning.setVisible(true);
            enableBtnAddAdmin = false;
        } else if (!adminUsername.isEmpty() && usernameInUse) {
            usernameWarning.setVisible(true);
            enableBtnAddAdmin = false;
        } else {
            idAdminWarning.setVisible(false);
            usernameWarning.setVisible(false);
            enableBtnAddAdmin = true;
        }

        btnAddAdmin.setEnabled(enableBtnAddAdmin);

    }

    private void validateUsername() {
        String adminUsername = txtAdminUsername.getText();

        boolean usernameInUse = controller.isUsernameInUse(adminUsername);

        boolean enableBtnAddAdmin = true;

        if (!adminUsername.isEmpty() && usernameInUse) {
            usernameWarning.setVisible(true);
            enableBtnAddAdmin = false;
        } else {
            usernameWarning.setVisible(false);
        }

        btnAddAdmin.setEnabled(enableBtnAddAdmin);

    }

    private void editableFields() {
        txtAdminName.setEditable(true);
        txtAdminUsername.setEditable(true);
        txtAdminPassword.setEditable(true);
        txtAge.setEditable(true);
    }

    private void noEditableFields() {
        txtAdminName.setEditable(false);
        txtAdminUsername.setEditable(false);
        txtAdminPassword.setEditable(false);
        txtAge.setEditable(false);
    }

    private void fillFields(String id) {
        Traveler traveler = controller.searchTraveler(id);
        if (traveler != null) {
            txtAdminName.setText(traveler.getFullname());
            txtAdminUsername.setText(traveler.getUsername());
            txtAdminPassword.setText(traveler.getPassword());
            txtAge.setText(String.valueOf(txtAge.getText()));
        }

    }

    private void cleanFillFields() {
        txtAdminName.setText("");
        txtAdminUsername.setText("");
        txtAdminPassword.setText("");
        txtAge.setText("");
    }

    private boolean hasEmptyFields() {
        return (txtAdminId.getText().isEmpty() || txtAdminName.getText().isEmpty() || txtAdminEmail.getText().isEmpty() || txtAge.getText().isEmpty()
                || txtAdminSalary.getText().isEmpty() || txtAdminUsername.getText().isEmpty() || txtAdminPassword.getText().isEmpty());
    }

    private void hideWarnings() {
        idAdminWarning.setVisible(false);
        usernameWarning.setVisible(false);
        lblEmployeeFound.setVisible(false);
    }

    private void addAdmin() {
        if (hasEmptyFields()) {
            JOptionPane.showMessageDialog(null, "Ingrese todos los datos del admin");
            return;
        }

        String adminId = txtAdminId.getText();
        String adminName = txtAdminName.getText();
        String emailAdmin = txtAdminEmail.getText();
        int age = Integer.parseInt(txtAge.getText());
        String usernameAdmin = txtAdminUsername.getText();
        String passwordAdmin = txtAdminPassword.getText();
        double salaryAdmin = Double.parseDouble(txtAdminSalary.getText());
        Traveler traveler = controller.searchTraveler(adminId);
        Employee admin = new Employee(emailAdmin, salaryAdmin, age, Role.AIRLINE_ADMIN, adminId, adminName, usernameAdmin, passwordAdmin);

        if (traveler != null) {
            int option = JOptionPane.showConfirmDialog(this, "La cédula ingresada ya está registrada como viajero. "
                    + "¿Desea registrarse como empleado?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                controller.addTravelerAsEmployee(admin);
                JOptionPane.showMessageDialog(null, "Registro exitoso");
                view.fillTable();
                cleanFields();
                return;
            }
        }

        try {
            controller.addEmployee(admin);
            JOptionPane.showMessageDialog(null, "Registro exitoso");
            view.fillTable();
            cleanFields();
        } catch (UserAlreadyRegisteredException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
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
        btnAddAdmin = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        txtAdminId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtAdminName = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        txtAdminEmail = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtAdminSalary = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        txtAdminUsername = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        txtAdminPassword = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        idAdminWarning = new javax.swing.JLabel();
        usernameWarning = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        lblEmployeeFound = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        btnAddAdmin.setBackground(new java.awt.Color(255, 255, 255));
        btnAddAdmin.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnAddAdmin.setForeground(new java.awt.Color(0, 102, 153));
        btnAddAdmin.setText("GUARDAR");
        btnAddAdmin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 153), 2, true));
        btnAddAdmin.setContentAreaFilled(false);
        btnAddAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAdminActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setText("ID:");

        txtAdminId.setBorder(null);
        txtAdminId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAdminIdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAdminIdKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setText("Nombre:");

        txtAdminName.setBorder(null);
        txtAdminName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAdminNameKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 153));
        jLabel3.setText("Email:");

        txtAdminEmail.setBorder(null);

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 153));
        jLabel4.setText("Salario:");

        txtAdminSalary.setBorder(null);
        txtAdminSalary.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAdminSalaryKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setText("Usuario:");

        txtAdminUsername.setBorder(null);
        txtAdminUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAdminUsernameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAdminUsernameKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 153));
        jLabel6.setText("Contraseña:");

        txtAdminPassword.setBorder(null);
        txtAdminPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAdminPasswordKeyTyped(evt);
            }
        });

        idAdminWarning.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 10)); // NOI18N
        idAdminWarning.setForeground(new java.awt.Color(204, 0, 0));
        idAdminWarning.setText("NO PUEDE TENER DOS CUENTAS");

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

        lblEmployeeFound.setBackground(new java.awt.Color(0, 102, 153));
        lblEmployeeFound.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 10)); // NOI18N
        lblEmployeeFound.setForeground(new java.awt.Color(0, 102, 153));
        lblEmployeeFound.setText("VIAJERO YA REGISTRADO");

        jLabel8.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 153));
        jLabel8.setText("Edad:");

        txtAge.setBorder(null);
        txtAge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAgeKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addGap(26, 26, 26))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator3)
                                    .addComponent(txtAdminEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(idAdminWarning)
                                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jSeparator1)
                                        .addComponent(txtAdminId, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblEmployeeFound)))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(usernameWarning)
                                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jSeparator5)
                                        .addComponent(txtAdminUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(112, 112, 112)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator4)
                                    .addComponent(txtAdminSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator6)
                                    .addComponent(txtAdminPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator2)
                                    .addComponent(txtAdminName, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(360, 360, 360)
                        .addComponent(jLabel8)
                        .addGap(71, 71, 71)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator8)
                                .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(228, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(idAdminWarning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAdminName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAdminId, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(lblEmployeeFound)
                .addGap(87, 87, 87)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAdminEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAdminSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(100, 100, 100)
                .addComponent(usernameWarning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAdminUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAdminPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(77, 77, 77)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(93, 93, 93)
                .addComponent(btnAddAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
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

    private void btnAddAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAdminActionPerformed
        addAdmin();
    }//GEN-LAST:event_btnAddAdminActionPerformed

    private void txtAdminIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdminIdKeyReleased
        validateFields();
    }//GEN-LAST:event_txtAdminIdKeyReleased

    private void txtAdminIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdminIdKeyTyped
        String id = txtAdminId.getText();
        char c = evt.getKeyChar();

        if (!Character.isDigit(c) || id.length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtAdminIdKeyTyped

    private void txtAdminNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdminNameKeyTyped
        String name = txtAdminName.getText();
        char c = evt.getKeyChar();

        if (Character.isDigit(c) || name.length() == 50) {
            evt.consume();
        }
    }//GEN-LAST:event_txtAdminNameKeyTyped

    private void txtAdminSalaryKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdminSalaryKeyTyped
        String salary = txtAdminSalary.getText();
        char c = evt.getKeyChar();

        if (!Character.isDigit(c) || salary.length() == 15) {
            evt.consume();
        }
    }//GEN-LAST:event_txtAdminSalaryKeyTyped

    private void txtAdminUsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdminUsernameKeyReleased
        validateUsername();
    }//GEN-LAST:event_txtAdminUsernameKeyReleased

    private void txtAdminUsernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdminUsernameKeyTyped
        String username = txtAdminUsername.getText().trim();
        char c = evt.getKeyChar();
        if (!Character.isLetterOrDigit(c) || username.length() == 20) {
            evt.consume();
        }
    }//GEN-LAST:event_txtAdminUsernameKeyTyped

    private void txtAdminPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdminPasswordKeyTyped
        String pass = txtAdminPassword.getText().trim();
        char c = evt.getKeyChar();
        if (!Character.isLetterOrDigit(c) || pass.length() == 20) {
            evt.consume();
        }
    }//GEN-LAST:event_txtAdminPasswordKeyTyped

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        view2.validateDesktop();
        view2.openAdminsAirline(airline, airlineView);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void txtAgeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAgeKeyTyped
        String age = txtAge.getText().trim();
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || age.length() == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_txtAgeKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddAdmin;
    private javax.swing.JButton btnClose;
    private javax.swing.JLabel idAdminWarning;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel lblEmployeeFound;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField txtAdminEmail;
    private javax.swing.JTextField txtAdminId;
    private javax.swing.JTextField txtAdminName;
    private javax.swing.JTextField txtAdminPassword;
    private javax.swing.JTextField txtAdminSalary;
    private javax.swing.JTextField txtAdminUsername;
    private javax.swing.JTextField txtAge;
    private javax.swing.JLabel usernameWarning;
    // End of variables declaration//GEN-END:variables
}
