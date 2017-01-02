/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;


import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import simplemysql.SimpleMySQL;
import simplemysql.SimpleMySQLResult;


/**
 *
 * @author Jonatan
 */
public class JDPreferencias extends javax.swing.JDialog {
String actuala;
String actualb;
    /**
     * Creates new form JDPreferencias
     */
    public JDPreferencias(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        txtinferior.setLineWrap(true);
        txtinferior1.setLineWrap(true);
        txtinferior3.setLineWrap(true);
        txtsuperior.setLineWrap(true);
        
        SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        SimpleMySQLResult rs;
        rs = mysql.Query ("SELECT * FROM opciones");
        while (rs.next()){
            if("BASEDATOS".equals(rs.getString("OPCION"))){
                        txtbasedatos.setText(rs.getString("VALOR"));
            } if("SERIEA".equals(rs.getString("OPCION"))){
                       txtvecesa.setText(rs.getString("VALOR"));
            } if("SERIEB".equals(rs.getString("OPCION"))){
                if (Integer.valueOf(rs.getString("VALOR"))!=0) {
                jCheckBox1.setSelected(true);
           txtvecesb.setText(rs.getString("VALOR"));
            }else{
                txtvecesb.setText(rs.getString("VALOR"));
                 jLabel4.setVisible(false);
          jLabel5.setVisible(false);
          txtvecesa.setVisible(false);
          txtvecesb.setVisible(false);
          jButton3.setVisible(false);
          jButton4.setVisible(false);
          jlactuala.setVisible(false);
          jlactualb.setVisible(false);
          jlactuala1.setVisible(false);
          jlactuala2.setVisible(false);
          jlactuala3.setVisible(false);
          
                }
                        
            } if("VECESCORTESIA".equals(rs.getString("OPCION"))){
                        txtvecescortesia.setText(rs.getString("VALOR"));
             } if("HENTRADA".equals(rs.getString("OPCION"))){
                         if (Integer.valueOf(rs.getString("VALOR"))==1) {
                jCheckBox2.setSelected(true);
            }else{
                   jCheckBox2.setSelected(false);      
                         }          
            } if("TEXTO2".equals(rs.getString("OPCION"))){
                        txtinferior.setText(rs.getString("VALOR"));
                        } if("TEXTO3".equals(rs.getString("OPCION"))){
                        txtinferior1.setText(rs.getString("VALOR"));
                         } if("TEXTO4".equals(rs.getString("OPCION"))){
                        txtinferior3.setText(rs.getString("VALOR"));
            } if("TEXTO1".equals(rs.getString("OPCION"))){
                        txtsuperior.setText(rs.getString("VALOR"));
            } if("CAJONES".equals(rs.getString("OPCION"))){
                        txtcajones.setText(rs.getString("VALOR"));
            } if("BOLETOPERDIDO".equals(rs.getString("OPCION"))){
                        jTextField1.setText(rs.getString("VALOR"));
            } if("LUGAREXPEDICION".equals(rs.getString("OPCION"))){
                        jTextField2.setText(rs.getString("VALOR"));
            } if("HAPERTURA".equals(rs.getString("OPCION"))){
                        jfhorae1.setText(rs.getString("VALOR"));
            } if("HCIERRE".equals(rs.getString("OPCION"))){
                        jfhoraf1.setText(rs.getString("VALOR"));
            }  

                        
                       
         }
        
        jCheckBox1.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
          if (jCheckBox1.isSelected()) {
               jLabel4.setVisible(true);
          jLabel5.setVisible(true);
          txtvecesa.setVisible(true);
          txtvecesb.setVisible(true);
          jButton3.setVisible(true);
          jButton4.setVisible(true);
          jlactuala.setVisible(true);
          jlactualb.setVisible(true);
          jlactuala1.setVisible(true);
          jlactuala2.setVisible(true);
          jlactuala3.setVisible(true);
          
          }else{
         
         
           jLabel4.setVisible(false);
          jLabel5.setVisible(false);
          txtvecesa.setVisible(false);
          txtvecesb.setVisible(false);
          jButton3.setVisible(false);
          jButton4.setVisible(false);
          jlactuala.setVisible(false);
          jlactualb.setVisible(false);
          jlactuala1.setVisible(false);
          jlactuala2.setVisible(false);
          jlactuala3.setVisible(false);
          }
      }
    });
            
         
      
        rs = mysql.Query ("SELECT * FROM estacionados WHERE SERIE=\"A\" ORDER BY ID DESC LIMIT 1");
        
        while (rs.next()){  jlactuala2.setText(rs.getString("TICKET")); } rs.close(); 
        rs = mysql.Query ("SELECT * FROM estacionados WHERE SERIE=\"B\" ORDER BY ID DESC LIMIT 1");
        
        while (rs.next()){  jlactuala3.setText(rs.getString("TICKET")); } rs.close(); 
        rs.close(); 
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlactuala1 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtinferior2 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtbasedatos = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtsuperior = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtinferior = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtvecesa = new javax.swing.JTextField();
        txtvecesb = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtvecescortesia = new javax.swing.JTextField();
        txtcajones = new javax.swing.JTextField();
        jlactuala = new javax.swing.JLabel();
        jlactualb = new javax.swing.JLabel();
        jlactuala2 = new javax.swing.JLabel();
        jlactuala3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtinferior1 = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtinferior3 = new javax.swing.JTextArea();
        jfhorae1 = new javax.swing.JFormattedTextField();
        jfhoraf1 = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();

        jlactuala1.setText("Actual");

        jButton6.setText("Eliminar A");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        txtinferior2.setColumns(20);
        txtinferior2.setRows(5);
        jScrollPane4.setViewportView(txtinferior2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Preferencias"));

        jLabel1.setText("IP Base de Datos");

        txtsuperior.setColumns(20);
        txtsuperior.setRows(5);
        jScrollPane1.setViewportView(txtsuperior);

        jLabel2.setText("Texto Superior Boleto");

        txtinferior.setColumns(20);
        txtinferior.setRows(5);
        jScrollPane2.setViewportView(txtinferior);

        jLabel3.setText("Texto Inferior Boleto Portatil");

        jLabel4.setText("Veces Serie A");

        jLabel5.setText("Veces Serie B");

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Reinicair");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Reiniciar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel6.setText("Veces para Cortesia");

        jLabel7.setText("Cajones Disponibles");

        jlactuala.setText("Actual");

        jlactualb.setText("Actual");

        jlactuala2.setText("0");

        jlactuala3.setText("0");

        jButton5.setText("Eliminar A");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel8.setText("Boleto Perdido");

        jLabel9.setText("Lugar Expedicion");

        jCheckBox1.setText("Usar Segundo Folio");
        jCheckBox1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox1StateChanged(evt);
            }
        });

        jButton7.setText("Eliminar B");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Eliminar Cortes");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        txtinferior1.setColumns(20);
        txtinferior1.setRows(5);
        jScrollPane3.setViewportView(txtinferior1);

        jLabel10.setText("Texto Inferior Boleto PC");

        txtinferior3.setColumns(20);
        txtinferior3.setRows(5);
        jScrollPane5.setViewportView(txtinferior3);

        try {
            jfhorae1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfhorae1.setText("00:00:00");

        try {
            jfhoraf1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfhoraf1.setText("00:00:00");

        jLabel11.setText("Apertura");

        jLabel12.setText("Cierre");

        jCheckBox2.setText("Cierra el mismo dia");
        jCheckBox2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox2StateChanged(evt);
            }
        });
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jlactuala)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jlactuala2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtvecesa, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jlactualb)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jlactuala3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(txtvecesb))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3)
                                    .addComponent(jButton4)))
                            .addComponent(jLabel2)
                            .addComponent(jCheckBox1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtbasedatos, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jfhoraf1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jfhorae1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel8)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel9))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jTextField2)
                                                    .addComponent(jTextField1)
                                                    .addComponent(txtcajones, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtvecescortesia, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel10))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane3)
                                .addGap(15, 15, 15))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane5)
                                .addContainerGap())))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtbasedatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtvecesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlactuala)
                            .addComponent(jlactuala2))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtvecesb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlactualb)
                            .addComponent(jlactuala3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jfhorae1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11))
                            .addComponent(jCheckBox2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jfhoraf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(jButton7)
                            .addComponent(jButton8))
                        .addGap(14, 14, 14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jCheckBox1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtvecescortesia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(13, 13, 13)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtcajones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel9))))
                        .addGap(29, 29, 29)
                        .addComponent(jLabel3)
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
mysql.Query ("UPDATE opciones SET VALOR= \""+txtsuperior.getText()+"\" WHERE ID=2");
mysql.Query ("UPDATE opciones SET VALOR= \""+txtinferior.getText()+"\" WHERE ID=3");
mysql.Query ("UPDATE opciones SET VALOR= \""+txtinferior1.getText()+"\" WHERE ID=13");
mysql.Query ("UPDATE opciones SET VALOR= \""+txtinferior3.getText()+"\" WHERE ID=17");
mysql.Query ("UPDATE opciones SET VALOR= \""+txtbasedatos.getText()+"\" WHERE ID=8");       
 mysql.Query ("UPDATE opciones SET VALOR= \""+txtcajones.getText()+"\" WHERE ID=10"); 
 mysql.Query ("UPDATE opciones SET VALOR= \""+txtvecescortesia.getText()+"\" WHERE ID=9");
 mysql.Query ("UPDATE opciones SET VALOR= \""+txtvecesa.getText()+"\" WHERE ID=6");
 mysql.Query ("UPDATE opciones SET VALOR= \""+jfhorae1.getText()+"\" WHERE ID=15");
 mysql.Query ("UPDATE opciones SET VALOR= \""+jfhoraf1.getText()+"\" WHERE ID=16");
        if (jCheckBox1.isSelected()) {
             mysql.Query ("UPDATE opciones SET VALOR= \""+txtvecesb.getText()+"\" WHERE ID=7");
        }else{
         mysql.Query ("UPDATE opciones SET VALOR= 0 WHERE ID=7");
        }
        
        if (jCheckBox2.isSelected()) {
             mysql.Query ("UPDATE opciones SET VALOR= 1 WHERE ID=18");
        }else{
          mysql.Query ("UPDATE opciones SET VALOR= 0 WHERE ID=18");
        }

