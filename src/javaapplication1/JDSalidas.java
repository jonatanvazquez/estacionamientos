
package javaapplication1;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.text.ParseException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;


public class JDSalidas extends javax.swing.JDialog {
public int siguiente = JDEntradas.siguiente;
public String estado="PAGADO";
public static  int linea=0;
public Db db;
public Autos auto;
public Impresiones impresion;
int importe=0;
    public JDSalidas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
       
        db=new Db();
        auto=new Autos();
        impresion=new Impresiones();
        PlainDocument doc = new TextLimiter(7);
        doc.setDocumentFilter(new upperCASEJTEXTFIELD());
        txtFolio.setDocument(doc);
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
        JCTarifa1.setModel(new DefaultComboBoxModel(db.ListaTarifas()));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdfa = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();

        new Timer(1000, (ActionEvent e) -> {
            txtHorasalida.setText(sdf.format(cal.getTime()));
            txtFechasalida.setText(sdfa.format(cal.getTime()));
            jLabel14.setText(Integer.toString(db.CajonesOcupados()));
            jLabel15.setText(Integer.toString(db.CajonesDisponibles()));
        }).start();

        JCTarifa1.addItemListener(new ItemListener() {
     @Override
     public void itemStateChanged(ItemEvent e) {
         calcular();

     }

 });

      JCTicket2.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
          int boletop=Integer.parseInt(db.ObtenerOpcion("BOLETOPERDIDO"));

