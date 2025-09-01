/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.docente;

/**
 *
 * @author LAB-USR-AREQUIPA
 */
public class Docente {
    private String nombre;
    private String apellido;
    private int horas;
    private String tipo;

    public Docente(String nombre, String apellido, int horas, String tipo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.horas = horas;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    
     
    }
   public double sueldobruto(){
      double tarifa;
      if (tipo == null) tarifa = 0;
      else if (tipo.equalsIgnoreCase("Ciencia")) tarifa = 3;
      else if (tipo.equalsIgnoreCase("Letras")) tarifa = 5;
      else tarifa = 0;
      return horas*tarifa;
    }
    public double descuento(){
        return sueldobruto()*0.1;}
        
     public double sueldoneto(){
         return sueldobruto()- descuento();}

      public static void main(String[] args){
          Docente doc2 = new Docente("Juan", "Perez", 4, "Letras");
        
         System.out.println("Nombre: " + doc2.getNombre()); 
         System.out.println("Apellido: " + doc2.getApellido()); 
         System.out.println("Tipo: " + doc2.getTipo());
         System.out.println("Sueldo: " + doc2.sueldoneto());
      }
}
    
   
   