mysql.Query ("UPDATE opciones SET VALOR= \""+jTextField1.getText()+"\" WHERE ID=11");
   mysql.Query ("UPDATE opciones SET VALOR= \""+jTextField2.getText()+"\" WHERE ID=12");  
mysql.close();
        
        
        
        
        
        
        this.dispose(); 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        mysql.Query ("INSERT INTO estacionados (TICKET,PLACAS,MARCA,TARIFA,ENTRADA,SALIDA,ESTATUS,USUARIOEMISION,USUARIOCORTE, SERIE) VALUES(\"0\",\"RESETA\",\"\",\"\",NOW(),NOW(), \"CANCELADO\",\"ADMINISTRADOR\",\"ADMINISTRADOR\",\"A\")");
        jlactuala2.setText("0");
        mysql.close();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        mysql.Query ("INSERT INTO estacionados (TICKET,PLACAS,MARCA,TARIFA,ENTRADA,SALIDA,ESTATUS,USUARIOEMISION,USUARIOCORTE, SERIE) VALUES(\"0\",\"RESETA\",\"\",\"\",NOW(),NOW(), \"CANCELADO\",\"ADMINISTRADOR\",\"ADMINISTRADOR\",\"B\")");
        jlactuala3.setText("0");
        mysql.close();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        JDialog.setDefaultLookAndFeelDecorated(true);
    int response = JOptionPane.showConfirmDialog(null, "Deseas ELIMINAR FOLIOS A?", "ELIMINAR BASE DE DATOS",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.YES_OPTION) {
        SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        mysql.Query ("DELETE FROM estacionados WHERE ID!=1 AND ID!=2 AND SERIE=\"A\"");
        
        mysql.close();
    
    }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jCheckBox1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox1StateChanged
       // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1StateChanged

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
     JDialog.setDefaultLookAndFeelDecorated(true);
    int response = JOptionPane.showConfirmDialog(null, "Deseas ELIMINAR FOLIO B", "ELIMINAR BASE DE DATOS",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.YES_OPTION) {
        SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
                mysql.Query ("DELETE FROM estacionados WHERE ID!=1 AND ID!=2 AND SERIE=\"B\"");
        
        mysql.close();
    
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
     JDialog.setDefaultLookAndFeelDecorated(true);
    int response = JOptionPane.showConfirmDialog(null, "Deseas ELIMINAR CORTES?", "ELIMINAR BASE DE DATOS",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.YES_OPTION) {
        SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        mysql.Query ("DELETE FROM cortes");
        
        mysql.close();
    
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jCheckBox2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox2StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2StateChanged

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

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
            java.util.logging.Logger.getLogger(JDPreferencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDPreferencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDPreferencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDPreferencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDPreferencias dialog = new JDPreferencias(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JFormattedTextField jfhorae1;
    private javax.swing.JFormattedTextField jfhoraf1;
    private javax.swing.JLabel jlactuala;
    private javax.swing.JLabel jlactuala1;
    private javax.swing.JLabel jlactuala2;
    private javax.swing.JLabel jlactuala3;
    private javax.swing.JLabel jlactualb;
    private javax.swing.JTextField txtbasedatos;
    private javax.swing.JTextField txtcajones;
    private javax.swing.JTextArea txtinferior;
    private javax.swing.JTextArea txtinferior1;
    private javax.swing.JTextArea txtinferior2;
    private javax.swing.JTextArea txtinferior3;
    private javax.swing.JTextArea txtsuperior;
    private javax.swing.JTextField txtvecesa;
    private javax.swing.JTextField txtvecesb;
    private javax.swing.JTextField txtvecescortesia;
    // End of variables declaration//GEN-END:variables
}
