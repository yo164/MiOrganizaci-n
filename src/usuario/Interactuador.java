package usuario;

import java.util.ArrayList;
import java.util.Scanner;

import pool.ConnectionPool;
import empleados.Empleado;
import empleados.GestorEmpleados;

public interface Interactuador {    

      public static final String RED_BRIGHT = "\033[0;91m"; // RED
    public static final String GREEN_BRIGHT = "\033[0;92m"; // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String RESET = "\033[0m"; // Text Reset
    // Configuración de la conexión a la base de datos
    final String URL = "jdbc:mariadb://localhost:3306/organizacion";
    final String USUARIO = "root";
    final String CLAVE = "";

    ConnectionPool pool = new ConnectionPool(URL, USUARIO, CLAVE);
    GestorEmpleados miGestor = new GestorEmpleados(pool.getConnection());    
    Scanner sc = new Scanner(System.in);

    // Consulta ordenada de empleados 
    public static void consultaOrdenada() {
        // TODO: Declaración e inicialización a null del ArrayList donde guardaremos el resultado de la consulta
        // TODO: Petición al usuario de los criterios de ordenación.

        try{     
            // TODO: Solicitud al gestor, comprobación del resultado y muestra de mensaje de error o listado de empleados.             
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Consulta de datos por ID
    public static void consultaPorIdentificador() { 
        // TODO: Petición al usuario del identificador del empleado (preferentemente usando la función solicitarValorNumérico). 

        try{
            // TODO: Solicitud al gestor, comprobación del resultado y muestra de mensaje de error o datos del empleado. 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Alta de nuevo empleado
    public static void altaEmpleado() {     
        // TODO: Petición al usuario de todos los datos del nuevo empleado (preferentemente usando la función solicitarValorNumérico). 
        System.out.println("DAR DE ALTA"); 
        System.out.println("Introduzca el Nombre del nuevo empleado");
        String nombre = sc.nextLine(); 
        System.out.println("Introduzca los Apellidos del nuevo empleado");
        String apellido = sc.nextLine(); 
        System.out.println("Introduzca el Cargo del nuevo empleado");
        String cargo = sc.nextLine();
        System.out.println("Introduzca el id del jefe del nuevo empleado");
        int idJefe = Integer.parseInt(sc.nextLine());

        try{
            Empleado empleado = new Empleado(nombre, apellido, cargo, idJefe);
            
            if(miGestor.create(empleado)){
                System.out.println("EMPLEADO CREADO CON EXITO");
            }
            // TODO: Solicitud al gestor, comprobación del resultado y muestra de mensaje de confirmación.     
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Actualización de los datos de un empleado
    public static void modificaciónEmpleado() throws InterruptedException{ 
        // TODO: Petición al usuario de todos los datos del empleado a modificar (preferentemente usando la función solicitarValorNumérico).
        System.out.println("Introduca el Id del Empleado que quiere actualizar");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Introduzca el Nuevo nombre o pulse enter para conservar el actual");
        String nombre = sc.nextLine();
        System.out.println("Introduzca el Nuevo apellido o pulse enter para conservar el actual");
        String apellido = sc.nextLine();
        System.out.println("Introduzca el Nuevo cargo o pulse enter para conservar el actual");
        String cargo = sc.nextLine();
        System.out.println("Introduzca el Id del Nuevo jefe del empleado o pulse 0 para conservar el actual");
        int idJefe = Integer.parseInt(sc.nextLine());

        try{
            Empleado empleado = new Empleado(id, nombre, apellido, cargo, idJefe);
            if (miGestor.update(empleado)) {
                System.out.println("Procesando...");
                Thread.sleep(2000);
                System.out.println("EL EMPLEADO HA SIDO ACTUALIZADO CON EXITO");
            }
            // TODO: Solicitud al gestor, comprobación del resultado y muestra de mensaje de confirmación.        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Baja de un empleado
    public static void bajaEmpleado()throws InterruptedException {   
        // TODO: Petición al usuario del identificador del empleado (preferentemente usando la función solicitarValorNumérico).     
        System.out.println("BAJA EMPLEADO");
        System.out.println("Introduzca el Id del empleado que quiere dar de baja");
        int id = (Integer) solicitarValorNumérico(Integer.class);
        try{
            // TODO: Solicitud al gestor, comprobación del resultado y muestra de mensaje de confirmación.
            if (miGestor.delete(id)) {
                System.out.println("Borrando....");
                Thread.sleep(2000);
                
                System.out.println("USUARIO BORRADO");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    // Baja de un empleado
    public static void consultarSuperiores() throws InterruptedException{         
        // TODO: Declaración e inicialización a null del ArrayList donde guardaremos el resultado de la consulta 
        ArrayList<Empleado> resultados = null;
        // TODO: Petición al usuario del identificador del empleado (preferentemente usando la función solicitarValorNumérico). 
        System.out.println("Introduzca el Id del trabajador cuyos jefes quiere consultar");
        int id = (Integer) solicitarValorNumérico(Integer.class);
        Thread.sleep(200);
        
        
        try{                   
            // TODO: Solicitud al gestor, comprobación del resultado y muestra de mensaje de error o listado de empleados.
            resultados = miGestor.consultarSuperiores(id);
            if(resultados != null ){
                
                System.out.println("Los superiores jerarquicos de:");
                Thread.sleep(200);
                System.out.println(resultados.get(0).toString() + "\n");
                for (int i = 1; i < resultados.size(); i++) {
                    Thread.sleep(200);
                    System.out.println(resultados.get(i).toString());
                }
            }else {
                System.out.printf("%sERROR AL MOSTRAR LOS SUPERIORES%s", RED_BRIGHT, RESET);

            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    // Baja de un empleado
    public static void consultarSubordinados() { 
        // TODO: Declaración e inicialización a null del ArrayList donde guardaremos el resultado de la consulta 
        
        
        // TODO: Petición al usuario del identificador del empleado (preferentemente usando la función solicitarValorNumérico).  
        
        try{      
            // TODO: Solicitud al gestor, comprobación del resultado y muestra de mensaje de error o listado de empleados. 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Solicitar opción al usuario
    public static int solicitarElección(int OPCIÓN_MAX) {
        int elección = 0;
        try {
            elección = sc.nextInt();
        if (elección < 1 || elección > OPCIÓN_MAX) throw new IllegalArgumentException();
        } catch (Exception e) {
            System.out.println("Opción inválida.\n");
        } finally {
            sc.nextLine(); // Limpiamos buffer de entrada
        }
        return elección;
    }

     // Solicitar valor numérico al usuario     
     // Ejemplo de uso 1: int existencias = (Integer) solicitarValorNumérico(Integer.class);
     // Ejemplo de uso 2: long código = (Long) solicitarValorNumérico(Long.class);
     // Ejemplo de uso 3: double precioCompra = (Double) solicitarValorNumérico(Double.class);
     private static Object solicitarValorNumérico(Class<?> tipo) {
        Object resultado = null;
        while (resultado == null) {
            try { 
                if (tipo == Short.class) resultado = sc.nextShort();
                else if (tipo == Integer.class) resultado = sc.nextInt();
                else if (tipo == Long.class) resultado = sc.nextLong();
                else if (tipo == Float.class) resultado = sc.nextFloat();
                else if (tipo == Double.class) resultado = sc.nextDouble();
                else System.out.println("Tipo de dato no soportado.");
            } catch (Exception e) {
                System.out.print("Valor inválido.\nPruebe de nuevo: ");
            } finally {
                sc.nextLine(); // Limpiamos buffer de entrada
            }
        }        
        return resultado;
    }

    // Cerrar scanner
    public static void cerrarScanner() {
        sc.close();
    }

}
