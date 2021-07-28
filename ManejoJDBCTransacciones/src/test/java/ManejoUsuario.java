import datos.Conexion;
import datos.UsuarioJDBC;
import domain.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ManejoUsuario {

    public static void main(String[] args) {
        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            UsuarioJDBC usuarioJDBC = new UsuarioJDBC(conexion);
            Usuario cambioUsuario = new Usuario("Celes","166",2);
            usuarioJDBC.actualizar(cambioUsuario);

            Usuario nuevoUser = new Usuario("Sofi","334");
            usuarioJDBC.insertar(nuevoUser);

            conexion.commit();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.out.println("Entramos al rollBack");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }





    }
}
