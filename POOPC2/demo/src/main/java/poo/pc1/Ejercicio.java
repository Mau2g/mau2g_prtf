package poo.pc1;

public class Ejercicio {
    public static void main(String[] args) {
        // Probar la clase Cuenta
        System.out.println("=== Prueba de la clase Cuenta ===");
        Cuenta cuenta1 = new Cuenta("Juan Pérez");
        Cuenta cuenta2 = new Cuenta("María García", 1000);

        System.out.println("Cuenta 1 - Titular: " + cuenta1.getTitular() + ", Saldo: " + cuenta1.getCantidad());
        System.out.println("Cuenta 2 - Titular: " + cuenta2.getTitular() + ", Saldo: " + cuenta2.getCantidad());

        cuenta1.ingresar(500);
        cuenta2.retirar(300);

        System.out.println("\nDespués de operaciones:");
        System.out.println("Cuenta 1 - Saldo: " + cuenta1.getCantidad());
        System.out.println("Cuenta 2 - Saldo: " + cuenta2.getCantidad());

        // Probar la clase Trabajador
        System.out.println("\n=== Prueba de la clase Trabajador ===");
        procesar();
    }

    // Método procesar para la clase Trabajador
    public static void procesar() {
        Trabajador trabajador = new Trabajador(1001, "Carlos López", 40, 25.0);
        System.out.println("Datos iniciales del trabajador:");
        listado(trabajador);

        // Incrementar 10 horas
        trabajador.horasTrabajadas += 10;
        System.out.println("\nDatos después de incrementar 10 horas:");
        listado(trabajador);
    }

    // Método listado para mostrar datos del trabajador
    public static void listado(Trabajador trabajador) {
        System.out.println("Código: " + trabajador.codigo);
        System.out.println("Nombre: " + trabajador.nombre);
        System.out.println("Horas trabajadas: " + trabajador.horasTrabajadas);
        System.out.println("Tarifa por hora: S/" + trabajador.tarifaPorHora);
        System.out.println("Sueldo bruto: S/" + trabajador.sueldoBruto());
        System.out.println("Descuento: S/" + trabajador.descuento());
        System.out.println("Sueldo neto: S/" + trabajador.sueldoNeto());
    }
}
