package datos;

import domain.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaJDBC {//Data access object

    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email, telefono FROM persona"; // '?' indica el parametro
    private static final  String SQL_INSERT = "INSERT INTO persona(nombre, apellido, email, telefono) VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido= ?, email= ?, telefono= ? WHERE id_persona = ?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE id_persona = ?";

    public List<Persona> seleccionar(){
        Connection connection = null; // variable de conexion
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Persona persona = null;
        List<Persona> personas = new ArrayList<>();

        try {
            connection = Conexion.getConnection(); // iniciamos la conexion
            preparedStatement = Conexion.getConnection().prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();// ejecuta el query

            while (resultSet.next()){
                int idPersona = resultSet.getInt("id_persona");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String email = resultSet.getString("email");
                String telefono = resultSet.getString("telefono");
                persona = new Persona(idPersona,nombre,apellido,email,telefono);
                personas.add(persona);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        finally {
            try {
                Conexion.close(resultSet);
                Conexion.close(preparedStatement);
                Conexion.close(connection);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return personas;
    }

    public int insertar(Persona persona){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int registros =0;
        try {
            connection = Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, persona.getNombre());
            preparedStatement.setString(2, persona.getApellido());
            preparedStatement.setString(3, persona.getEmail());
            preparedStatement.setString(4, persona.getTelefono());

            registros = preparedStatement.executeUpdate();//para actualizar el estado de la db.

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {
            try {
                Conexion.close(preparedStatement);
                Conexion.close(connection);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }


    public int actualizar(Persona persona){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int registros =0;
        try {
            connection = Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, persona.getNombre());
            preparedStatement.setString(2, persona.getApellido());
            preparedStatement.setString(3, persona.getEmail());
            preparedStatement.setString(4, persona.getTelefono());
            preparedStatement.setInt(5, persona.getIdPersona());

            registros = preparedStatement.executeUpdate();//para actualizar el estado de la db.

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {
            try {
                Conexion.close(preparedStatement);
                Conexion.close(connection);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int eliminar(Persona persona){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int registros =0;
        try {
            connection = Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, persona.getIdPersona());

            registros = preparedStatement.executeUpdate();//para actualizar el estado de la db.

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {
            try {
                Conexion.close(preparedStatement);
                Conexion.close(connection);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

}
