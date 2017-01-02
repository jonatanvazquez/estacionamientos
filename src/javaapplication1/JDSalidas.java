/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.awt.AWTException;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Robot;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javaapplication1.JDEntradas.drawStringMultiLine;
import static javaapplication1.JDEntradas.siguiente;

import javax.swing.JDialog;
import static javax.swing.JOptionPane.showMessageDialog;
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
public class JDSalidas extends javax.swing.JDialog {
public int siguiente = JDEntradas.siguiente;
public String estado="PAGADO";
public static  int linea=0;
    public JDSalidas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
         
        txtFolio.requestFocusInWindow();
       txtFolio.addKeyListener(new KeyListenerCustom(this));
       txtpago.addKeyListener(new KeyListenerCustom(this));
       JCTicket1.setFocusable(false);
       JCTicket2.setFocusable(false);
       JBF9.setFocusable(false);
       JBF10.setFocusable(false);
       JBF11.setFocusable(false);
       JBF12.setFocusable(false);
       JBF13.setFocusable(false);
       JBF14.setFocusable(false);
       JBF15.setFocusable(false);
       JBF16.setFocusable(false);
       JCTarifa1.setFocusable(false);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdfa = new SimpleDateFormat("dd/MM/yyyy");
        
        
        new Timer(1000, new ActionListener() {
            
        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar cal = Calendar.getInstance();
    	cal.getTime();
            txtHorasalida.setText(sdf.format(cal.getTime()));
        txtFechasalida.setText(sdfa.format(cal.getTime()));
        
        SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        SimpleMySQLResult rs;
            rs = mysql.Query ("SELECT * FROM opciones");
        String opciones[]=new String[25];
        int i=0;
        while (rs.next()){ 
            opciones[i] =rs.getString("OPCION"); 
            opciones[i] =rs.getString("VALOR"); 
        i++;
        } 
       
       int cajones=Integer.parseInt(opciones[9]);
       
        rs = mysql.Query ("SELECT * FROM estacionados WHERE SALIDA IS NULL ORDER BY ID DESC ");
    	
       cajones=cajones- rs.getNumRows();
       jLabel14.setText(Integer.toString(rs.getNumRows()));
       jLabel15.setText(Integer.toString(cajones));
        }
    }).start();
        
         SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
        SimpleMySQLResult rs;
        rs = mysql.Query ("SELECT * FROM tarifas");
        while (rs.next()){

                        String USUARIO=rs.getString("DESCRIPCION");
                        JCTarifa1.addItem(USUARIO);
                  
         }
        
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
       jLabel14.setText(Integer.toString(rs.getNumRows()));
       jLabel15.setText(Integer.toString(cajones));
        
        
        
        JCTarifa1.addItemListener(new ItemListener() {
     @Override
     public void itemStateChanged(ItemEvent e) {
          SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
       SimpleMySQLResult rs;
       Date now = new Date();
 
int mismodia=0;
String hapertura="";
String hcierre="";
      rs = mysql.Query ("SELECT * FROM opciones");
      while (rs.next()){ 
          if(rs.getString("ID").equals("18")){
        mismodia=Integer.parseInt(rs.getString("VALOR"));
                }
          if(rs.getString("ID").equals("15")){
       hapertura=rs.getString("VALOR");
                }
          if(rs.getString("ID").equals("16")){
        hcierre=rs.getString("VALOR");
                }
      }
       rs = mysql.Query ("SELECT *,NOW() AS ahora FROM estacionados WHERE TICKET= \""+txtFolio.getText().substring(1,7)+"\" AND SERIE = \""+txtFolio.getText().substring(0,1)+"\" ORDER BY ID DESC LIMIT 1");
                
        String fechaentrada="yyyy-MM-dd";
String horaentrada="00:00:00";
 String fechasalida="yyyy-MM-dd";
String horasalida="00:00:00";
  

        while (rs.next()){ 
            

            SimpleDateFormat sistema = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd"); 
            SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss"); 
            String tentrada= rs.getString("ENTRADA");
            String tsalida= rs.getString("ahora");

                  try {
                      fechaentrada= fecha.format(sistema.parse(tentrada));
                      horaentrada= hora.format(sistema.parse(tentrada));
                      
                      fechasalida= fecha.format(sistema.parse(tsalida));
                      horasalida= hora.format(sistema.parse(tsalida));
                  } catch (ParseException ex) {
                      Logger.getLogger(JDSalidas.class.getName()).log(Level.SEVERE, null, ex);
                  }
              

Date d1f = null;
Date d1h = null;
Date d2f = null;
Date d2h = null;

String time= null;
            try {
			d1f = fecha.parse(fechaentrada);
			d2f = fecha.parse(fechasalida);
                        
                        d1h = hora.parse(horaentrada);
			d2h = hora.parse(horasalida);
 
			//in milliseconds
			long diff = d2f.getTime() - d1f.getTime();
                        
                        long diff2 = d2h.getTime() - d1h.getTime();
 
			long diferenciadias = diff / (24 * 60 * 60 * 1000);
                        long diffMinutes = diff2 / (60 * 1000) % 60;
			long diffHours = diff2 / (60 * 60 * 1000) % 24;
                        long diffSeconds = diff2 / 1000 % 60;
                        
                        String diferenciatiempo = String.format("%02d:%02d:%02d", Math.abs(diffHours), Math.abs(diffMinutes), Math.abs(diffSeconds));
                        
txttiempo1.setText(Long.toString(diferenciadias)); 
     txttiempo.setText(diferenciatiempo); 
		} catch (Exception esa) {
			esa.printStackTrace();
		}
            
         
            
            
            
            txtpago.requestFocusInWindow();
            txtpago.selectAll();

             rs = mysql.Query ("SELECT * FROM tarifas WHERE DESCRIPCION= \""+JCTarifa1.getSelectedItem()+"\"");
            System.out.println(JCTarifa1.getSelectedItem());
             
        while (rs.next()){ 
            
            if (mismodia==0) {
                if(txttiempo1.getText().equals("0"))
                {}else{
                txtimporte.setText(Integer.toString(Integer.parseInt(txttiempo1.getText())*80));
                try {
                        d1h = hora.parse(hapertura);
			d2h = hora.parse(horasalida);
                       
                        long diff2 = d2h.getTime() - d1h.getTime();
                        long diffMinutes = diff2 / (60 * 1000) % 60;
			long diffHours = diff2 / (60 * 60 * 1000) % 24;
                        long diffSeconds = diff2 / 1000 % 60;
                     
                        String diferenciatiempo = String.format("%02d:%02d:%02d", Math.abs(diffHours), Math.abs(diffMinutes), Math.abs(diffSeconds));
                        txttiempo.setText(diferenciatiempo); 
		} catch (Exception esa) {
			esa.printStackTrace();
		}
                }
           }else{
            if(txttiempo1.getText().equals("0"))
                {
                    if (horaentrada.compareTo(hapertura)>=0) {
                        
                    }else{
                    txtimporte.setText("80");
                    }
                
                }else{
            txtimporte.setText(Integer.toString((Integer.parseInt(txttiempo1.getText())+1)*80));
                try {
                        d1h = hora.parse(hapertura);
			d2h = hora.parse(horasalida);
                       
                        long diff2 = d2h.getTime() - d1h.getTime();
                        long diffMinutes = diff2 / (60 * 1000) % 60;
			long diffHours = diff2 / (60 * 60 * 1000) % 24;
                        long diffSeconds = diff2 / 1000 % 60;
                     
                        String diferenciatiempo = String.format("%02d:%02d:%02d", Math.abs(diffHours), Math.abs(diffMinutes), Math.abs(diffSeconds));
                        txttiempo.setText(diferenciatiempo); 
		} catch (Exception esa) {
			esa.printStackTrace();
		}
            }
            }
            txtimporte.setText("0");
            
            for (int i = 1; i <= 48; i++) {
                
             if (txttiempo.getText().compareTo(rs.getString("tiempoe"+i))>=0 && txttiempo.getText().compareTo(rs.getString("tiempos"+i))<0 ) {
        
                 txtimporte.setText(Integer.toString(Integer.parseInt(rs.getString("tiempot"+i))+Integer.parseInt(txtimporte.getText())));
             System.out.println(rs.getString("tiempot"+i));
             }
     }
        }
     
        }
     }
 });
      rs.close(); 
      
      
      JCTicket2.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
          
          SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
          SimpleMySQLResult rs;
         rs = mysql.Query ("SELECT * FROM opciones WHERE ID=11");
           int boletop=0;
        while (rs.next()){ 
           boletop=Integer.parseInt(rs.getString("VALOR"));
                
             
        } 
          
          
          if (JCTicket2.isSelected()) {
              txtimporte.setText(Integer.toString(Integer.parseInt(txtimporte.getText())+boletop));
              estado="BOLETOPERDIDO";
              
          }else{
              txtimporte.setText(Integer.toString(Integer.parseInt(txtimporte.getText())-boletop));
              estado="PAGADO";
          }
      }
    });
      txtFechaentradas.setEditable(false);
      txtFechasalida.setEditable(false);
      txtHoraentradas.setEditable(false);
      txtHorasalida.setEditable(false);
      txtimporte.setEditable(false);
      txtMarca.setEditable(false);
      txttiempo.setEditable(false);
      txtcambio.setEditable(false);
      txtPlacas.setEditable(false);
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
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
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtFolio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        JCTarifa1 = new javax.swing.JComboBox();
        JBF9 = new javax.swing.JButton();
        JBF10 = new javax.swing.JButton();
        JBF11 = new javax.swing.JButton();
        JBF12 = new javax.swing.JButton();
        JBF13 = new javax.swing.JButton();
        JBF14 = new javax.swing.JButton();
        JBF15 = new javax.swing.JButton();
        JBF16 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtHoraentradas = new javax.swing.JTextField();
        txtFechaentradas = new javax.swing.JTextField();
        txtHorasalida = new javax.swing.JTextField();
        txtFechasalida = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        txttiempo = new javax.swing.JTextField();
        txtpago = new javax.swing.JTextField();
        txtPlacas = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtimporte = new javax.swing.JTextField();
        txtcambio = new javax.swing.JTextField();
        JBGrabar1 = new javax.swing.JButton();
        JCTicket1 = new javax.swing.JCheckBox();
        JCTicket2 = new javax.swing.JCheckBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txttiempo1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();

        jButton1.setText("Salidas");

        jButton2.setText("Entradas");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Tarifa");

        JCTarifa.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        JCTarifa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        JCTicket.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JCTicket.setText("Doble Ticket");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton3.setText("Salidas");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Entradas");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Folio");

        txtFolio.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtFolio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFolioActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Tarifa");

        JCTarifa1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        JCTarifa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCTarifa1ActionPerformed(evt);
            }
        });

        JBF9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JBF9.setText("F1");
        JBF9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBF9ActionPerformed(evt);
            }
        });

        JBF10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JBF10.setText("F2");
        JBF10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBF10ActionPerformed(evt);
            }
        });

        JBF11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JBF11.setText("F3");
        JBF11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBF11ActionPerformed(evt);
            }
        });

        JBF12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JBF12.setText("F4");
        JBF12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBF12ActionPerformed(evt);
            }
        });

        JBF13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JBF13.setText("F5");
        JBF13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBF13ActionPerformed(evt);
            }
        });

        JBF14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JBF14.setText("F6");
        JBF14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBF14ActionPerformed(evt);
            }
        });

        JBF15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JBF15.setText("F7");
        JBF15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBF15ActionPerformed(evt);
            }
        });

        JBF16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JBF16.setText("F8");
        JBF16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBF16ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Fecha Entrada");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Hora Entrada");

        txtHoraentradas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtFechaentradas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtHorasalida.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtFechasalida.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Fecha Salida");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Hora Salida");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Tiempo");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Pago");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Marca");

        txtMarca.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txttiempo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtpago.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtPlacas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Placas");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Importe");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Cambio");

        txtimporte.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtimporte.setText("0");
        txtimporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtimporteActionPerformed(evt);
            }
        });

        txtcambio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        JBGrabar1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JBGrabar1.setText("Grabar F9");
        JBGrabar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBGrabar1ActionPerformed(evt);
            }
        });

        JCTicket1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JCTicket1.setText("Imprimir Comprobante");
        JCTicket1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCTicket1ActionPerformed(evt);
            }
        });

        JCTicket2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JCTicket2.setText("Boleto Perdido");
        JCTicket2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCTicket2ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("0");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("0");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Ocupados:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Disponibles:");

        txttiempo1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txttiempo1.setText("0");
        txttiempo1.setEnabled(false);
        txttiempo1.setFocusable(false);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Dias");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(205, 205, 205))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(JBF9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JBF10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JBF11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JBF12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JBF13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JBF14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JBF15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JBF16)
                                .addGap(0, 45, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JCTarifa1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(309, 309, 309)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHorasalida, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechasalida, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpago, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcambio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txttiempo1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtPlacas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtimporte, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtHoraentradas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechaentradas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(JCTicket2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JCTicket1)
                .addGap(22, 22, 22)
                .addComponent(JBGrabar1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JCTarifa1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JBF9)
                            .addComponent(JBF10)
                            .addComponent(JBF11)
                            .addComponent(JBF12)
                            .addComponent(JBF13)
                            .addComponent(JBF14)
                            .addComponent(JBF15)
                            .addComponent(JBF16))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtFechaentradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtHoraentradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtFechasalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtHorasalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPlacas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtimporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtpago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txttiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtcambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttiempo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBGrabar1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JCTicket1)
                    .addComponent(JCTicket2))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFolioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFolioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFolioActionPerformed

    private void JBF9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF9ActionPerformed
