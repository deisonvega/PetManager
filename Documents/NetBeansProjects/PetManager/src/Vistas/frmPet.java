/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Classes.*;
import Controller.*;
import static com.oracle.jrockit.jfr.ContentType.Address;
import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class frmPet extends javax.swing.JFrame {

    ControllerPet ControllerP;
    ControllerDoctor Controllerdoct;
    ControllerHospital Controllerhospit;

    LinkedList<clsPet> dogObjctList = new LinkedList<>();
    LinkedList<clsPet> catObjctList = new LinkedList<>();
    LinkedList<clsHospital> hospitalObjctList = new LinkedList<>();
    LinkedList<clsBusquedaDoctHospital> dochosObjctList = new LinkedList<>();
    
    
    

    public frmPet() {

        initComponents();
        this.ControllerP = new ControllerPet();
        this.Controllerdoct = new ControllerDoctor();
        this.Controllerhospit = new ControllerHospital();
        
        this.dogObjctList = ControllerP.ListPet("Perro");
        this.catObjctList = ControllerP.ListPet("Gato");
        this.dochosObjctList = Controllerdoct.ListDoc();
        this.hospitalObjctList = Controllerhospit.ListHospital();

        this.FillListDog();
        this.FillListCat();
        this.FillListDoctor();
        this.tabladog();
        this.tablacat();
        this.tablahospital();
        this.tabladoctor();
        this.tabladoctor2();
        this.tablahospital2();
        this.deshabilitarbtn();

    }

    private void RefrecarReporteestadoSalud() {
        LinkedList<clsReporteEstadoSalud> reporte = ControllerP.ListPetreporteSalud();

        DefaultPieDataset dataset = new DefaultPieDataset();
        for (clsReporteEstadoSalud record : reporte) {
            dataset.setValue(record.getEstadosalud(), record.getContadordeestados());
            JFreeChart chart = ChartFactory.createPieChart3D("Clasificacion Por Estado De Salud DOG ", dataset, true, true, true);
            ChartPanel panel = new ChartPanel(chart);
            panel.setMouseWheelEnabled(true);
            Grafico.setLayout(new java.awt.BorderLayout());
            Grafico.add(panel, BorderLayout.CENTER);
            Grafico.validate();
        }
    }
    
      private void RefrecarReporteestadoSaludcat() {
        LinkedList<clsReporteEstadoSalud> reporte = ControllerP.ListPetreporteSaludCat();

        DefaultPieDataset dataset = new DefaultPieDataset();
        for (clsReporteEstadoSalud record : reporte) {
            dataset.setValue(record.getEstadosalud(), record.getContadordeestados());
            JFreeChart chart = ChartFactory.createPieChart3D("Clasificacion Por Estado De Salud CAT ", dataset, true, true, true);
            ChartPanel panel = new ChartPanel(chart);
            panel.setMouseWheelEnabled(true);
            Grafico.setLayout(new java.awt.BorderLayout());
            Grafico.add(panel, BorderLayout.CENTER);
            Grafico.validate();
        }
    }
    
    // Reporte Raza De Perro
      private void RefrecarReporteRazaDog() {
          LinkedList<clsReporteDeRaza> reporte = ControllerP.ListPetreporteRazaDog();

        DefaultPieDataset dataset = new DefaultPieDataset();
        for (clsReporteDeRaza record : reporte) {
            dataset.setValue(record.getRaza(), record.getContarraza());

            JFreeChart chart = ChartFactory.createPieChart3D("Raza De DOG", dataset, true, true, true);
            ChartPanel panel = new ChartPanel(chart);
            panel.setMouseWheelEnabled(true);
            Grafico.setLayout(new java.awt.BorderLayout());
            Grafico.add(panel, BorderLayout.CENTER);
            Grafico.validate();
         }
        }
        
        // Reporte Raza De Gatos
        private void RefrescarReporteRazaCat(){
         LinkedList<clsReporteDeRaza> reporte =ControllerP.ListPetreporteRazaCat();
         
        DefaultPieDataset dataset = new DefaultPieDataset();
        
          for (clsReporteDeRaza record : reporte) {
            dataset.setValue(record.getRaza(), record.getContarraza());
         
         JFreeChart chart = ChartFactory.createPieChart3D("Raza De CAT",dataset, true,true,true);
         ChartPanel panel = new ChartPanel(chart);
         panel.setMouseWheelEnabled(true);
         Grafico.setLayout(new java.awt.BorderLayout());
         Grafico.add(panel, BorderLayout.CENTER);
         
         Grafico.validate();
          }
        }
        

    //=====================================================================================================================
   
    private void tablahospital() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Id", "Nombre Sala", "Numero Habitacion"});
        for (clsHospital hosp : hospitalObjctList) {
            modelo.addRow(new Object[]{hosp.getId_hospital(), hosp.getNombre(), hosp.getNumero()});
        }
        tablahospital.setModel(modelo);
        this.hospitalObjctList = Controllerhospit.ListHospital();

    }
    
    private void tablahospital2() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Id", "Nombre Sala", "Numero Habitacion"});
        for (clsHospital hosp2 : hospitalObjctList) {
            modelo.addRow(new Object[]{hosp2.getId_hospital(), hosp2.getNombre(), hosp2.getNumero()});
        }
        tablahospital2.setModel(modelo);
        this.hospitalObjctList = Controllerhospit.ListHospital();
    }

   private void tabladoctor() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Nit","Nombre", "Direccion","Telefono"});
        for (clsBusquedaDoctHospital doct : dochosObjctList) {
            modelo.addRow(new Object[]{doct.getNit(),doct.getNamed(),doct.getAddress(),doct.getPhone()});
        }
        tabladoctor.setModel(modelo);
       this.dochosObjctList = Controllerdoct.ListDoc();
    }
   
   private void tabladoctor2() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Nit","Nombre", "Direccion","Telefono"});
        for (clsBusquedaDoctHospital doct : dochosObjctList) {
            modelo.addRow(new Object[]{doct.getNit(),doct.getNamed(),doct.getAddress(),doct.getPhone()});
        }
        tabladoctor2.setModel(modelo);
       this.dochosObjctList = Controllerdoct.ListDoc();
    }
   
   

    private void tabladog() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Codigo", "Nombre", "Estado", "Color", "Año"});
        for (clsPet Dog : dogObjctList) {
            modelo.addRow(new Object[]{Dog.getCode(), Dog.getName(), Dog.getHealth_status(), Dog.getColor(), Dog.getBorn_year()});
        }
        tabladog.setModel(modelo);
       // this.dogObjctList = ControllerP.ListPet("Perro");
    }

    private void tablacat() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Codigo", "Nombre", "Estado", "Color", "Año"});
        for (clsPet Cat : catObjctList) {
            modelo.addRow(new Object[]{Cat.getCode(), Cat.getName(), Cat.getHealth_status(), Cat.getColor(), Cat.getBorn_year()});
            //this.catObjctList = ControllerP.ListPet("Gato");
        }

        tablacat.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        List = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txtColorDog = new javax.swing.JTextField();
        txtNameDog = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCodeDog = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtBornYearDog = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbBreedDog = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        cmbHealthStatusDog = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        btnCrearDog = new javax.swing.JButton();
        btnBuscarDog = new javax.swing.JButton();
        btnEditarDog = new javax.swing.JButton();
        btnEliminarDog = new javax.swing.JButton();
        chePedigree = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        txtPet_Id = new javax.swing.JTextField();
        txtDog_Id = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabladog = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmbHealthStatusCat = new javax.swing.JComboBox();
        cmbBreedCat = new javax.swing.JComboBox();
        btnCrearCat = new javax.swing.JButton();
        btnBuscarCat = new javax.swing.JButton();
        btnEditarCat = new javax.swing.JButton();
        btnEliminarCat = new javax.swing.JButton();
        txtCodeCat = new javax.swing.JTextField();
        txtBornYearCat = new javax.swing.JTextField();
        txtNameCat = new javax.swing.JTextField();
        txtColorCat = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtPetC_Id = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtCat_Id = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablacat = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PetListdog = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        PetListCat = new javax.swing.JList();
        btnExportarDog = new javax.swing.JButton();
        btnExportarCat = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        DocList = new javax.swing.JList();
        btnExportarDoctor = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        label = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        labelphone = new javax.swing.JLabel();
        txtNameDoctor = new javax.swing.JTextField();
        txtAddressDoctor = new javax.swing.JTextField();
        txtNitDoctor = new javax.swing.JTextField();
        txtPhoneDoctor = new javax.swing.JTextField();
        btnCrearDoctor = new javax.swing.JButton();
        btnBuscarDoctor = new javax.swing.JButton();
        btnEditarDoctor = new javax.swing.JButton();
        btnEliminarDoctor = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablahospital = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtHospNumHabit = new javax.swing.JTextField();
        txtHospNomSa = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel27 = new javax.swing.JLabel();
        txtIDHos = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtIdDoc = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        tabladoctor2 = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtHospital_Id = new javax.swing.JTextField();
        txtHosNumHabitacion = new javax.swing.JTextField();
        txtHosNomSala = new javax.swing.JTextField();
        btnCrearVeterinary = new javax.swing.JButton();
        btnBuscarVeterinary = new javax.swing.JButton();
        btnEditarVeterinary = new javax.swing.JButton();
        btnEliminarVeterinary = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabladoctor = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablahospital2 = new javax.swing.JTable();
        jP = new javax.swing.JPanel();
        btnGraficoDog = new javax.swing.JButton();
        btnGraficoDogRaza = new javax.swing.JButton();
        btnGraficoCatRaza = new javax.swing.JButton();
        btnGraficoCat = new javax.swing.JButton();
        Grafico = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        jTextField2.setText("jTextField2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(88, 185, 240));

        List.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        List.setOpaque(true);
        List.setPreferredSize(new java.awt.Dimension(776, 496));

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtColorDog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtColorDogActionPerformed(evt);
            }
        });

        txtNameDog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameDogActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Name");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Code");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Born Year");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Color");

        cmbBreedDog.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        cmbBreedDog.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pastor Aleman", "Pomerania", "Labrador", "Bulldog", "Husky", "Chihuahua", "Pit Bull", "Pug", "Doberman", "Bull Terrier", "Pinscher", "Dalmata" }));
        cmbBreedDog.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmbBreedDog.setInheritsPopupMenu(true);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Breed");

        cmbHealthStatusDog.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        cmbHealthStatusDog.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enfermo", "Sano", "Recuperado", "Muerto" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Health Status");

        btnCrearDog.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCrearDog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/CREAR.png"))); // NOI18N
        btnCrearDog.setText("Crear");
        btnCrearDog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearDogActionPerformed(evt);
            }
        });

        btnBuscarDog.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBuscarDog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BUSCAR.png"))); // NOI18N
        btnBuscarDog.setText("Buscar");
        btnBuscarDog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDogActionPerformed(evt);
            }
        });

        btnEditarDog.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEditarDog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/EDITAR.png"))); // NOI18N
        btnEditarDog.setText("Editar");
        btnEditarDog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarDogActionPerformed(evt);
            }
        });

        btnEliminarDog.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminarDog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ELIMINAR.png"))); // NOI18N
        btnEliminarDog.setText("Eliminar");
        btnEliminarDog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarDogActionPerformed(evt);
            }
        });

        chePedigree.setBackground(new java.awt.Color(153, 153, 153));
        chePedigree.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        chePedigree.setText("Pedigree");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Pet_Id");

        txtPet_Id.setEditable(false);
        txtPet_Id.setEnabled(false);
        txtPet_Id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPet_IdActionPerformed(evt);
            }
        });

        txtDog_Id.setEditable(false);
        txtDog_Id.setEnabled(false);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Dog_Id");

        tabladog.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tabladog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        tabladog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabladogMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabladog);

        jSeparator1.setBackground(new java.awt.Color(88, 185, 240));
        jSeparator1.setForeground(new java.awt.Color(88, 185, 240));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(88, 185, 240)));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(txtCodeDog, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel15))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPet_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbHealthStatusDog, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(49, 49, 49)
                                .addComponent(txtBornYearDog, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(txtDog_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(chePedigree)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel26))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbBreedDog, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtColorDog, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(31, 31, 31)
                                .addComponent(txtNameDog, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(117, 117, 117))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)
                            .addComponent(jSeparator1))))
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(btnCrearDog)
                .addGap(18, 18, 18)
                .addComponent(btnBuscarDog)
                .addGap(18, 18, 18)
                .addComponent(btnEditarDog)
                .addGap(18, 18, 18)
                .addComponent(btnEliminarDog)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtCodeDog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtBornYearDog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cmbHealthStatusDog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtPet_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNameDog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtColorDog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(cmbBreedDog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(91, 91, 91)))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtDog_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chePedigree))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearDog, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarDog, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditarDog, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarDog, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        List.addTab("Dogs", jPanel2);

        jPanel7.setBackground(new java.awt.Color(102, 102, 102));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Code");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Born Year");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Name");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Color");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Health Status");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Breed");

        cmbHealthStatusCat.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cmbHealthStatusCat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enfermo", "Sano", "Muerto", "Recuperado" }));

        cmbBreedCat.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cmbBreedCat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Gato Persa", "Bengala", "Maine Coon", "Gato Esfinge", "Siames", "Ragdoll", "Munchkin", "Fold Escoces", "Siberiano", "Angora Turco" }));

        btnCrearCat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCrearCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/CREAR.png"))); // NOI18N
        btnCrearCat.setText("Crear");
        btnCrearCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearCatActionPerformed(evt);
            }
        });

        btnBuscarCat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBuscarCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BUSCAR.png"))); // NOI18N
        btnBuscarCat.setText("Buscar");
        btnBuscarCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCatActionPerformed(evt);
            }
        });

        btnEditarCat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEditarCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/EDITAR.png"))); // NOI18N
        btnEditarCat.setText("Editar");
        btnEditarCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarCatActionPerformed(evt);
            }
        });

        btnEliminarCat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminarCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ELIMINAR.png"))); // NOI18N
        btnEliminarCat.setText("Eliminar");
        btnEliminarCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCatActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Pet_Id");

        txtPetC_Id.setEditable(false);
        txtPetC_Id.setEnabled(false);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Cat_Id");

        txtCat_Id.setEditable(false);
        txtCat_Id.setEnabled(false);
        txtCat_Id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCat_IdActionPerformed(evt);
            }
        });

        tablacat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tablacat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jScrollPane5.setViewportView(tablacat);

        jSeparator2.setBackground(new java.awt.Color(88, 185, 240));
        jSeparator2.setForeground(new java.awt.Color(88, 185, 240));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(88, 185, 240)));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(64, 64, 64)
                                .addComponent(txtPetC_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(cmbHealthStatusCat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8))
                                .addGap(43, 43, 43)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCodeCat, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBornYearCat, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel13)
                            .addComponent(jLabel23))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbBreedCat, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNameCat, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtColorCat, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCat_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addComponent(jLabel21)
                        .addGap(0, 182, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5)
                            .addComponent(jSeparator2))))
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(btnCrearCat)
                .addGap(18, 18, 18)
                .addComponent(btnBuscarCat)
                .addGap(18, 18, 18)
                .addComponent(btnEditarCat)
                .addGap(18, 18, 18)
                .addComponent(btnEliminarCat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel21))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtNameCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtColorCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(cmbBreedCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtCodeCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtBornYearCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(cmbHealthStatusCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPetC_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23)
                        .addComponent(txtCat_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarCat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCrearCat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditarCat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarCat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        List.addTab("Cats", jPanel3);

        jPanel8.setBackground(new java.awt.Color(102, 102, 102));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        PetListdog.setBackground(new java.awt.Color(204, 255, 204));
        PetListdog.setBorder(new javax.swing.border.MatteBorder(null));
        PetListdog.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PetListdog.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(PetListdog);

        PetListCat.setBackground(new java.awt.Color(153, 255, 153));
        PetListCat.setBorder(new javax.swing.border.MatteBorder(null));
        PetListCat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PetListCat.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(PetListCat);

        btnExportarDog.setBackground(new java.awt.Color(204, 255, 255));
        btnExportarDog.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExportarDog.setText("Dog");
        btnExportarDog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarDogActionPerformed(evt);
            }
        });

        btnExportarCat.setBackground(new java.awt.Color(204, 255, 255));
        btnExportarCat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExportarCat.setText("Cat");
        btnExportarCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarCatActionPerformed(evt);
            }
        });

        DocList.setBackground(new java.awt.Color(153, 255, 102));
        DocList.setBorder(new javax.swing.border.MatteBorder(null));
        DocList.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        DocList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane8.setViewportView(DocList);

        btnExportarDoctor.setBackground(new java.awt.Color(204, 255, 255));
        btnExportarDoctor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExportarDoctor.setText("Doctor");
        btnExportarDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarDoctorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane8)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExportarCat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportarDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportarDog, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(btnExportarDog, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(btnExportarCat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExportarDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(48, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        List.addTab("Lists", jPanel4);

        jPanel11.setBackground(new java.awt.Color(102, 102, 102));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Name");

        label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label.setText("Nit");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Address");

        labelphone.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelphone.setText("Phone");

        btnCrearDoctor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCrearDoctor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/CREAR.png"))); // NOI18N
        btnCrearDoctor.setText("Crear");
        btnCrearDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearDoctorActionPerformed(evt);
            }
        });

        btnBuscarDoctor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBuscarDoctor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BUSCAR.png"))); // NOI18N
        btnBuscarDoctor.setText("Buscar");
        btnBuscarDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDoctorActionPerformed(evt);
            }
        });

        btnEditarDoctor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEditarDoctor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/EDITAR.png"))); // NOI18N
        btnEditarDoctor.setText("Editar");
        btnEditarDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarDoctorActionPerformed(evt);
            }
        });

        btnEliminarDoctor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminarDoctor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ELIMINAR.png"))); // NOI18N
        btnEliminarDoctor.setText("Eliminar");
        btnEliminarDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarDoctorActionPerformed(evt);
            }
        });

        tablahospital.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tablahospital.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Nit", "Address", "Phone"
            }
        ));
        tablahospital.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablahospitalMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tablahospital);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Num Habitacion");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("Nombre Sala");

        txtHospNumHabit.setEnabled(false);

        txtHospNomSa.setEnabled(false);

        jSeparator3.setBackground(new java.awt.Color(88, 185, 240));
        jSeparator3.setForeground(new java.awt.Color(88, 185, 240));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(88, 185, 240)));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("Id_Hosp");

        txtIDHos.setEnabled(false);
        txtIDHos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDHosActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("Id_Doc");

        txtIdDoc.setEnabled(false);

        tabladoctor2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tabladoctor2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Nit", "Address", "Phone"
            }
        ));
        tabladoctor2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabladoctor2MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tabladoctor2);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator3)
                .addContainerGap())
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label)
                    .addComponent(jLabel25)
                    .addComponent(jLabel14))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNameDoctor, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(txtNitDoctor)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(txtHospNomSa, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(labelphone))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(txtPhoneDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel27))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(txtAddressDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIDHos, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(txtIdDoc)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtHospNumHabit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(132, 132, 132))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCrearDoctor)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarDoctor)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditarDoctor)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarDoctor)
                        .addGap(157, 157, 157))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNitDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(txtAddressDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel28)
                        .addComponent(txtIdDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelphone)
                            .addComponent(txtPhoneDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27)
                            .addComponent(txtIDHos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNameDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))))
                .addGap(26, 26, 26)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(txtHospNumHabit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(txtHospNomSa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditarDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        List.addTab("Doctor", jPanel9);

        jPanel12.setBackground(new java.awt.Color(102, 102, 102));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("ID_Hospital");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Nombre Sala");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Num Habitacion");

        txtHospital_Id.setEditable(false);
        txtHospital_Id.setEnabled(false);

        btnCrearVeterinary.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCrearVeterinary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/CREAR.png"))); // NOI18N
        btnCrearVeterinary.setText("Crear");
        btnCrearVeterinary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearVeterinaryActionPerformed(evt);
            }
        });

        btnBuscarVeterinary.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBuscarVeterinary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BUSCAR.png"))); // NOI18N
        btnBuscarVeterinary.setText("Buscar");
        btnBuscarVeterinary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarVeterinaryActionPerformed(evt);
            }
        });

        btnEditarVeterinary.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEditarVeterinary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/EDITAR.png"))); // NOI18N
        btnEditarVeterinary.setText("Editar");
        btnEditarVeterinary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarVeterinaryActionPerformed(evt);
            }
        });

        btnEliminarVeterinary.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminarVeterinary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ELIMINAR.png"))); // NOI18N
        btnEliminarVeterinary.setText("Eliminar");
        btnEliminarVeterinary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarVeterinaryActionPerformed(evt);
            }
        });

        tabladoctor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tabladoctor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tabladoctor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Address", "Nit", "Phone"
            }
        ));
        jScrollPane6.setViewportView(tabladoctor);

        tablahospital2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tablahospital2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tablahospital2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tablahospital2);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addComponent(btnCrearVeterinary)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarVeterinary)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditarVeterinary)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarVeterinary))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel18))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHospital_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHosNomSala, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHosNumHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 156, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtHosNomSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtHosNumHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtHospital_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearVeterinary, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarVeterinary, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditarVeterinary, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarVeterinary, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        List.addTab("Hospital", jPanel10);

        jP.setBackground(new java.awt.Color(102, 102, 102));

        btnGraficoDog.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGraficoDog.setText("Estado Salud Dog");
        btnGraficoDog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficoDogActionPerformed(evt);
            }
        });

        btnGraficoDogRaza.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGraficoDogRaza.setText("Raza Dog");
        btnGraficoDogRaza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficoDogRazaActionPerformed(evt);
            }
        });

        btnGraficoCatRaza.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGraficoCatRaza.setText("Raza Cat");
        btnGraficoCatRaza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficoCatRazaActionPerformed(evt);
            }
        });

        btnGraficoCat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGraficoCat.setText("Estado De Salud Cat");
        btnGraficoCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficoCatActionPerformed(evt);
            }
        });

        Grafico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Dog_Cat - copia.jpg"))); // NOI18N

        javax.swing.GroupLayout jPLayout = new javax.swing.GroupLayout(jP);
        jP.setLayout(jPLayout);
        jPLayout.setHorizontalGroup(
            jPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Grafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(btnGraficoDog, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGraficoCat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGraficoDogRaza)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGraficoCatRaza, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPLayout.setVerticalGroup(
            jPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Grafico, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGraficoDog, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGraficoCat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGraficoDogRaza, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGraficoCatRaza, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        List.addTab("Grafico", jP);

        jPanel5.setBackground(new java.awt.Color(0, 102, 204));

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Segoe UI Semilight", 3, 48)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/VET CLINIC.png"))); // NOI18N
        jLabel1.setText("     Veterinary Clinic");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(List, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(List, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameDogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameDogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameDogActionPerformed

    private void txtColorDogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtColorDogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtColorDogActionPerformed
    /// BOTON CREAR DOG
    private void btnCrearDogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearDogActionPerformed
        try {
            String code = txtCodeDog.getText();
            String name = txtNameDog.getText();
            String color = txtColorDog.getText();

            int born_year = Integer.parseInt(txtBornYearDog.getText());
            String health_status = cmbHealthStatusDog.getSelectedItem().toString();
            String breed = cmbBreedDog.getSelectedItem().toString();
            boolean Pedigree = chePedigree.isSelected();
            boolean Crear;
            // HACEMOS LAS VALIDACIONES 
            if (code.equals("") || name.equals("") || color.equals("")) {
                JOptionPane.showMessageDialog(this, "Hay Elementos En Blanco");
            } else if (!ExistDog(code)) {

                clsDog dog = new clsDog(0, Pedigree, breed, 0, name, code, born_year, color, health_status);
                Crear = ControllerP.CreatePet((clsDog) dog);
                if (Crear) {
                    JOptionPane.showMessageDialog(this, "Datos almacenados");
                    this.clearDogFields();
                    //Actualizar Lista
                    this.dogObjctList = ControllerP.ListPet("Perro");
                    this.FillListDog();
                    this.tabladog();
                }
            } else {

                JOptionPane.showMessageDialog(this, "Mascota existente");
                this.clearDogFields();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Año inválido");


    }//GEN-LAST:event_btnCrearDogActionPerformed
    }

    /// BOTON CREAR CAT 
    private void btnCrearCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearCatActionPerformed

        try {
            String code = txtCodeCat.getText();
            String name = txtNameCat.getText();
            String color = txtColorCat.getText();

            int born_year = Integer.parseInt(txtBornYearCat.getText());
            String health_status = cmbHealthStatusCat.getSelectedItem().toString();
            String breed = cmbBreedCat.getSelectedItem().toString();
            boolean Crear;
            // HACEMOS LAS VALIDACIONES 
            if (code.equals("") || name.equals("") || color.equals("")) {
                JOptionPane.showMessageDialog(this, "Hay Elementos En Blanco");
            } else if (!ExistCat(code)) {

                clsCat cat = new clsCat(WIDTH, breed, WIDTH, name, code, born_year, color, health_status);
                Crear = ControllerP.CreatePet((clsCat) cat);
                if (Crear) {
                    JOptionPane.showMessageDialog(this, "Datos almacenados");
                    this.clearCatFields();
                    //Actualizar Lista
                    this.catObjctList = ControllerP.ListPet("Gato");
                    this.FillListCat();
                    this.tablacat();
                }
            } else {

                JOptionPane.showMessageDialog(this, "Mascota existente");
                this.clearDogFields();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Año inválido");
        }
    }//GEN-LAST:event_btnCrearCatActionPerformed
    /// BOTON BUSCAR DOG 
    private void btnBuscarDogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDogActionPerformed

        String code = txtCodeDog.getText();
        clsDog dog = (clsDog) ControllerP.BusquedaPet(code, "Perro");
        if (dog == null) {
            JOptionPane.showMessageDialog(this, "CODIGO INVALIDO PERRO NO EXISTE ");
        } else {
            txtNameDog.setText(dog.getName());
            txtBornYearDog.setText(dog.getBorn_year() + "");
            txtColorDog.setText(dog.getColor());
            cmbHealthStatusDog.setSelectedItem(dog.getHealth_status());
            cmbBreedDog.setSelectedItem(dog.getBreed());
            chePedigree.setSelected(dog.isPedigree());
            txtPet_Id.setText(dog.getPetId() + "");
            txtDog_Id.setText(dog.getDogId() + "");
            //this.clearDogFields();
            this.dogObjctList = ControllerP.ListPet("Perro");
            this.FillListDog();
            this.tabladog();
            this.habilitarbtnbuscarDog();

        }
    }//GEN-LAST:event_btnBuscarDogActionPerformed
    /// BOTON BUSCAR CAT
    private void btnBuscarCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCatActionPerformed

        String code = txtCodeCat.getText();
        clsCat cat = (clsCat) ControllerP.BusquedaPet(code, "Gato");
        if (cat == null) {
            JOptionPane.showMessageDialog(this, "CODIGO INVALIDO GATO NO EXISTE ");
        } else {
            txtNameCat.setText(cat.getName());
            txtBornYearCat.setText(cat.getBorn_year() + "");
            txtColorCat.setText(cat.getColor());
            cmbHealthStatusCat.setSelectedItem(cat.getHealth_status());
            cmbBreedCat.setSelectedItem(cat.getBreed());
            txtPetC_Id.setText(cat.getPetId() + "");
            txtCat_Id.setText(cat.getCatId() + "");
            //this.clearCatFields();
            this.catObjctList = ControllerP.ListPet("Gato");
            this.FillListCat();
            this.tablacat();
            this.habilitarbtnbuscarCat();

        }
    }//GEN-LAST:event_btnBuscarCatActionPerformed
    /// BOTON EDITAR DOG 
    private void btnEditarDogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarDogActionPerformed

        String code = txtCodeDog.getText();
        if (!code.equals("")) {
            String name = txtNameDog.getText();
            String color = txtColorDog.getText();

            int bornYear = Integer.parseInt(txtBornYearDog.getText());
            String Breed = cmbBreedDog.getSelectedItem().toString();
            String heathStatus = cmbHealthStatusDog.getSelectedItem().toString();
            boolean pedigree = chePedigree.isSelected();
            try {
                int petId = Integer.parseInt(txtPet_Id.getText());
                int dogId = Integer.parseInt(txtDog_Id.getText());
                clsDog dog = new clsDog(dogId, pedigree, Breed, petId, name, code, bornYear, color, heathStatus);
                boolean edito = ControllerP.EditPet(dog);
                if (edito) {
                    JOptionPane.showMessageDialog(this, "Codigo editado");
                    this.clearDogFields();
                    this.dogObjctList = ControllerP.ListPet("Perro");
                    this.FillListDog();
                    this.tabladog();
                    this.btnCrearDog.setEnabled(true);
                    this.deshabilitarbtn();

                } else {
                    JOptionPane.showMessageDialog(this, "Error actualizando el registro");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Debe buscar primero el registro a editar");

            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe buscar primero el registro a editar");

        }

    }//GEN-LAST:event_btnEditarDogActionPerformed
    /// BOTON ELIMINAR DOG
    private void btnEliminarDogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarDogActionPerformed

        String code = txtCodeDog.getText();
        if (!code.equals("")) {
            String name = txtNameDog.getText();
            String color = txtColorDog.getText();

            int bornYear = Integer.parseInt(txtBornYearDog.getText());
            String Breed = cmbBreedDog.getSelectedItem().toString();
            String heathStatus = cmbHealthStatusDog.getSelectedItem().toString();
            boolean pedigree = chePedigree.isSelected();
            try {
                int petId = Integer.parseInt(txtPet_Id.getText());
                int dogId = Integer.parseInt(txtDog_Id.getText());
                clsDog dog = new clsDog(dogId, pedigree, Breed, petId, name, code, bornYear, color, heathStatus);
                boolean elimino = ControllerP.DeletePet(dog);
                if (elimino) {
                    JOptionPane.showMessageDialog(this, "Codigo Eliminado");
                    this.clearDogFields();
                    this.dogObjctList = ControllerP.ListPet("Perro");
                    this.FillListDog();
                    this.tabladog();
                    this.btnCrearDog.setEnabled(true);
                    this.deshabilitarbtn();

                } else {
                    JOptionPane.showMessageDialog(this, "Error actualizando el registro");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Debe buscar primero el registro a eliminar");

            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe buscar primero el registro a eliminar");

        }

    }//GEN-LAST:event_btnEliminarDogActionPerformed
    /// BOTON EDITAR CAT
    private void btnEditarCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarCatActionPerformed

        String code = txtCodeCat.getText();
        if (!code.equals("")) {
            String name = txtNameCat.getText();
            String color = txtColorCat.getText();

            int bornYear = Integer.parseInt(txtBornYearCat.getText());
            String Breed = cmbBreedCat.getSelectedItem().toString();
            String heathStatus = cmbHealthStatusCat.getSelectedItem().toString();
            
            try {
                System.out.println("entro a editar gato");
                int petId = Integer.parseInt(txtPetC_Id.getText());
                int catId = Integer.parseInt(txtCat_Id.getText());
                clsCat cat = new clsCat(catId, Breed, petId, name, code, bornYear, color, heathStatus);
                boolean edito = ControllerP.EditPet(cat);
                if (edito) {
                    JOptionPane.showMessageDialog(this, "Codigo editado");
                    this.clearCatFields();
                    this.catObjctList = ControllerP.ListPet("Gato");
                    this.FillListCat();
                    this.tablacat();
                    this.btnCrearCat.setEnabled(true);
                    this.deshabilitarbtn();

                } else {
                    JOptionPane.showMessageDialog(this, "Error actualizando el registro");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Debe buscar primero el registro a editar");

            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe buscar primero el registro a editar");
        }

    }//GEN-LAST:event_btnEditarCatActionPerformed
    /// BOTON ELIMINAR CAT
    private void btnEliminarCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCatActionPerformed

        String code = txtCodeCat.getText();
        if (!code.equals("")) {
            String name = txtNameCat.getText();
            String color = txtColorCat.getText();

            int bornYear = Integer.parseInt(txtBornYearCat.getText());
            String Breed = cmbBreedCat.getSelectedItem().toString();
            String heathStatus = cmbHealthStatusCat.getSelectedItem().toString();
            try {
                int petId = Integer.parseInt(txtPetC_Id.getText());
                int catId = Integer.parseInt(txtCat_Id.getText());
                clsCat cat = new clsCat(petId, Breed, petId, name, code, bornYear, color, heathStatus);
                boolean elimino = ControllerP.DeletePet(cat);
                if (elimino) {
                    JOptionPane.showMessageDialog(this, "Codigo Eliminado");
                    this.clearCatFields();
                    this.catObjctList = ControllerP.ListPet("Gato");
                    this.FillListCat();
                    this.tablacat();
                    this.btnCrearCat.setEnabled(true);
                    this.deshabilitarbtn();

                } else {
                    JOptionPane.showMessageDialog(this, "Error actualizando el registro");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Debe buscar primero el registro a eliminar");

            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe buscar primero el registro a eliminar");

        }

    }//GEN-LAST:event_btnEliminarCatActionPerformed
    /// BOTON CREAR  DOCTOR
    private void btnCrearDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearDoctorActionPerformed

        try {
            String name = txtNameDoctor.getText();
            String address = txtAddressDoctor.getText();
            String nit = txtNitDoctor.getText();
            String phone = txtPhoneDoctor.getText();

            boolean Crear;
            // HACEMOS LAS VALIDACIONES 
            if (name.equals("") || address.equals("") || phone.equals("") || nit.equals("")) {
                JOptionPane.showMessageDialog(this, "Hay Elementos En Blanco");

            } else {
                int filaseleccionada = tablahospital.getSelectedRow();
                int id_hospital = Integer.parseInt(String.valueOf(tablahospital.getValueAt(filaseleccionada, 0)));

                clsDoctor doct = new clsDoctor(name, nit, address, phone, id_hospital);
                Crear = Controllerdoct.CreateDoctor(doct);

                if (Crear) {
                    JOptionPane.showMessageDialog(this, "Datos almacenados");
                    this.clearDoctorFields();
                    //Actualizar Lista
                    this.dochosObjctList = Controllerdoct.ListDoc();
                    this.FillListDoctor();
                    this.tabladoctor();
                    this.tabladoctor2();

                } else {
                    JOptionPane.showMessageDialog(this, "Datos no almacenados");
                }
                this.clearDogFields();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Valores Invalidos");

        }
    }//GEN-LAST:event_btnCrearDoctorActionPerformed
    ///BOTON CREAR VETERINARIA             
    private void btnCrearVeterinaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearVeterinaryActionPerformed

           try {
            String name = txtHosNomSala.getText();
            String numero = txtHosNumHabitacion.getText();

            boolean Crear;
            // HACEMOS LAS VALIDACIONES 
            if (name.equals("") || numero.equals("")) {
                JOptionPane.showMessageDialog(this, "Hay Elementos En Blanco");
            } else {
                clsHospital hospit = new clsHospital(NORMAL, name, numero);
                Crear = Controllerhospit.CreateHospital((clsHospital) hospit);
                if (Crear) {
                    JOptionPane.showMessageDialog(this, "Datos almacenados");

                    this.hospitalObjctList = Controllerhospit.ListHospital();
                    this.tablahospital();
                    this.tablahospital2();
                    this.clearhospitalfields();

                } else {
                    JOptionPane.showMessageDialog(this, "Sala Existente");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "numero de habitacion invalido");
        }

    }//GEN-LAST:event_btnCrearVeterinaryActionPerformed
    /// BOTON BUSCAR DOCTOR 
    private void btnBuscarDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDoctorActionPerformed
        
        String nit = txtNitDoctor.getText();
       clsBusquedaDoctHospital dochos = (clsBusquedaDoctHospital) Controllerdoct.BusquedaDochos(nit);
        if (dochos == null) {
            JOptionPane.showMessageDialog(this, "CODIGO INVALIDO DOCTOR NO EXISTE ");
            
        } else {
            System.out.println("entre boton");
            txtNameDoctor.setText(dochos.getNamed());
            txtAddressDoctor.setText(dochos.getAddress());
            txtPhoneDoctor.setText(dochos.getPhone());
            txtHospNumHabit.setText(dochos.getNumero()); 
            txtHospNomSa.setText(dochos.getSalah());
            txtIDHos.setText(dochos.getId_hos()+ "");
            txtIdDoc.setText(dochos.getId()+ "");

            this.dochosObjctList = Controllerdoct.ListDoc();
            this.FillListDoctor();
            this.tabladoctor();
            this.tabladoctor2();
            this.habilitarbtnbuscardoctor();

        }  
        
    }//GEN-LAST:event_btnBuscarDoctorActionPerformed
    /// BOTON EDITAR DOCTOR 
    private void btnEditarDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarDoctorActionPerformed

        // int fila = tablahospital.getSelectedRow();
       // int id_hospital = Integer.parseInt(tablahospital.getModel().getValueAt(fila, 0).toString());
        
        String nit = txtNitDoctor.getText();
        if (!nit.equals("")) {
            String name = txtNameDoctor.getText();
            String address = txtAddressDoctor.getText();
            String phone = txtPhoneDoctor.getText();
            try {
                int id_hospital2 = Integer.parseInt(txtIDHos.getText());
                
                String numero = txtHospNumHabit.getText();
                String sala = txtHospNomSa.getText();
                clsDoctor doct = new clsDoctor(name, nit, address, phone, id_hospital2);
                boolean edito = Controllerdoct.EditDoctor(doct);
                if (edito) {
                    JOptionPane.showMessageDialog(this, "Codigo editado");
                    this.clearDoctorFields();
                    this.dochosObjctList = Controllerdoct.ListDoc();
                    this.FillListDoctor();
                    this.tabladoctor();
                    this.tabladoctor2();
                    this.btnCrearDoctor.setEnabled(true);
                    this.deshabilitarbtn();

                } else {
                    JOptionPane.showMessageDialog(this, "Error actualizando el registro");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Debe buscar primero el registro a editar");

            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe buscar primero el registro a editar");

        }
        
        
        
    }//GEN-LAST:event_btnEditarDoctorActionPerformed
    ///  BOTON ELIMINAR DOCTOR
    private void btnEliminarDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarDoctorActionPerformed
        
        String nit = txtNitDoctor.getText();
        if (!nit.equals("")) {
            String name = txtNameDoctor.getText();
            String address = txtAddressDoctor.getText();
            String phone = txtPhoneDoctor.getText();
            try {
                int id_hospital2 = Integer.parseInt(txtIDHos.getText());
                int id_doctor = Integer.parseInt(txtIdDoc.getText());
                String numero = txtHospNumHabit.getText();
                String sala = txtHospNomSa.getText();
                clsDoctor doct = new clsDoctor(id_doctor, name, nit, address, phone, id_hospital2);
                boolean elimino = Controllerdoct.DeleteDoctor(doct);
                if (elimino) {
                    JOptionPane.showMessageDialog(this, "Codigo Eliminado");
                    this.clearDoctorFields();
                    this.dochosObjctList = Controllerdoct.ListDoc();
                    this.FillListDoctor();
                    this.tabladoctor();
                    this.tabladoctor2();
                    this.btnCrearDoctor.setEnabled(true);
                    this.deshabilitarbtn();

                } else {
                    JOptionPane.showMessageDialog(this, "Error actualizando el registro");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Debe buscar primero el registro a eliminar");

            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe buscar primero el registro a eliminar");

        } 
        
        
    }//GEN-LAST:event_btnEliminarDoctorActionPerformed
    ///  BOTON BUSCAR VETERINARIA
    private void btnBuscarVeterinaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVeterinaryActionPerformed

        
        String num = txtHosNumHabitacion.getText();
        clsHospital hosp = (clsHospital) Controllerhospit.BuscarHospitaL(num);
        if (hosp == null) {
            JOptionPane.showMessageDialog(this, "CODIGO ESTA SALA NO EXISTE ");

        } else {
            txtHosNomSala.setText(hosp.getNombre());
            txtHospital_Id.setText(hosp.getId_hospital()+ "");
            
            this.dochosObjctList = Controllerdoct.ListDoc();
            this.tablahospital();
            this.tablahospital2();
            this.habilitarbtnbuscarhospital();

        }
        
    }//GEN-LAST:event_btnBuscarVeterinaryActionPerformed
    ///  BOTON EDITAR VETERINARIA
    private void btnEditarVeterinaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarVeterinaryActionPerformed

            String numero = txtHosNumHabitacion.getText();
        if (!numero.equals("")) {
            String name = txtHosNomSala.getText();
            try {
                int id_hospital = Integer.parseInt(txtHospital_Id.getText());
                
                clsHospital hospit = new clsHospital(id_hospital, name, numero);
                Boolean edito = Controllerhospit.EditHospital(hospit);
                if (edito) {
                    JOptionPane.showMessageDialog(this, "Codigo editado");
                    this.clearhospitalfields();
                    this.hospitalObjctList = Controllerhospit.ListHospital();
                    this.tablahospital();
                    this.tablahospital2();
                    btnCrearVeterinary.setEnabled(true);
                    this.deshabilitarbtn();
                    

                } else {
                    JOptionPane.showMessageDialog(this, "Error actualizando el registro");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Debe buscar primero el registro a editar");

            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe buscar primero el registro a editar");

        } 
    }//GEN-LAST:event_btnEditarVeterinaryActionPerformed

    private void btnEliminarVeterinaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarVeterinaryActionPerformed

         String num = txtHosNumHabitacion.getText();
        if (!num.equals("")) {
            try {
                int id_hospital2 = Integer.parseInt(txtHospital_Id.getText());
                boolean elimino = Controllerhospit.DeleteHospital(id_hospital2);
                if (elimino) {
                    JOptionPane.showMessageDialog(this, "Codigo Eliminado");
                    this.clearDoctorFields();
                    this.hospitalObjctList = Controllerhospit.ListHospital();
                    this.FillListDoctor();
                    this.clearhospitalfields();
                    this.tablahospital();
                    this.tablahospital2();
                    btnCrearVeterinary.setEnabled(true);
                    this.deshabilitarbtn();
                } else {
                    JOptionPane.showMessageDialog(this, "Error actualizando el registro");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al conectarse en la base de datos");

            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe buscar primero el registro a eliminar");

        }
    }//GEN-LAST:event_btnEliminarVeterinaryActionPerformed

    private void txtPet_IdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPet_IdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPet_IdActionPerformed
// ACCION DE  CLICK DE DATOS EN EL FORMULARIO DE LA TABLA HOSPITAL EN EL DOCTOR
    private void tablahospitalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablahospitalMouseClicked

        int seleccion = tablahospital.rowAtPoint(evt.getPoint());
        txtHospNomSa.setText(String.valueOf(tablahospital.getValueAt(seleccion, 1)));
        txtHospNumHabit.setText(String.valueOf(tablahospital.getValueAt(seleccion, 2)));
        txtIDHos.setText(String.valueOf(tablahospital.getValueAt(seleccion, 0)));

    }//GEN-LAST:event_tablahospitalMouseClicked
// ACCION DE CLICK DE DATOS EN EL FORMULARIO DE DOG
    private void tabladogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabladogMouseClicked

    }//GEN-LAST:event_tabladogMouseClicked
    // CREACION DEL BOTON EXPORTAR DE DOG
    private void btnExportarDogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarDogActionPerformed

        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet();
        book.setSheetName(0, "Perro");

        String[] headers = new String[]{
            "Codigo",
            "Nombre",
            "Color",
            "Estado de salud",
            "Pedigree"
        };
        CellStyle headerCellStyle = book.createCellStyle();
        HSSFFont fuente = book.createFont();
        fuente.setBold(true);
        headerCellStyle.setFont(fuente);

        HSSFRow headersRow = sheet.createRow(0);

        for (int i = 0; i < headers.length; i++) {
            String header = headers[i];
            HSSFCell celda = headersRow.createCell(i);
            celda.setCellStyle(headerCellStyle);
            celda.setCellValue(header);
        }
        for (int i = 0; i < dogObjctList.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            String code = dogObjctList.get(i).getCode();
            String nombre = dogObjctList.get(i).getName();
            String color = dogObjctList.get(i).getColor();
            String estadosalud = dogObjctList.get(i).getHealth_status();
            boolean pedigree = ((clsDog) dogObjctList.get(i)).isPedigree();
            String PedigreeString = pedigree ? "SI" : "NO";
            row.createCell(0).setCellValue(code);
            row.createCell(1).setCellValue(nombre);
            row.createCell(2).setCellValue(color);
            row.createCell(3).setCellValue(estadosalud);
            row.createCell(4).setCellValue(PedigreeString);
        }

        try {
            FileOutputStream archivo = new FileOutputStream("Reportedog.xls");
            book.write(archivo);
            archivo.close();
            JOptionPane.showMessageDialog(this, "Exportacion Realizada");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(frmPet.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(frmPet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnExportarDogActionPerformed
    // CREACION DEL BOTON EXPORTAR DE CAT
    private void btnGraficoDogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficoDogActionPerformed

        this.RefrecarReporteestadoSalud();
    }//GEN-LAST:event_btnGraficoDogActionPerformed

    private void btnExportarCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarCatActionPerformed

        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet();
        book.setSheetName(0, "Gato");

        String[] headers = new String[]{
            "Codigo",
            "Nombre",
            "Color",
            "Estado de salud",};
        CellStyle headerCellStyle = book.createCellStyle();
        HSSFFont fuente = book.createFont();
        fuente.setBold(true);
        headerCellStyle.setFont(fuente);

        HSSFRow headersRow = sheet.createRow(0);

        for (int i = 0; i < headers.length; i++) {
            String header = headers[i];
            HSSFCell celda = headersRow.createCell(i);
            celda.setCellStyle(headerCellStyle);
            celda.setCellValue(header);
        }
        for (int i = 0; i < catObjctList.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            String code = catObjctList.get(i).getCode();
            String nombre = catObjctList.get(i).getName();
            String color = catObjctList.get(i).getColor();
            String estadosalud = catObjctList.get(i).getHealth_status();

            row.createCell(0).setCellValue(code);
            row.createCell(1).setCellValue(nombre);
            row.createCell(2).setCellValue(color);
            row.createCell(3).setCellValue(estadosalud);
        }

        try {
            FileOutputStream archivo = new FileOutputStream("Reportecat.xls");
            book.write(archivo);
            archivo.close();
            JOptionPane.showMessageDialog(this, "Exportacion Realizada");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(frmPet.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(frmPet.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnExportarCatActionPerformed

    private void txtCat_IdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCat_IdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCat_IdActionPerformed

    private void btnGraficoCatRazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficoCatRazaActionPerformed
        this.RefrescarReporteRazaCat();
    }//GEN-LAST:event_btnGraficoCatRazaActionPerformed

    private void btnGraficoDogRazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficoDogRazaActionPerformed
      this.RefrecarReporteRazaDog();
    }//GEN-LAST:event_btnGraficoDogRazaActionPerformed

    private void btnGraficoCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficoCatActionPerformed
       this.RefrecarReporteestadoSaludcat();
    }//GEN-LAST:event_btnGraficoCatActionPerformed

    private void txtIDHosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDHosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDHosActionPerformed

    private void btnExportarDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarDoctorActionPerformed
       
       HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet();
        book.setSheetName(0, "doct");

        String[] headers = new String[]{
            "ID",
            "Nombre",
            "Nit",
            "Address",
            "Phone",
            "ID_Hospital"
        };
        CellStyle headerCellStyle = book.createCellStyle();
        HSSFFont fuente = book.createFont();
        fuente.setBold(true);
        headerCellStyle.setFont(fuente);

        HSSFRow headersRow = sheet.createRow(0);

        for (int i = 0; i < headers.length; i++) {
            String header = headers[i];
            HSSFCell celda = headersRow.createCell(i);
            celda.setCellStyle(headerCellStyle);
            celda.setCellValue(header);
        }
        for (int i = 0; i < dochosObjctList.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            int id = dochosObjctList.get(i).getId();
            String nombre = dochosObjctList.get(i).getNamed();
            String nit = dochosObjctList.get(i).getNit();
            String address = dochosObjctList.get(i).getAddress();
            String phone = dochosObjctList.get(i).getPhone();
            String id_hospital = (dochosObjctList.get(i).getId_hos())+"";
            row.createCell(0).setCellValue(id);
            row.createCell(1).setCellValue(nombre);
            row.createCell(2).setCellValue(nit);
            row.createCell(3).setCellValue(address);
            row.createCell(4).setCellValue(phone);
            row.createCell(5).setCellValue(id_hospital);
        }

        try {
            FileOutputStream archivo = new FileOutputStream("Reportedoctor.xls");
            book.write(archivo);
            archivo.close();
            JOptionPane.showMessageDialog(this, "Exportacion Realizada");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(frmPet.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(frmPet.class.getName()).log(Level.SEVERE, null, ex);
        }
                         
    }//GEN-LAST:event_btnExportarDoctorActionPerformed

    private void tabladoctor2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabladoctor2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabladoctor2MouseClicked

//**************************************************************************
    private boolean ExistDog(String code) {
        boolean existe = false;
        for (clsPet dog : dogObjctList) {

            if (dog.getCode().equals(code)) {
                existe = true;
            }
        }
        return existe;
    }

    private boolean ExistCat(String code) {
        boolean existe = false;
        for (clsPet cat : catObjctList) {

            if (cat.getCode().equals(code)) {
                existe = true;
            }
        }
        return existe;
    }
    
    private boolean ExistDoctor(String code) {
        boolean existe = false;
        for (clsBusquedaDoctHospital doct : dochosObjctList) {

            if (doct.getNit().equals(code)) {
                existe = true;
            }
        }
        return existe;
    }
        
//**************************************************************************

    // ================== METODOS DE LIST DOG ================== //
    // METODO PARA LLENAR LA LISTA DE  DOG
    private void FillListDog() {
        DefaultListModel model = new DefaultListModel();
        int index = 0;
        for (clsPet dog : dogObjctList) {
            String perro = dog.getCode() + "    " + dog.getName() + "   " + dog.getBorn_year() + "   " + dog.getColor() + "   " + dog.getHealth_status();
            model.add(index, perro);
            index++;
        }

        PetListdog.setModel(model);
    }

    // METODO PARA LIMPIAR LOS TEXFILS DE DOG
    public void clearDogFields() {
        txtCodeDog.setText("");
        txtNameDog.setText("");
        txtBornYearDog.setText("");
        txtColorDog.setText("");
        cmbHealthStatusDog.setSelectedIndex(0);
        cmbBreedDog.setSelectedIndex(0);
        chePedigree.setSelected(false);
        txtPet_Id.setText("");
        txtDog_Id.setText("");

    }

    // ================== METODOS DE CAT ================== //   
    // METODO PARA LLENAR LA LISTA DE  CAT
    private void FillListCat() {
        DefaultListModel model = new DefaultListModel();
        int index = 0;
        for (clsPet cat : catObjctList) {
            String gato = cat.getCode() + "    " + cat.getName() + "   " + cat.getBorn_year() + "   " + cat.getColor() + "   " + cat.getHealth_status();
            model.add(index, gato);
            index++;
        }
        PetListCat.setModel(model);
    }

    // METODO PARA LIMPIAR LOS TEXFILS DE CAT
    public void clearCatFields() {
        txtCodeCat.setText("");
        txtNameCat.setText("");
        txtBornYearCat.setText("");
        txtColorCat.setText("");
        cmbHealthStatusCat.setSelectedIndex(0);
        cmbBreedCat.setSelectedIndex(0);
        txtPetC_Id.setText("");
        txtCat_Id.setText("");

    }
    // ==================================================== //
    
    // ================== METODOS DE DOCTOR ================== // 
    // METODO PARA LLENAR LA LISTA DE  DOCTOR
    
   private void FillListDoctor() {
        DefaultListModel model = new DefaultListModel();
        int index = 0;
        for (clsBusquedaDoctHospital doct : dochosObjctList) {
            String doctor = doct.getNit()+"   "+doct.getNamed()+"   "+doct.getAddress()+"   "+doct.getPhone();
            model.add(index, doctor);
            index++;
        }
      DocList.setModel(model);
    }
   
    // METODO PARA LIMPIAR LOS TEXFILS DE DOCTOR
    public void clearDoctorFields() {
        txtNameDoctor.setText("");
        txtAddressDoctor.setText("");
        txtNitDoctor.setText("");
        txtPhoneDoctor.setText("");
    }

    // ==================================================== //
    
    public void clearhospitalfields() {
        txtHosNomSala.setText("");
        txtHosNumHabitacion.setText("");
        txtHospital_Id.setText("");
    }
    
    
       private void deshabilitarbtn() {
        btnEditarDog.setEnabled(false);
        btnEliminarDog.setEnabled(false);
        btnEditarCat.setEnabled(false);
        btnEliminarCat.setEnabled(false);
        btnEditarDoctor.setEnabled(false);
        btnEliminarDoctor.setEnabled(false);
        btnEditarVeterinary.setEnabled(false);
        btnEliminarVeterinary.setEnabled(false);
    }
    
    private void habilitarbtnbuscarDog(){
        btnCrearDog.setEnabled(false);
        btnEditarDog.setEnabled(true);
        btnEliminarDog.setEnabled(true);
    }
    
    private void habilitarbtnbuscarCat(){
        btnCrearCat.setEnabled(false);
        btnEditarCat.setEnabled(true);
        btnEliminarCat.setEnabled(true);
    }
    
    private void habilitarbtnbuscardoctor(){
        btnCrearDoctor.setEnabled(false);
        btnEditarDoctor.setEnabled(true);
        btnEliminarDoctor.setEnabled(true);
    }
    
    private void habilitarbtnbuscarhospital(){
        btnCrearVeterinary.setEnabled(false);
        btnEditarVeterinary.setEnabled(true);
        btnEliminarVeterinary.setEnabled(true);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmPet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new frmPet().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList DocList;
    private javax.swing.JLabel Grafico;
    private javax.swing.JTabbedPane List;
    private javax.swing.JList PetListCat;
    private javax.swing.JList PetListdog;
    private javax.swing.JButton btnBuscarCat;
    private javax.swing.JButton btnBuscarDoctor;
    private javax.swing.JButton btnBuscarDog;
    private javax.swing.JButton btnBuscarVeterinary;
    private javax.swing.JButton btnCrearCat;
    private javax.swing.JButton btnCrearDoctor;
    private javax.swing.JButton btnCrearDog;
    private javax.swing.JButton btnCrearVeterinary;
    private javax.swing.JButton btnEditarCat;
    private javax.swing.JButton btnEditarDoctor;
    private javax.swing.JButton btnEditarDog;
    private javax.swing.JButton btnEditarVeterinary;
    private javax.swing.JButton btnEliminarCat;
    private javax.swing.JButton btnEliminarDoctor;
    private javax.swing.JButton btnEliminarDog;
    private javax.swing.JButton btnEliminarVeterinary;
    private javax.swing.JButton btnExportarCat;
    private javax.swing.JButton btnExportarDoctor;
    private javax.swing.JButton btnExportarDog;
    private javax.swing.JButton btnGraficoCat;
    private javax.swing.JButton btnGraficoCatRaza;
    private javax.swing.JButton btnGraficoDog;
    private javax.swing.JButton btnGraficoDogRaza;
    private javax.swing.JCheckBox chePedigree;
    private javax.swing.JComboBox cmbBreedCat;
    private javax.swing.JComboBox cmbBreedDog;
    private javax.swing.JComboBox cmbHealthStatusCat;
    private javax.swing.JComboBox cmbHealthStatusDog;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jP;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel label;
    private javax.swing.JLabel labelphone;
    private javax.swing.JTable tablacat;
    private javax.swing.JTable tabladoctor;
    private javax.swing.JTable tabladoctor2;
    private javax.swing.JTable tabladog;
    private javax.swing.JTable tablahospital;
    private javax.swing.JTable tablahospital2;
    private javax.swing.JTextField txtAddressDoctor;
    private javax.swing.JTextField txtBornYearCat;
    private javax.swing.JTextField txtBornYearDog;
    private javax.swing.JTextField txtCat_Id;
    private javax.swing.JTextField txtCodeCat;
    private javax.swing.JTextField txtCodeDog;
    private javax.swing.JTextField txtColorCat;
    private javax.swing.JTextField txtColorDog;
    private javax.swing.JTextField txtDog_Id;
    private javax.swing.JTextField txtHosNomSala;
    private javax.swing.JTextField txtHosNumHabitacion;
    private javax.swing.JTextField txtHospNomSa;
    private javax.swing.JTextField txtHospNumHabit;
    private javax.swing.JTextField txtHospital_Id;
    private javax.swing.JTextField txtIDHos;
    private javax.swing.JTextField txtIdDoc;
    private javax.swing.JTextField txtNameCat;
    private javax.swing.JTextField txtNameDoctor;
    private javax.swing.JTextField txtNameDog;
    private javax.swing.JTextField txtNitDoctor;
    private javax.swing.JTextField txtPetC_Id;
    private javax.swing.JTextField txtPet_Id;
    private javax.swing.JTextField txtPhoneDoctor;
    // End of variables declaration//GEN-END:variables

}
