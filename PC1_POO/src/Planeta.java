public class Planeta {
    public enum Tipo { GASEOSO, TERRESTRE, ENANO }
    private String nombre;
    private int cantidadSatélites;
    private double masa;
    private double volumen;
    private double diámetro;
    private double distanciaSol;
    private Tipo tipo;
    private boolean visible;

    public Planeta(String nombre, int cantidadSatélites, double masa, double volumen, double diámetro, double distanciaSol, Tipo tipo, boolean visible) {
        this.nombre = nombre;
        this.cantidadSatélites = cantidadSatélites;
        this.masa = masa;
        this.volumen = volumen;
        this.diámetro = diámetro;
        this.distanciaSol = distanciaSol;
        this.tipo = tipo;
        this.visible = visible;
    }

    public void mostrarDatos() {
        System.out.println(nombre + ", Satélites: " + cantidadSatélites + ", Masa: " + masa + ", Volumen: " + volumen + ", Diámetro: " + diámetro + ", Distancia al Sol: " + distanciaSol + ", Tipo: " + tipo + ", Visible: " + visible);
    }

    public double densidad() {
        return masa / volumen;
    }

    public boolean esExterior() {
        return distanciaSol > 778.5;
    }

    public static void main(String[] args) {
        Planeta marte = new Planeta("Marte", 2, 6.39e23, 1.63e11, 6779, 227.9, Tipo.TERRESTRE, true);
        Planeta neptuno = new Planeta("Neptuno", 14, 1.02e26, 6.25e13, 49244, 4495.1, Tipo.GASEOSO, false);
        marte.mostrarDatos();
        System.out.println("Densidad: " + marte.densidad());
        System.out.println("Exterior: " + marte.esExterior());
        neptuno.mostrarDatos();
        System.out.println("Densidad: " + neptuno.densidad());
        System.out.println("Exterior: " + neptuno.esExterior());
    }
}