JCTarifa1.setSelectedIndex(0);        // TODO add your handling code here:
    }//GEN-LAST:event_JBF9ActionPerformed

    private void JBF10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF10ActionPerformed
JCTarifa1.setSelectedIndex(1);        // TODO add your handling code here:
    }//GEN-LAST:event_JBF10ActionPerformed

    private void JBF11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF11ActionPerformed
JCTarifa1.setSelectedIndex(2);        // TODO add your handling code here:
    }//GEN-LAST:event_JBF11ActionPerformed

    private void JBF12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF12ActionPerformed
JCTarifa1.setSelectedIndex(3);        // TODO add your handling code here:
    }//GEN-LAST:event_JBF12ActionPerformed

    private void JBF13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF13ActionPerformed
JCTarifa1.setSelectedIndex(4);        // TODO add your handling code here:
    }//GEN-LAST:event_JBF13ActionPerformed

    private void JBF14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF14ActionPerformed
JCTarifa1.setSelectedIndex(5);        // TODO add your handling code here:
    }//GEN-LAST:event_JBF14ActionPerformed

    private void JBF15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF15ActionPerformed
JCTarifa1.setSelectedIndex(6);        // TODO add your handling code here:
    }//GEN-LAST:event_JBF15ActionPerformed

    private void JBF16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF16ActionPerformed
