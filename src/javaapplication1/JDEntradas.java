/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.swing.JDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import simplemysql.SimpleMySQL;
import simplemysql.SimpleMySQLResult;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;



/**
 *
 * @author Jonatan
 */
public class JDEntradas  extends javax.swing.JDialog   {
public static  int siguiente;
public String lalaserie = null;
public static  int linea=0;
public static  int copia=0;

    /**
     * Creates new form JDEntradas
     * @param parent
     * @param modal
     */
    public JDEntradas(java.awt.Frame parent, boolean modal)  {
        
        super(parent, modal);
         SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        SimpleMySQLResult rs;
       
        
      initComponents();
        this.setLocationRelativeTo(null);
        txtPlacas.addKeyListener(new KeyListenerCustom(this));
        txtModelo.addKeyListener(new KeyListenerCustom(this));
        JCTarifa.setFocusable(false);
        JBF1.setFocusable(false);
        JBF2.setFocusable(false);
        JBF3.setFocusable(false);
        JBF4.setFocusable(false);
        JBF5.setFocusable(false);
        JBF6.setFocusable(false);
        JBF7.setFocusable(false);
        JBF8.setFocusable(false);
        JCTicket.setFocusable(false);
        txtPlacas.requestFocusInWindow();
     
        PlainDocument doc = new TextLimiter(7);
        PlainDocument doc2 = new TextLimiter(7);

        doc.setDocumentFilter(new upperCASEJTEXTFIELD());
        doc2.setDocumentFilter(new upperCASEJTEXTFIELD());
        txtPlacas.setDocument(doc);
        txtModelo.setDocument(doc2);
        txtFechaentrada.setEditable(false);
        txthoraentrada.setEditable(false);
        
       
        rs = mysql.Query ("SELECT * FROM tarifas");
        while (rs.next()){

                        String USUARIO=rs.getString("DESCRIPCION");
                        JCTarifa.addItem(USUARIO);
                  
         }
        
       
        
        rs.close(); 
        
        
        
        
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdfa = new SimpleDateFormat("dd/MM/yyyy");
        
        
        new Timer(1000, new ActionListener() {
            
            
            
            
            
        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar cal = Calendar.getInstance();
    	cal.getTime();
            txthoraentrada.setText(sdf.format(cal.getTime()));
            txtFechaentrada.setText(sdfa.format(cal.getTime()));
            
             SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        SimpleMySQLResult rs;
            rs = mysql.Query ("SELECT * FROM opciones");
        String opciones[]=new String[20];
        int i=0;
        while (rs.next()){ 
            opciones[i] =rs.getString("OPCION"); 
            opciones[i] =rs.getString("VALOR"); 
        i++;
        } 
       
       int cajones=Integer.parseInt(opciones[9]);
       
        rs = mysql.Query ("SELECT * FROM estacionados WHERE SALIDA IS NULL ORDER BY ID DESC ");
    	
       cajones=cajones- rs.getNumRows();
       jLabel8.setText(Integer.toString(rs.getNumRows()));
       jLabel9.setText(Integer.toString(cajones));
            
        }
        
        
    }).start();
        
        

       
        
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtPlacas = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txthoraentrada = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFechaentrada = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        JCTarifa = new javax.swing.JComboBox();
        JBF1 = new javax.swing.JButton();
        JBF2 = new javax.swing.JButton();
        JBF3 = new javax.swing.JButton();
        JBF4 = new javax.swing.JButton();
        JBF5 = new javax.swing.JButton();
        JBF6 = new javax.swing.JButton();
        JBF7 = new javax.swing.JButton();
        JBF8 = new javax.swing.JButton();
        JBGrabar = new javax.swing.JButton();
        JCTicket = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Emisión de Tickets"));
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
        });

        jButton1.setText("Salidas");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Entradas");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Tarifa");

        txtPlacas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPlacas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPlacasKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Marca");

        txthoraentrada.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Placas");

        txtModelo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtModelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtModeloKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtModeloKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Fecha Entrada");

        txtFechaentrada.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Hora Entrada");

        JCTarifa.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        JCTarifa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCTarifaActionPerformed(evt);
            }
        });

        JBF1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JBF1.setText("F1");
        JBF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBF1ActionPerformed(evt);
            }
        });

        JBF2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JBF2.setText("F2");
        JBF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBF2ActionPerformed(evt);
            }
        });

        JBF3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JBF3.setText("F3");
        JBF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBF3ActionPerformed(evt);
            }
        });

        JBF4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JBF4.setText("F4");
        JBF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBF4ActionPerformed(evt);
            }
        });

        JBF5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JBF5.setText("F5");
        JBF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBF5ActionPerformed(evt);
            }
        });

        JBF6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JBF6.setText("F6");
        JBF6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBF6ActionPerformed(evt);
            }
        });

        JBF7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JBF7.setText("F7");
        JBF7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBF7ActionPerformed(evt);
            }
        });

        JBF8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JBF8.setText("F8");
        JBF8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBF8ActionPerformed(evt);
            }
        });

        JBGrabar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JBGrabar.setText("Grabar F9");
        JBGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBGrabarActionPerformed(evt);
            }
        });
        JBGrabar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JBGrabarKeyPressed(evt);
            }
        });

        JCTicket.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JCTicket.setText("Doble Ticket");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Disponibles:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Ocupados:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("0");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txthoraentrada, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechaentrada, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(txtPlacas)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(132, 132, 132))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(JCTicket)
                                        .addGap(22, 22, 22))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(JBF1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JBF2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JBF3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JBF4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JBF5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JBF6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(JBF7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JBF8))
                                    .addComponent(JBGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JCTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPlacas, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtFechaentrada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txthoraentrada, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JCTarifa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBF1)
                    .addComponent(JBF2)
                    .addComponent(JBF3)
                    .addComponent(JBF4)
                    .addComponent(JBF5)
                    .addComponent(JBF6)
                    .addComponent(JBF7)
                    .addComponent(JBF8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JCTicket))
                .addGap(14, 14, 14))
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

    private void JBF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF1ActionPerformed
