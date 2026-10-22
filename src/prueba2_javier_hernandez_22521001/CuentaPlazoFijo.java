
package prueba2_javier_hernandez_22521001;

import java.util.Calendar;
public class CuentaPlazoFijo extends CuentaBancaria{
    private Calendar findePlazo;
    private double intereses;

    public CuentaPlazoFijo(int numero, String cliente, String moneda) {
        super(numero, cliente, moneda);
        this.intereses = 0.0;
        this.findePlazo = null;
    }

    public void setPlazo(int anio, int mes, int dia) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setLenient(false);
            cal.set(anio, mes - 1, dia);
            cal.getTime(); // Fuerza la validación de la fecha
            this.findePlazo = cal;
        } catch (Exception e) {
            throw new IllegalArgumentException("Fecha de vencimiento inválida: " + e.getMessage());
        }
    }

    public boolean estaVencida() {
        if (findePlazo == null) return false;
        Calendar actual = Calendar.getInstance();
        return !actual.before(findePlazo);
    }

    @Override
    public boolean retirar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser mayor a cero.");
        }

        if (!estaVencida()) {
            if (monto > intereses) {
                return false;
            }
            intereses -= monto;
            registrarMovimiento("RETIRO INTERESES -" + monto + "  intereses rest: " + intereses);
            return true;
        } else {
            if (intereses > 0) {
                this.saldo += intereses;
                registrarMovimiento("CONSOLIDACION INTERESES +" + intereses + "  saldo: " + this.saldo);
                intereses = 0;
            }
            return super.retirar(monto);
        }
            
        }
    
    @Override
    public void registrarIntereses() {
        if (!estaVencida()) {
            double interesGenerado = saldo * tipo.tasa();
            intereses += interesGenerado;
            registrarMovimiento("INTERES ACUMULADO +" + interesGenerado + "  total int: " + intereses);
        } else {
            super.registrarIntereses();
        }
    }

    @Override
    public void reintegrar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a reintegrar debe ser mayor a cero.");
        }
        if (!estaVencida()) {
            intereses += monto;
            registrarMovimiento("REVERSA INTERESES +" + monto + "  intereses: " + intereses);
        } else {
            super.reintegrar(monto);
        }
    }

    @Override
    public void quienSoy() {
        System.out.println("Soy una CuentaPlazoFijo");
    }

    @Override
    public String toString() {
        String fechaVenc;
        if (findePlazo != null) {
            fechaVenc = findePlazo.getTime().toString();
        } else {
            fechaVenc = "No definida";
        }
        return super.toString() + " | Vencimiento: " + fechaVenc + " | Int. Acumulados: " + intereses + " | Vencida: " + estaVencida();
    }
}
