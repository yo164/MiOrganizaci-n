package empleados;

public class Empleado {

    public static final String RED_BRIGHT = "\033[0;91m"; // RED
    public static final String GREEN_BRIGHT = "\033[0;92m"; // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String RESET = "\033[0m"; // Text Reset
    
    // TODO: Añade aquí todo lo necesario para que la aplicación funcione:

    // variables de instancia, constructor(es), getters, setters...
    private int id;
    private String nombre;
    private String apellido;
    private String cargo;
    private int jefeId;

    public Empleado(int id, String nombre, String apellido, String cargo, int jefeId){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.jefeId = jefeId;
    }

    public Empleado(String nombre, String apellido, String cargo, int jefeId){
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.jefeId = jefeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getJefeId() {
        return jefeId;
    }

    public void setJefeId(int jefeId) {
        this.jefeId = jefeId;
    }

    
    // Añade un método toString() que permita visualizar las listas de empleados


    // con campos coloreados y alineados en columnas

    @Override
    public String toString() {
        return String.format("id:%4d;Nombre:%20s;Apellido:%20s;Cargo:%20s;IdJefe:%4d", this.id, this.nombre, this.apellido, this.cargo, this.jefeId);
    }
    
}