
package javaapplication1;

import java.util.Map;

public class Autos {
    public String placas;
    public Db db;

    
    public String[] Agregar(String placas,String modelo,String usuario,String tarifa){
        String serie="";
        String folio="";
        db=new Db();
        String serieactual=db.SiguienteSerie();
        int siguiente=db.SiguienteFolio(serieactual);
        db.Consulta("INSERT INTO estacionados (TICKET,PLACAS,MARCA,TARIFA,ENTRADA,ESTATUS,USUARIOEMISION, SERIE) VALUES(\""+siguiente+"\",\""+placas+"\",\""+modelo+"\",\""+tarifa+"\",NOW(), \"Registro\",\""+usuario+"\",\""+serieactual+"\")");
        String[] resultado={serie,folio};
        return resultado;
    }
    
    public Map<String,String> Consultar(String Ticket,String Serie){
        db=new Db();
        Map<String,String> Boleto=db.ConsultarBoleto(Ticket, Serie);
        return Boleto;
    }
    
    public void Actualizar(String tiempo,String dias,String importe,String estado,String tarifa,String ticket,String serie){
        db=new Db();
        db.Consulta("UPDATE estacionados SET SALIDA=NOW(),TIEMPO=\""+tiempo+"\",DIAS=\""+dias+"\",IMPORTE=\""+importe+"\",ESTATUS=\""+estado+"\", TARIFA=\""+tarifa+"\"  WHERE TICKET= \""+ticket+"\" AND SERIE = \""+serie+"\" AND ESTATUS= \"Registro\"");
    
    
    }
    
    

    
    
}

