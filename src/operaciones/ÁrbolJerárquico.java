package operaciones;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ÁrbolJerárquico<T> {
    /** consultarSuperiores
     * Función que devuelve una lista con los superiores de un empleado
     * @param id id del individuo para buscar sus superiores
     * @return devuelve una lista conteniendo los superiores
     * @throws SQLException
     */
    public ArrayList<T> consultarSuperiores(int id) throws SQLException;

    /** consultarSubordinados
     * Función que devuelve una lista con los subordinados de un empleado
     * @param id id del empleado para buscar sus subordinados
     * @return devuelve una lista conteniendo los subordinados
     * @throws SQLException
     */
    public ArrayList<T> consultarSubordinados(int id) throws SQLException;
}
