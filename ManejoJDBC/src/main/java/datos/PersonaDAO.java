package datos;

import domain.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {//Data access object

    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email, telefono FROM persona";

    public List<Persona> seleccionar(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Persona persona = null;
        List<Persona> personas = new ArrayList<>();

        try {
            connection = Conexion.getConnection();
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

}
