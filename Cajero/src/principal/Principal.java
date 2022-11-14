package principal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        if (verificarPIN()) {
            System.out.println("Bienvenido José Alberto Rodríguez Navarro en la rama Develop\n");
            char cont = 'y';
            menuCajero(cont);
        } else {
            System.out.println("Fin del programa.");
        }
    }

    private static boolean verificarPIN(){
        boolean pass = false;
        int pin;
        int i = 1;
        Scanner scan = new Scanner (System.in);

        do {
            System.out.print("Ingrese el PIN: ");
            pin = scan.nextInt();
            pass = true;

            if (pin != 1235) {
                System.out.println( "El PIN no es válido.\n");
                i+=1;
            }
        } while (pin != 1235 && i <= 3);

        return pass;
    }

    private static void menuCajero(char cont) {
        Scanner input = new Scanner (System.in);
        Cuenta miCuenta = new Cuenta(1000,0);

        while (cont == 'y') {
            System.out.println("Consultar saldo: 1\n" + "Retirar dinero: 2\n" + "Historicos de movimientos: 3\n");
            int option = input.nextInt();
            System.out.println();
            String consult;
            String fecha;
            Date fechaMovimiento = new Date();
            DateFormat fechaFormat = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat horaFormat = new SimpleDateFormat("HH:mm:ss");

            switch (option) {
                case 1:
                    System.out.println("Su saldo es: $" + miCuenta.getSaldo());
                    miCuenta.setMovimientos(miCuenta.getMovimientos()+1);
                    fecha =  " Fecha: " + fechaFormat.format(fechaMovimiento) + " Hora: " + horaFormat.format(fechaMovimiento);
                    consult = "Se consulto el saldo: $" + miCuenta.getSaldo() + "," + fecha;
                    miCuenta.hisMovimientos(consult);
                    System.out.println();
                    break;
                case 2:
                    retirarDinero(miCuenta, input, fechaMovimiento, fechaFormat, horaFormat);
                    break;
                case 3:
                    System.out.print("Movimientos: " + miCuenta.getMovimientos());
                    System.out.println(miCuenta.getHistorico());
                    miCuenta.setMovimientos(miCuenta.getMovimientos()+1);
                    fecha = " Fecha: " + fechaFormat.format(fechaMovimiento) + " Hora: " + horaFormat.format(fechaMovimiento);
                    consult = "Se consulto el historial," + fecha;
                    miCuenta.hisMovimientos(consult);
                    System.out.println();
                    break;
                default:
                    System.out.println("No existe la opción ingresada\n");
                    break;
            }

            System.out.println("¿Desea regresar al menu principal? (y/n)");
            cont = input.next().charAt(0);
            System.out.println();
        }
        System.out.println("Fin del programa.");
    }

    private static void retirarDinero(Cuenta miCuenta, Scanner input, Date fechaMovimiento, DateFormat fechaFormat,  DateFormat horaFormat) {
        String consult;
        String fecha;
        Boolean esEntero = false;

        if (miCuenta.getSaldo() != 0) {
            do {
                String numero = input.nextLine();
                try {
                    int retiro = Integer.parseInt(numero);
                    esEntero = true;
                    miCuenta.setSaldo(miCuenta.getSaldo() - (int) retiro);
                    System.out.println("Tu nuevo saldo es: $" + miCuenta.getSaldo());
                    miCuenta.setMovimientos(miCuenta.getMovimientos()+1);
                    fecha = " Fecha: " + fechaFormat.format(fechaMovimiento) + " Hora: " + horaFormat.format(fechaMovimiento);
                    consult = "Se retiro $" + retiro + "," + fecha;
                    miCuenta.hisMovimientos(consult);
                    System.out.println();
                } catch (NumberFormatException e) {
                    System.out.println("¿Cuanto dinero desea retirar? (Debe ser un numero entero multiplo de 100)");
                }
            } while (!esEntero);
        } else {
            System.out.println("No tiene fondos suficientes.");
        }
    }

}
