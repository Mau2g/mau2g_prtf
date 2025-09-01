package poo.pc1;

public class Trabajador {
    // Atributos públicos
    public int codigo;
    public String nombre;
    public int horasTrabajadas;
    public double tarifaPorHora;

    // Constructor
    public Trabajador(int codigo, String nombre, int horasTrabajadas, double tarifaPorHora) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.horasTrabajadas = horasTrabajadas;
        this.tarifaPorHora = tarifaPorHora;
    }

    // Método que retorna el sueldo bruto
    public double sueldoBruto() {
        return horasTrabajadas * tarifaPorHora;
    }

    // Método que retorna el descuento (15% del sueldo bruto)
    public double descuento() {
        return sueldoBruto() * 0.15;
    }

    // Método que retorna el sueldo neto
    public double sueldoNeto() {
        return sueldoBruto() - descuento();
    }

    // Método para mostrar los datos del trabajador
    public void mostrarDatos() {
        System.out.println("Código: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Horas trabajadas: " + horasTrabajadas);
        System.out.println("Tarifa por hora: S/" + tarifaPorHora);
        System.out.println("Sueldo bruto: S/" + sueldoBruto());
        System.out.println("Descuento: S/" + descuento());
        System.out.println("Sueldo neto: S/" + sueldoNeto());
    }

    // Método main para probar la funcionalidad
    public static void main(String[] args) {
        // Crear un trabajador con datos de prueba
        Trabajador trabajador = new Trabajador(1001, "Carlos López", 40, 25.0);
        
        // Mostrar datos iniciales
        System.out.println("=== Datos iniciales del trabajador ===");
        trabajador.mostrarDatos();

        // Incrementar 10 horas
        trabajador.horasTrabajadas += 10;
        
        // Mostrar datos después del incremento
        System.out.println("\n=== Datos después de incrementar 10 horas ===");
        trabajador.mostrarDatos();
    }
}
