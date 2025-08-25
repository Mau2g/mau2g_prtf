public class AutomovilMulta {
    public enum Combustible { GASOLINA, DIESEL, ELECTRICO, HIBRIDO }
    public enum Tipo { SEDAN, SUV, COUPE, PICKUP }
    public enum Color { ROJO, AZUL, NEGRO, BLANCO, GRIS }
    private String marca;
    private String modelo;
    private String motor;
    private Combustible combustible;
    private Tipo tipo;
    private int puertas;
    private int asientos;
    private int velocidadMax;
    private Color color;
    private int velocidadActual;
    private boolean automatico;
    private int cantidadMultas;
    private static final int MULTA_EXCESO = 350;

    public AutomovilMulta(String marca, String modelo, String motor, Combustible combustible, Tipo tipo, int puertas, int asientos, int velocidadMax, Color color, boolean automatico) {
        this.marca = marca;
        this.modelo = modelo;
        this.motor = motor;
        this.combustible = combustible;
        this.tipo = tipo;
        this.puertas = puertas;
        this.asientos = asientos;
        this.velocidadMax = velocidadMax;
        this.color = color;
        this.automatico = automatico;
        this.velocidadActual = 0;
        this.cantidadMultas = 0;
    }

    public void acelerar(int incremento) {
        if (velocidadActual + incremento > velocidadMax) {
            cantidadMultas++;
            velocidadActual = velocidadMax;
        } else {
            velocidadActual += incremento;
        }
    }

    public void desacelerar(int decremento) {
        if (velocidadActual - decremento < 0) {
            velocidadActual = 0;
        } else {
            velocidadActual -= decremento;
        }
    }

    public void frenar() {
        velocidadActual = 0;
    }

    public boolean tieneMultas() {
        return cantidadMultas > 0;
    }

    public int totalMultas() {
        return cantidadMultas * MULTA_EXCESO;
    }

    public void mostrarDatos() {
        System.out.println(marca + " " + modelo + ", Motor: " + motor + ", Combustible: " + combustible + ", Tipo: " + tipo + ", Puertas: " + puertas + ", Asientos: " + asientos + ", Velocidad Máx: " + velocidadMax + ", Color: " + color + ", Automático: " + automatico + ", Velocidad Actual: " + velocidadActual + ", Multas: " + cantidadMultas);
    }

    public static void main(String[] args) {
        AutomovilMulta auto = new AutomovilMulta("Honda", "Civic", "2.0L", Combustible.GASOLINA, Tipo.COUPE, 2, 4, 200, Color.ROJO, false);
        auto.mostrarDatos();
        auto.acelerar(100);
        auto.mostrarDatos();
        auto.acelerar(150);
        auto.mostrarDatos();
        auto.acelerar(60);
        auto.mostrarDatos();
        System.out.println("¿Tiene multas? " + auto.tieneMultas());
        System.out.println("Total a pagar: " + auto.totalMultas());
    }
}