JCTarifa.setSelectedIndex(0);        // TODO add your handling code here:
    }//GEN-LAST:event_JBF1ActionPerformed

    private void JBF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF2ActionPerformed
 JCTarifa.setSelectedIndex(1);       // TODO add your handling code here:
    }//GEN-LAST:event_JBF2ActionPerformed

    private void JBF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF3ActionPerformed
   JCTarifa.setSelectedIndex(2);     // TODO add your handling code here:
    }//GEN-LAST:event_JBF3ActionPerformed

    private void JBF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF4ActionPerformed
  JCTarifa.setSelectedIndex(3);      // TODO add your handling code here:
    }//GEN-LAST:event_JBF4ActionPerformed

    private void JBF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF5ActionPerformed
   JCTarifa.setSelectedIndex(4);     // TODO add your handling code here:
    }//GEN-LAST:event_JBF5ActionPerformed

    private void JBF6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF6ActionPerformed
  JCTarifa.setSelectedIndex(5);      // TODO add your handling code here:
    }//GEN-LAST:event_JBF6ActionPerformed

    private void JBF7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF7ActionPerformed
  JCTarifa.setSelectedIndex(6);      // TODO add your handling code here:
    }//GEN-LAST:event_JBF7ActionPerformed

    private void JBF8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF8ActionPerformed
  JCTarifa.setSelectedIndex(7);      // TODO add your handling code here:
    }//GEN-LAST:event_JBF8ActionPerformed

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed
      // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1KeyPressed

    private void JBGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBGrabarActionPerformed

        if (txtModelo.getText().equals("") && txtPlacas.getText().equals(""))  {
            showMessageDialog(null, "Ingresa Placas o modelo");
            txtPlacas.requestFocusInWindow();
        }else{
            SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
       SimpleMySQLResult rs;
       
       
       rs = mysql.Query ("SELECT * FROM opciones");
        String opciones[]=new String[20];
        int i=0;
        while (rs.next()){ 
            opciones[i] =rs.getString("OPCION"); 
            opciones[i] =rs.getString("VALOR"); 
        i++;
        } 
       
       String serieactual=opciones[3];
       int seriea = Integer.parseInt(opciones[5]);
       int contserie = Integer.parseInt(opciones[4]);
       int serieb = Integer.parseInt(opciones[6]);
       seriea--;
       serieb--;
       if("A".equals(serieactual)){
       if(contserie>=seriea&&serieb>=0){ contserie=0; serieactual="B"; }else{ contserie++; }
        }else if("B".equals(serieactual)){
       if(contserie>=serieb){ contserie=0; serieactual="A"; }else{ contserie++; }
       }
       
       mysql.Query ("UPDATE opciones SET VALOR= \""+serieactual+"\" WHERE ID=4");
       mysql.Query ("UPDATE opciones SET VALOR=\""+contserie+"\" WHERE ID=5");
       
       
       
        rs = mysql.Query ("SELECT TICKET FROM estacionados WHERE SERIE = \""+serieactual+"\" ORDER BY ID DESC LIMIT 1");
        int siguiente=2;
        while (rs.next()){ String algunassiguen =rs.getString("TICKET"); 
        siguiente = Integer.parseInt(algunassiguen);
        } 
        siguiente++;
        
        
     
        rs.close();
      
      
      
        mysql.Query ("INSERT INTO estacionados (TICKET,PLACAS,MARCA,TARIFA,ENTRADA,ESTATUS,USUARIOEMISION, SERIE) VALUES(\""+siguiente+"\",\""+txtPlacas.getText()+"\",\""+txtModelo.getText()+"\",\""+JCTarifa.getSelectedItem()+"\",NOW(), \"Registro\",\"ADMINISTRADOR\",\""+serieactual+"\")");
        

        mysql.close();
          PrinterJob job = PrinterJob.getPrinterJob();
          PageFormat pf = job.defaultPage();  
          
            Paper paper = new Paper();  
            paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());  
            pf.setPaper(paper);  
            
            job.setPrintable( new MiPrintable(),pf);
            
