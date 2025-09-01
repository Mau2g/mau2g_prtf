package poo.pc1;

public class Cuenta {
    private String titular;
    private double cantidad;

    // Constructor con titular obligatorio
    public Cuenta(String titular) {
        this.titular = titular;
        this.cantidad = 0;
    }

    // Constructor con titular y cantidad
    public Cuenta(String titular, double cantidad) {
        this.titular = titular;
        this.cantidad = cantidad;
    }

    // Métodos get y set
    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    // Método para ingresar dinero
    public void ingresar(double cantidad) {
        if (cantidad > 0) {
            this.cantidad += cantidad;
        }
    }

    // Método para retirar dinero
    public void retirar(double cantidad) {
        if (this.cantidad - cantidad < 0) {
            this.cantidad = 0;
        } else {
            this.cantidad -= cantidad;
        }
    }

    // Método main para probar la funcionalidad
    public static void main(String[] args) {
        // Crear cuenta solo con titular
        Cuenta cuenta1 = new Cuenta("Juan Pérez");
        System.out.println("Cuenta 1:");
        System.out.println("Titular: " + cuenta1.getTitular());
        System.out.println("Saldo inicial: " + cuenta1.getCantidad());

        // Crear cuenta con titular y cantidad inicial
        Cuenta cuenta2 = new Cuenta("María García", 1000);
        System.out.println("\nCuenta 2:");
        System.out.println("Titular: " + cuenta2.getTitular());
        System.out.println("Saldo inicial: " + cuenta2.getCantidad());

        // Probar método ingresar
        cuenta1.ingresar(500);
        System.out.println("\nCuenta 1 después de ingresar 500:");
        System.out.println("Nuevo saldo: " + cuenta1.getCantidad());

        // Probar método retirar
        cuenta2.retirar(300);
        System.out.println("\nCuenta 2 después de retirar 300:");
        System.out.println("Nuevo saldo: " + cuenta2.getCantidad());

        // Probar caso especial: retirar más de lo disponible
        cuenta1.retirar(1000);
        System.out.println("\nCuenta 1 después de intentar retirar más de lo disponible:");
        System.out.println("Saldo final: " + cuenta1.getCantidad());
    }
}
