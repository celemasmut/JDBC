package datos;

import domain.Persona;
import domain.Usuario;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioJDBC {

    private Connection conexionTransaccional;

    private static final String SQL_SELECT = "SELECT id_usuario, username, password FROM usuario"; // '?' indica el parametro
    private static final  String SQL_INSERT = "INSERT INTO usuario(username, password) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET username = ?, password= ? WHERE id_usuario = ?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?";

    public UsuarioJDBC(){}

    public UsuarioJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public List<Usuario> seleccionar() throws SQLException { // coecxxion a la base de datos pero solo que no se va a cerrar.
        Connection connection = null; // variable de conexion
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Usuario usuario = null ;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            connection = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection(); // iniciamos la conexion
            preparedStatement = Conexion.getConnection().prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();// ejecuta el query

            while (resultSet.next()){
                int id_usuario = resultSet.getInt("id_usuario");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                usuario = new Usuario();
                usuario.setId_usuario(id_usuario);
                usuario.setUserName(username);
                usuario.setPassword(password);
                usuarios.add(usuario);
            }

        }
        finally {
            Conexion.close(resultSet);
            Conexion.close(preparedStatement);
            if(this.conexionTransaccional == null) {
                Conexion.close(connection);
            }
        }
        return usuarios;
    }

    public int insertar(Usuario usuario)throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int registros =0;
        try {
            connection = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection(); // iniciamos la conexion
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, usuario.getUserName());
            preparedStatement.setString(2, usuario.getPassword());
            registros = preparedStatement.executeUpdate();//para actualizar el estado de la db.

        } finally {
            Conexion.close(preparedStatement);
            if(this.conexionTransaccional == null) {
                Conexion.close(connection);
            }
        }
        return registros;
    }


    public int actualizar(Usuario usuario)throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int registros =0;
        try {
            connection = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection(); // iniciamos la conexion
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, usuario.getUserName());
            preparedStatement.setString(2, usuario.getPassword());
            preparedStatement.setInt(3, usuario.getId_usuario());

            registros = preparedStatement.executeUpdate();//para actualizar el estado de la db.

        } finally {
            Conexion.close(preparedStatement);
            if(this.conexionTransaccional == null) {
                Conexion.close(connection);
            }
        }
        return registros;
    }

    public int eliminar(Usuario usuario)throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int registros =0;
        try {
            connection = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection(); // iniciamos la conexion
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, usuario.getId_usuario());

            registros = preparedStatement.executeUpdate();//para actualizar el estado de la db.

        } finally {
            Conexion.close(preparedStatement);
            if(this.conexionTransaccional == null) {
                Conexion.close(connection);
            }
        }
        return registros;
    }


}