try 
{
   
   job.print();
   if(JCTicket.isSelected()){
    copia=1;
   job.print();
   }
   
} 
catch (PrinterException es) 
{
   es.printStackTrace();
}
        
    txtPlacas.setText("");
        txtModelo.setText("");
        JCTarifa.setSelectedIndex(0);
        copia=0;
 rs = mysql.Query ("SELECT * FROM opciones");
        i=0;
        while (rs.next()){ 
            opciones[i] =rs.getString("OPCION"); 
            opciones[i] =rs.getString("VALOR"); 
        i++;
        } 
       
       int cajones=Integer.parseInt(opciones[9]);
         rs = mysql.Query ("SELECT * FROM estacionados WHERE SALIDA IS NULL ORDER BY ID DESC ");

       cajones=cajones- rs.getNumRows();
       jLabel8.setText(Integer.toString(rs.getNumRows()));
       jLabel9.setText(Integer.toString(cajones));
        txtPlacas.requestFocusInWindow();    
        }

    }//GEN-LAST:event_JBGrabarActionPerformed

    private void JBGrabarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JBGrabarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JBGrabarKeyPressed

    private void txtPlacasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacasKeyPressed
        
    }//GEN-LAST:event_txtPlacasKeyPressed

    private void txtModeloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModeloKeyPressed
      
    }//GEN-LAST:event_txtModeloKeyPressed

    private void txtModeloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModeloKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModeloKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        this.setVisible(false);
            JDSalidas jds=new JDSalidas(null, rootPaneCheckingEnabled);
            jds.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 this.setVisible(false);
            JDEntradas jds=new JDEntradas(null, rootPaneCheckingEnabled);
            jds.setVisible(rootPaneCheckingEnabled);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void JCTarifaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCTarifaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JCTarifaActionPerformed

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
            java.util.logging.Logger.getLogger(JDEntradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDEntradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDEntradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDEntradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JDEntradas dialog = new JDEntradas(new javax.swing.JFrame(), true);
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
    
    
    public class upperCASEJTEXTFIELD extends DocumentFilter {

    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String text,
            AttributeSet attr) throws BadLocationException {
        fb.insertString(offset, text.toUpperCase(), attr);
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text,
            AttributeSet attrs) throws BadLocationException {

        fb.replace(offset, length, text.toUpperCase(), attrs);
    }
}
    
    public class TextLimiter extends PlainDocument {

    private Integer limit;

    public TextLimiter(Integer limit) {
        super();
        this.limit = limit;
    }

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str == null) {
            return;
        }
        if (limit == null || limit < 1 || ((getLength() + str.length()) <= limit)) {
            super.insertString(offs, str, a);
        } else if ((getLength() + str.length()) > limit) {
            String insertsub = str.substring(0, (limit - getLength()));
            super.insertString(offs, insertsub, a);
        }
    }
}

