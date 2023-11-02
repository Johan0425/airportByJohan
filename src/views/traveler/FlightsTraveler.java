/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package views.traveler;

import controllers.FlightReservationController;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.Airplane;
import model.Flight;
import util.LSE;

/**
 *
 * @author joanp
 */
public class FlightsTraveler extends javax.swing.JInternalFrame {

    private final FlightReservationController controller;
    private final ActionsTraveler view;

    /**
     * Creates new form Flights
     *
     * @param view
     */
    public FlightsTraveler(ActionsTraveler view) {
        initComponents();
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
        setResizable(false);
        setSize(1200, 750);
        controller = new FlightReservationController();
        this.view = view;
        fillTable();
    }

    public final void fillTable() {
        DefaultTableModel model = new DefaultTableModel();

        LSE<Flight> flights = controller.listAllFlights();
        model.setColumnIdentifiers(new Object[]{
            "ID", "Capitan", "Avión", "Fecha", "Hora", "Origen", "Destino", "Tiempo aprox"
        });

        flightsTable.setModel(model);

        List<Flight> flightList = new ArrayList<>(flights.size());

        for (int i = 0; i < flights.size(); i++) {
            Flight flight = flights.get(i);
            flightList.add(flight);
            model.addRow(new Object[]{
                flight.getId(),
                flight.getCaptain().getFullname(),
                flight.getAirplane().getModel(),
                flight.getDate(),
                flight.getHour(),
                flight.getOrigin(),
                flight.getDestination(),
                flight.getAproximateTime()
            });
        }

        Comparator<Flight> dateTimeComparator = Comparator.comparing(
                flight -> LocalDateTime.of(flight.getDate(), flight.getHour())
        );

        flightList.sort(dateTimeComparator);

        for (int i = 0; i < flightList.size(); i++) {
            Flight flight = flightList.get(i);
            model.setValueAt(flight.getId(), i, 0);
            model.setValueAt(flight.getCaptain().getFullname(), i, 1);
            model.setValueAt(flight.getAirplane().getModel(), i, 2);
            model.setValueAt(flight.getDate(), i, 3);
            model.setValueAt(flight.getHour(), i, 4);
            model.setValueAt(flight.getOrigin(), i, 5);
            model.setValueAt(flight.getDestination(), i, 6);
            model.setValueAt(flight.getAproximateTime(), i, 7);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        flightsTable = new javax.swing.JTable();
        btnPreviewAirplane = new javax.swing.JButton();

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        flightsTable.setBackground(new java.awt.Color(255, 255, 255));
        flightsTable.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        flightsTable.setForeground(new java.awt.Color(0, 102, 153));
        flightsTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(flightsTable);

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

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 968, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(402, 402, 402)
                        .addComponent(btnPreviewAirplane)))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(btnPreviewAirplane, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(266, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPreviewAirplaneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviewAirplaneActionPerformed
        int selected = flightsTable.getSelectedRow();

        if (selected >= 0) {

            String value = flightsTable.getModel().getValueAt(selected, 0).toString();
            int id = Integer.parseInt(value);

            Flight flight = controller.findFlightById(id);
            Airplane airplane = flight.getAirplane();

            if (airplane != null) {

                int groupSeats = airplane.getNumCols() / 2;
                view.validateDesktop();
                view.openPreviewPlane(groupSeats, airplane);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el avión");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un avión de la tabla para previsualizar");
        }
    }//GEN-LAST:event_btnPreviewAirplaneActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPreviewAirplane;
    private javax.swing.JTable flightsTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
