/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.main;

import controllers.LoginController;
import controllers.TravelerController;
import enums.Role;
import static enums.Role.AIRLINE_ADMIN;
import static enums.Role.FLIGHT_CAPTAIN;
import static enums.Role.GENERAL_ADMIN;
import static enums.Role.LOGISTICS_EMPLOYEE;
import static enums.Role.MAINTENANCE_MANAGER;
import static enums.Role.TRAVELER;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Airline;
import model.Employee;
import model.Traveler;
import model.User;
import singleton.Singleton;
import util.LSE;
import view.login.HasMultiUserView;
import view.login.LoginView;
import views.admin.AdminTasks;
import views.adminAirlines.AirlineAdminTasks;
import views.logisticsEmployee.LogisticsEmployeeTasks;
import views.traveler.RegisterView;
import views.traveler.UpdateManagmentUserTraveler;

/**
 *
 * @author joanp
 */
public class MainView extends javax.swing.JFrame {

    private final TravelerController controller;
    private final LoginController controller2;

    /**
     * Creates new form MainView
     *
     */
    public MainView() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vista principal del aeropuerto");
        setLocationRelativeTo(null);
        setResizable(false);
        controller = new TravelerController();
        controller2 = new LoginController();
        hidePanelMenu();
        validateButton();
        addAdmin();
    }

    public final void validateButton() {

        try {

            User user = controller2.getUser();

            if (lblUserName.getText().isEmpty()) {
                btnManagments.setVisible(false);
            } else if (user == null) {
                btnManagments.setVisible(false);
            }

        } catch (IllegalStateException ex) {
            btnManagments.setVisible(false);
        }

    }

    public void validateBtnForEmployess() {
        try {
            User user = Singleton.getINSTANCE().getUser();

            if (user != null) {

                switch (user.getRole()) {

                    case TRAVELER -> {
                        btnManagments.setVisible(false);
                    }

                    case FLIGHT_CAPTAIN -> {
                        btnManagments.setVisible(false);
                    }

                    case MAINTENANCE_MANAGER -> {
                        btnManagments.setVisible(false);
                    }
                    default -> {
                        btnManagments.setVisible(false);
                    }
                }
            }

        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public void validateBtnForLogisticEmployee(Airline airline) {
        try {
            User user = Singleton.getINSTANCE().getUser();

            switch (user.getRole()) {
                case LOGISTICS_EMPLOYEE -> {
                    openLogisticsEmployeeTasks(airline);
                    btnManagments.setVisible(true);
                }

                case TRAVELER -> {
                    btnManagments.setVisible(false);
                }

                case FLIGHT_CAPTAIN -> {
                    btnManagments.setVisible(false);
                }

                case MAINTENANCE_MANAGER -> {
                    btnManagments.setVisible(false);
                }
                default -> {
                    btnManagments.setVisible(false);
                }
            }

        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public void validateBtnManagmentAirlineAdmin(Employee employee, Airline airline) {
        try {
            User user = Singleton.getINSTANCE().getUser();

            switch (user.getRole()) {
                case AIRLINE_ADMIN -> {
                    openAirlineAdminTasks(employee, airline);
                    btnManagments.setVisible(true);
                }
                default -> {
                    btnManagments.setVisible(false);
                }
            }

        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void validateBtnManagmentGeneralAdmin(User user1) {
        try {
            User user = Singleton.getINSTANCE().getUser();

            switch (user.getRole()) {
                case GENERAL_ADMIN -> {
                    openAdminTasks(user1);
                    btnManagments.setVisible(true);
                }

                default -> {
                    btnManagments.setVisible(false);
                }
            }

        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void addAdmin() {
        LSE<User> users = Singleton.getINSTANCE().getUsers();
        User GeneralAdmin = new User(Role.GENERAL_ADMIN, "0", "administrador", "admin", "123");
        users.addDato(GeneralAdmin);
        Singleton.getINSTANCE().writeUser();

    }

    public void setLaberlAirline(String airline) {
        lblAirlineName.setText(airline);
    }

    public String getLabelUser() {
        return lblUserName.getText();
    }

    private void setLaberlUserNameLogged() {
        try {
            User userLoggued = Singleton.getINSTANCE().getUser();
            String username = userLoggued.getUsername();
            lblUserName.setText(username);

        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void setLabelUserName(String name) {
        lblUserName.setText(name);
    }

    private void hidePanelMenu() {
        Animacion.Animacion.mover_izquierda(0, -300, 1, 1, panelMenu);
    }

    public void validateDesktop() {
        if (dsMain.getComponentCount() > 0) {
            dsMain.getComponent(0).setVisible(false);
            dsMain.remove(0);
        }
    }

    public void openLogisticsEmployeeTasks(Airline airline) {
        LogisticsEmployeeTasks view = new LogisticsEmployeeTasks(airline);
        dsMain.add(view);
        view.setVisible(true);
    }

    public void openMultiUsersView(User user) {
        HasMultiUserView view = new HasMultiUserView(user, this);
        dsMain.add(view);
        view.setVisible(true);
    }

    public void openUpdatedTravelerInformation(Traveler traveler) {
        UpdateManagmentUserTraveler view = new UpdateManagmentUserTraveler(traveler, this);
        dsMain.add(view);
        view.setVisible(true);
    }

    public void openAirlineAdminTasks(Employee employee, Airline airline) {
        validateDesktop();
        AirlineAdminTasks view = new AirlineAdminTasks(employee, airline);
        dsMain.add(view);
        view.setVisible(true);
    }

    public void openAdminTasks(User user) {
        validateDesktop();
        AdminTasks view = new AdminTasks(user);
        dsMain.add(view);
        view.setVisible(true);
    }

    public void openLoginView() {
        validateDesktop();
        LoginView view = new LoginView(this);
        dsMain.add(view);
        view.setVisible(true);
    }

    public void openRegisterView() {
        validateDesktop();
        RegisterView view = new RegisterView(this);
        dsMain.add(view);
        view.setVisible(true);
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
        jPanel1 = new javax.swing.JPanel();
        lblUserName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblAirlineName = new javax.swing.JLabel();
        btnMenu = new javax.swing.JButton();
        btnSettings = new javax.swing.JButton();
        ImageIcon icon = new ImageIcon(getClass().getResource("/icon/fondo-blanco-2.jpeg"));
        Image image = icon.getImage();
        dsMain = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics g) {
                g.drawImage(image,0,0,getWidth(),getHeight(),this);
            }
        };
        panelMenu = new javax.swing.JPanel();
        btnHome = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        btnAirlines = new javax.swing.JButton();
        btnManagments = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));

        lblUserName.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        lblUserName.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/user.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/international-departures.png"))); // NOI18N

        lblAirlineName.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        lblAirlineName.setForeground(new java.awt.Color(255, 255, 255));

        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/menu.png"))); // NOI18N
        btnMenu.setContentAreaFilled(false);
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        btnSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gear.png"))); // NOI18N
        btnSettings.setContentAreaFilled(false);
        btnSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSettingsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(47, 47, 47)
                .addComponent(lblUserName)
                .addGap(137, 137, 137)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(lblAirlineName)
                .addGap(132, 132, 132)
                .addComponent(btnSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3))
                                    .addComponent(btnMenu))
                                .addGap(14, 14, 14))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblAirlineName)
                                .addGap(24, 24, 24))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblUserName)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout dsMainLayout = new javax.swing.GroupLayout(dsMain);
        dsMain.setLayout(dsMainLayout);
        dsMainLayout.setHorizontalGroup(
            dsMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1227, Short.MAX_VALUE)
        );
        dsMainLayout.setVerticalGroup(
            dsMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelMenu.setBackground(new java.awt.Color(204, 204, 204));

        btnHome.setBackground(new java.awt.Color(255, 255, 255));
        btnHome.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        btnHome.setForeground(new java.awt.Color(0, 0, 0));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/home.png"))); // NOI18N
        btnHome.setText("               HOME");
        btnHome.setBorder(null);
        btnHome.setContentAreaFilled(false);
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(255, 255, 255));
        btnLogin.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(0, 0, 0));
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/log-in.png"))); // NOI18N
        btnLogin.setText("                LOGIN");
        btnLogin.setBorder(null);
        btnLogin.setContentAreaFilled(false);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnAirlines.setBackground(new java.awt.Color(255, 255, 255));
        btnAirlines.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        btnAirlines.setForeground(new java.awt.Color(0, 0, 0));
        btnAirlines.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit.png"))); // NOI18N
        btnAirlines.setText("       REGISTRO");
        btnAirlines.setBorder(null);
        btnAirlines.setContentAreaFilled(false);
        btnAirlines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAirlinesActionPerformed(evt);
            }
        });

        btnManagments.setBackground(new java.awt.Color(255, 255, 255));
        btnManagments.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        btnManagments.setForeground(new java.awt.Color(0, 0, 0));
        btnManagments.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/flight.png"))); // NOI18N
        btnManagments.setText("    GESTIONES");
        btnManagments.setBorder(null);
        btnManagments.setContentAreaFilled(false);
        btnManagments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManagmentsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAirlines, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnManagments, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAirlines, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnManagments, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 717, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(dsMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dsMain)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        int posicion = panelMenu.getX();
        if (posicion > -1) {
            Animacion.Animacion.mover_izquierda(0, -300, 2, 2, panelMenu);
        } else {
            Animacion.Animacion.mover_derecha(-300, 0, 2, 2, panelMenu);
        }
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        validateDesktop();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        openLoginView();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnAirlinesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAirlinesActionPerformed
        openRegisterView();
    }//GEN-LAST:event_btnAirlinesActionPerformed

    private void btnManagmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManagmentsActionPerformed
        try {
            User user = Singleton.getINSTANCE().getUser();

            switch (user.getRole()) {
                case AIRLINE_ADMIN:
                    Airline airline = controller2.searchAirline(user.getId());
                    openAirlineAdminTasks((Employee) user, airline);
                    break;
                case FLIGHT_CAPTAIN:

                    break;
                case GENERAL_ADMIN:
                    openAdminTasks(user);
                    break;
                case LOGISTICS_EMPLOYEE:

                    break;
                case MAINTENANCE_MANAGER:

                    break;
                case TRAVELER:

                    break;

                default:
                    JOptionPane.showMessageDialog(null, "No puede acceder, no tiene los permisos");
            }

        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnManagmentsActionPerformed

    private void btnSettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSettingsMouseClicked

        try {
            User user = Singleton.getINSTANCE().getUser();
            String id = user.getId();
            Traveler traveler = controller.searchTraveler(id);
            Employee employee = controller.searchEmployee(id);

            switch (user.getRole()) {
                case TRAVELER:
                    openUpdatedTravelerInformation(traveler);
                    break;
                case AIRLINE_ADMIN:
                    break;
                case FLIGHT_CAPTAIN:

                    break;
                case GENERAL_ADMIN:

                    break;
                case LOGISTICS_EMPLOYEE:

                    break;
                case MAINTENANCE_MANAGER:

                    break;
                default:
                    JOptionPane.showMessageDialog(null, "No puede modificar su información, no tienes el permiso");
            }

        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnSettingsMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAirlines;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnManagments;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnSettings;
    private javax.swing.JDesktopPane dsMain;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAirlineName;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel panelMenu;
    // End of variables declaration//GEN-END:variables
}