public class KeyListenerCustom implements KeyListener{
    private JDialog dialog=null;
    public KeyListenerCustom(JDialog dialog){

        this.dialog=dialog;
    }
    public void keyTyped(KeyEvent e) {

    }
 
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode()==123) {//27 es el código de la tecla esc
           
            dialog.setVisible(false);
            JDSalidas jds=new JDSalidas(null, rootPaneCheckingEnabled);
            jds.setVisible(rootPaneCheckingEnabled);
        }
        if(e.getKeyCode()==KeyEvent.VK_F9){
            if (txtModelo.getText().equals("") && txtPlacas.getText().equals(""))  {
            showMessageDialog(null, "Ingresa Placas o modelo");
        }else{
            SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
       SimpleMySQLResult rs;
       
       
       rs = mysql.Query ("SELECT * FROM opciones");
        String opciones[]=new String[20];
        int i=0;
        while (rs.next()){ 
            opciones[i] =rs.getString("OPCION"); 
            opciones[i] =rs.getString("VALOR"); 
        i++;
        } 
       
       String serieactual=opciones[3];
       int seriea = Integer.parseInt(opciones[5]);
       int contserie = Integer.parseInt(opciones[4]);
       int serieb = Integer.parseInt(opciones[6]);
       seriea--;
       serieb--;
       if("A".equals(serieactual)){
       if(contserie>=seriea&&serieb>=0){ contserie=0; serieactual="B"; }else{ contserie++; }
        }else if("B".equals(serieactual)){
       if(contserie>=serieb){ contserie=0; serieactual="A"; }else{ contserie++; }
       }
              
       mysql.Query ("UPDATE opciones SET VALOR= \""+serieactual+"\" WHERE ID=4");
       mysql.Query ("UPDATE opciones SET VALOR=\""+contserie+"\" WHERE ID=5");
       
       
       
        rs = mysql.Query ("SELECT TICKET FROM estacionados WHERE SERIE = \""+serieactual+"\" ORDER BY ID DESC LIMIT 1");
        int siguiente=2;
        while (rs.next()){ String algunassiguen =rs.getString("TICKET"); 
        siguiente = Integer.parseInt(algunassiguen);
        } 
        siguiente++;
        
        
     
        rs.close();
      
      
      
        mysql.Query ("INSERT INTO estacionados (TICKET,PLACAS,MARCA,TARIFA,ENTRADA,ESTATUS,USUARIOEMISION, SERIE) VALUES(\""+siguiente+"\",\""+txtPlacas.getText()+"\",\""+txtModelo.getText()+"\",\""+JCTarifa.getSelectedItem()+"\",NOW(), \"Registro\",\"ADMINISTRADOR\",\""+serieactual+"\")");
        

        mysql.close();
          PrinterJob job = PrinterJob.getPrinterJob();
          PageFormat pf = job.defaultPage();  
          
            Paper paper = new Paper();  
            paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());  
            pf.setPaper(paper);  
            
            job.setPrintable( new MiPrintable(),pf);
            
try 
{
   
   job.print();
   if(JCTicket.isSelected()){
    copia=1;
   job.print();
   }
   
   
} 
catch (PrinterException es) 
{
   es.printStackTrace();
}
        
    txtPlacas.setText("");
        txtModelo.setText("");
        JCTarifa.setSelectedIndex(0);
        copia=0;
        
       rs = mysql.Query ("SELECT * FROM opciones");
        i=0;
        while (rs.next()){ 
            opciones[i] =rs.getString("OPCION"); 
            opciones[i] =rs.getString("VALOR"); 
        i++;
        } 
       
       int cajones=Integer.parseInt(opciones[9]);
       
        rs = mysql.Query ("SELECT * FROM estacionados WHERE SALIDA IS NULL ORDER BY ID DESC ");
    	
       cajones=cajones- rs.getNumRows();
       jLabel8.setText(Integer.toString(rs.getNumRows()));
       jLabel9.setText(Integer.toString(cajones));
        
        }
            txtPlacas.requestFocusInWindow();  
        }else if(e.getKeyCode()==KeyEvent.VK_ENTER){
        
        if(txtPlacas.isFocusOwner()){    
        txtModelo.requestFocusInWindow();
        txtModelo.selectAll();
        }else if(txtModelo.isFocusOwner()){
        txtPlacas.requestFocusInWindow();
        txtPlacas.selectAll();
        }
                
                
        }else if(e.getKeyCode()==KeyEvent.VK_F1){
        
        JCTarifa.setSelectedIndex(0);
        
        }else if(e.getKeyCode()==KeyEvent.VK_F2){
        
        JCTarifa.setSelectedIndex(1);
        
        }else if(e.getKeyCode()==KeyEvent.VK_F3){
        
        JCTarifa.setSelectedIndex(2);
        
        }else if(e.getKeyCode()==KeyEvent.VK_F4){
        
        JCTarifa.setSelectedIndex(3);
        }else if(e.getKeyCode()==KeyEvent.VK_F5){
        
        JCTarifa.setSelectedIndex(4);
        }else if(e.getKeyCode()==KeyEvent.VK_F6){
        
        JCTarifa.setSelectedIndex(5);
        }else if(e.getKeyCode()==KeyEvent.VK_F7){
        
        JCTarifa.setSelectedIndex(6);
        }else if(e.getKeyCode()==KeyEvent.VK_F8){
        
        JCTarifa.setSelectedIndex(7);
        }else if(e.getKeyCode()==KeyEvent.VK_F10){
        
        
        
        txtPlacas.setText("");
        txtModelo.setText("");
        JCTarifa.setSelectedIndex(0);
        txtPlacas.requestFocusInWindow();
        
        }else if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
        
        this.dialog.dispose();
        
        }
        
        
    }
 
    public void keyReleased(KeyEvent e) {
           
    }
 
}