JCTarifa1.setSelectedIndex(7);        // TODO add your handling code here:
    }//GEN-LAST:event_JBF16ActionPerformed

    private void JCTicket1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCTicket1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JCTicket1ActionPerformed

    private void JBF8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JBF8ActionPerformed

    private void JBF6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JBF6ActionPerformed

    private void JBF7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JBF7ActionPerformed

    private void JBF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JBF1ActionPerformed

    private void JBF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JBF5ActionPerformed

    private void JBF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JBF4ActionPerformed

    private void JBF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JBF3ActionPerformed

    private void JBF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBF2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JBF2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
this.dispose();
JDEntradas jde=new JDEntradas(null, rootPaneCheckingEnabled);
jde.setVisible(rootPaneCheckingEnabled);// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
this.dispose();
JDSalidas jds = new JDSalidas(null, rootPaneCheckingEnabled);
jds.setVisible(rootPaneCheckingEnabled);// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void JCTicket2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCTicket2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JCTicket2ActionPerformed

    private void JCTarifa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCTarifa1ActionPerformed
      
        
    
   
    }//GEN-LAST:event_JCTarifa1ActionPerformed

    private void JBGrabar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBGrabar1ActionPerformed
 SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
       SimpleMySQLResult rs;
       rs = mysql.Query ("SELECT * FROM estacionados WHERE TICKET= \""+txtFolio.getText().substring(1,7)+"\" AND SERIE = \""+txtFolio.getText().substring(0,1)+"\" AND ESTATUS= \"Registro\"");
       if(rs.next()){
       mysql.Query ("UPDATE estacionados SET SALIDA=NOW(),TIEMPO=\""+txttiempo.getText()+"\",DIAS=\""+txttiempo1.getText()+"\",IMPORTE=\""+txtimporte.getText()+"\",ESTATUS=\""+estado+"\", TARIFA=\""+JCTarifa1.getSelectedItem()+"\"  WHERE TICKET= \""+txtFolio.getText().substring(1,7)+"\" AND SERIE = \""+txtFolio.getText().substring(0,1)+"\"");
       
          
      
      

        mysql.close();
          PrinterJob job = PrinterJob.getPrinterJob();
          PageFormat pf = job.defaultPage();  
          
            Paper paper = new Paper();  
            paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());  
            pf.setPaper(paper);  
            
            job.setPrintable( new MiPrintable(),pf);
            
