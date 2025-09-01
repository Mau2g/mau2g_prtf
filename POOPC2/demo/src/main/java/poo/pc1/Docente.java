package poo.pc1;

public class Docente {
 private String nombre;
 private String apellido; 
 private int horas;
 private String tipo;
 
 //a) constructor público que inicializa a todos los atributos
    public Docente(String nombre, String apellido, int horas, String tipo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.horas = horas;
        this.tipo = tipo;
    }

 //b) Métodos de acceso: set/get para los atributos.
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

  //c) Un método que retorne el nombre completo (nombre y apellido)
    public String nombrecompleto() {return nombre + " " + apellido;
    }
    
  //d) Un método que retorne el suelto Bruto (Ciencia=3; Letras=5)
    public double sueldobruto(){
      double tarifa;
      if (tipo == null) tarifa = 0;
      else if (tipo.equalsIgnoreCase("Ciencia")) tarifa = 3;
      else if (tipo.equalsIgnoreCase("Letras")) tarifa = 5;
      else tarifa = 0;
      return horas*tarifa;
    }
    
  //e) Un método que retorne el descuento del 0.10
    public double descuento(){return sueldobruto()*0.1;}
    
  //f) Un método que retorne el sueldo neto 
    public double sueldoneto(){return sueldobruto()- descuento();}
    
    public static void main(String[] args) {
        Docente d1 = new Docente("Tulio", "Checo", 30, "Ciencia");
        System.out.println("Docente: " + d1.nombrecompleto());
        System.out.println("Tipo : " + d1.getTipo());
        System.out.println("Sueldo Bruto : " + d1.sueldobruto());
        System.out.println("Sueldo Neto : " + d1.sueldoneto());   
    }
}

