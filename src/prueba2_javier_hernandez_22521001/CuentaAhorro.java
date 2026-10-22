
package prueba2_javier_hernandez_22521001;

import java.util.Calendar;
public class CuentaAhorro extends CuentaBancaria {
    protected Calendar ultimoUso;
    
    public CuentaAhorro(int numero, String cliente, String moneda) {
        super(numero, cliente, moneda);
        this.ultimoUso = Calendar.getInstance();
    }
    
    public boolean estaActiva() {
        Calendar limite6Meses = Calendar.getInstance();
        limite6Meses.add(Calendar.MONTH, -6);
        return ultimoUso.after(limite6Meses);
    }
    
    @Override
    public void depositar(double monto) {
        if (!estaActiva()) {
            throw new IllegalStateException("La cuenta de ahorro está inactiva (más de 6 meses sin uso).");
        }
        super.depositar(monto);
        this.ultimoUso = Calendar.getInstance();
    }
    
   @Override
    public boolean retirar(double monto) {
        if (!estaActiva()) {
            throw new IllegalStateException("La cuenta de ahorro está inactiva (más de 6 meses sin uso).");
        }
        boolean exito = super.retirar(monto);
        if (exito) {
            this.ultimoUso = Calendar.getInstance();
        }
        return exito;
    }

   @Override
    public void quienSoy() {
        System.out.println("Soy una CuentaAhorro");
    }

    @Override
    public String toString() {
        return super.toString() + " | Ultimo Uso: " + ultimoUso.getTime() + " | Activa: " + estaActiva();
    }
    
}
