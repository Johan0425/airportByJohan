/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package views.logisticsEmployee;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import model.Airline;

/**
 *
 * @author joanp
 */
public class LogisticsEmployeeTasks extends javax.swing.JInternalFrame {

    private final Airline airline;

    /**
     * Creates new form LogisticsEmployeeTasks
     *
     * @param airline
     */
    public LogisticsEmployeeTasks(Airline airline) {
        initComponents();
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
        setResizable(false);
        setSize(1200, 750);
        this.airline = airline;
    }

    public void validateDesktop() {
        if (dsMain.getComponentCount() > 0) {
            dsMain.getComponent(0).setVisible(false);
            dsMain.remove(0);
        }

    }

    public void openFlights() {
        Flights view = new Flights(airline, this);
        dsMain.add(view);
        view.setVisible(true);
    }
    
    public void openRegisterFlight(Airline airline, Flights view2) {
        RegisterFlight view = new RegisterFlight(airline, view2);
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
        btnFlights = new javax.swing.JButton();

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

        btnFlights.setBackground(new java.awt.Color(0, 102, 153));
        btnFlights.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnFlights.setForeground(new java.awt.Color(255, 255, 255));
        btnFlights.setText("Vuelos");
        btnFlights.setBorder(null);
        btnFlights.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFlightsActionPerformed(evt);
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
                .addComponent(btnFlights, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(556, Short.MAX_VALUE))
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
                        .addComponent(btnFlights, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btnFlightsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFlightsActionPerformed
        validateDesktop();
        openFlights();
    }//GEN-LAST:event_btnFlightsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFlights;
    private javax.swing.JDesktopPane dsMain;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