try 
{
   if(JCTicket2.isSelected()){}else{
   
   if(JCTicket1.isSelected()||JCTicket2.isSelected()){
   job.print();
   }
   }
   
} 
catch (PrinterException es) 
{
   es.printStackTrace();
}
if(JCTicket2.isSelected()){
          
    this.dispose();
   JDBoletoPerdido bp = new JDBoletoPerdido(this,true);
   JDBoletoPerdido.jTextField7.setText(txtFolio.getText().substring(1,7));
   JDBoletoPerdido.jTextField8.setText(txtFolio.getText().substring(0,1));
   bp.setVisible(true);
    try {
               job.print();
           } catch (PrinterException es) {
               es.printStackTrace();
           }
   }
       }else{
       showMessageDialog(null, "No es un Folio Valido o ya está Pagado");
       }
    txtFolio.setText("");
        txtimporte.setText("");
        txtpago.setText("");
        txtFechaentradas.setText("");
        txtFechasalida.setText("");
        txtHoraentradas.setText("");
        txtHoraentradas.setText("");
        txtPlacas.setText("");
        txttiempo.setText("");
        txttiempo1.setText("");
        txtcambio.setText("");
        JCTicket1.setSelected(false);
        JCTicket2.setSelected(false);
        JCTarifa1.setSelectedIndex(0);
        txtFolio.requestFocusInWindow(); 
        
        
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
       jLabel14.setText(Integer.toString(rs.getNumRows()));
       jLabel15.setText(Integer.toString(cajones));
        
    }//GEN-LAST:event_JBGrabar1ActionPerformed

    private void txtimporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtimporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtimporteActionPerformed

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
            java.util.logging.Logger.getLogger(JDSalidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDSalidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDSalidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDSalidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDSalidas dialog = new JDSalidas(new javax.swing.JFrame(), true);
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
public String lalaserie = null;    
public class KeyListenerCustom implements KeyListener{
    private JDialog dialog=null;
    public KeyListenerCustom(JDialog dialog){
        System.out.println("Dialog!");
        this.dialog=dialog;
    }
    public void keyTyped(KeyEvent e) {
        System.out.println("Tecla apretada");
    }
 
    public void keyPressed(KeyEvent e) {
        System.out.println("entro");
        if(e.getKeyCode()==123) {//27 es el código de la tecla esc
           
            dialog.setVisible(false);
            JDEntradas jds=new JDEntradas(null, rootPaneCheckingEnabled);
            jds.setVisible(rootPaneCheckingEnabled);
        }
        
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
             if(txtFolio.isFocusOwner()){
              SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
       SimpleMySQLResult rs;
       Date now = new Date();
 
int mismodia=0;
String hapertura="";
String hcierre="";
      rs = mysql.Query ("SELECT * FROM opciones");
      while (rs.next()){ 
          if(rs.getString("ID").equals("18")){
        mismodia=Integer.parseInt(rs.getString("VALOR"));
                }
          if(rs.getString("ID").equals("15")){
       hapertura=rs.getString("VALOR");
                }
          if(rs.getString("ID").equals("16")){
        hcierre=rs.getString("VALOR");
                }
      }
       rs = mysql.Query ("SELECT *,NOW() AS ahora FROM estacionados WHERE TICKET= \""+txtFolio.getText().substring(1,7)+"\" AND SERIE = \""+txtFolio.getText().substring(0,1)+"\" ORDER BY ID DESC LIMIT 1");
                
        String fechaentrada="yyyy-MM-dd";
String horaentrada="00:00:00";
 String fechasalida="yyyy-MM-dd";
String horasalida="00:00:00";
  

        while (rs.next()){ 
            
             if (rs.getString("ESTATUS").equals("PAGADO")) {
                     showMessageDialog(null, "Boleto ya pagado");
                     txtpago.setText("Pagado");
                     txtimporte.setText(rs.getString("IMPORTE"));
                     txtFolio.requestFocusInWindow();
                 }
                 if (rs.getString("ESTATUS").equals("BOLETOPERDIDO")) {
                     showMessageDialog(null, "Boleto perdido");
                     txtpago.setText("Perdido");
                     txtFolio.requestFocusInWindow();
                 }
                 if (rs.getString("ESTATUS").equals("CANCELADO")) {
                     showMessageDialog(null, "Boleto cancelado");
                     txtimporte.setText("Cancelado");
                     txtpago.requestFocusInWindow();
                 }
            SimpleDateFormat sistema = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd"); 
            SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss"); 
            String tentrada= rs.getString("ENTRADA");
            String tsalida= rs.getString("ahora");

                  try {
                      fechaentrada= fecha.format(sistema.parse(tentrada));
                      horaentrada= hora.format(sistema.parse(tentrada));
                      
                      fechasalida= fecha.format(sistema.parse(tsalida));
                      horasalida= hora.format(sistema.parse(tsalida));
                  } catch (ParseException ex) {
                      Logger.getLogger(JDSalidas.class.getName()).log(Level.SEVERE, null, ex);
                  }
              

Date d1f = null;
Date d1h = null;
Date d2f = null;
Date d2h = null;

String time= null;
            try {
			d1f = fecha.parse(fechaentrada);
			d2f = fecha.parse(fechasalida);
                        
                        d1h = hora.parse(horaentrada);
			d2h = hora.parse(horasalida);
 
			//in milliseconds
			long diff = d2f.getTime() - d1f.getTime();
                        
                        long diff2 = d2h.getTime() - d1h.getTime();
 
			long diferenciadias = diff / (24 * 60 * 60 * 1000);
                        long diffMinutes = diff2 / (60 * 1000) % 60;
			long diffHours = diff2 / (60 * 60 * 1000) % 24;
                        long diffSeconds = diff2 / 1000 % 60;
                        
                        String diferenciatiempo = String.format("%02d:%02d:%02d", Math.abs(diffHours), Math.abs(diffMinutes), Math.abs(diffSeconds));
                        
txttiempo1.setText(Long.toString(diferenciadias)); 
     txttiempo.setText(diferenciatiempo); 
		} catch (Exception esa) {
			esa.printStackTrace();
		}
            
           
            
            txtFechaentradas.setText(fechaentrada); 
            txtHoraentradas.setText(horaentrada);
            JCTarifa1.setSelectedItem(rs.getString("TARIFA"));
            txtPlacas.setText(rs.getString("PLACAS"));
            txtMarca.setText(rs.getString("MARCA"));
            
            
            
            
            txtpago.requestFocusInWindow();
            txtpago.selectAll();
            if (rs.getString("ESTATUS").equals("PAGADO")) {
                     
                     txtFolio.requestFocusInWindow();
                     txtimporte.setText(rs.getString("IMPORTE"));
                     txttiempo1.setText(rs.getString("DIAS"));
                     txtFolio.selectAll();
                 }
                 if (rs.getString("ESTATUS").equals("BOLETOPERDIDO")) {
                     
                     txtFolio.requestFocusInWindow();
                     txtFolio.selectAll();
                 }
                 if (rs.getString("ESTATUS").equals("CANCELADO")) {
                     
                     txtFolio.requestFocusInWindow();
                     txtFolio.selectAll();
                 }
            
             rs = mysql.Query ("SELECT * FROM tarifas WHERE DESCRIPCION= \""+JCTarifa1.getSelectedItem()+"\"");
            System.out.println(JCTarifa1.getSelectedItem());
             
        while (rs.next()){ 
            
            if (mismodia==0) {
                if(txttiempo1.getText().equals("0"))
                {}else{
                txtimporte.setText(Integer.toString(Integer.parseInt(txttiempo1.getText())*80));
                try {
                        d1h = hora.parse(hapertura);
			d2h = hora.parse(horasalida);
                       
                        long diff2 = d2h.getTime() - d1h.getTime();
                        long diffMinutes = diff2 / (60 * 1000) % 60;
			long diffHours = diff2 / (60 * 60 * 1000) % 24;
                        long diffSeconds = diff2 / 1000 % 60;
                     
                        String diferenciatiempo = String.format("%02d:%02d:%02d", Math.abs(diffHours), Math.abs(diffMinutes), Math.abs(diffSeconds));
                        txttiempo.setText(diferenciatiempo); 
		} catch (Exception esa) {
			esa.printStackTrace();
		}
                }
           }else{
            if(txttiempo1.getText().equals("0"))
                {
                    if (horaentrada.compareTo(hapertura)>=0) {
                        
                    }else{
                    txtimporte.setText("80");
                    }
                
                }else{
                    txtimporte.setText(Integer.toString((Integer.parseInt(txttiempo1.getText())+1)*80));
                try {
                        d1h = hora.parse(hapertura);
			d2h = hora.parse(horasalida);
                       
                        long diff2 = d2h.getTime() - d1h.getTime();
                        long diffMinutes = diff2 / (60 * 1000) % 60;
			long diffHours = diff2 / (60 * 60 * 1000) % 24;
                        long diffSeconds = diff2 / 1000 % 60;
                     
                        String diferenciatiempo = String.format("%02d:%02d:%02d", Math.abs(diffHours), Math.abs(diffMinutes), Math.abs(diffSeconds));
                        txttiempo.setText(diferenciatiempo); 
		} catch (Exception esa) {
			esa.printStackTrace();
		}
            }
            }
            
            txtimporte.setText("0");
            for (int i = 1; i <= 48; i++) {

             if (txttiempo.getText().compareTo(rs.getString("tiempoe"+i))>=0 && txttiempo.getText().compareTo(rs.getString("tiempos"+i))<0 ) {
                
                 txtimporte.setText(Integer.toString(Integer.parseInt(rs.getString("tiempot"+i))+Integer.parseInt(txtimporte.getText())));

             }
     }
        }
     
        } 
        
       
         }else if(txtpago.isFocusOwner()){
         
         int debe = Integer.parseInt(txtimporte.getText());
         int paga = Integer.parseInt(txtpago.getText());
         int total = paga-debe;
         txtcambio.setText(String.valueOf(total));
         
         txtFolio.requestFocusInWindow();
         txtFolio.selectAll();
         
         
         
         }
        
    }else if(e.getKeyCode()==KeyEvent.VK_F9){
            SimpleMySQL mysql;
        mysql = new SimpleMySQL();
        mysql.connect("localhost", "root", "x4899954", "estacionamientos");
       SimpleMySQLResult rs;
       rs = mysql.Query ("SELECT * FROM estacionados WHERE TICKET= \""+txtFolio.getText().substring(1,7)+"\" AND SERIE = \""+txtFolio.getText().substring(0,1)+"\" AND ESTATUS= \"Registro\"");
       if(rs.next()){
       mysql.Query ("UPDATE estacionados SET SALIDA=NOW(),TIEMPO=\""+txttiempo.getText()+"\",DIAS=\""+txttiempo1.getText()+"\",IMPORTE=\""+txtimporte.getText()+"\",ESTATUS=\""+estado+"\", TARIFA=\""+JCTarifa1.getSelectedItem()+"\" WHERE TICKET= \""+txtFolio.getText().substring(1,7)+"\" AND SERIE = \""+txtFolio.getText().substring(0,1)+"\"");
       
          
      
      

        mysql.close();
          PrinterJob job = PrinterJob.getPrinterJob();
          PageFormat pf = job.defaultPage();  
          
            Paper paper = new Paper();  
            paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());  
            pf.setPaper(paper);  
            
            job.setPrintable( new MiPrintable(),pf);
            
try 
{
   
   
   if(JCTicket1.isSelected()){
   job.print();
   }
   
   
} 
catch (PrinterException es) 
{
   es.printStackTrace();
}
       }else{
       showMessageDialog(null, "No es un Folio Valido o ya está Pagado");
       }
    txtFolio.setText("");
        txtimporte.setText("0");
        txtpago.setText("");
        txtFechaentradas.setText("");
        txtFechasalida.setText("");
        txtHoraentradas.setText("");
        txtHoraentradas.setText("");
        txtPlacas.setText("");
        txttiempo.setText("");
        txttiempo1.setText("0");
        txtcambio.setText("");
        JCTicket1.setSelected(false);
        JCTicket2.setSelected(false);
        JCTarifa1.setSelectedIndex(0);
        txtFolio.requestFocusInWindow();    
        
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
       jLabel14.setText(Integer.toString(rs.getNumRows()));
       jLabel15.setText(Integer.toString(cajones));
        
        }else if(e.getKeyCode()==KeyEvent.VK_F1){
        
        JCTarifa1.setSelectedIndex(0);
        
        }else if(e.getKeyCode()==KeyEvent.VK_F2){
        
        JCTarifa1.setSelectedIndex(1);
        
        }else if(e.getKeyCode()==KeyEvent.VK_F3){
        
        JCTarifa1.setSelectedIndex(2);
        
        }else if(e.getKeyCode()==KeyEvent.VK_F4){
        
        JCTarifa1.setSelectedIndex(3);
        }else if(e.getKeyCode()==KeyEvent.VK_F5){
        
        JCTarifa1.setSelectedIndex(4);
        }else if(e.getKeyCode()==KeyEvent.VK_F6){
        
        JCTarifa1.setSelectedIndex(5);
        }else if(e.getKeyCode()==KeyEvent.VK_F7){
        
        JCTarifa1.setSelectedIndex(6);
        }else if(e.getKeyCode()==KeyEvent.VK_F8){
        
        JCTarifa1.setSelectedIndex(7);
        
        }else if(e.getKeyCode()==KeyEvent.VK_F10){
        
        txtimporte.setText("0");
        txtpago.setText("");
        txtFechaentradas.setText("");
        txtFechasalida.setText("");
        txtHoraentradas.setText("");
        txtHoraentradas.setText("");
        txtPlacas.setText("");
        txttiempo.setText("");
        txttiempo1.setText("0");
        txtcambio.setText("");
        JCTicket1.setSelected(false);
        JCTicket2.setSelected(false);
        JCTarifa1.setSelectedIndex(0);
        txtFolio.setText("");   
        JCTarifa1.setSelectedIndex(0);
        txtFolio.requestFocus();
        
        }else if(e.getKeyCode()==KeyEvent.VK_A){
        
            System.out.println("sales");
        
        }else if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
        
        this.dialog.dispose();
 }
        
    }
 
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_A) {
            txtFolio.setText("A");
        }else if (e.getKeyCode()==KeyEvent.VK_B){
        txtFolio.setText("B");
        }
   
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
              Logger.getLogger(JDSalidas.class.getName()).log(Level.SEVERE, null, ex);
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
        rs = mysql.Query ("SELECT * FROM estacionados WHERE TICKET= \""+txtFolio.getText().substring(1,7)+"\" AND SERIE = \""+txtFolio.getText().substring(0,1)+"\"");
        
        while (rs.next()){  
                     
        g.setFont(new Font("Arial", Font.BOLD, 11));
        linea=linea+4;
        g.drawString("Servicio Estacionamiento Nota De Consumo", 10,linea);
        linea=linea+3;
        g.drawString("______________________________________________", 10,linea);
        g.setFont(new Font("Arial", Font.BOLD, 22));
        java.text.DecimalFormat nft2 = new  java.text.DecimalFormat("#000000.###");  
        nft2.setDecimalSeparatorAlwaysShown(false); 
        linea=linea+25;
        g.drawString("FOLIO: "+rs.getString("SERIE")+nft2.format(Integer.parseInt(rs.getString("TICKET"))), 60,linea);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        linea=linea+20;
        g.drawString("PLACAS: "+rs.getString("PLACAS"), 70,linea);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        linea=linea+15;
   g.drawString("MARCA: "+rs.getString("MARCA"), 40,linea);
   linea=linea+12;
    DateFormat fold = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         DateFormat fnew = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        g.drawString("ENTRADA: ", 40,linea);
              try {
                  g.drawString(fnew.format(fold.parse(rs.getString("ENTRADA").replace(".0", ""))), 130,linea);
              } catch (ParseException ex) {
                  Logger.getLogger(JDSalidas.class.getName()).log(Level.SEVERE, null, ex);
              }
        linea=linea+12;
        g.drawString("SALIDA  : ", 40,linea);
              try {
                  g.drawString(fnew.format(fold.parse(rs.getString("SALIDA").replace(".0", ""))), 130,linea);
              } catch (ParseException ex) {
                  Logger.getLogger(JDSalidas.class.getName()).log(Level.SEVERE, null, ex);
              }
        linea=linea+12;
        g.drawString("TARIFA: "+rs.getString("TARIFA"), 40,linea);
            if (JCTicket2.isSelected()) {
                linea=linea+12;
                g.drawString("BOLETO PERDIDO", 40,linea);
            }
        linea=linea+12;
g.drawString("RESP. TURNO: "+JDLogin.elusuario.toUpperCase(), 40,linea);     
linea=linea+12;
        g.drawString("TIEMPO: "+rs.getString("TIEMPO"), 40,linea);
        linea=linea+12;
        g.drawString("IMPORTE: $"+rs.getString("IMPORTE")+".00", 40,linea);
        
        
        } 
        linea=linea+12;
         g.drawString("FORMA DE PAGO: EFECTIVO", 40,linea);
        rs = mysql.Query ("SELECT * FROM opciones WHERE ID = 12");
        linea=linea+12;
        while (rs.next()){ 
                        g.drawString("LUGAR DE EXPEDICION: "+rs.getString("VALOR"), 40,linea);
                      } rs.close();
        
                              
        
