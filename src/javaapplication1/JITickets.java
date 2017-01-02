/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javaapplication1.JITickets.drawStringMultiLine;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import simplemysql.SimpleMySQL;
import simplemysql.SimpleMySQLResult;

/**
 *
 * @author Jonatan
 */

public class JITickets extends javax.swing.JInternalFrame {
DefaultTableModel tablapagados= new DefaultTableModel(){

      
         
             
              @Override
    public boolean isCellEditable(int row, int column) {
       //all cells false
       return false;
    }
        };
    /**
     * Creates new form JITickets
     */
    public JITickets() {
        initComponents();
        
        jComboBox2.addKeyListener(new JFMenu.KeyListenerCustom(this));
        jTextField2.addKeyListener(new JFMenu.KeyListenerCustom(this));
        jButton1.addKeyListener(new JFMenu.KeyListenerCustom(this));
        jButton2.addKeyListener(new JFMenu.KeyListenerCustom(this));
        jButton3.addKeyListener(new JFMenu.KeyListenerCustom(this));
        jButton4.addKeyListener(new JFMenu.KeyListenerCustom(this));
        
          this.jTable1.setModel(tablapagados);
      this.tablapagados.addColumn("ID Interno");
        this.tablapagados.addColumn("Ticket");
        this.tablapagados.addColumn("Placas");
        this.tablapagados.addColumn("Marca");
        this.tablapagados.addColumn("Entrada");
        this.tablapagados.addColumn("Salida");
        this.tablapagados.addColumn("Tiempo");
        this.tablapagados.addColumn("Dias");
        this.tablapagados.addColumn("Importe");
        this.tablapagados.addColumn("Tarifa");
        this.tablapagados.addColumn("Estatus");
        this.tablapagados.addColumn("Emision");
        
        SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        SimpleMySQLResult rs=null;
        rs = mysql.Query ("SELECT * FROM estacionados");
        int contador=1;
        java.text.DecimalFormat nft = new  
java.text.DecimalFormat("#000000.###");  
nft.setDecimalSeparatorAlwaysShown(false); 
         DateFormat fold = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         DateFormat fnew = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
         String SALIDA;
        while (rs.next()){
            try {
                String ELID= String.valueOf(rs.getString("ID"));
                String TICKET=rs.getString("SERIE")+nft.format(Integer.parseInt(rs.getString("TICKET")));
                String PLACAS=rs.getString("PLACAS");
                String MARCA=rs.getString("MARCA");
                
                
                String ENTRADA=fnew.format(fold.parse(rs.getString("ENTRADA").replace(".0", "")));
                if (rs.getString("SALIDA")!= null && !rs.getString("SALIDA").isEmpty()) {
                    SALIDA=fnew.format(fold.parse(rs.getString("SALIDA").replace(".0", "")));
                }else{
                    SALIDA=rs.getString("SALIDA");
                }
                String TIEMPO=rs.getString("TIEMPO");
                String DIAS=rs.getString("DIAS");
                String IMPORTE=rs.getString("IMPORTE");
                String TARIFA=rs.getString("TARIFA");
                String ESTATUS=rs.getString("ESTATUS");
                if (ESTATUS.equals("Registro")) {
                    ESTATUS="ESTACIONADO";
                }
                String USUARIOEMISION=rs.getString("USUARIOEMISION");
                Object fila []= { ELID, TICKET, PLACAS, MARCA,ENTRADA,SALIDA,TIEMPO,DIAS,IMPORTE,TARIFA,ESTATUS,USUARIOEMISION};
                tablapagados.addRow(fila);
                contador++;
            } catch (ParseException ex) {
                Logger.getLogger(JITickets.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        rs.close();
        mysql.close();
                jTable1.getColumnModel().getColumn(1).setPreferredWidth(60);
         jTable1.getColumnModel().getColumn(3).setPreferredWidth(140);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(11).setPreferredWidth(170);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
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
        jTextField2 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setIconifiable(true);
        setMaximizable(true);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Placas", "Ticket", "Tarifa" }));

        jButton1.setText("Buscar");
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

        jButton3.setText("Reimprimir Ticket");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Reimprimir  Pago");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Imprimir Busqueda");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(25, 25, 25)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addGap(11, 11, 11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)))
                .addGap(17, 17, 17))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       
       
