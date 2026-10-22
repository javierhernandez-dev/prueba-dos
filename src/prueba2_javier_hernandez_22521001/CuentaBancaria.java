
package prueba2_javier_hernandez_22521001;

import java.util.Calendar;
public class CuentaBancaria {
    protected int numero;
    protected double saldo;
    protected String cliente;
    protected String moneda;
    protected TipoCuenta tipo;
    protected Calendar apertura;
    protected String [] historial = new String[50];
    protected int cantidadMovimientos=0;
    
    public CuentaBancaria(int numero, String cliente, String moneda){
      this.numero = numero;
      this.cliente = cliente;
      this.moneda = moneda;
      this.tipo = tipo;
      this.saldo=0.0;
      this.apertura= Calendar.getInstance();
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }
    
    public void depositar(double monto){
      if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor a cero.");
        }
        this.saldo += monto;
        registrarMovimiento("DEPOSITO +" + monto + " -> saldo: " + this.saldo);
    }
    
    
    public boolean retirar(double monto){
      if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser mayor a cero.");
        }
        if (monto > saldo) {
            return false;
        }
       if (monto > saldo) {
            return false;
        }
        this.saldo -= monto;
        registrarMovimiento("RETIRO -" + monto + " -> saldo: " + this.saldo);
        return true;
    }
    
    public void reintegrar(double monto){
      if(monto <= 0){
      throw new IllegalArgumentException("El monto a reintegrar debe ser mayor a cero.");
      }
     this.saldo += monto;
        registrarMovimiento("REVERSA +" + monto + " -> saldo: " + this.saldo);
    
    }
    
    public void registrarIntereses(){
      double interes = saldo * tipo.tasa();
        if (interes > 0) {
            this.saldo += interes;
            registrarMovimiento("INTERESES +" + interes + " -> saldo: " + this.saldo);
        }
    }
    
    public void quienSoy(){
        System.out.println("CuentaBancaria (CuentaBancaria (Clase base)");
    
    }
    
    public void registrarMovimiento(String linea) {
        try {
            if (cantidadMovimientos >= historial.length) {
                throw new IllegalStateException("Historial de movimientos lleno.");
            }
            historial[cantidadMovimientos] = linea;
            cantidadMovimientos++;
        } catch (IllegalStateException e) {
            System.out.println("ADVERTENCIA: " + e.getMessage() + " La transaccion se completó pero no se registró en el historial.");
        }
    }
    
    public void imprimirHistorial() {
        System.out.println("=== HISTORIAL DE CUENTA #" + numero + " ===");
        for (int i = 0; i < cantidadMovimientos; i++) {
            System.out.println((i + 1) + ". " + historial[i]);
        }
    }

    public String toString() {
        return "Cuenta #" + numero + " | Cliente: " + cliente + " | Saldo: " + saldo + " " + moneda+ " | Tipo: " + tipo + " | Apertura: " + apertura.getTime();
    }
    
    
}
