
package prueba2_javier_hernandez_22521001;


public class Banco {
    private CuentaBancaria[] cuentas = new CuentaBancaria[20];
    private int cantidadCuentas = 0;

    public void agregarCuenta(CuentaBancaria c) {
        if (cantidadCuentas >= cuentas.length) {
            throw new IllegalStateException("El banco ha alcanzado el límite máximo de cuentas (20).");
        }
        try {
            buscarPorNumero(c.getNumero());
            throw new IllegalArgumentException("El número de cuenta " + c.getNumero() + " ya existe.");
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("ya existe")) {
                throw e; // Relanza el error si es duplicado
            }
        }
        cuentas[cantidadCuentas] = c;
        cantidadCuentas++;
    }
    public CuentaBancaria buscarPorNumero(int numero) {
        return buscarPorNumeroAux(numero, 0);
    }
    
    private CuentaBancaria buscarPorNumeroAux(int numero, int indice) {
        if (indice >= cantidadCuentas) {
            throw new IllegalArgumentException("No se encontró ninguna cuenta con el número: " + numero);
        }
        if (cuentas[indice].getNumero() == numero) {
            return cuentas[indice];
        }
        return buscarPorNumeroAux(numero, indice + 1);
    }
    
    public void ordenarPorSaldo() {
        ordenarPorSaldoAux(0, 0);
    }
    
    private void ordenarPorSaldoAux(int i, int j) {
        if (i >= cantidadCuentas - 1) {
            return;
        }

        if (j < cantidadCuentas - 1 - i) {
            if (cuentas[j].getSaldo() > cuentas[j + 1].getSaldo()) {
                CuentaBancaria aux = cuentas[j];
                cuentas[j] = cuentas[j + 1];
                cuentas[j + 1] = aux;
            }
            ordenarPorSaldoAux(i, j + 1);
        } else {
            ordenarPorSaldoAux(i + 1, 0);
        }
    }
    public void transferir(int numeroOrigen, int numeroDestino, double monto) {
        if (numeroOrigen == numeroDestino) {
            throw new IllegalArgumentException("La cuenta origen y destino no pueden ser la misma.");
        }

        CuentaBancaria origen = buscarPorNumero(numeroOrigen);
        CuentaBancaria destino = buscarPorNumero(numeroDestino);

        boolean retirpExitoso = origen.retirar(monto);
        if (!retirpExitoso) {
            throw new IllegalStateException("Fondos insuficientes o retiro no permitido en la cuenta de origen.");
        }

        try {
            destino.depositar(monto);
        } catch (Exception e) {
            
            origen.reintegrar(monto);
            throw new IllegalStateException("Error al depositar en la cuenta destino. Transferencia cancelada y dinero revertido. Causa: " + e.getMessage());
        }
    
        }
    
    public void aplicarInteresesGlobal() {
        for (int i = 0; i < cantidadCuentas; i++) {
            try {
                cuentas[i].registrarIntereses();
            } catch (Exception e) {
                System.out.println("Error aplicando intereses a la cuenta #" + cuentas[i].getNumero() + ": " + e.getMessage());
            }
        }
    }
    
    public double saldoTotalDelBanco() {
        return saldoTotalDelBancoAux(0);
    }
    
    private double saldoTotalDelBancoAux(int indice) {
        if (indice >= cantidadCuentas) {
            return 0.0;
        }
        return cuentas[indice].getSaldo() + saldoTotalDelBancoAux(indice + 1);
    }
    
   public void generarReporte(){
     ordenarPorSaldo();
       System.out.println("\n========= REPORTE DE CUENTAS (ORDEBADS POR SALDO) ===========");
       for (int i = 0; i < cantidadCuentas; i++) {
           System.out.println(cuentas[i].toString());
       }
       System.out.println("===========================================");
       System.out.println("SALDO TOTAL EN EL BANCO: " + saldoTotalDelBanco());
      
   }
}
