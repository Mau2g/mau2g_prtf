public class Persona {
    private String nombre;
    private String apellido;
    private String dni;
    private int anioNacimiento;

    public Persona(String nombre, String apellido, String dni, int anioNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.anioNacimiento = anioNacimiento;
    }

    public void mostrarDatos() {
        System.out.println(nombre + " " + apellido + ", DNI: " + dni + ", Año de nacimiento: " + anioNacimiento);
    }

    public static void main(String[] args) {
        Persona p1 = new Persona("Lucia", "Ramirez", "12345678A", 1998);
        Persona p2 = new Persona("Carlos", "Mendoza", "87654321B", 2001);
        p1.mostrarDatos();
        p2.mostrarDatos();
    }
}
