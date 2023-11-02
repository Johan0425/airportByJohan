/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package views.logisticsEmployee;

import controllers.FlightsController;
import enums.Cities;
import enums.Event;
import exceptions.FlightException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.Airline;
import model.Airplane;
import model.Captain;
import model.Flight;
import util.LSE;

/**
 *
 * @author joanp
 */
public class UpdateFlight extends javax.swing.JInternalFrame {

    private final Airline airline;
    private final Flight flight;
    private final Flights view;
    private final LogisticsEmployeeTasks view2;
    private final FlightsController controller;

    /**
     * Creates new form UpdateFlight
     *
     * @param airline
     * @param flight
     * @param view
     * @param view2
     */
    public UpdateFlight(Airline airline, Flight flight, Flights view, LogisticsEmployeeTasks view2) {
        initComponents();
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
        setResizable(false);
        setSize(1200, 750);
        this.airline = airline;
        this.flight = flight;
        this.view = view;
        this.view2 = view2;
        controller = new FlightsController(airline);
        setCbxDestination();
        setCbxOrigin();
        showFlightData();
        hideWarnings();
    }

    private void hideWarnings() {
        warningDate.setVisible(false);
        warningHour.setVisible(false);
    }

    private void fillTableCaptains() {
        DefaultTableModel model = new DefaultTableModel();
        Cities location = Cities.valueOf(cbxOrigin.getSelectedItem().toString());
        LocalTime hour = LocalTime.parse(txtHour.getText());
        int apxtime = Integer.parseInt(txtAproximateTime.getText());

        LocalDate date = validateDate(txtDate.getText());

        LSE<Captain> captains = controller.listAvailableCaptains(location, date, hour, hour.plusHours(apxtime));

        model.setColumnIdentifiers(new Object[]{
            "ID", "Nombre"
        });
        captainsTable.setModel(model);
        if (!captains.isEmpty()) {
            for (int i = 0; i < captains.size(); i++) {
                model.addRow(new Object[]{
                    captains.get(i).getId(),
                    captains.get(i).getFullname()
                });
            }
        }
    }

    private void fillTableAirplanes() {
        DefaultTableModel model = new DefaultTableModel();

        Cities location = Cities.valueOf(cbxOrigin.getSelectedItem().toString());
        LocalTime hour = LocalTime.parse(txtHour.getText());
        int apxtime = Integer.parseInt(txtAproximateTime.getText());

        LocalDate date = validateDate(txtDate.getText());

        LSE<Airplane> airplanes = controller.listAvailableAirplanes(location, date, hour, hour.plusHours(apxtime));

        model.setColumnIdentifiers(new Object[]{
            "Modelo", "N° filas", "N° Columnas"
        });
        airplanesTable.setModel(model);
        if (!airplanes.isEmpty()) {
            for (int i = 0; i < airplanes.size(); i++) {
                model.addRow(new Object[]{
                    airplanes.get(i).getModel(),
                    airplanes.get(i).getNumRows(),
                    airplanes.get(i).getNumCols()
                });
            }
        }
    }

    private LocalDate validateDate(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate selectedDate = null;

        try {
            if (!date.isEmpty()) {
                selectedDate = LocalDate.parse(date, dateFormatter);

            }

        } catch (Exception ex) {

        }
        return selectedDate;
    }

    private void setCbxOrigin() {
        Cities[] cities = Cities.values();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        model.addElement("Seleccione una opción");
        for (Cities city : cities) {
            model.addElement(city.toString());
        }

        cbxOrigin.setModel(model);
        cbxOrigin.setSelectedIndex(0);
    }

    private void setCbxDestination() {
        Cities[] cities = Cities.values();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        model.addElement("Seleccione una opción");
        for (Cities city : cities) {
            model.addElement(city.toString());
        }

        cbxDestination.setModel(model);
        cbxDestination.setSelectedIndex(0);
    }

    private boolean hasEmptyFields() {
        return (cbxOrigin.getSelectedIndex() == 0 || cbxDestination.getSelectedIndex() == 0 || txtHour.getText().isEmpty()
                || txtAproximateTime.getText().isEmpty() || txtDate.getText().isEmpty());
    }

    private boolean isValidTimeFormat(String time) {
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        formatoHora.setLenient(false);

        try {
            Date date = formatoHora.parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int hours = calendar.get(Calendar.HOUR_OF_DAY);
            int minutes = calendar.get(Calendar.MINUTE);

            return hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59;
        } catch (ParseException ex) {
            return false;
        }
    }

    private void formatDate() {

        String date = txtDate.getText().trim().replaceAll("/", "");

        StringBuilder formattedText = new StringBuilder();

        for (int i = 0; i < date.length(); i++) {
            formattedText.append(date.charAt(i));
            if (i == 1 || i == 3) {
                formattedText.append('/');
            }
        }
        txtDate.setText(formattedText.toString());
    }