       PrinterJob job = PrinterJob.getPrinterJob();
          PageFormat pf = job.defaultPage();  
          
            Paper paper = new Paper();  
            paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());  
            pf.setPaper(paper);  
            
            job.setPrintable( new JITickets.MiPrintable(),pf);
            
try 
{

   job.print();

} 
catch (PrinterException es) 
{
   es.printStackTrace();
}
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       tablapagados.setNumRows(0); 
        SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        SimpleMySQLResult rs=null;
        
         if(jTextField2.getText().equals("")){
        rs = mysql.Query ("SELECT * FROM estacionados");
        }else{
        
        if (jComboBox2.getSelectedItem().toString()=="Placas") {
                rs = mysql.Query ("SELECT * FROM estacionados WHERE PLACAS LIKE \"%"+jTextField2.getText()+"%\"");
            }else if (jComboBox2.getSelectedItem().toString()=="Ticket"){
                rs = mysql.Query ("SELECT * FROM estacionados WHERE (TICKET LIKE \"%"+jTextField2.getText().replaceAll("[^\\d.]", "").replaceFirst("^0+(?!$)", "")+"%\" AND SERIE LIKE \"%"+jTextField2.getText().replaceAll("[0-9]", "")+"%\")");
            }else{
                rs = mysql.Query ("SELECT * FROM estacionados WHERE TARIFA LIKE \"%"+jTextField2.getText()+"%\"");
            }
        
        }
        
       
        int contador=1;
        java.text.DecimalFormat nft = new  
java.text.DecimalFormat("#000000.###");  
nft.setDecimalSeparatorAlwaysShown(false); 
         DateFormat fold = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         DateFormat fnew = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        while (rs.next()){
            String SALIDA;
           try {
               String ELID= String.valueOf(rs.getString("ID"));
               String TICKET=rs.getString("SERIE")+nft.format(Integer.parseInt(rs.getString("TICKET")));
               String PLACAS=rs.getString("PLACAS");
               String MARCA=rs.getString("MARCA");
               String ENTRADA=fnew.format(fold.parse(rs.getString("ENTRADA").replace(".0", "")));
               if (rs.getString("SALIDA")!= null && !rs.getString("SALIDA").isEmpty()) {
               SALIDA=fnew.format(fold.parse(rs.getString("SALIDA").replace(".0", "")));    
               }else{
               SALIDA=rs.getString("SALIDA");
               }
               String TIEMPO=rs.getString("TIEMPO");
               String DIAS=rs.getString("DIAS");
               String IMPORTE=rs.getString("IMPORTE");
               String TARIFA=rs.getString("TARIFA");
               String ESTATUS=rs.getString("ESTATUS");
               if (ESTATUS.equals("Registro")) {
                   ESTATUS="ESTACIONADO";
               }
               String USUARIOEMISION=rs.getString("USUARIOEMISION");
               Object fila []= { ELID, TICKET, PLACAS, MARCA,ENTRADA,SALIDA,TIEMPO,DIAS,IMPORTE,TARIFA,ESTATUS,USUARIOEMISION};
               tablapagados.addRow(fila);
               contador++;
           } catch (ParseException ex) {
               Logger.getLogger(JITickets.class.getName()).log(Level.SEVERE, null, ex);
           }

        }
        rs.close();
        mysql.close();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        String seleccionado = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
         JDialog.setDefaultLookAndFeelDecorated(true);
    int response = JOptionPane.showConfirmDialog(null, "Deseas cancelar to ticket "+seleccionado+"?", "Eliminar Tiket",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.YES_OPTION) {
       SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        SimpleMySQLResult rs;
        rs = mysql.Query ("SELECT * FROM estacionados WHERE SALIDA IS NULL AND ID=\""+seleccionado+"\"");
        if (rs.next()) {
         mysql.Query ("UPDATE estacionados SET ESTATUS= \"CANCELADO\", SALIDA=NOW(),IMPORTE=0,TIEMPO=00:00:00  WHERE ID=\""+seleccionado+"\"");
   
        }else{
        mysql.Query ("UPDATE estacionados SET ESTATUS= \"CANCELADO\", MARCA=NOW(), IMPORTE=0  WHERE ID=\""+seleccionado+"\"");
        }
                mysql.close();
        jButton1.doClick();
    } else  {
      
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
 SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        SimpleMySQLResult rs;
        String seleccionado = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString(); 
        rs = mysql.Query ("SELECT * FROM estacionados WHERE ID ="+seleccionado+" AND ESTATUS=\"PAGADO\" ");         
        if(rs.next()){
       
          PrinterJob job = PrinterJob.getPrinterJob();
          PageFormat pf = job.defaultPage();  
          
            Paper paper = new Paper();  
            paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());  
            pf.setPaper(paper);  
            
            job.setPrintable( new JITickets.MiPrintable2(),pf);
            
try 
{
   
   
  
   job.print();

   
   
} 
catch (PrinterException es) 
{
   es.printStackTrace();
}
        }else{
        showMessageDialog(null, "Boleto no pagado o Cancelado");
        }  
     
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        MessageFormat header=new MessageFormat("Inicio del Reporte");
        MessageFormat footer=new MessageFormat("Fin del Reporte");
        try {
            jTable1.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (Exception e) {
        }
        
        
    }//GEN-LAST:event_jButton5ActionPerformed
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
              Logger.getLogger(JDEntradas.class.getName()).log(Level.SEVERE, null, ex);
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
                        drawStringMultiLine(g, str, 295, 10, 50);
                      } rs.close();
                      String seleccionado = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString(); 
        rs = mysql.Query ("SELECT * FROM estacionados WHERE ID ="+seleccionado+"");
        String lalaserie="";
        String palcas="";
        String marca="";
        String entrada="";
        String tarifa="";
        int siguiente=0;
        while (rs.next()){  
            siguiente = Integer.parseInt(rs.getString("TICKET"));
            lalaserie=rs.getString("SERIE"); 
            palcas=rs.getString("PLACAS"); 
       marca=rs.getString("MARCA");
        entrada=rs.getString("ENTRADA");
         tarifa=rs.getString("TARIFA");
        } rs.close();        
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
        SimpleDateFormat sdfa = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfa2 = new SimpleDateFormat("dd/MM/yyyy");
        g.setFont(new Font("Arial", Font.BOLD, 12));
        JDSalidas.linea=JDSalidas.linea+5;
        g.drawString("TICKET DE ENTRADA", 80,JDSalidas.linea);
        JDSalidas.linea=JDSalidas.linea+5;
        g.drawString("______________________________________________", 10,JDSalidas.linea);
        g.setFont(new Font("Arial", Font.BOLD, 22));
        java.text.DecimalFormat nft = new  
