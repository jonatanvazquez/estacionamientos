
package javaapplication1;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javaapplication1.JDSalidas.linea;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import simplemysql.SimpleMySQL;
import simplemysql.SimpleMySQLResult;
import javax.swing.table.DefaultTableModel;



        
        
public class JDCortes extends javax.swing.JDialog {
    public static String series="";
DefaultTableModel tablausuarios= new DefaultTableModel();
private void msgbox(String s){ JOptionPane.showMessageDialog(null, s); } 
    public JDCortes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         this.setLocationRelativeTo(null);
         this.JTablausuarios.setModel(tablausuarios);
        this.tablausuarios.addColumn("ID");
        this.tablausuarios.addColumn("Usuario");
        this.tablausuarios.addColumn("Inicio");
        this.tablausuarios.addColumn("Final");
 
        
        SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        SimpleMySQLResult rs;
        rs = mysql.Query ("SELECT * FROM cortes");
        while (rs.next()){
                        String ID=rs.getString("ID");
                        String USUARIO=rs.getString("USUARIO");
                        String FECHAINICIO=rs.getString("FECHAINICIO");
                        String FECHAFIN=rs.getString("FECHAFIN");
                        Object fila []= { ID,USUARIO,FECHAINICIO,FECHAFIN};
                        this.tablausuarios.addRow(fila);
         }
        
        this.JTablausuarios.changeSelection(0, 0, false, false);
        rs.close(); 
        DefaultListModel listModel= new DefaultListModel();
        jList1.setModel(listModel);
        int elementos=0;
        rs = mysql.Query ("SELECT DESCRIPCION FROM tarifas");
        while (rs.next()){
            listModel.addElement(rs.getString("DESCRIPCION"));
             elementos++;          
         }
        jList1.setSelectionInterval(0, elementos);
       
        rs.close(); 
       
      jList1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent event) {
            
             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        int row = JTablausuarios.getSelectedRow();
        String entrada=JTablausuarios.getValueAt(row, 2).toString();
        String salida=JTablausuarios.getValueAt(row, 3).toString();
        Date entradad;
        Calendar myCal = new GregorianCalendar();
    try {
        entradad = formatter.parse(entrada);
        myCal.setTime(entradad);
       
        dateChooserCombo1.setCurrent(myCal);
    } catch (ParseException ex) {
        Logger.getLogger(JDCortes.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        
        }
        });
        
     
       
    }


    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jfhorae1 = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txthorafin = new javax.swing.JFormattedTextField();
        txthorainicio = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTablausuarios = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        seriea = new javax.swing.JCheckBox();
        serieb = new javax.swing.JCheckBox();

        try {
            jfhorae1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Imprimir Corte"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Dia Inicio");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Dia Final");

        try {
            txthorafin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txthorafin.setText("23:59:59");
        txthorafin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        try {
            txthorainicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txthorainicio.setText("00:00:01");
        txthorainicio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Hora Inicio");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Hora Final");

        JTablausuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        JTablausuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTablausuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTablausuarios);

        jButton1.setText("Imprimir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Excel");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar Corte", "Especificar Fechas" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jList1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setViewportView(jList1);

        jLabel5.setText("Seleccionar Tarifas");

        jCheckBox1.setText("Imprimir Solo Totales");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        seriea.setSelected(true);
        seriea.setText("A");

        serieb.setSelected(true);
        serieb.setText("B");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel2))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txthorafin, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                    .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(txthorainicio)
                                    .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seriea)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(serieb)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1)
                    .addComponent(seriea)
                    .addComponent(serieb))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(2, 2, 2)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txthorafin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txthorainicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTablausuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTablausuariosMouseClicked
      
    }//GEN-LAST:event_JTablausuariosMouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PrinterJob job = PrinterJob.getPrinterJob();
          PageFormat pf = job.defaultPage();  
          
            Paper paper = new Paper();  
            paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());  
            pf.setPaper(paper);  
            
            job.setPrintable( new JDCortes.MiPrintable(),pf);
            