          if (JCTicket2.isSelected()) {
              importe=importe+boletop;
              txtimporte.setText(Integer.toString(importe));
              estado="BOLETOPERDIDO";
              
          }else{
              importe=importe-boletop;
              txtimporte.setText(Integer.toString(importe));
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
    
         public void calcular(){
        Map<String,String> Boleto=db.ConsultarBoleto(txtFolio.getText().substring(1,7), txtFolio.getText().substring(0,1));
        SimpleDateFormat completa = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S"); 
        SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd"); 
        SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss"); 
        if(Boleto == null || Boleto.isEmpty()){
            showMessageDialog(null, "No es un Folio Valido");
        }else if(!Boleto.get("ESTATUS").equals("Registro")){
            showMessageDialog(null, "Folio ya pagado");
            txttiempo1.setText(Boleto.get("DIAS")); 
            txttiempo.setText(Boleto.get("TIEMPO"));
            txtimporte.setText(Boleto.get("IMPORTE"));
            txtMarca.setText(Boleto.get("MARCA"));
            txtPlacas.setText(Boleto.get("PLACAS"));
            try {
                txtFechaentradas.setText(fecha.format(fecha.parse(Boleto.get("ENTRADA"))));
                txtHoraentradas.setText(hora.format(completa.parse(Boleto.get("ENTRADA"))));
                txtFechasalida.setText(fecha.format(fecha.parse(Boleto.get("SALIDA"))));
                txtHorasalida.setText(hora.format(completa.parse(Boleto.get("SALIDA"))));
            } catch (ParseException ex) {}
            txtFolio.requestFocusInWindow();
            txtFolio.selectAll();
            
        }else{
        int mismodia=Integer.parseInt(db.ObtenerOpcion("HENTRADA"));
        int TarifaNocturna=Integer.parseInt(db.ObtenerOpcion("TNOCTURNA"));
        int diferenciadias=0;
        long fechaentrada=0,horaentrada=0,fechasalida=0,horasalida=0,horacierre=0,horaapertura=0;
        String diferenciatiempo="";
        try {
            horaentrada= completa.parse(Boleto.get("ENTRADA")).getTime();
            horasalida= completa.parse(Boleto.get("ahora")).getTime();
            horacierre= fechaentrada+hora.parse(hora.format(hora.parse(db.ObtenerOpcion("HCIERRE")))).getTime();
            horaapertura= fechasalida+hora.parse(hora.format(hora.parse(db.ObtenerOpcion("HAPERTURA")))).getTime();
            
            long diferenciadiaslong =((horasalida - horaentrada) / (24 * 60 * 60 * 1000));
            diferenciadias = (int)diferenciadiaslong;       
            diferenciatiempo = DiferenciaTiempo(horasalida - horaentrada);
            
        } catch (Exception ex) {}
        
            txtpago.requestFocusInWindow();
            txtpago.selectAll();
            
            if (mismodia==1) { //SI CIERRA EL MISMO DÍA
                if(diferenciadias!=0) { //SI PASÓ MAS DE UN DÍA
                    long tiemposalida=0;
                    if (horasalida>horaapertura) {tiemposalida=(horasalida-horaapertura); }
                    diferenciatiempo = DiferenciaTiempo((horacierre - horaentrada)+(tiemposalida));
                        System.out.println(tiemposalida);
                }else{ //SI NO PASÓ MAS DE UN DÍA
                    diferenciatiempo = DiferenciaTiempo(horasalida-horaentrada);
                    System.out.println("no ya valio");
                }
           }else{ //SI NO CIERRA EL MISMO DÍA
                if(fechaentrada==fechasalida&&horasalida<horaapertura){ //SI LLEGÓ EL MISMO DIA Y SE FUE ANTES DE CERRAR
                    diferenciatiempo = DiferenciaTiempo(horasalida-horaentrada);
                }
                else if(fechaentrada==fechasalida&&horasalida>horaapertura){ //SI LLEGÓ EL MISMO DÍA Y SE QUEDÓ LA NOCHE
                    diferenciadias=1;
                    diferenciatiempo = DiferenciaTiempo((horacierre - horaentrada)+(horasalida-horaapertura));
                }
                else if(diferenciadias==1&&horasalida<horaapertura){ //SI LLEGÓ UN DÍA ANTES Y SE FUÉ ANTES DE CERRAR
                    diferenciadias--;
                    diferenciatiempo = DiferenciaTiempo(horasalida-horaentrada);
                }
                else if(diferenciadias==1&&horasalida>horaapertura){ //SI LLEGÓ UN DÍA ANTES PERO SE QUEDÓ LA NOCHE
                    diferenciatiempo = DiferenciaTiempo((horasalida-horaapertura)+(horacierre-horaentrada));
                }else if(diferenciadias>=1){
                    diferenciatiempo = DiferenciaTiempo((horasalida-horaapertura)+(horacierre-horaentrada)+((diferenciadias-1)*(horacierre-horaapertura)));
                }
            }
            importe=diferenciadias*TarifaNocturna; //COSTO POR LAS NOCHES
            
            Map<String,String> Tarifa=db.Tarifa(JCTarifa1.getSelectedItem().toString());
            
            for (int i = 1; i <= 48; i++) {
                if (diferenciatiempo.compareTo(Tarifa.get("tiempoe"+i))>=0 && diferenciatiempo.compareTo(Tarifa.get("tiempos"+i))<0 ) {
                    importe=importe+Integer.parseInt(Tarifa.get("tiempot"+i));
                }
            }
            txttiempo1.setText(Integer.toString(diferenciadias)); 
            txttiempo.setText(diferenciatiempo);
            txtimporte.setText(Integer.toString(importe));
            txtMarca.setText(Boleto.get("MARCA"));
            txtPlacas.setText(Boleto.get("PLACAS"));
    try {
        txtFechaentradas.setText(fecha.format(fecha.parse(Boleto.get("ENTRADA"))));
        txtHoraentradas.setText(hora.format(completa.parse(Boleto.get("ENTRADA"))));
    } catch (ParseException ex) {}
            
         }
     }
    public String DiferenciaTiempo(long diferencia){
        String DirefenciaTiempo="";
        long diffMinutes = diferencia / (60 * 1000) % 60;
	long diffHours = diferencia / (60 * 60 * 1000) % 24;
        long diffSeconds = diferencia / 1000 % 60;
        DirefenciaTiempo = String.format("%02d:%02d:%02d", Math.abs(diffHours), Math.abs(diffMinutes), Math.abs(diffSeconds));
        return DirefenciaTiempo;
    }

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

       Map<String,String> Boleto=db.ConsultarBoletoPagado(txtFolio.getText().substring(1,7), txtFolio.getText().substring(0,1));
       if(Boleto == null || Boleto.isEmpty()){
           showMessageDialog(null, "No es un Folio Valido o ya está Pagado");
       }else{
           auto.Actualizar(txttiempo.getText(), txttiempo1.getText(), txtimporte.getText(), estado, JCTarifa1.getSelectedItem().toString(), txtFolio.getText().substring(1,7), txtFolio.getText().substring(0,1));
           if(JCTicket1.isSelected()&&!JCTicket2.isSelected()){
           impresion.Imprimecomprobante(Integer.parseInt(txtFolio.getText().substring(1,7)), txtFolio.getText().substring(0,1));
           }else if(JCTicket2.isSelected()){
               this.dispose();
               JDBoletoPerdido bp = new JDBoletoPerdido(this,true);
               JDBoletoPerdido.jTextField7.setText(txtFolio.getText().substring(1,7));
               JDBoletoPerdido.jTextField8.setText(txtFolio.getText().substring(0,1));
               bp.setVisible(true);
               impresion.Imprimecomprobante(Integer.parseInt(txtFolio.getText().substring(1,7)), txtFolio.getText().substring(0,1));
            }
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
         
    }//GEN-LAST:event_JBGrabar1ActionPerformed

    private void txtimporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtimporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtimporteActionPerformed


    public static void main(String args[]) {

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
public String lalaserie = null;    
public class KeyListenerCustom implements KeyListener{
    private JDialog dialog=null;
    public KeyListenerCustom(JDialog dialog){
        this.dialog=dialog;
    }
    public void keyTyped(KeyEvent e) {

    }
 
    public void keyPressed(KeyEvent e) {
        
        switch(e.getKeyCode()){
            case 123:
                dialog.setVisible(false);
                JDEntradas jds=new JDEntradas(null, rootPaneCheckingEnabled);
                jds.setVisible(rootPaneCheckingEnabled);
                break;
            case KeyEvent.VK_ENTER:
                if(txtFolio.isFocusOwner()){
                    calcular();
                }else if(txtpago.isFocusOwner()){
                    int debe = Integer.parseInt(txtimporte.getText());
                    int paga = Integer.parseInt(txtpago.getText());
                    int total = paga-debe;
                    txtcambio.setText(String.valueOf(total));
                    txtFolio.requestFocusInWindow();
                    txtFolio.selectAll();
                }
                break;
            case KeyEvent.VK_F9:
                Map<String,String> Boleto=db.ConsultarBoletoPagado(txtFolio.getText().substring(1,7), txtFolio.getText().substring(0,1));
                if(Boleto == null || Boleto.isEmpty()){
                    
                    showMessageDialog(null, "No es un Folio Valido o ya está Pagado");
                }else{
                    System.out.println("es qui");
                    auto.Actualizar(txttiempo.getText(), txttiempo1.getText(), txtimporte.getText(), estado, JCTarifa1.getSelectedItem().toString(), txtFolio.getText().substring(1,7), txtFolio.getText().substring(0,1));
                    if(JCTicket1.isSelected()&&!JCTicket2.isSelected()){
                    impresion.Imprimecomprobante(Integer.parseInt(txtFolio.getText().substring(1,7)), txtFolio.getText().substring(0,1));
                    }else if(JCTicket2.isSelected()){
                        this.dialog.dispose();
                        JDBoletoPerdido bp = new JDBoletoPerdido(this.dialog,true);
                        JDBoletoPerdido.jTextField7.setText(txtFolio.getText().substring(1,7));
                        JDBoletoPerdido.jTextField8.setText(txtFolio.getText().substring(0,1));
                        bp.setVisible(true);
                        impresion.Imprimecomprobante(Integer.parseInt(txtFolio.getText().substring(1,7)), txtFolio.getText().substring(0,1));
                     }
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
                break;
            case KeyEvent.VK_F1:
                JCTarifa1.setSelectedIndex(0); break;
            case KeyEvent.VK_F2:
                JCTarifa1.setSelectedIndex(1); break;
            case KeyEvent.VK_F3:
                JCTarifa1.setSelectedIndex(2); break;
            case KeyEvent.VK_F4:
                JCTarifa1.setSelectedIndex(3); break;
            case KeyEvent.VK_F5:
                JCTarifa1.setSelectedIndex(4); break;
            case KeyEvent.VK_F6:
                JCTarifa1.setSelectedIndex(5); break;
            case KeyEvent.VK_F7:
                JCTarifa1.setSelectedIndex(6); break;
            case KeyEvent.VK_F8:
                JCTarifa1.setSelectedIndex(7); break;
            case KeyEvent.VK_F10:
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
                break;
            case KeyEvent.VK_ESCAPE:
                this.dialog.dispose(); break;
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

