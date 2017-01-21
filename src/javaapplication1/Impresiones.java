
package javaapplication1;

import java.awt.*;
import java.awt.print.*;
import java.util.*;
import static java.awt.print.Printable.*;
import java.io.*;
import java.text.*;
import static javaapplication1.Impresiones.ticket;
import javax.imageio.ImageIO;
import net.sourceforge.barbecue.*;

public class Impresiones {
    public static int ticket=0;
    public static String serie="A";
    public static Boolean serieb=false;
    public Db db;
    public int IDCorte;
    String inicial;
    String terminal;
    boolean detalle;

    public void Imprimeboleto(boolean doble){
            Imprimir(new Boleto());
        if(doble){
            Imprimir(new Boleto());
        }

    }
    public void Imprimecomprobante(int numero,String laserie){
        ticket=numero;
        serie=laserie;
        Imprimir(new Comprobante());
    
    
    }
    public void ImprimeCorte(int NoCorte,boolean lista){
        IDCorte=NoCorte;
        db=new Db();
        detalle=lista;
        serieb="1".equals(db.ObtenerOpcion("SERIEB"));
        inicial = db.FechasCorte(IDCorte).get("Inicio");
        terminal = db.FechasCorte(IDCorte).get("Final");
        Imprimir(new Corte());
    
    } 
    public void ImprimeCorteFechas(String inicio,String ultimo,boolean lista){
        db=new Db();
        IDCorte=0;
        detalle=lista;
        DateFormat fold = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat fnew = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        serieb="1".equals(db.ObtenerOpcion("SERIEB"));
        try {
            inicial = fold.format(fnew.parse(inicio));
            terminal = fold.format(fnew.parse(ultimo));
        } catch (ParseException ex) { }
        
        Imprimir(new Corte());
    
    } 
    