java.text.DecimalFormat("#000000.###");  
nft.setDecimalSeparatorAlwaysShown(false);
JDSalidas.linea=JDSalidas.linea+24;
        g.drawString("FOLIO: "+lalaserie+nft.format(siguiente), 60,JDSalidas.linea);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        JDSalidas.linea=JDSalidas.linea+20;
        g.drawString("PLACAS: "+palcas, 70,JDSalidas.linea);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        JDSalidas.linea=JDSalidas.linea+15;
        g.drawString("MARCA: "+marca, 40,JDSalidas.linea);
        JDSalidas.linea=JDSalidas.linea+15;
          try {
              g.drawString("FECHA: ", 40,JDSalidas.linea);
              g.drawString(sdfa2.format(sdfa.parse(entrada.replace(".0", ""))), 100,JDSalidas.linea);
          } catch (ParseException ex) {
              Logger.getLogger(JITickets.class.getName()).log(Level.SEVERE, null, ex);
          }
          JDSalidas.linea=JDSalidas.linea+12;
          try {
              g.drawString("HORA: ", 40,JDSalidas.linea);
               g.drawString(sdf.format(sdfa.parse(entrada.replace(".0", ""))), 100,JDSalidas.linea);
          } catch (ParseException ex) {
              Logger.getLogger(JITickets.class.getName()).log(Level.SEVERE, null, ex);
          }
          JDSalidas.linea=JDSalidas.linea+12;
        g.drawString("TARIFA: "+tarifa, 40,JDSalidas.linea);
        JDSalidas.linea=JDSalidas.linea+12;
        g.drawString("RESP. TURNO: "+JDLogin.elusuario.toUpperCase(), 40,JDSalidas.linea); 
   JDSalidas.linea=JDSalidas.linea+37;