    private void showFlightData() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        cbxOrigin.setSelectedItem(flight.getOrigin().toString());
        cbxDestination.setSelectedItem(flight.getDestination().toString());
        txtDate.setText(String.valueOf(flight.getDate().format(dateFormatter)));
        txtHour.setText(String.valueOf(flight.getHour()));
        txtAproximateTime.setText(String.valueOf(flight.getAproximateTime()));
        lblCaptain.setText(flight.getCaptain().getId());
        lblAirplane.setText(flight.getAirplane().getModel());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtAproximateTime = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtHour = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        cbxOrigin = new javax.swing.JComboBox<>();
        cbxDestination = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        airplanesTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        captainsTable = new javax.swing.JTable();
        txtDate = new javax.swing.JFormattedTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        warningHour = new javax.swing.JLabel();
        warningDate = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblCaptain = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblAirplane = new javax.swing.JLabel();
        btnUpdateFlight = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(204, 0, 51));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información Vuelo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel1.setBackground(new java.awt.Color(0, 102, 153));
        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setText("Origen:");

        jLabel2.setBackground(new java.awt.Color(0, 102, 153));
        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setText("Destino:");

        jLabel5.setBackground(new java.awt.Color(0, 102, 153));
        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setText("Fecha:");

        txtAproximateTime.setEnabled(false);
        txtAproximateTime.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAproximateTimeKeyReleased(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(0, 102, 153));
        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 153));
        jLabel6.setText("Hora:");

        try {
            txtHour.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtHour.setEnabled(false);
        txtHour.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHourKeyReleased(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(0, 102, 153));
        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 153));
        jLabel7.setText("Tiempo aproximado:");

        cbxOrigin.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxOriginItemStateChanged(evt);
            }
        });

        cbxDestination.setEnabled(false);
        cbxDestination.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDestinationItemStateChanged(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(0, 102, 153));
        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 153));
        jLabel4.setText("Capitan:");

        jLabel3.setBackground(new java.awt.Color(0, 102, 153));
        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 153));
        jLabel3.setText("Avión:");

        airplanesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Modelo", "N° Filas", "N° Columnas"
            }
        ));
        airplanesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                airplanesTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(airplanesTable);

        captainsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Nombre"
            }
        ));
        captainsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                captainsTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(captainsTable);

        try {
            txtDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDate.setEnabled(false);
        txtDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDateKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDateKeyTyped(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 102, 153));
        jSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jSeparator2.setForeground(new java.awt.Color(0, 102, 153));
        jSeparator2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jSeparator3.setForeground(new java.awt.Color(0, 102, 153));
        jSeparator3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jSeparator4.setForeground(new java.awt.Color(0, 102, 153));
        jSeparator4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        warningHour.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        warningHour.setForeground(new java.awt.Color(204, 0, 51));
        warningHour.setText("HORA INVALIDA");

        warningDate.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        warningDate.setForeground(new java.awt.Color(204, 0, 51));
        warningDate.setText("FECHA INVALIDA");

        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 153));
        jLabel9.setText("ID Capitan:");

        lblCaptain.setText(" ");

        jLabel8.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 153));
        jLabel8.setText("Avión:");

        lblAirplane.setText(" ");

        btnUpdateFlight.setBackground(new java.awt.Color(255, 255, 255));
        btnUpdateFlight.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnUpdateFlight.setForeground(new java.awt.Color(0, 102, 153));
        btnUpdateFlight.setText("ACTUALIZAR");
        btnUpdateFlight.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 2));
        btnUpdateFlight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateFlightActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/redo.png"))); // NOI18N
        jButton2.setContentAreaFilled(false);

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/undo.png"))); // NOI18N
        jButton3.setContentAreaFilled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(lblCaptain, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel7)
                                                    .addComponent(txtAproximateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel1)
                                                    .addComponent(cbxOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(jLabel5)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(warningDate))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jSeparator3)
                                            .addComponent(jLabel2)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(18, 18, 18)
                                                .addComponent(warningHour))
                                            .addComponent(txtHour)
                                            .addComponent(cbxDestination, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(lblAirplane, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(13, 13, 13))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(btnUpdateFlight, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(warningDate))
                        .addGap(23, 23, 23)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(warningHour))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAproximateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(lblAirplane))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(btnUpdateFlight, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3)
                            .addComponent(jButton2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCaptain))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(187, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(210, 210, 210))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAproximateTimeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAproximateTimeKeyReleased
        if (!txtAproximateTime.getText().isEmpty()) {
            fillTableAirplanes();
            fillTableCaptains();
        }
    }//GEN-LAST:event_txtAproximateTimeKeyReleased

    private void txtHourKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHourKeyReleased
        LocalDate date = validateDate(txtDate.getText());
        LocalTime currentTime = LocalTime.now();

        try {
            if (!isValidTimeFormat(txtHour.getText())) {
                warningHour.setVisible(true);
                btnUpdateFlight.setEnabled(false);
            } else {
                LocalTime selectedTime = LocalTime.parse(txtHour.getText());

                if (date != null && selectedTime.isBefore(currentTime) && date.isEqual(LocalDate.now())) {
                    warningHour.setVisible(true);
                    btnUpdateFlight.setEnabled(false);
                } else {
                    warningHour.setVisible(false);
                    btnUpdateFlight.setEnabled(true);
                    txtAproximateTime.setEnabled(true);
                    if (!hasEmptyFields()) {
                        fillTableAirplanes();
                        fillTableCaptains();
                    }
                }
            }
        } catch (DateTimeParseException ex) {
        }
    }//GEN-LAST:event_txtHourKeyReleased

    private void cbxOriginItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxOriginItemStateChanged
        if (cbxOrigin.getSelectedIndex() != 0) {
            cbxDestination.setEnabled(true);
            if (!hasEmptyFields()) {
                fillTableAirplanes();
                fillTableCaptains();
            }
        }
    }//GEN-LAST:event_cbxOriginItemStateChanged

    private void cbxDestinationItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDestinationItemStateChanged
        if (cbxDestination.getSelectedIndex() != 0) {
            txtDate.setEnabled(true);
            if (!hasEmptyFields()) {
                fillTableAirplanes();
                fillTableCaptains();
            }
        }
    }//GEN-LAST:event_cbxDestinationItemStateChanged

    private void txtDateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDateKeyReleased
        formatDate();

        if (!txtDate.getText().isEmpty()) {
            LocalDate date = validateDate(txtDate.getText());

            if ((date == null || date.isBefore(LocalDate.now()))) {
                warningDate.setVisible(true);
                btnUpdateFlight.setEnabled(false);
            } else {
                warningDate.setVisible(false);
                btnUpdateFlight.setEnabled(true);
            }

            txtHour.setEnabled(true);
            if (!hasEmptyFields()) {
                fillTableAirplanes();
                fillTableCaptains();
            }
        } else {
            warningDate.setVisible(false);
            btnUpdateFlight.setEnabled(true);
        }

    }//GEN-LAST:event_txtDateKeyReleased

    private void txtDateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDateKeyTyped
        String date = txtDate.getText();
        char c = evt.getKeyChar();

        if (!Character.isDigit(c) || date.length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDateKeyTyped

    private void btnUpdateFlightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateFlightActionPerformed
        if (hasEmptyFields() || lblAirplane.getText().isEmpty() || lblCaptain.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese todos los datos");
            return;
        }

        if (cbxOrigin.getSelectedIndex() == cbxDestination.getSelectedIndex()) {
            JOptionPane.showMessageDialog(null, "Seleccione correctamente el origen y destino");
            return;
        }

        Cities origin = Cities.valueOf(cbxOrigin.getSelectedItem().toString());
        Cities destination = Cities.valueOf(cbxDestination.getSelectedItem().toString());
        LocalTime hour = LocalTime.parse(txtHour.getText());
        int apxtime = Integer.parseInt(txtAproximateTime.getText());
        LocalDate date = validateDate(txtDate.getText());
        Captain captain = controller.searchCaptain(lblCaptain.getText());
        Airplane airplane = controller.searchAirplane(lblAirplane.getText());

        try {
            Flight updatedFlight = new Flight(captain, airplane, date, hour, apxtime, origin, destination);
            boolean success = controller.updateFlight(updatedFlight, flight.getId());
            if (success) {
                JOptionPane.showMessageDialog(null, "Vuelo editado exitosamente");
                view.fillTable();
                this.disable();
//                this.fli = updatedFlight;
//                this.evt = Event.ADD;
//                controller.addToZ(fli);

            } else {
                JOptionPane.showMessageDialog(null, "No se ha modificado nada");
            }
        } catch (FlightException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnUpdateFlightActionPerformed

    private void captainsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_captainsTableMouseClicked
        int seleccionado = captainsTable.getSelectedRow();
        String captain = captainsTable.getModel().getValueAt(seleccionado, 0).toString();

        lblCaptain.setText(captain);
    }//GEN-LAST:event_captainsTableMouseClicked

    private void airplanesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_airplanesTableMouseClicked
        int seleccionado = airplanesTable.getSelectedRow();
        String airpalne = airplanesTable.getModel().getValueAt(seleccionado, 0).toString();

        lblAirplane.setText(airpalne);
    }//GEN-LAST:event_airplanesTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable airplanesTable;
    private javax.swing.JButton btnUpdateFlight;
    private javax.swing.JTable captainsTable;
    private javax.swing.JComboBox<String> cbxDestination;
    private javax.swing.JComboBox<String> cbxOrigin;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblAirplane;
    private javax.swing.JLabel lblCaptain;
    private javax.swing.JTextField txtAproximateTime;
    private javax.swing.JFormattedTextField txtDate;
    private javax.swing.JFormattedTextField txtHour;
    private javax.swing.JLabel warningDate;
    private javax.swing.JLabel warningHour;
    // End of variables declaration//GEN-END:variables
}