    public void Imprimir(Printable clase){
        PrinterJob job = PrinterJob.getPrinterJob();
        PageFormat pf = job.defaultPage();  
        Paper paper = new Paper();  
        paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());  
        pf.setPaper(paper);  
        job.setPrintable( clase,pf);
        try {
            job.print();

            } catch (PrinterException es) {
            }
    }

    class Boleto implements Printable {
        public Db db;
        public int linea=0;
        public int siguiente=0;
        SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat fold = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @Override
        public int print (Graphics g, PageFormat f, int pageIndex) 
        {
         db=new Db();
         if (pageIndex == 0) { 
                 Map<String,String> UltimoBoleto=db.UltimoBoleto();
                 siguiente=Integer.parseInt(UltimoBoleto.get("TICKET"));
                 String lalaserie=UltimoBoleto.get("SERIE"); 
                 try {
                     FormatoTexto.outputtingBarcodeAsPNG(siguiente,lalaserie);
                 } catch (BarcodeException ex) {
                 } 
                 Image img1 = Toolkit.getDefaultToolkit().getImage("C:/Estacionamientos/LogoTicket.jpg");
                 g.drawImage(img1, 0, 0, 290, 30, null);
                 g.setFont(new Font("Arial", Font.BOLD, 8)); 
                 linea=40;
                 linea=FormatoTexto.drawStringMultiLine(g, db.ObtenerOpcion("TEXTO1"), 270, 10, linea);            
                 g.setFont(new Font("Arial", Font.BOLD, 12));
                 linea=linea+15;
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
                 g.drawString("PLACAS: "+UltimoBoleto.get("PLACAS"), 70,linea);
                 g.setFont(new Font("Arial", Font.BOLD, 12));
                 linea=linea+15;
                 g.drawString("MARCA: "+UltimoBoleto.get("MODELO"), 40,linea);
                 linea=linea+12;
                 g.drawString("FECHA: ", 40,linea);
                 try {
                     g.drawString(fecha.format(fold.parse(UltimoBoleto.get("ENTRADA"))), 100,linea);
                 } catch (ParseException ex) {}
                 linea=linea+12;
                 g.drawString("HORA: ", 40,linea);
                 try {
                     g.drawString(hora.format(fold.parse(UltimoBoleto.get("ENTRADA"))), 100,linea);
                 } catch (ParseException ex) {}
                 linea=linea+12;
                 g.drawString("TARIFA: "+UltimoBoleto.get("TARIFA"), 40,linea);
                 linea=linea+12;
                 g.drawString("RESP. TURNO: "+JDLogin.elusuario.toUpperCase(), 40,linea);     
                 Image img3=null;
                 try {
                     img3 = ImageIO.read(new File("mybarcode.jpg"));
                 } catch (IOException ex) { }
                 linea=linea+4; 
                 g.drawImage(img3, 10,linea,  null);
                 linea=linea+50;
                 g.setFont(new Font("Arial", Font.BOLD, 12));
                 g.drawString("______________________________________________", 10,linea); 
                 g.setFont(new Font("Arial", Font.BOLD, 6));  
                 linea=linea+12;
                 linea=FormatoTexto.drawStringMultiLine(g, db.ObtenerOpcion("TEXTO3"), 280, 10, linea);
                 linea=linea+8;
                 FormatoTexto.drawStringMultiLine(g, db.ObtenerOpcion("TEXTO4"), 280, 10, linea);
                 linea=linea+12;
                 return PAGE_EXISTS;
             }else{
              return NO_SUCH_PAGE;
             }
         }
}
    class Comprobante implements Printable {
        public Db db;
        public int linea=0;
        int ticket=Impresiones.ticket;
        String serie=Impresiones.serie;
        Map<String,String> Boleto;
        @Override
        public int print (Graphics g, PageFormat f, int pageIndex) 
        {
           db=new Db();
           Boleto=db.ConsultarBoleto(Integer.toString(ticket), serie);
           if (pageIndex == 0) 
           {
               try {
                   FormatoTexto.outputtingBarcodeAsPNG(ticket,serie);
               } catch (BarcodeException ex) {}

               Image img1 = Toolkit.getDefaultToolkit().getImage("C:/Estacionamientos/LogoTicket.jpg");
               g.drawImage(img1, 0, 0, 290, 30, null);
               linea=40;
               g.setFont(new Font("Arial", Font.BOLD, 8));  
               linea=FormatoTexto.drawStringMultiLine(g, db.ObtenerOpcion("TEXTO1"), 270, 10, linea);
               g.setFont(new Font("Arial", Font.BOLD, 11));
               linea=linea+15;
               g.drawString("Servicio Estacionamiento Nota De Consumo", 10,linea);
               linea=linea+3;
               g.drawString("______________________________________________", 10,linea);
               g.setFont(new Font("Arial", Font.BOLD, 22));
               java.text.DecimalFormat nft2 = new  java.text.DecimalFormat("#000000.###");  
               nft2.setDecimalSeparatorAlwaysShown(false); 
               linea=linea+25;
               g.drawString("FOLIO: "+serie+nft2.format(ticket), 60,linea);
               g.setFont(new Font("Arial", Font.BOLD, 15));
               linea=linea+20;
               g.drawString("PLACAS: "+Boleto.get("PLACAS"), 70,linea);
               g.setFont(new Font("Arial", Font.BOLD, 12));
               linea=linea+15;
               g.drawString("MARCA: "+Boleto.get("MARCA"), 40,linea);
               linea=linea+12;
               DateFormat fold = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               DateFormat fnew = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
               g.drawString("ENTRADA: ", 40,linea);
               try {
                    g.drawString(fnew.format(fold.parse(Boleto.get("ENTRADA").replace(".0", ""))), 130,linea);
               } catch (ParseException ex) { }
               linea=linea+12;
               g.drawString("SALIDA  : ", 40,linea);
               try {
                    g.drawString(fnew.format(fold.parse(Boleto.get("SALIDA").replace(".0", ""))), 130,linea);
               } catch (ParseException ex) { }
               linea=linea+12;
               g.drawString("TARIFA: "+Boleto.get("TARIFA"), 40,linea);
               if (Boleto.get("ESTATUS").equals("BOLETOPERDIDO")) {
                  linea=linea+12;
                  g.drawString("BOLETO PERDIDO", 40,linea);
               }
               linea=linea+12;
               g.drawString("RESP. TURNO: "+JDLogin.elusuario.toUpperCase(), 40,linea);     
               linea=linea+12;
               g.drawString("TIEMPO: "+Boleto.get("TIEMPO"), 40,linea);
               linea=linea+12;
               g.drawString("IMPORTE: $"+Boleto.get("IMPORTE")+".00", 40,linea);
               linea=linea+12;
               g.drawString("FORMA DE PAGO: EFECTIVO", 40,linea);
               linea=linea+12;
               g.drawString("LUGAR DE EXPEDICION: "+db.ObtenerOpcion("LUGAREXPEDICION"), 40,linea);
               g.setFont(new Font("Arial", Font.BOLD, 40));
               linea=linea+5;
               g.setFont(new Font("Arial", Font.BOLD, 12));
               g.drawString("______________________________________________", 10,linea); 
               g.setFont(new Font("Arial", Font.BOLD, 13));  
               linea=linea+18;
               g.drawString("GRACIAS POR SU VISITA, VUELVA PRONTO", 20,linea)  ;  
               return PAGE_EXISTS;
           }else{
              return NO_SUCH_PAGE;
           }
        }

}
    class Corte implements Printable {      
        public int lineamax=999999999;
        public int lineamax1=999999999;
        int avance=0;
        public Db db;
        String pagados,toleranciasc,tpagados,pagados2,tpagados2,pagados3,tpagados3,folios="";
        int lapagina=1;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yy");
        public int linea=0;
        int contando,total,contando2,total2,tolerancias=0;

        @Override
        public int print (Graphics g, PageFormat f, int pageIndex) 
       {    
            db=new Db();
            java.text.DecimalFormat nft = new java.text.DecimalFormat("#000000.###"); 
            nft.setDecimalSeparatorAlwaysShown(false); 
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            DateFormat fold = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DateFormat fnew = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            
            String[] Tarifas=db.ListaTarifas();
            if(serieb){
                Map<String,Map> Boletosb=db.ListaBoletos(inicial,terminal, "B");
                Map<Integer,Map> Estacionadosb=db.ListaEstacionados(inicial,terminal, "B");
            }


            
            Map<String,Map> Boletos=db.ListaBoletos(inicial,terminal, serie);
            Map<Integer,Map> Estacionados=db.ListaEstacionados(inicial,terminal, serie);
            
            if (pageIndex==0) 
            {
                Image img1 = Toolkit.getDefaultToolkit().getImage("C:/Estacionamientos/LogoTicket.jpg");
                g.drawImage(img1, 0, 0, 290, 30, null);
                
                g.setFont(new Font("Arial", Font.BOLD, 12)); 
                linea=80;
                linea=FormatoTexto.drawStringMultiLine(g, db.ObtenerOpcion("TEXTO1"), 295, 10, linea);
                g.setFont(new Font("Arial", Font.BOLD, 12));
                linea=linea+20;
                g.drawString("CORTE DE CAJA", 80,linea);
                g.drawString(Integer.toString(IDCorte), 180,linea);
                g.drawString("______________________________________________", 10,++linea);
                g.setFont(new Font("Arial", Font.BOLD, 13));
                linea=linea+20;
                g.setFont(new Font("Arial", Font.BOLD, 12));
                g.drawString("IMPRESION DE REPORTE: "+df.format(new Date()), 10,linea);
                g.setFont(new Font("Arial", Font.BOLD, 14));
                linea=linea+20;
                try {
                    g.drawString("DEL: "+fnew.format(fold.parse(inicial.replace(".0", ""))), 20,linea);
                    linea=linea+13;
                    g.drawString("AL: "+fnew.format(fold.parse(terminal.replace(".0", ""))), 30,linea);
                } catch (ParseException ex) { }
                linea=linea+19;
                g.drawString("F. INICIAL: ", 10,linea); 
                g.drawString(serie+nft.format(Integer.parseInt(Boletos.get("Folios").get("Inicial").toString())), 90,linea); 
                g.drawString("F. FINAL: ", 160,linea); 
                g.drawString(serie+nft.format(Integer.parseInt(Boletos.get("Folios").get("Final").toString())), 240,linea); 
                linea=linea+20;
                g.drawString("TOTAL FOLIOS: ", 10,linea);
                g.drawString("______________________________________________", 10,linea); 
                g.drawString(Boletos.get("Folios").get("NumeroFolios").toString(), 210,linea);
                linea=linea+15;
                g.drawString("TOLERANCIAS: ", 10,linea);
                g.drawString(Boletos.get("Folios").get("Tolerancias").toString(), 210,linea);
                linea=linea+15;
                g.drawString("PAGADOS: ", 10,linea);
                g.drawString(Boletos.get("Folios").get("Normales").toString(), 210,linea);
                g.drawString("$"+Integer.toString(total), 240,linea);
                linea=linea+15;
                g.drawString("CANCELADOS: ", 10,linea);
                g.drawString(Boletos.get("Folios").get("Cancelados").toString(), 210,linea);  
                linea=linea+20;
                
                Map<String,Integer> BoletosTarifa=Boletos.get("BoletosTarifa");
                Map<String,Integer> TotalesTarifa=Boletos.get("TotalesTarifa");
                for (Map.Entry<String,Integer> T : BoletosTarifa.entrySet()){ 
                    g.drawString("T"+Integer.toString(Arrays.asList(Tarifas).indexOf(T.getKey()))+"  "+T.getKey()+":", 15,linea);
                    g.drawString(Integer.toString(T.getValue()), 210,linea);
                    g.drawString("$"+Integer.toString(TotalesTarifa.get(T.getKey())), 240,linea);
                    linea=linea+15;
                } 
                g.drawString("ESTACIONADOS: "+db.Estacionados(inicial,terminal, serie), 10,linea);
                linea=linea+15;
                g.drawString("PENSIONES: 0", 10,linea);
                linea=linea+15;
                g.drawString("PERDIDOS: "+Boletos.get("Folios").get("Perdidos").toString(), 10,linea);
                linea=linea+15;
                g.drawString("RESP.DE TURNO: "+JDLogin.elusuario, 10,linea);
                g.setFont(new Font("Arial", Font.BOLD, 12));
                g.drawString("______________________________________________", 10,linea); 
                g.setFont(new Font("Arial", Font.BOLD, 12));
                linea=linea+15;
                avance=1;
            }
            if(avance==1){
                g.setFont(new Font("Arial", Font.BOLD, 12));
                Map<Integer,Map> ListaBoletos=Boletos.get("Boletos");
                if (detalle) {
                for (Map.Entry<Integer,Map> B : ListaBoletos.entrySet()){
                    if (B.getKey()<lineamax) {
                    if (B.getValue().get("ESTATUS").equals("CANCELADO")) {
                        g.drawString("CA:", 10,linea);
                    }else{
                        if(B.getValue().get("ESTATUS").equals("CANCELADO")){g.drawString("BP" , 100,linea);}
                        g.drawString("T"+Integer.toString(Arrays.asList(Tarifas).indexOf(B.getValue().get("TARIFA")))+":", 10,linea);
                    }
                    g.drawString(B.getValue().get("SERIE")+nft.format(Integer.parseInt(B.getValue().get("TICKET").toString())), 30,linea);
                    g.drawString(B.getValue().get("PLACAS").toString() , 120,linea);
                    g.drawString(B.getValue().get("TIEMPO").toString() , 180,linea);
                    g.drawString("$"+B.getValue().get("IMPORTE")+".00" , 240,linea);
                    linea=linea+15;
                    }
                    if(linea>=672){
                        linea=15;
                        lineamax=B.getKey();
                        return PAGE_EXISTS;
                    }
                } 
                }
                g.setFont(new Font("Arial", Font.BOLD, 12));
                g.drawString("TOTAL PAGADOS:                   "+Boletos.get("Folios").get("NumeroFolios").toString() , 10,linea);
                g.drawString("$"+Boletos.get("Folios").get("Total").toString()+".00" , 240,linea);
                linea=linea+20;
                g.drawString("----ESTACIONADOS-----", 10,linea);
                linea=linea+15;
                if(detalle){
                for (Map.Entry<Integer,Map> E : Estacionados.entrySet()){  
                    g.drawString(E.getValue().get("SERIE")+nft.format(Integer.parseInt(E.getValue().get("TICKET").toString())) , 10,linea);
                    g.drawString(E.getValue().get("PLACAS").toString() ,80,linea);
                    g.drawString(E.getValue().get("ENTRADA").toString().replace("-", "/") , 140,linea);
                    linea=linea+15;
                } 
                }
                g.drawString("TOTAL ESTACIONADOS:                   "+db.Estacionados(inicial,terminal, serie) , 10,linea);
                if (serieb) {
                    linea=linea+15;
                    g.drawString("TOTAL PAGADOS A:         "+pagados2+"          $"+tpagados2 , 10,linea);
                    linea=linea+15;
                    g.drawString("TOTAL PAGADOS B:         "+pagados+"          $"+tpagados, 10,linea);
                    linea=linea+15;
                    g.drawString("TOTAL PAGADOS:           "+pagados3+"          $"+tpagados3, 10,linea);
                 } 
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

}

  

class FormatoTexto {
    public static void outputtingBarcodeAsPNG(int siguiente,String lalaserie) throws BarcodeException {
        java.text.DecimalFormat nft = new  java.text.DecimalFormat("#000000.###");  
        nft.setDecimalSeparatorAlwaysShown(false); 
        Barcode barcode = BarcodeFactory.createCode128B(lalaserie+nft.format(siguiente));
        barcode.setDrawingText(false);
        barcode.setBarHeight(50);
        try {
            File f = new File("mybarcode.jpg");
            BarcodeImageHandler.saveJPEG(barcode, f);
        } catch (Exception e) { }
    }
    public static int drawStringMultiLine(Graphics g, String text, int lineWidth, int x, int y) {
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
                    //y=y+m.getHeight();
                }
            }
            if(currentLine.trim().length() > 0) {
                g.drawString(currentLine, x, y);
            }
        }
        return y;
    }
    public static String justificar(FontMetrics m,String currentLine){

     ArrayList<Integer> espacios = new ArrayList<>();
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
}