class MiPrintable implements Printable 
{
   public int print (Graphics g, PageFormat f, int pageIndex) 
   {
       
   
       
      if (pageIndex == 0) 
      { 
          try {
              // Imprime "Hola mundo" en la primera pagina, en la posicion 100,100

              outputtingBarcodeAsPNG();
          } catch (BarcodeException ex) {
             showMessageDialog(null, ex);
          }
    

    Image img1 = Toolkit.getDefaultToolkit().getImage("C:/Estacionamientos/LogoTicket.jpg");
    g.drawImage(img1, 0, 0, 290, 30, null);
          
          g.setFont(new Font("Arial", Font.BOLD, 8));  
          SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        SimpleMySQLResult rs;
        rs = mysql.Query ("SELECT * FROM opciones WHERE ID = 2");
        while (rs.next()){ String str =rs.getString("VALOR");
                        drawStringMultiLine(g, str, 270, 10, 50);
                      } rs.close();
        rs = mysql.Query ("SELECT * FROM estacionados ORDER BY ID DESC LIMIT 1");
        
        while (rs.next()){  siguiente = Integer.parseInt(rs.getString("TICKET"));
                      lalaserie=rs.getString("SERIE"); } rs.close();              
        g.setFont(new Font("Arial", Font.BOLD, 12));
        linea=linea+5;
        g.drawString("TICKET DE ENTRADA", 80,linea);
        linea=linea+5;
        g.drawString("______________________________________________", 10,linea);
        g.setFont(new Font("Arial", Font.BOLD, 22));
        java.text.DecimalFormat nft = new  
java.text.DecimalFormat("#000000.###");  
nft.setDecimalSeparatorAlwaysShown(false); 
linea=linea+24;
        g.drawString("FOLIO: "+lalaserie+nft.format(siguiente), 60,linea);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        linea=linea+20;
        g.drawString("PLACAS: "+txtPlacas.getText(), 70,linea);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        linea=linea+15;
        g.drawString("MARCA: "+txtModelo.getText(), 40,linea);
        linea=linea+12;
        g.drawString("FECHA: ", 40,linea);
         
                g.drawString(txtFechaentrada.getText().replace("-", "/"), 100,linea);
        linea=linea+12;
        g.drawString("HORA: ", 40,linea);

        g.drawString(txthoraentrada.getText(), 100,linea);
        linea=linea+12;
        g.drawString("TARIFA: "+JCTarifa.getSelectedItem(), 40,linea);
        linea=linea+12;
g.drawString("RESP. TURNO: "+JDLogin.elusuario.toUpperCase(), 40,linea);     

   Image img3=null;
     
          try {
              img3 = ImageIO.read(new File("mybarcode.jpg"));
          } catch (IOException ex) {
              Logger.getLogger(JDEntradas.class.getName()).log(Level.SEVERE, null, ex);
          }
        linea=linea+4; 
    g.drawImage(img3, 10,linea,  null);
     linea=linea+50;
    g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("______________________________________________", 10,linea); 
g.setFont(new Font("Arial", Font.BOLD, 6));  
          System.out.println("hasta aqui bien");
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        rs = mysql.Query ("SELECT * FROM opciones WHERE ID = 13");
        linea=linea+12;
         
        if(copia==0){
        while (rs.next()){ String str =rs.getString("VALOR");
                        drawStringMultiLine(g, str, 280, 10, linea);
                      } rs.close();
                      
        }
        rs = mysql.Query ("SELECT * FROM opciones WHERE ID = 17");
        
         
        if(copia==0){
        while (rs.next()){ String str =rs.getString("VALOR");
                        drawStringMultiLine(g, str, 280, 10, linea);
                      } rs.close();
                      
        }
        linea=linea+12;
         return PAGE_EXISTS;
      }
      else{
         return NO_SUCH_PAGE;
      }
      }
    public void outputtingBarcodeAsPNG() throws BarcodeException {
        // get a Barcode from the BarcodeFactory
        java.text.DecimalFormat nft = new  
java.text.DecimalFormat("#000000.###");  
         SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        SimpleMySQLResult rs;
       
        rs = mysql.Query ("SELECT * FROM estacionados ORDER BY ID DESC LIMIT 1");
        
        while (rs.next()){  siguiente = Integer.parseInt(rs.getString("TICKET"));
                      lalaserie=rs.getString("SERIE"); } rs.close();     
nft.setDecimalSeparatorAlwaysShown(false); 
		Barcode barcode = BarcodeFactory.createCode128B(lalaserie+nft.format(siguiente));
                barcode.setDrawingText(false);
                barcode.setBarHeight(50);

        try {
            File f = new File("mybarcode.jpg");

            // Let the barcode image handler do the hard work
            BarcodeImageHandler.saveJPEG(barcode, f);
        } catch (Exception e) {
            // Error handling here
        }
    }
}
public static void drawStringMultiLine(Graphics g, String text, int lineWidth, int x, int y) {
    FontMetrics m = g.getFontMetrics();
    lineWidth=280;
    System.out.println("vamos"+text);
    if(m.stringWidth(text) < lineWidth) {
        g.drawString(text, x, y);
    } else {
        String[] words = text.split(" ");
        String currentLine = words[0];
        for(int i = 1; i < words.length; i++) {
            if(m.stringWidth(currentLine+words[i]) < lineWidth) {
                currentLine += " "+words[i];
            } else {
                
                for (int j = 0; j <= 280; j++) {
                 
                }
                System.out.println("hasta aqui bien"+currentLine);
                g.drawString(justificar(m,currentLine), x, y);
                y += m.getHeight();
                currentLine = words[i];
                linea=y+m.getHeight();
                
            }
        }
        if(currentLine.trim().length() > 0) {
            g.drawString(currentLine, x, y);
        }
    }
}


