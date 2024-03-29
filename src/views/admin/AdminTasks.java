/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package views.admin;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import model.Airline;
import model.Employee;
import model.User;
import singleton.Singleton;

/**
 *
 * @author joanp
 */
public class AdminTasks extends javax.swing.JInternalFrame {

    private final User user;

    /**
     * Creates new form AdminTasks
     *
     * @param user
     */
    public AdminTasks(User user) {
        initComponents();
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
        setTitle("Gestiones administrador");
        setResizable(false);
        setSize(1200, 750);
        this.user = user;
        User userLogged = Singleton.getINSTANCE().getUser();
        System.out.println(userLogged.getFullname());
    }

    public void validateDesktop() {
        if (dsMain.getComponentCount() > 0) {
            dsMain.getComponent(0).setVisible(false);
            dsMain.remove(0);
        }
    }

    public void openUpdateAdminAirline(Employee admin, AdminsAirline viewAdminsAirline, Airline airline, Airlines airlinesView) {
        UpdateAdminAirline view = new UpdateAdminAirline(admin, viewAdminsAirline, airlinesView, airline, this);
        dsMain.add(view);
        view.setVisible(true);
    }

    public void openRegisterAdminAirlinesView(Airline airline, Airlines airlinesView, AdminsAirline viewAdminsAirline) {
        RegisterAdminAirline view = new RegisterAdminAirline(airline, airlinesView, viewAdminsAirline, this);
        dsMain.add(view);
        view.setVisible(true);
    }

    public void openAdminsAirline(Airline airline, Airlines airlinesView) {
        AdminsAirline view = new AdminsAirline(airline, airlinesView, this);
        dsMain.add(view);
        view.setVisible(true);
    }

    public void openCreateAirlinesView(Airlines viewAirlines) {
        CreateAirline view = new CreateAirline(viewAirlines, this);
        dsMain.add(view);
        view.setVisible(true);
    }

    public void openUpdatedMaintenanceManagerView(Employee employee, MaintenanceManagers viewMaintenance) {
        UpdateMaintenanceManager view = new UpdateMaintenanceManager(employee, viewMaintenance, this);
        dsMain.add(view);
        view.setVisible(true);
    }

    public void openRegisterMaintenanceView(MaintenanceManagers viewMaintenance) {
        RegisterMaintenanceManager view = new RegisterMaintenanceManager(viewMaintenance, this);
        dsMain.add(view);
        view.setVisible(true);
    }

    public void openMaintenanceManagersView() {
        MaintenanceManagers view = new MaintenanceManagers(this);
        dsMain.add(view);
        view.setVisible(true);
    }

    public void openAirlinesView() {
        Airlines view = new Airlines(this);
        dsMain.add(view);
        view.setVisible(true);
    }

    public void openRequestView() {
        Request view = new Request();
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
        ImageIcon icon = new ImageIcon(getClass().getResource("/icon/fondo-blanco-2.jpeg"));
        Image image = icon.getImage();
        dsMain = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics g) {
                g.drawImage(image,0,0,getWidth(),getHeight(),this);
            }
        };
        jLabel1 = new javax.swing.JLabel();
        btnMaintenanceEmployee = new javax.swing.JButton();
        btnAirlines = new javax.swing.JButton();
        btnRequest = new javax.swing.JButton();

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout dsMainLayout = new javax.swing.GroupLayout(dsMain);
        dsMain.setLayout(dsMainLayout);
        dsMainLayout.setHorizontalGroup(
            dsMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dsMainLayout.setVerticalGroup(
            dsMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 667, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setText("GESTIONAR:");

        btnMaintenanceEmployee.setBackground(new java.awt.Color(0, 102, 153));
        btnMaintenanceEmployee.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnMaintenanceEmployee.setForeground(new java.awt.Color(255, 255, 255));
        btnMaintenanceEmployee.setText("Gestores de mantenimiento");
        btnMaintenanceEmployee.setBorder(null);
        btnMaintenanceEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaintenanceEmployeeActionPerformed(evt);
            }
        });

        btnAirlines.setBackground(new java.awt.Color(0, 102, 153));
        btnAirlines.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnAirlines.setForeground(new java.awt.Color(255, 255, 255));
        btnAirlines.setText("Aerolineas");
        btnAirlines.setBorder(null);
        btnAirlines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAirlinesActionPerformed(evt);
            }
        });

        btnRequest.setBackground(new java.awt.Color(0, 102, 153));
        btnRequest.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnRequest.setForeground(new java.awt.Color(255, 255, 255));
        btnRequest.setText("Solicitudes");
        btnRequest.setBorder(null);
        btnRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRequestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dsMain, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel1)
                .addGap(72, 72, 72)
                .addComponent(btnMaintenanceEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnAirlines, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btnRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(214, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(21, 21, 21))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnMaintenanceEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAirlines, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(dsMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnAirlinesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAirlinesActionPerformed
        validateDesktop();
        openAirlinesView();
    }//GEN-LAST:event_btnAirlinesActionPerformed

    private void btnMaintenanceEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaintenanceEmployeeActionPerformed
        validateDesktop();
        openMaintenanceManagersView();
    }//GEN-LAST:event_btnMaintenanceEmployeeActionPerformed

    private void btnRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRequestActionPerformed
        validateDesktop();
        openRequestView();
    }//GEN-LAST:event_btnRequestActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAirlines;
    private javax.swing.JButton btnMaintenanceEmployee;
    private javax.swing.JButton btnRequest;
    private javax.swing.JDesktopPane dsMain;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
