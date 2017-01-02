/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import simplemysql.SimpleMySQL;
import simplemysql.SimpleMySQLResult;

/**
 *
 * @author Jonatan
 */

public class NewJInternalFrame extends javax.swing.JInternalFrame {
public void algo(){}
DefaultTableModel tablaestacionados= new DefaultTableModel(){

        
            public Class getColumnClass(int column) {
                if(column==0){
                return Icon.class;
                }else{
                return String.class;
                }
            }
            
             @Override
    public boolean isCellEditable(int row, int column) {
       //all cells false
       return false;
    }
        };
DefaultTableModel tablapagados= new DefaultTableModel(){

      
             public Class getColumnClass(int column) {
                if(column==0){
                return Icon.class;
                }else{
                return String.class;
                }
            }
             
              @Override
    public boolean isCellEditable(int row, int column) {
       //all cells false
       return false;
    }
        };

    /**
     * Creates new form NewJInternalFrame
     */
    public NewJInternalFrame() {
        
         DateFormat fold = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         DateFormat fnew = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        
        initComponents();
        ImageIcon imagen= new ImageIcon("Entrada1.gif");
        ImageIcon imagen2= new ImageIcon("Salida2.gif");
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//              jlbienvenido.setText("Bienvenido "+JDLogin.elusuario);
      tblcobrados.setModel(tablaestacionados);
      this.tablaestacionados.addColumn("Simbolo");
        this.tablaestacionados.addColumn("Ticket");
        this.tablaestacionados.addColumn("Placas");

        this.tablaestacionados.addColumn("Marca");
        this.tablaestacionados.addColumn("Entrada");
        this.tablaestacionados.addColumn("Tarifa");
        this.tablaestacionados.addColumn("Emision");
          
         this.tblestacionados.setModel(tablapagados);
      this.tablapagados.addColumn("Simbolo");
        this.tablapagados.addColumn("Ticket");
        this.tablapagados.addColumn("Placas");
        this.tablapagados.addColumn("Estatus");
        this.tablapagados.addColumn("Marca");
        this.tablapagados.addColumn("Entrada");
        this.tablapagados.addColumn("Salida");
        this.tablapagados.addColumn("Tarifa");
        this.tablapagados.addColumn("Tiempo");
        this.tablapagados.addColumn("Dias");
        this.tablapagados.addColumn("Importe");
        
        
        this.tablapagados.addColumn("Emision");
       new Timer(1000, new ActionListener() {
            
        @Override
        public void actionPerformed(ActionEvent e) {
            tablaestacionados.setNumRows(0); 
            tablapagados.setNumRows(0); 
      SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        SimpleMySQLResult rs=null;
        rs = mysql.Query ("SELECT * FROM estacionados WHERE ESTATUS=\"Registro\" AND USUARIOCORTE IS NULL");
        String cuantos=Integer.toString(rs.getNumRows());
        rs = mysql.Query ("SELECT * FROM estacionados WHERE ESTATUS=\"CANCELADO\" AND USUARIOCORTE IS NULL");
        String cuantos2=Integer.toString(rs.getNumRows());
        rs = mysql.Query ("SELECT * FROM estacionados WHERE (ESTATUS=\"PAGADO\" OR ESTATUS=\"BOLETOPERDIDO\") AND USUARIOCORTE IS NULL");
        String cuantos3=Integer.toString(rs.getNumRows());
        int lalana=0;
        while (rs.next()){
        lalana=lalana+Integer.parseInt(rs.getString("IMPORTE"));
        }
        rs = mysql.Query ("SELECT VALOR FROM opciones WHERE ID=\"10\"");
        String cajones=rs.getString("VALOR");
        String cuantos4=Integer.toString(Integer.parseInt(cuantos2)+Integer.parseInt(cuantos3));
        String cajones2=Integer.toString(Integer.parseInt(cajones)-Integer.parseInt(cuantos));
        if(jTextField2.getText().equals("")){
        rs = mysql.Query ("SELECT * FROM estacionados WHERE ESTATUS=\"Registro\"");
        }else{
        
        if (jComboBox2.getSelectedItem().toString()=="Placas") {
                rs = mysql.Query ("SELECT * FROM estacionados WHERE ESTATUS=\"Registro\" AND PLACAS LIKE \"%"+jTextField2.getText()+"%\"");
            }else{
                rs = mysql.Query ("SELECT * FROM estacionados WHERE ESTATUS=\"Registro\" AND (TICKET LIKE \"%"+jTextField2.getText().replaceAll("[^\\d.]", "").replaceAll("0", "")+"%\" AND SERIE LIKE \"%"+jTextField2.getText().replaceAll("[0-9]", "")+"%\")");
            }
        
        }
        
        
            
        
        java.text.DecimalFormat nft = new  
java.text.DecimalFormat("#000000.###");  
nft.setDecimalSeparatorAlwaysShown(false); 
		int contador=0;
           
                
   
        while (rs.next()){
                        
                try {
                    String ELID= String.valueOf(contador);
                    String TICKET=rs.getString("SERIE")+nft.format(Integer.parseInt(rs.getString("TICKET")));
                    String PLACAS=rs.getString("PLACAS");
                    String MARCA=rs.getString("MARCA");
                    
                    String ENTRADA=fnew.format(fold.parse(rs.getString("ENTRADA").replace(".0", "")))  ;
                  
                    String TARIFA=rs.getString("TARIFA");
                    String USUARIOEMISION=rs.getString("USUARIOEMISION");
                    Object fila []= {imagen, TICKET, PLACAS, MARCA,ENTRADA,TARIFA,USUARIOEMISION};
                    tablaestacionados.addRow(fila);
                    contador++;
                } catch (ParseException ex) {
                    Logger.getLogger(NewJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

        }
        jLabel6.setText(cuantos);
        jLabel11.setText(cuantos2);
        jLabel9.setText(cuantos3);
        jLabel13.setText(cuantos4);
        jLabel16.setText(cajones2);
        jLabel17.setText("$"+Integer.toString(lalana));

if(jTextField1.getText().equals("")){
       rs = mysql.Query ("SELECT * FROM estacionados WHERE USUARIOCORTE IS NULL  AND ESTATUS!=\"Registro\"");
        }else{
        
        if (jComboBox1.getSelectedItem().toString()=="Placas") {
                rs = mysql.Query ("SELECT * FROM estacionados WHERE USUARIOCORTE IS NULL AND ESTATUS!=\"Registro\" AND PLACAS LIKE \"%"+jTextField1.getText()+"%\"");
            }else{
                rs = mysql.Query ("SELECT * FROM estacionados WHERE USUARIOCORTE IS NULL AND ESTATUS!=\"Registro\" AND (TICKET LIKE \"%"+jTextField1.getText().replaceAll("[^\\d.]", "").replaceAll("0", "")+"%\" AND SERIE LIKE \"%"+jTextField1.getText().replaceAll("[0-9]", "")+"%\")");
            }
        
        }
         
        
		contador=1;
        while (rs.next()){
                        String ELID= String.valueOf(contador);
                        String TICKET=rs.getString("SERIE")+nft.format(Integer.parseInt(rs.getString("TICKET")));
                        String PLACAS=rs.getString("PLACAS");
                        String MARCA=rs.getString("MARCA");
                        String ENTRADA = null;
                try {
                    ENTRADA = fnew.format(fold.parse(rs.getString("ENTRADA").replace(".0", "")));
                } catch (ParseException ex) {
                    Logger.getLogger(NewJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                        String SALIDA = null;
                        if (rs.getString("SALIDA")!=null) {
                            try {
                                SALIDA=fnew.format(fold.parse(rs.getString("SALIDA").replace(".0", "")))  ;
                            } catch (ParseException ex) {
                                Logger.getLogger(NewJInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
            }else{
                        SALIDA=rs.getString("SALIDA");
                        }
                        
                        String TIEMPO=rs.getString("TIEMPO");
                        String DIAS=rs.getString("DIAS");
                        String IMPORTE=rs.getString("IMPORTE");
                        String TARIFA=rs.getString("TARIFA");
                        String ESTATUS=rs.getString("ESTATUS");
                        String USUARIOEMISION=rs.getString("USUARIOEMISION");
                        Object fila []= { imagen2, TICKET, PLACAS,ESTATUS, MARCA,ENTRADA,SALIDA,TARIFA,TIEMPO,DIAS,IMPORTE,USUARIOEMISION};
                        tablapagados.addRow(fila);
                        contador++;

        }
        rs.close();
        mysql.close();
        
        
         
        }
    }).start();
        
        tblestacionados.getColumnModel().getColumn(1).setPreferredWidth(60);
         tblestacionados.getColumnModel().getColumn(3).setPreferredWidth(140);
        tblestacionados.getColumnModel().getColumn(4).setPreferredWidth(150);
        tblestacionados.getColumnModel().getColumn(5).setPreferredWidth(150);
        tblestacionados.getColumnModel().getColumn(6).setPreferredWidth(150);
        tblestacionados.getColumnModel().getColumn(11).setPreferredWidth(170);
        tblestacionados.setRowHeight(30);
        tblestacionados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        
        
        
      tblcobrados.getColumnModel().getColumn(0).setPreferredWidth(60);
      tblcobrados.getColumnModel().getColumn(4).setPreferredWidth(150);
      tblcobrados.getColumnModel().getColumn(6).setPreferredWidth(160);
        tblcobrados.setRowHeight(30);
                tblcobrados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblcobrados = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblestacionados = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Estacionados");

        jLabel14.setText("Corte:");

        setBorder(javax.swing.BorderFactory.createTitledBorder("Resummen"));
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jSplitPane2.setDividerLocation(400);

        jPanel1.setPreferredSize(new java.awt.Dimension(223, 447));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Estacionados");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Placas", "Ticket" }));

        tblcobrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblcobrados.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        tblcobrados.setDoubleBuffered(true);
        tblcobrados.setDragEnabled(true);
        tblcobrados.setEditingColumn(0);
        tblcobrados.setEditingRow(0);
        jScrollPane3.setViewportView(tblcobrados);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel4.setText("Total Estacionados:");

        jLabel5.setText("Pensiones:");

        jLabel7.setText("0");

        jLabel6.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 153, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)))
        );

        jSplitPane2.setLeftComponent(jPanel1);

        tblestacionados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        tblestacionados.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        tblestacionados.setDoubleBuffered(true);
        tblestacionados.setDragEnabled(true);
        jScrollPane2.setViewportView(tblestacionados);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Salidas");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Placas", "Ticket" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel8.setText("Total Pagados:");

        jLabel9.setText("0");

        jLabel10.setText("Cancelados:");

        jLabel11.setText("0");

        jLabel13.setText("0");

        jLabel12.setText("Corte:");

        jLabel15.setText("Cajones Disponibles:");

        jLabel16.setText("0");

        jLabel17.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jLabel9)
                        .addComponent(jLabel17)
                        .addComponent(jLabel10)
                        .addComponent(jLabel11))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel13)
                        .addComponent(jLabel15)
                        .addComponent(jLabel16))))
        );

        jSplitPane2.setRightComponent(jPanel2);
        jPanel2.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable tblcobrados;
    private javax.swing.JTable tblestacionados;
    // End of variables declaration//GEN-END:variables

public Class getColumnClass(int Column){
    if(Column==0){
    return Icon.class;
    }
    return null;
}

}