public static String justificar(FontMetrics m,String currentLine){

 ArrayList<Integer> espacios = new ArrayList<Integer>();
         for (int index = currentLine.indexOf(" "); index >= 0; index = currentLine.indexOf(" ", index + 1)){
         espacios.add(index);
        }
        StringBuilder salida=new StringBuilder();
        salida.append(currentLine);
        int contador=0;
        int contador2=0;
        while (m.stringWidth(salida.toString())<=280) {    
          contador=0;

            for (int espacio : espacios) {
                salida.insert(espacio+contador+contador2, ' ');
                
                
                contador=contador+1+contador2;
                if (m.stringWidth(salida.toString())>=280) {
                    break;
                }
            }
            contador2++;
            
        }



return salida.toString();
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBF1;
    private javax.swing.JButton JBF2;
    private javax.swing.JButton JBF3;
    private javax.swing.JButton JBF4;
    private javax.swing.JButton JBF5;
    private javax.swing.JButton JBF6;
    private javax.swing.JButton JBF7;
    private javax.swing.JButton JBF8;
    private javax.swing.JButton JBGrabar;
    private javax.swing.JComboBox JCTarifa;
    private javax.swing.JCheckBox JCTicket;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JTextField txtFechaentrada;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtPlacas;
    private javax.swing.JTextField txthoraentrada;
    // End of variables declaration//GEN-END:variables
}