try 
{
        if (seriea.isSelected()&&!serieb.isSelected()) {
              series="AND SERIE = \"A\"";
              job.print();
          }else if(!seriea.isSelected()&&serieb.isSelected()){
              series="AND SERIE = \"B\"";
              job.print();
          }else if(seriea.isSelected()&&serieb.isSelected()){
          series="AND SERIE = \"A\"";
              job.print();
               series="AND SERIE = \"B\"";
              job.print();
              
          }     
   
  
   } 
catch (PrinterException es) 
{
   es.printStackTrace();
}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

  
   public static void main(String args[]) {
       
       
      
        
        
        
        
        
        
    }
   
   class MiPrintable implements Printable 
{
       public int lineamax=999999999;
       public int lineamax1=999999999;
          int avance=0;
   String pagados="";
   String toleranciasc="";
        String tpagados="";
        String pagados2="";
        String tpagados2="";
        String pagados3="";
        String tpagados3="";
                 String inicial="";
        String terminal="";
        String folios="";
        int lapagina=1;
   public int print (Graphics g, PageFormat f, int pageIndex) 
   {



         java.text.DecimalFormat nft = new  
  java.text.DecimalFormat("#000000.###"); 
      if (pageIndex == 0) 
      {
          
 
         
    Image img1 = Toolkit.getDefaultToolkit().getImage("C:/Estacionamientos/LogoTicket.jpg");
    g.drawImage(img1, 0, 0, 290, 30, null);
          
          g.setFont(new Font("Arial", Font.BOLD, 12));  

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yy");
        if (jComboBox1.getSelectedItem().toString().equals("Seleccionar Corte")){
        int row = JTablausuarios.getSelectedRow();
        inicial=JTablausuarios.getValueAt(row, 2).toString();
        terminal=JTablausuarios.getValueAt(row, 3).toString();
        } else{
           String start="";
            String ending="";
        try {
             start = dateFormat.format(dateFormat2.parse(dateChooserCombo1.getText()));
            ending = dateFormat.format(dateFormat2.parse(dateChooserCombo2.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(JDCortes.class.getName()).log(Level.SEVERE, null, ex);
        }
        inicial=start+" "+txthorainicio.getText();
        terminal=ending+" "+txthorafin.getText();
            
        
        }
        
          SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        SimpleMySQLResult rs;
         SimpleMySQLResult rs2;
         SimpleMySQLResult rs3;
        rs = mysql.Query ("SELECT * FROM opciones WHERE ID = 2");
        while (rs.next()){ String str =rs.getString("VALOR");
                        drawStringMultiLine(g, str, 295, 10, 50);
                      } rs.close();
        
                
                      
        rs = mysql.Query ("SELECT * FROM cortes WHERE FECHAINICIO = \""+inicial+"\" AND FECHAFIN = \""+terminal+"\" ");
         g.setFont(new Font("Arial", Font.BOLD, 12));
        linea=linea+4;
        g.drawString("CORTE DE CAJA", 80,linea);
        while (rs.next()){ 
        g.drawString(rs.getString("ID"), 180,linea);
                       
                      } rs.close();
        g.drawString("______________________________________________", 10,++linea);
        rs = mysql.Query ("SELECT * FROM estacionados WHERE ENTRADA >= \""+inicial+"\" AND ENTRADA <= \""+terminal+"\" "+series+" ORDER BY ID DESC ");
        g.setFont(new Font("Arial", Font.BOLD, 13));
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                linea=linea+20;
         g.setFont(new Font("Arial", Font.BOLD, 12));
 g.drawString("IMPRESION DE REPORTE: "+df.format(new Date()), 10,linea);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        linea=linea+20;
            DateFormat fold = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         DateFormat fnew = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
          try {
              g.drawString("DEL: "+fnew.format(fold.parse(inicial.replace(".0", ""))), 20,linea);
          } catch (ParseException ex) {
              Logger.getLogger(JDCortes.class.getName()).log(Level.SEVERE, null, ex);
          }
        linea=linea+13;
          try {
              g.drawString("AL: "+fnew.format(fold.parse(terminal.replace(".0", ""))), 30,linea);
          } catch (ParseException ex) {
              Logger.getLogger(JDCortes.class.getName()).log(Level.SEVERE, null, ex);
          }
        linea=linea+4;

 
nft.setDecimalSeparatorAlwaysShown(false); 
        linea=linea+15;
 rs = mysql.Query ("SELECT * FROM estacionados WHERE ENTRADA >= \""+inicial+"\" AND ENTRADA <= \""+terminal+"\" "+series+"  ORDER BY ID ASC LIMIT 1 ");
  
          if (rs.next()) {
              rs = mysql.Query ("SELECT * FROM estacionados WHERE ENTRADA >= \""+inicial+"\" AND ENTRADA <= \""+terminal+"\" "+series+"  ORDER BY ID ASC LIMIT 1 ");
         while (rs.next()){  
       g.drawString("F. INICIAL: ", 10,linea); 
       g.drawString(rs.getString("SERIE")+nft.format(Integer.parseInt(rs.getString("TICKET"))), 90,linea); 

        }
          }else{
          rs = mysql.Query ("SELECT * FROM estacionados WHERE \"x\"=\"x\"  "+series+"  ORDER BY ID DESC LIMIT 1 ");
           while (rs.next()){  
       g.drawString("F. INICIAL: ", 10,linea); 
       g.drawString(rs.getString("SERIE")+nft.format(Integer.parseInt(rs.getString("TICKET"))), 90,linea); 

        }
          }
      
 rs = mysql.Query ("SELECT * FROM estacionados WHERE ENTRADA >= \""+inicial+"\" AND ENTRADA <= \""+terminal+"\"  "+series+"  ORDER BY ID DESC LIMIT 1 ");
   if (rs.next()) {
       rs = mysql.Query ("SELECT * FROM estacionados WHERE ENTRADA >= \""+inicial+"\" AND ENTRADA <= \""+terminal+"\"  "+series+"  ORDER BY ID DESC LIMIT 1 ");
         while (rs.next()){  
       g.drawString("F. FINAL: ", 160,linea); 
       g.drawString(rs.getString("SERIE")+nft.format(Integer.parseInt(rs.getString("TICKET"))), 240,linea); 
        }
          }else{
          rs = mysql.Query ("SELECT * FROM estacionados WHERE \"x\"=\"x\"  "+series+"  ORDER BY ID DESC LIMIT 1 ");
           while (rs.next()){  
       g.drawString("F. FINAL: ", 160,linea); 
       g.drawString(rs.getString("SERIE")+nft.format(Integer.parseInt(rs.getString("TICKET"))), 240,linea); 
        }
          }



         rs = mysql.Query ("SELECT * FROM estacionados WHERE ENTRADA >= \""+inicial+"\" AND ENTRADA <= \""+terminal+"\"  "+series+"  ORDER BY ID DESC");
        int contando=0;
        int total=0;
        int contando2=0;
        int total2=0;
        int tolerancias=0;
        while (rs.next()){ 
        if(Integer.parseInt(rs.getString("IMPORTE"))==0){
        tolerancias++;
        }else{
        contando++;
        }  
        total=total+Integer.parseInt(rs.getString("IMPORTE"));
        } rs.close();  
         if (series.equals("AND SERIE = \"B\"")) {
         rs = mysql.Query ("SELECT * FROM estacionados WHERE ENTRADA >= \""+inicial+"\" AND ENTRADA <= \""+terminal+"\"  AND SERIE = \"A\" ORDER BY ID DESC ");
        while (rs.next()){  
        contando2++;
        total2=total2+Integer.parseInt(rs.getString("IMPORTE"));
        } rs.close(); 
         
        
         }

        pagados=  Integer.toString(contando);
        tpagados=Integer.toString(total);
        toleranciasc=Integer.toString(tolerancias);
        folios=Integer.toString(tolerancias+contando);
        pagados2=  Integer.toString(contando2);
        tpagados2=Integer.toString(total2);
        pagados3=  Integer.toString(contando2+contando);
        tpagados3=Integer.toString(total2+total);
        linea=linea+20;
        g.drawString("TOTAL FOLIOS: ", 10,linea);
                linea=linea+20;
         g.drawString("______________________________________________", 10,linea);
        g.drawString(folios, 210,linea);
        linea=linea+15;
        g.drawString("TOLERANCIAS: ", 10,linea);
        g.drawString(toleranciasc, 210,linea);
        linea=linea+15;
        g.drawString("PAGADOS: ", 10,linea);
        g.drawString(Integer.toString(contando), 210,linea);
        g.drawString("$"+Integer.toString(total), 240,linea);
        rs = mysql.Query ("SELECT * FROM estacionados WHERE ENTRADA >= \""+inicial+"\" AND ENTRADA <= \""+terminal+"\" AND ESTATUS =\"CANCELADO\" "+series+"  ORDER BY ID DESC ");
        contando=0;
        total=0;
        while (rs.next()){  
        contando++;
        } rs.close();  
        linea=linea+15;
        g.drawString("CANCELADOS: ", 10,linea);
        g.drawString(Integer.toString(contando), 210,linea);

        
        rs = mysql.Query ("SELECT DISTINCT TARIFA FROM estacionados WHERE ENTRADA >= \""+inicial+"\" AND ENTRADA <= \""+terminal+"\"  "+series+"  ORDER BY ID DESC ");
linea=linea+15;
        
         while (rs.next()){  
             
             rs3 = mysql.Query ("SELECT * FROM tarifas WHERE DESCRIPCION = \""+rs.getString("TARIFA")+"\" ");
             while (rs3.next()){
                 
        g.drawString("T"+rs3.getString("ID")+"  "+rs.getString("TARIFA")+":", 30,linea);
             }
        
        rs2 = mysql.Query ("SELECT * FROM estacionados WHERE ENTRADA >= \""+inicial+"\" AND ENTRADA <= \""+terminal+"\" AND TARIFA=\""+rs.getString("TARIFA")+"\"  "+series+"  ORDER BY ID DESC ");
        contando=0;
        total=0;
        while (rs2.next()){  
        contando++;
        
        total=total+Integer.parseInt(rs2.getString("IMPORTE"));
        } rs2.close(); 
        g.drawString(Integer.toString(contando), 210,linea);
        g.drawString("$"+Integer.toString(total), 240,linea);
        linea=linea+15;
        } rs.close();  
        
         rs = mysql.Query ("SELECT * FROM estacionados WHERE ENTRADA >= \""+inicial+"\" AND ENTRADA <= \""+terminal+"\" AND SALIDA IS NULL "+series+"  ORDER BY ID DESC ");
        g.drawString("ESTACIONADOS: "+rs.getNumRows(), 10,linea);
        g.drawString("PENSIONES: 0", 140,linea);
        rs2 = mysql.Query ("SELECT * FROM estacionados WHERE ENTRADA >= \""+inicial+"\" AND ENTRADA <= \""+terminal+"\" AND ESTATUS =\"BOLETOPERDIDO\" "+series+"  ORDER BY ID DESC ");
      linea=linea+15;
         g.drawString("PERDIDOS: "+rs2.getNumRows(), 10,linea);
        linea=linea+15;
         g.drawString("RESP.DE TURNO: "+JDLogin.elusuario, 10,linea);
         linea=linea+15;
        

    g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("______________________________________________", 10,linea); 
g.setFont(new Font("Arial", Font.BOLD, 12));

 
nft.setDecimalSeparatorAlwaysShown(false); 

  rs = mysql.Query ("SELECT * FROM estacionados WHERE ENTRADA >= \""+inicial+"\" AND ENTRADA <= \""+terminal+"\" "+series+"  ORDER BY ID DESC ");
   
linea=linea+15;
if(jCheckBox1.isSelected()){}else{
avance=1;

        
}
         
      }else{
          
     linea=15;
            if(pageIndex==0){
                return PAGE_EXISTS;
       }
      }
                 SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        SimpleMySQLResult rs;
         SimpleMySQLResult rs2;
         SimpleMySQLResult rs3;
      if(avance==1){
           
          
            rs = mysql.Query ("SELECT * FROM estacionados WHERE ENTRADA >= \""+inicial+"\" AND ENTRADA <= \""+terminal+"\" "+series+" AND ID < "+lineamax+" ORDER BY ID DESC ");
            g.setFont(new Font("Arial", Font.BOLD, 12));
  while (rs.next()){  
      if(pageIndex!=0){
      rs3 = mysql.Query ("SELECT * FROM tarifas WHERE DESCRIPCION = \""+rs.getString("TARIFA")+"\" ");
             while (rs3.next()){
                 if (rs.getString("ESTATUS").equals("CANCELADO")) {
                     g.drawString("CA:", 10,linea);
                 }else{
        g.drawString("T"+rs3.getString("ID")+":", 10,linea);
                 }
             }
g.drawString(rs.getString("SERIE")+nft.format(Integer.parseInt(rs.getString("TICKET"))) , 30,linea);
      if (rs.getString("ESTATUS").equals("BOLETOPERDIDO")) {
       g.drawString("BP" , 100,linea);   
      }
g.drawString(rs.getString("PLACAS") , 120,linea);
g.drawString(rs.getString("TIEMPO") , 180,linea);
g.drawString("$"+rs.getString("IMPORTE")+".00" , 240,linea);
      }
linea=linea+15;
if(linea>=672){


       if(pageIndex>lapagina){
            lineamax=Integer.parseInt(rs.getString("ID"));
           lapagina=pageIndex;
       }
   
    System.out.println("_____________________________");
    System.out.println("Terminó en el TICKET: ");
    System.out.println(rs.getString("TICKET"));
    System.out.println(pageIndex);
    System.out.println("_____________________________");
    return PAGE_EXISTS;
}
        }    
        
g.setFont(new Font("Arial", Font.BOLD, 12));
g.drawString("TOTAL PAGADOS:                   "+pagados , 10,linea);
g.drawString("$"+tpagados+".00" , 240,linea);
linea=linea+20;
g.drawString("----ESTACIONADOS-----", 10,linea);
linea=linea+15;
 rs = mysql.Query ("SELECT * FROM estacionados WHERE ENTRADA >= \""+inicial+"\" AND ENTRADA <= \""+terminal+"\" AND SALIDA IS NULL "+series+"  ORDER BY ID DESC ");
 while (rs.next()){  
g.drawString(rs.getString("SERIE")+nft.format(Integer.parseInt(rs.getString("TICKET"))) , 10,linea);
g.drawString(rs.getString("PLACAS") , 80,linea);
g.drawString(rs.getString("ENTRADA").replace("-", "/") , 140,linea);
linea=linea+15;
        } 
 g.drawString("TOTAL ESTACIONADOS:                   "+rs.getNumRows() , 10,linea);
          
 
    if (series=="AND SERIE = \"B\"") {
        linea=linea+15;
        g.drawString("TOTAL PAGADOS A:         "+pagados2+"          $"+tpagados2 , 10,linea);
        linea=linea+15;
        g.drawString("TOTAL PAGADOS B:         "+pagados+"          $"+tpagados, 10,linea);
        linea=linea+15;
        g.drawString("TOTAL PAGADOS:           "+pagados3+"          $"+tpagados3, 10,linea);
    } rs.close();
                avance=0;
                 return PAGE_EXISTS;
         
      }else if(avance==0){
          avance=3;
          return PAGE_EXISTS;
       
      }else{
      return NO_SUCH_PAGE;
      }
   }
   
}
public static void drawStringMultiLine(Graphics g, String text, int lineWidth, int x, int y) {
    FontMetrics m = g.getFontMetrics();
    if(m.stringWidth(text) < lineWidth) {
        g.drawString(text, x, y);
    } else {
        String[] words = text.split(" ");
        String currentLine = words[0];
        for(int i = 1; i < words.length; i++) {
            if(m.stringWidth(currentLine+words[i]) < lineWidth) {
                currentLine += " "+words[i];
            } else {
                g.drawString(currentLine, x, y);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTablausuarios;
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JFormattedTextField jfhorae1;
    private javax.swing.JCheckBox seriea;
    private javax.swing.JCheckBox serieb;
    public static javax.swing.JFormattedTextField txthorafin;
    public static javax.swing.JFormattedTextField txthorainicio;
    // End of variables declaration//GEN-END:variables
}
