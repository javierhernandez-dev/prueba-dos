
package prueba2_javier_hernandez_22521001;


public class TipoCuenta {
    private final String nombre;
    private final double tasa;
    
    public static final TipoCuenta AHORRO = new TipoCuenta("AHORRO", 0.03);
    public static final TipoCuenta CHEQUES = new TipoCuenta("CHEQUES",0.00);
    public static final TipoCuenta PLAZO = new TipoCuenta("Plazo", 0.05);
    
    private TipoCuenta(String nombre, double tasa) {
        this.nombre = nombre;
        this.tasa = tasa;
    }

    public double tasa() {
        return this.tasa;
    }

    public String toString() {
        return this.nombre;
    }
    
}