g.setFont(new Font("Arial", Font.BOLD, 30));
g.drawString("*REIMPRESION*", 50,JDSalidas.linea);
   
     
 Image img3=null;
     
          try {
              img3 = ImageIO.read(new File("mybarcode.jpg"));
          } catch (IOException ex) {
              Logger.getLogger(JDEntradas.class.getName()).log(Level.SEVERE, null, ex);
          }
          JDSalidas.linea=JDSalidas.linea+10;
    g.drawImage(img3, 10,JDSalidas.linea,  null);
    g.setFont(new Font("Arial", Font.BOLD, 12));
    JDSalidas.linea=JDSalidas.linea+50;
        g.drawString("______________________________________________", 10,JDSalidas.linea); 
g.setFont(new Font("Arial", Font.BOLD, 8));  
          JDSalidas.linea=JDSalidas.linea+15;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        rs = mysql.Query ("SELECT * FROM opciones WHERE ID = 13");
        while (rs.next()){ String str =rs.getString("VALOR");
                        drawStringMultiLine(g, str, 295, 10, JDSalidas.linea);
                      } rs.close();
         rs = mysql.Query ("SELECT * FROM opciones WHERE ID = 17");
        while (rs.next()){ String str =rs.getString("VALOR");
                        drawStringMultiLine(g, str, 295, 10, JDSalidas.linea);
                      } rs.close();              
               
         return PAGE_EXISTS;
      }
      else
         return NO_SUCH_PAGE;
   }
    public void outputtingBarcodeAsPNG() throws BarcodeException {
        // get a Barcode from the BarcodeFactory
        java.text.DecimalFormat nft = new  
java.text.DecimalFormat("#000000.###");  
         SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        SimpleMySQLResult rs;
        String seleccionado = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString(); 
        rs = mysql.Query ("SELECT * FROM estacionados WHERE ID ="+seleccionado+"");
        String lalaserie="";
        int siguiente=0;
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

class MiPrintable2 implements Printable 
{
   public int print (Graphics g, PageFormat f, int pageIndex) 
   {
       
   
       
      if (pageIndex == 0) 
      {
         

    Image img1 = Toolkit.getDefaultToolkit().getImage("C:/Estacionamientos/LogoTicket.jpg");
    g.drawImage(img1, 0, 0, 290, 30, null);
          
          g.setFont(new Font("Arial", Font.BOLD, 8));  
          SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        SimpleMySQLResult rs;
        rs = mysql.Query ("SELECT * FROM opciones WHERE ID = 2");
        while (rs.next()){ String str =rs.getString("VALOR");
                        drawStringMultiLine(g, str, 295, 10, 50);
                      } rs.close();
                        String seleccionado = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString(); 
        rs = mysql.Query ("SELECT * FROM estacionados WHERE ID ="+seleccionado+" ");
        
        while (rs.next()){  
                     
        g.setFont(new Font("Arial", Font.BOLD, 12));
        JDSalidas.linea=JDSalidas.linea+4;
         g.drawString("Servicio Estacionamiento Nota De Consumo", 10,JDSalidas.linea);
           JDSalidas.linea=JDSalidas.linea+3;
        g.drawString("______________________________________________", 10,JDSalidas.linea);
        g.setFont(new Font("Arial", Font.BOLD, 22));
        java.text.DecimalFormat nft2 = new  
java.text.DecimalFormat("#000000.###");  
nft2.setDecimalSeparatorAlwaysShown(false); 
JDSalidas.linea=JDSalidas.linea+25;
        g.drawString("FOLIO: "+rs.getString("SERIE")+nft2.format(Integer.parseInt(rs.getString("TICKET"))), 60,JDSalidas.linea);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        JDSalidas.linea=JDSalidas.linea+20;
        g.drawString("PLACAS: "+rs.getString("PLACAS"), 70,JDSalidas.linea);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        JDSalidas.linea=JDSalidas.linea+15;
   g.drawString("MARCA: "+rs.getString("MARCA"), 40,JDSalidas.linea);
   JDSalidas.linea=JDSalidas.linea+12;
    DateFormat fold = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         DateFormat fnew = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        g.drawString("ENTRADA: ", 40,JDSalidas.linea);
        try {
            g.drawString(fnew.format(fold.parse(rs.getString("ENTRADA").replace(".0", ""))), 100,JDSalidas.linea);
        } catch (ParseException ex) {
            Logger.getLogger(JITickets.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDSalidas.linea=JDSalidas.linea+12;
        g.drawString("SALIDA  : ", 40,JDSalidas.linea);
        try {
            g.drawString(fnew.format(fold.parse(rs.getString("SALIDA").replace(".0", ""))), 100,JDSalidas.linea);
        } catch (ParseException ex) {
            Logger.getLogger(JITickets.class.getName()).log(Level.SEVERE, null, ex);
        }
        JDSalidas.linea=JDSalidas.linea+12;
        g.drawString("TARIFA: "+rs.getString("TARIFA"), 40,JDSalidas.linea);
        JDSalidas.linea=JDSalidas.linea+12;
g.drawString("RESP. TURNO: "+JDLogin.elusuario.toUpperCase(), 40,JDSalidas.linea); 
JDSalidas.linea=JDSalidas.linea+12;
        g.drawString("TIEMPO: "+rs.getString("TIEMPO"), 40,JDSalidas.linea);
        JDSalidas.linea=JDSalidas.linea+12;
        g.drawString("IMPORTE: $"+rs.getString("IMPORTE")+".00", 40,JDSalidas.linea);
        JDSalidas.linea=JDSalidas.linea+12;
        g.drawString("FORMA DE PAGO: EFECTIVO", 40,JDSalidas.linea);
        
        } 
        
        
        rs = mysql.Query ("SELECT * FROM opciones WHERE ID = 12");
        JDSalidas.linea=JDSalidas.linea+12;
        while (rs.next()){ 
                        g.drawString("LUGAR DE EXPEDICION: "+rs.getString("VALOR"), 40,JDSalidas.linea);
                      } rs.close();
        
                              
        
g.setFont(new Font("Arial", Font.BOLD, 40));

   JDSalidas.linea=JDSalidas.linea+5;

    g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("______________________________________________", 10,JDSalidas.linea); 
g.setFont(new Font("Arial", Font.BOLD, 13));  
          JDSalidas.linea=JDSalidas.linea+18;
     g.drawString("GRACIAS POR SU VISITA,VUELVA PRONTO", 20,JDSalidas.linea)  ; 
               
         return PAGE_EXISTS;
      }
      else
         return NO_SUCH_PAGE;
   }
    public void outputtingBarcodeAsPNG() throws BarcodeException {
      // get a Barcode from the BarcodeFactory
        java.text.DecimalFormat nft = new  
java.text.DecimalFormat("#000000.###");  
nft.setDecimalSeparatorAlwaysShown(false); 
 SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        SimpleMySQLResult rs;
        
        rs = mysql.Query ("SELECT * FROM estacionados ORDER BY ID DESC LIMIT 1");
        int siguiente=0;
        String lalaserie="";
        while (rs.next()){  siguiente = Integer.parseInt(rs.getString("TICKET"));
                      lalaserie=rs.getString("SERIE"); } rs.close();  
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
    lineWidth=290;
    if(m.stringWidth(text) < lineWidth) {
        g.drawString(text, x, y);
    } else {
        String[] words = text.split(" ");
        String currentLine = words[0];
        for(int i = 1; i < words.length; i++) {
            if(m.stringWidth(currentLine+words[i]) < lineWidth) {
                currentLine += " "+words[i];
            } else {
                
                for (int j = 0; j <= 290; j++) {
                 
                }
                g.drawString(justificar(m,currentLine), x, y);
                System.out.println(justificar(m,currentLine));
                y += m.getHeight();
                currentLine = words[i];
                JDSalidas.linea=y+m.getHeight();
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
        while (m.stringWidth(salida.toString())<=290) {    
          contador=0;

            for (int espacio : espacios) {
                salida.insert(espacio+contador+contador2, ' ');
                
                
                contador=contador+1+contador2;
                if (m.stringWidth(salida.toString())>=290) {
                    break;
                }
            }
            contador2++;
            
        }



return salida.toString();
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    public static javax.swing.JComboBox jComboBox2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    public static javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
