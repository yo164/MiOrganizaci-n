package empleados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

import operaciones.CRUD;
import operaciones.ÁrbolJerárquico;

public class GestorEmpleados implements CRUD<Empleado>, ÁrbolJerárquico<Empleado> {
    Connection conn;

    public GestorEmpleados(Connection conn) {
        this.conn = conn;
    }

    @Override
    public ArrayList<Empleado> requestAll(String sortedBy) throws SQLException {
        // TODO: Declaración y creación del ArrayList
        ArrayList<Empleado> result = new ArrayList<>();
        // TODO: Construcción de la consulta SQL (incluyendo criterio de ordenación si
        // lo hubiera)
        String consultaSql = "SELECT * FROM empleado";
        if (sortedBy != null && !sortedBy.trim().isEmpty()) {
            consultaSql += " ORDER BY " + sortedBy + ";";
        } else {
            consultaSql += ";";
        }

        try (Statement stmt = this.conn.createStatement()) {
            // TODO: Ejecución de la consulta
            ResultSet resultados = stmt.executeQuery(consultaSql);

            // TODO: Recorrido del resultado de la consulta, creación del empleado y adición
            // al array
            while (resultados.next()) {
                Empleado empleado = new Empleado(

                        resultados.getInt("ID"),
                        resultados.getString("nombre"),
                        resultados.getString("apellido"),
                        resultados.getString("carto"),
                        resultados.getInt("jefe_id"));

                result.add(empleado);

            }

            return result;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public Empleado requestById(int idEmpleado) throws SQLException {
        // TODO: Declaración e inicialización a null del empleado a devolver
        Empleado result = null;

        // TODO: Construcción de la consulta SQL
        String consultaSql = "SELECT * FROM  empleado WHERE id = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(consultaSql)) {
            // TODO: Terminar de construir stmt
            stmt.setInt(1, idEmpleado);
            // TODO: Ejecución de la consulta

            ResultSet resultados = stmt.executeQuery();
            // TODO: Obtención de datos a partir de la consulta y creación del empleado a
            // devolver
            if (resultados.next()) {

                result = new Empleado(
                        resultados.getInt("ID"),
                        resultados.getString("nombre"),
                        resultados.getString("apellido"),
                        resultados.getString("cargo"),
                        resultados.getInt("jefe_id"));

            }

            return result;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean create(Empleado empleado) throws SQLException {
        // TODO: Construcción de la consulta SQL (insert into)
        String consultaSql = "INSERT INTO empleados(nombre, apellido, cargo, jefe_id) VALUES( ?, ?, ?, ?)";
        try (PreparedStatement stmt = this.conn.prepareStatement(consultaSql)) {
            // TODO: Terminar de construir stmt
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setString(3, empleado.getCargo());
            stmt.setInt(4, empleado.getJefeId());
            // TODO: Ejecución de la consulta

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0)
                throw new SQLException("Falló la creación del nuevo empleado.");
            return affectedRows == 1;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean update(Empleado empleado) throws SQLException {
        // TODO: Construcción de la consulta SQL (update)
        String consultaSql = "UPDATE empleados SET nombre = ?, apellido = ?, cargo = ?, jefe_id = ? WHERE ID = ?";
        String consultaCompara = "SELECT * FROM empleados WHERE ID = ?";
        int affectedRows = 0;
        try (PreparedStatement stmtCompara = this.conn.prepareStatement(consultaCompara)) {
            stmtCompara.setInt(1, empleado.getId());

            ResultSet empleadoComparar = stmtCompara.executeQuery();
            if(!empleadoComparar.next()){
                throw new SQLException("fallo 123 gestorempleados");
            }
            Empleado empleadoCompararObjeto = new Empleado(
                    empleadoComparar.getInt("ID"),
                    empleadoComparar.getString("nombre"),
                    empleadoComparar.getString("apellido"),
                    empleadoComparar.getString("cargo"),
                    empleadoComparar.getInt("jefe_id"));

            try (PreparedStatement stmt = this.conn.prepareStatement(consultaSql)) {
                // TODO: Terminar de construir stmt

                if(empleado.getNombre().trim().isEmpty()){
                    stmt.setString(1, empleadoCompararObjeto.getNombre());
                } else {
                    stmt.setString(1, empleado.getNombre());
                }
                if (empleado.getApellido().trim().isEmpty()) {
                    stmt.setString(2, empleadoCompararObjeto.getApellido());
                } else{
                    stmt.setString(2, empleado.getApellido());
                }
                if (empleado.getCargo().trim().isEmpty()) {
                    stmt.setString(3, empleadoCompararObjeto.getCargo());
                } else {
                    stmt.setString(3, empleado.getCargo());
                }
                if (empleado.getJefeId()==0) {
                    stmt.setInt(4, empleadoCompararObjeto.getJefeId());
                } else {
                    
                    stmt.setInt(4, empleado.getJefeId());
                }
                stmt.setInt(5, empleado.getId());
                // TODO: Ejecución de la consulta
                affectedRows = stmt.executeUpdate();

                if (affectedRows == 0)
                    throw new SQLException("Falló la actualización de los datos del empleado.");
                } catch (SQLException e) {
                    throw e;
                }
            }
            return affectedRows == 1;

    }

    @Override
    public boolean delete(int identificador) throws SQLException {
        // TODO: Construcción de la consulta SQL (delete)
        String consultaSql = "DELETE FROM empleados WHERE id = ?";

        try (PreparedStatement stmt = this.conn.prepareStatement(consultaSql)) {
            // TODO: Terminar de construir stmt
            stmt.setInt(1, identificador);
            // TODO: Ejecución de la consulta
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0)
                throw new SQLException("Falló el borrado de los datos del empleado.");
            return affectedRows == 1;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public ArrayList<Empleado> consultarSuperiores(int idEmpleado) throws SQLException {
        // TODO: Declaración y creación del ArrayList
        ArrayList<Empleado> result = new ArrayList<>();
        int idActual = idEmpleado;
        // TODO: Construcción de un bucle do-while / while...
        do {
            // TODO: Construcción de la consulta SQL (dentro del bucle)
            String sqlQuery = "SELECT * FROM empleados WHERE id = ?";
            try (PreparedStatement stmt = this.conn.prepareStatement(sqlQuery)) {
                // TODO: Terminar de construir stmt
                stmt.setInt(1, idActual);
                // TODO: Ejecución de la consulta
                ResultSet querySet = stmt.executeQuery();

                if (querySet.next()) {
                    // TODO: Obtención de datos a partir de la consulta y creación del empleado a
                    // añadir al arraylist
                    Empleado empleado = new Empleado(
                            querySet.getInt("ID"),
                            querySet.getString("nombre"),
                            querySet.getString("apellido"),
                            querySet.getString("cargo"),
                            querySet.getInt("jefe_id"));
                    
                        result.add(empleado);
                        
                    
                    idActual = empleado.getJefeId();
                } else {
                    throw new SQLException("No se pudo encontrar al empleado.");
                }
            } catch (SQLException e) {
                throw e;
            }
        } while (idActual != 0);
        return result;

    }

    @Override
    public ArrayList<Empleado> consultarSubordinados(int idEmpleado) throws SQLException {
        // TODO: Declaración y creación del ArrayList
        ArrayList<Empleado> result = new ArrayList<>();

        // lista para almacenar los ids cuos subordinados vamos a buscar

        // TODO: Construcción de un bucle do-while / while...

        // TODO: Construcción de la consulta SQL (dentro del bucle)
        String consultaSql = "SELECT * FROM empleados WHERE jefe_id = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(consultaSql)) {
            // TODO: Terminar de construir stmt
            stmt.setInt(1, idEmpleado);
            // TODO: Ejecución de la consulta
            ResultSet querySet = stmt.executeQuery();

            while (querySet.next()) {
                // TODO: Recorrido del resultado de la consulta, creación del empleado y adición
                // al array
                Empleado empleado = new Empleado(
                        querySet.getInt("ID"),
                        querySet.getString("nombre"),
                        querySet.getString("apellido"),
                        querySet.getString("cargo"),
                        querySet.getInt("jefe_id"));

                result.add(empleado);
            }

        } catch (SQLException e) {
            throw e;
        }
        return result;
    }
}
