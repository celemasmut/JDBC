import datos.Conexion;
import datos.PersonaJDBC;
import domain.Persona;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestManejoPersona {

    public static void main(String[] args) {
        Connection conexion = null;

        try {
             conexion = Conexion.getConnection();
            //revisar si la conexion esta en modo autocommit
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false); // para que no haga autocommit ya que lo haremos manualmente
            }
            PersonaJDBC personaJDBC = new PersonaJDBC(conexion); // para la transaccion
            Persona cambioPersona = new Persona(5,"Dani","Mendoza","mami@mail.com", "40404040");
            personaJDBC.actualizar(cambioPersona);
            Persona nuevaPersona = new Persona();
            nuevaPersona.setNombre("Aldo");
            nuevaPersona.setApellido("Mamsut");
            personaJDBC.insertar(nuevaPersona);

            conexion.commit();


        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex1){
                ex1.printStackTrace(System.out);
            }
        }



    }
}
