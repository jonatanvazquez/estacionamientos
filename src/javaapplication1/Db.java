
package javaapplication1;

import java.util.*;
import simplemysql.SimpleMySQL;
import simplemysql.SimpleMySQLResult;


public class Db {
    SimpleMySQL mysql;
    SimpleMySQLResult rs;
    SimpleMySQLResult re;
    String usuario=JDLogin.elusuario;
    
    //CONEXION
        String dbhost="localhost";
        String dbuser="root";
        String dbpassword="x4899954";
        String dbdb="estacionamientos";
        
    public SimpleMySQLResult  Consulta(String query){
        
        mysql = new SimpleMySQL();
        mysql.connect(dbhost,dbuser,dbpassword,dbdb);
        rs = mysql.Query (query);
        return rs;
    
    }
    public String[]           ListaTarifas(){
        
        ArrayList<String> Tarifas = new ArrayList();
        rs=this.Consulta("SELECT DESCRIPCION FROM tarifas ORDER BY ID ASC");
        while (rs.next()){
                        Tarifas.add(rs.getString("DESCRIPCION"));
         }
        rs.close();
        mysql.close();
        String[] ListaTarifas=Tarifas.toArray(new String[Tarifas.size()]);
        
        return ListaTarifas;
    
    }
    public int                CajonesDisponibles(){
        int CajonesDisponibles;
        int DisponibilidadCajones=Integer.parseInt(this.ObtenerOpcion("CAJONES"));
        int Ocupados=this.CajonesOcupados();
        CajonesDisponibles=DisponibilidadCajones-Ocupados;
    
        return CajonesDisponibles;
    }
    public int                CajonesOcupados(){
        int CajonesOcupados;
        rs=this.Consulta("SELECT * FROM estacionados WHERE SALIDA IS NULL ORDER BY ID DESC ");
        CajonesOcupados=rs.getNumRows();
        rs.close();
        mysql.close();
        return CajonesOcupados;
    }
    public String             ObtenerOpcion(String opcion){
        
        String valor;
        rs=this.Consulta(String.format("SELECT VALOR FROM opciones WHERE OPCION= '%s'",opcion));
        valor=rs.getString("VALOR");
        rs.close();
        mysql.close();
        return valor;
    
    }
    public String             SiguienteSerie(){
        String SiguienteSerie ;
       String serieactual=this.ObtenerOpcion("SERIEACTUAL");
       int seriea = Integer.parseInt(this.ObtenerOpcion("SERIEA"));
       int contserie = Integer.parseInt(this.ObtenerOpcion("VECESSERIE"));
       int serieb = Integer.parseInt(this.ObtenerOpcion("SERIEB"));
       seriea--;
       serieb--;
       if(null != serieactual)switch (serieactual) {
            case "A":
                if(contserie>=seriea&&serieb>=0){ contserie=0; serieactual="B"; }else{ contserie++; }
                break;
            case "B":
                if(contserie>=serieb){ contserie=0; serieactual="A"; }else{ contserie++; }
                break;
        }
        
       this.Consulta ("UPDATE opciones SET VALOR= \""+serieactual+"\" WHERE ID=4");
       this.Consulta ("UPDATE opciones SET VALOR=\""+contserie+"\" WHERE ID=5");
    
       SiguienteSerie=serieactual;
       
       return SiguienteSerie;
    
    }
    public int                SiguienteFolio(String serieactual){
        
        int SiguienteFolio=2;
        System.out.println(serieactual);
        rs=this.Consulta("SELECT TICKET FROM estacionados WHERE SERIE = \""+serieactual+"\" ORDER BY ID DESC LIMIT 1");
        if (rs.next()) {
            SiguienteFolio = Integer.parseInt(rs.getString("TICKET"));
        }else{
            SiguienteFolio=1;
        }
        
        SiguienteFolio++;
        return SiguienteFolio;
    }
    public Map<String,String> UltimoBoleto(){
        Map<String,String> UltimoBoleto;
        rs=this.Consulta("SELECT * FROM estacionados ORDER BY ID DESC LIMIT 1");
        UltimoBoleto=rs.FetchAssoc();
        rs.close();
        mysql.close();
        return UltimoBoleto;
    }
    public Map<String,String> ConsultarBoleto(String Ticket,String Serie){
        Map<String,String> Boleto;
        rs=this.Consulta("SELECT *,NOW() AS ahora FROM estacionados WHERE TICKET= \""+Ticket+"\" AND SERIE = \""+Serie+"\" ORDER BY ID DESC LIMIT 1");
        Boleto=rs.FetchAssoc();
        rs.close();
        mysql.close();
        return Boleto;
    }
    public Map<String,String> ConsultarBoletoPagado(String Ticket,String Serie){
        Map<String,String> Boleto;
        rs=this.Consulta("SELECT *,NOW() AS ahora FROM estacionados WHERE TICKET= \""+Ticket+"\" AND SERIE = \""+Serie+"\" AND ESTATUS= \"Registro\" ORDER BY ID DESC LIMIT 1");
        Boleto=rs.FetchAssoc();
        rs.close();
        mysql.close();
        return Boleto;
    }
    public Map<String,String> Tarifa(String Descripcion){
        Map<String,String> Tarifa;
        rs=this.Consulta("SELECT * FROM tarifas WHERE DESCRIPCION= \""+Descripcion+"\"");
        Tarifa=rs.FetchAssoc();
        rs.close();
        mysql.close();
        return Tarifa;
    }
    public Map<String,String> CorteAbierto(){
        Map<String,String> Corte;
        rs=this.Consulta("SELECT *,NOW() AS ahora FROM cortes WHERE FECHAFIN IS NULL");
        Corte=rs.FetchAssoc();
        rs.close();
        mysql.close();
        return Corte;
    }
    public int                CerrarCorte(){
        int Corte;
        rs=this.Consulta("SELECT ID FROM cortes WHERE FECHAFIN IS NULL");
        Corte=Integer.parseInt(rs.getString("ID"));
        rs=this.Consulta("UPDATE cortes SET FECHAFIN= NOW() WHERE FECHAFIN IS NULL");
        rs=this.Consulta("UPDATE estacionados SET USUARIOCORTE= \""+usuario+"\"  WHERE USUARIOCORTE IS NULL AND ESTATUS != \"Registro\" ");
        return Corte;
    }
    public void               AbrirCorte(){
        rs=this.Consulta("INSERT INTO cortes (USUARIO,FECHAINICIO) VALUES(\""+usuario+"\",NOW())");
    }
    public Map<String,String> FechasCorte(int Corte){
        Map<String,String> Fechas= new HashMap<>();
        rs=this.Consulta("SELECT * FROM cortes WHERE ID="+Integer.toString(Corte)+"");
        Fechas.put("Inicio", rs.getString("FECHAINICIO"));
        Fechas.put("Final", rs.getString("FECHAFIN"));

        rs.close();
        mysql.close();
        return Fechas;
    }
    public Map<String,Map>    ListaBoletos(String inicio,String ultimo,String Serie){
        
        Map<String,String> Folios = new HashMap<>();
        Map<String,Map> Resultado = new HashMap<>();
        Map<Integer,Map> Boletos= new HashMap<>();
        String[] Tarifas=this.ListaTarifas();
        HashMap<String,Integer> BoletosTarifa = new HashMap<>();
        HashMap<String,Integer> TotalesTarifa = new HashMap<>();
        for (String Tarifa : Tarifas) { BoletosTarifa.put(Tarifa, 0); TotalesTarifa.put(Tarifa, 0);}
        
        rs=this.Consulta("SELECT * FROM estacionados WHERE ENTRADA >= \""+inicio+"\" AND ENTRADA <= \""+ultimo+"\" AND SERIE= \""+Serie+"\" AND SALIDA!=\"NULL\" ORDER BY ID DESC");
        Integer contador=0,tolerancias=0,normales=0,total=0,cancelados=0,perdidos=0;
        Integer NumeroFolios=rs.getNumRows();
        
        for (int i = 0; i < NumeroFolios; i++) {
            Map<String,String> Fila=rs.FetchAssoc();
            Fila.put("IDTARIFA", Integer.toString(Arrays.asList(Tarifas).indexOf(Fila.get("TARIFA"))));
            
            Boletos.put(contador++, Fila);
            if(BoletosTarifa.containsKey(Fila.get("TARIFA"))){
                BoletosTarifa.put(Fila.get("TARIFA"),BoletosTarifa.get(Fila.get("TARIFA"))+1);
                TotalesTarifa.put(Fila.get("TARIFA"),TotalesTarifa.get(Fila.get("TARIFA"))+Integer.parseInt(Fila.get("IMPORTE")));
            }
            if("CANCELADO".equals(Fila.get("ESTATUS"))){cancelados++; }else{
            if("BOLETOPERDIDO".equals(Fila.get("ESTATUS"))){perdidos++;}
            if("0".equals(Fila.get("IMPORTE"))){tolerancias++; }else{ normales++; }  
            total=total+Integer.parseInt(Fila.get("IMPORTE"));
            
            }
        }
        Folios.put("Final", Boletos.get(0).get("TICKET").toString());
        
        Folios.put("Inicial", Boletos.get(contador-1).get("TICKET").toString());
        Folios.put("Tolerancias", Integer.toString(tolerancias));
        Folios.put("Normales", Integer.toString(normales));
         
        Folios.put("Total", Integer.toString(total));
        Folios.put("NumeroFolios", Integer.toString(NumeroFolios));
        Folios.put("Cancelados", Integer.toString(cancelados));
        Folios.put("Perdidos", Integer.toString(perdidos));
       
        Resultado.put("Boletos", Boletos);
        Resultado.put("Folios", Folios);
        Resultado.put("BoletosTarifa", BoletosTarifa);
        Resultado.put("TotalesTarifa", TotalesTarifa);
        
        rs.close();
        mysql.close();
        return Resultado;
    }
    public Map<Integer,Map>   ListaEstacionados(String inicio,String ultimo,String Serie){
        Map<Integer,Map> ListaEstacionados2 = new HashMap<>();
        Integer contador=0;
        rs=this.Consulta("SELECT * FROM estacionados WHERE ENTRADA >= \""+inicio+"\" AND ENTRADA <= \""+ultimo+"\" AND SALIDA IS NULL AND SERIE= \""+Serie+"\"  ORDER BY ID DESC");
        Integer NumeroFolios=rs.getNumRows();
        for (int i = 0; i < NumeroFolios; i++) {
            Map<String,String> Fila=rs.FetchAssoc();
            ListaEstacionados2.put(contador++, Fila);
        }
        return ListaEstacionados2;
    }
    public int                Estacionados(String inicio,String ultimo,String Serie){
        rs=this.Consulta("SELECT * FROM estacionados WHERE ENTRADA >= \""+inicio+"\" AND ENTRADA <= \""+ultimo+"\" AND SALIDA IS NULL AND SERIE= \""+Serie+"\"  ORDER BY ID DESC ");
        int Estacionados=rs.getNumRows();
        rs.close();
        mysql.close();
        return Estacionados;
    }
    public List<String[]> ListaCortes(){
        List<String[]> Cortes = new ArrayList<>();
        rs=this.Consulta("SELECT * FROM cortes");
        while (rs.next()){
            String ID=rs.getString("ID");
            String USUARIO=rs.getString("USUARIO");
            String FECHAINICIO=rs.getString("FECHAINICIO");
            String FECHAFIN=rs.getString("FECHAFIN");
            String fila []= { ID,USUARIO,FECHAINICIO,FECHAFIN};
            Cortes.add(fila);
        }
        
        return Cortes;
    }
    
    
    
}
