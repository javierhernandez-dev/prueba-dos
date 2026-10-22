
package prueba2_javier_hernandez_22521001;

import java.util.Scanner;
public class Prueba2_Javier_Hernandez_22521001 {

   
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner entrada = new Scanner(System.in);
        Banco banco = new Banco();
        int opcion = 0;

        do {
            try {
                System.out.println("\n================ MENÚ BANCO FINANZAS DEL VALLE ================");
                System.out.println("1. Crear cuenta");
                System.out.println("2. Depositar");
                System.out.println("3. Retirar");
                System.out.println("4. Transferir");
                System.out.println("5. Aplicar intereses globales");
                System.out.println("6. Ver reporte general y saldo total");
                System.out.println("7. Ver historial de movimientos");
                System.out.println("8. Girar cheque (Solo cuentas de Cheques)");
                System.out.println("9. Salir");
                System.out.print("Seleccione una opción: ");

                opcion = entrada.nextInt();
                entrada.nextLine(); 

                switch (opcion) {
                    case 1: {
                        System.out.println("\n--- TIPO DE CUENTA ---");
                        System.out.println("1. Ahorro");
                        System.out.println("2. Cheques");
                        System.out.println("3. Plazo Fijo");
                        System.out.print("Seleccione tipo: ");
                        int tipoSel = entrada.nextInt();

                        System.out.print("Número de cuenta: ");
                        int num = entrada.nextInt();
                        entrada.nextLine(); 
                        System.out.print("Nombre del cliente: ");
                        String cliente = entrada.nextLine();
                        System.out.print("Moneda (Lempiras/Dolares): ");
                        String moneda = entrada.nextLine();

                        if (tipoSel == 1) {
                            CuentaAhorro ca = new CuentaAhorro(num, cliente, moneda);
                            banco.agregarCuenta(ca);
                            System.out.println("¡Cuenta de Ahorros creada exitosamente!");
                        } else if (tipoSel == 2) {
                            System.out.print("Límite de sobregiro: ");
                            double sg = entrada.nextDouble();
                            entrada.nextLine(); 
                            CuentaCheques cc = new CuentaCheques(num, cliente, moneda, sg);
                            banco.agregarCuenta(cc);
                            System.out.println("¡Cuenta de Cheques creada exitosamente!");
                        } else if (tipoSel == 3) {
                            CuentaPlazoFijo cpf = new CuentaPlazoFijo(num, cliente, moneda);
                            System.out.print("Año de vencimiento (YYYY): ");
                            int anio = entrada.nextInt();
                            System.out.print("Mes de vencimiento (1-12): ");
                            int mes = entrada.nextInt();
                            System.out.print("Día de vencimiento (1-31): ");
                            int dia = entrada.nextInt();
                            entrada.nextLine(); 
                            
                            cpf.setPlazo(anio, mes, dia);
                            banco.agregarCuenta(cpf);
                            System.out.println("¡Cuenta de Plazo Fijo creada exitosamente!");
                        } else {
                            System.out.println("Tipo de cuenta inválido.");
                        }
                        break;
                    }

                    case 2: {
                        System.out.print("Número de cuenta: ");
                        int num = entrada.nextInt();
                        System.out.print("Monto a depositar: ");
                        double monto = entrada.nextDouble();
                        entrada.nextLine(); 

                        CuentaBancaria c = banco.buscarPorNumero(num);
                        c.depositar(monto);
                        System.out.println("¡Depósito realizado correctamente!");
                        break;
                    }

                    case 3: {
                        System.out.print("Número de cuenta: ");
                        int num = entrada.nextInt();
                        System.out.print("Monto a retirar: ");
                        double monto = entrada.nextDouble();
                        entrada.nextLine(); 

                        CuentaBancaria c = banco.buscarPorNumero(num);
                        if (c.retirar(monto)) {
                            System.out.println("¡Retiro realizado con éxito!");
                        } else {
                            System.out.println("Operación denegada: Fondos insuficientes o restricción de retiro en la cuenta.");
                        }
                        break;
                    }

                    case 4: {
                        System.out.print("Número de cuenta origen: ");
                        int origen = entrada.nextInt();
                        System.out.print("Número de cuenta destino: ");
                        int destino = entrada.nextInt();
                        System.out.print("Monto a transferir: ");
                        double monto = entrada.nextDouble();
                        entrada.nextLine(); 

                        banco.transferir(origen, destino, monto);
                        System.out.println("¡Transferencia realizada con éxito!");
                        break;
                    }

                    case 5: {
                        banco.aplicarInteresesGlobal();
                        System.out.println("¡Intereses aplicados exitosamente a todas las cuentas!");
                        break;
                    }

                    case 6: {
                        banco.generarReporte();
                        break;
                    }

                    case 7: {
                        System.out.print("Número de cuenta: ");
                        int num = entrada.nextInt();
                        entrada.nextLine(); 
                        CuentaBancaria c = banco.buscarPorNumero(num);
                        c.imprimirHistorial();
                        break;
                    }

                    case 8: {
                        System.out.print("Número de cuenta de Cheques: ");
                        int num = entrada.nextInt();
                        CuentaBancaria c = banco.buscarPorNumero(num);

                        if (c instanceof CuentaCheques) {
                            System.out.print("Monto del cheque a girar: ");
                            double monto = entrada.nextDouble();
                            entrada.nextLine(); 
                            ((CuentaCheques) c).girarCheque(monto);
                            System.out.println("¡Cheque girado exitosamente!");
                        } else {
                            System.out.println("Error: La cuenta ingresada NO es una cuenta de cheques.");
                        }
                        break;
                    }

                    case 9:
                        System.out.println("¡Saliendo del sistema! Éxito en tu evaluación.");
                        break;

                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }

            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
                entrada.nextLine(); 
            }

        } while (opcion != 9);

       
    }
        
    
    
}
