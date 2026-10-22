/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba2_javier_hernandez_22521001;


public class CuentaCheques extends CuentaBancaria {
    private double limiteSobregiro;

    public CuentaCheques(int numero, String cliente, String moneda, double limiteSobregiro) {
        super(numero, cliente, moneda);
        if (limiteSobregiro < 0) {
            throw new IllegalArgumentException("El límite de sobregiro no puede ser negativo.");
        }
        this.limiteSobregiro = limiteSobregiro;
    }
    
    public void girarCheque(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto del cheque debe ser mayor a cero.");
        }
        if (monto > (saldo + limiteSobregiro)) {
            throw new IllegalStateException("Fondos insuficientes para girar el cheque");
        }
        this.saldo -= monto;
        registrarMovimiento("CHEQUE GIRO -" + monto + " -> saldo: " + this.saldo);
    }

    @Override
    public void quienSoy() {
        System.out.println("Soy una CuentaCheques");
    }

    public String toString() {
        double sobregiroDisponible;
        if (saldo < 0) {
            sobregiroDisponible = limiteSobregiro + saldo;
        } else {
            sobregiroDisponible = limiteSobregiro;
        }
        return super.toString() + " | Sobregiro Max: " + limiteSobregiro + " | Sobregiro Disp: " + sobregiroDisponible;
    }
}
