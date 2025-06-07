import usuario.Interactuador;

public class MiOrganización implements Interactuador {
    
    public static final String RED_BRIGHT = "\033[0;91m"; // RED
    public static final String GREEN_BRIGHT = "\033[0;92m"; // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String RESET = "\033[0m"; // Text Reset

    public static void main(String[] args) throws Exception {
        // Variables y constantes
        final int OPCIÓN_MAX = 8;
        int elección;

        // Menú
        do {
            System.out.printf("\n%s¡Bienvenido a%s %sMiORGANIZACIÓN%s!\n", YELLOW_BRIGHT, RESET, GREEN_BRIGHT, RESET);
            System.out.println("Por favor, seleccione una de estas opciones:");
            System.out.println("(1) Consulta ordenada de empleados.");
            System.out.println("(2) Consulta de empleado por identificador.");
            System.out.println("(3) Alta de nuevo empleado.");
            System.out.println("(4) Actualización de los datos de un empleado.");
            System.out.println("(5) Baja de un empleado.");
            System.out.println("(6) Obtener todos los superiores jerárquicos de un empleado.");
            System.out.println("(7) Obtener todos los subordinados de un empleado.");
            System.out.println("(8) Salir del programa.");
            System.out.printf("%sOpción:%s ", GREEN_BRIGHT, RESET);
            elección = Interactuador.solicitarElección(OPCIÓN_MAX);

            switch (elección) {
            case 1: // Consulta ordenada de empleados
                Interactuador.consultaOrdenada();
                break;

            case 2: // Consulta de empleados por identificador
                Interactuador.consultaPorIdentificador();
                break;

            case 3: // Alta de nuevo empleado
                Interactuador.altaEmpleado();
                break;

            case 4: // Actualización de los datos de un empleado
                Interactuador.modificaciónEmpleado();
                break;

            case 5: // Baja de un empleado
                Interactuador.bajaEmpleado();
                break;     

            case 6: // Consultar superiores de un empleado
                Interactuador.consultarSuperiores();
                break;     

            case 7: // Consultar subordinados de un empleado
                Interactuador.consultarSubordinados();
                break;     
            }
        } while (elección != OPCIÓN_MAX);
        Interactuador.cerrarScanner();
    }
}