g.setFont(new Font("Arial", Font.BOLD, 40));

   
linea=linea+5;
    g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("______________________________________________", 10,linea); 
g.setFont(new Font("Arial", Font.BOLD, 13));  
          linea=linea+18;
     g.drawString("GRACIAS POR SU VISITA, VUELVA PRONTO", 20,linea)  ; 
       
                      
               
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
    lineWidth=280;
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
    private javax.swing.JButton JBF10;
    private javax.swing.JButton JBF11;
    private javax.swing.JButton JBF12;
    private javax.swing.JButton JBF13;
    private javax.swing.JButton JBF14;
    private javax.swing.JButton JBF15;
    private javax.swing.JButton JBF16;
    private javax.swing.JButton JBF2;
    private javax.swing.JButton JBF3;
    private javax.swing.JButton JBF4;
    private javax.swing.JButton JBF5;
    private javax.swing.JButton JBF6;
    private javax.swing.JButton JBF7;
    private javax.swing.JButton JBF8;
    private javax.swing.JButton JBF9;
    private javax.swing.JButton JBGrabar;
    private javax.swing.JButton JBGrabar1;
    private javax.swing.JComboBox JCTarifa;
    private javax.swing.JComboBox JCTarifa1;
    private javax.swing.JCheckBox JCTicket;
    private javax.swing.JCheckBox JCTicket1;
    private javax.swing.JCheckBox JCTicket2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtFechaentradas;
    private javax.swing.JTextField txtFechasalida;
    private javax.swing.JTextField txtFolio;
    private javax.swing.JTextField txtHoraentradas;
    private javax.swing.JTextField txtHorasalida;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtPlacas;
    private javax.swing.JTextField txtcambio;
    private javax.swing.JTextField txtimporte;
    private javax.swing.JTextField txtpago;
    private javax.swing.JTextField txttiempo;
    private javax.swing.JTextField txttiempo1;
    // End of variables declaration//GEN-END:variables

}

