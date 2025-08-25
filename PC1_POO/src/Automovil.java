public class Automovil {
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

    public Automovil(String marca, String modelo, String motor, Combustible combustible, Tipo tipo, int puertas, int asientos, int velocidadMax, Color color, boolean automatico) {
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
    }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public String getMotor() { return motor; }
    public void setMotor(String motor) { this.motor = motor; }
    public Combustible getCombustible() { return combustible; }
    public void setCombustible(Combustible combustible) { this.combustible = combustible; }
    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo tipo) { this.tipo = tipo; }
    public int getPuertas() { return puertas; }
    public void setPuertas(int puertas) { this.puertas = puertas; }
    public int getAsientos() { return asientos; }
    public void setAsientos(int asientos) { this.asientos = asientos; }
    public int getVelocidadMax() { return velocidadMax; }
    public void setVelocidadMax(int velocidadMax) { this.velocidadMax = velocidadMax; }
    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
    public int getVelocidadActual() { return velocidadActual; }
    public void setVelocidadActual(int velocidadActual) { this.velocidadActual = velocidadActual; }
    public boolean isAutomatico() { return automatico; }
    public void setAutomatico(boolean automatico) { this.automatico = automatico; }

    public void acelerar(int incremento) {
        if (velocidadActual + incremento > velocidadMax) {
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

    public double tiempoEstimado(double distancia) {
        if (velocidadActual == 0) return -1;
        return distancia / velocidadActual;
    }

    public void mostrarDatos() {
        System.out.println(marca + " " + modelo + ", Motor: " + motor + ", Combustible: " + combustible + ", Tipo: " + tipo + ", Puertas: " + puertas + ", Asientos: " + asientos + ", Velocidad Máx: " + velocidadMax + ", Color: " + color + ", Automático: " + automatico + ", Velocidad Actual: " + velocidadActual);
    }

    public static void main(String[] args) {
        Automovil auto = new Automovil("Toyota", "Corolla", "1.8L", Combustible.GASOLINA, Tipo.SEDAN, 4, 5, 180, Color.AZUL, true);
        auto.mostrarDatos();
        auto.acelerar(60);
        auto.mostrarDatos();
        auto.acelerar(150);
        auto.mostrarDatos();
        auto.desacelerar(30);
        auto.mostrarDatos();
        auto.frenar();
        auto.mostrarDatos();
    }
}
