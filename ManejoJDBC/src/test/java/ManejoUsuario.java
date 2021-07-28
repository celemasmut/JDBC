import datos.UsuarioJDBC;
import domain.Usuario;

import java.util.List;

public class ManejoUsuario {

    public static void main(String[] args) {
        UsuarioJDBC usuarioJDBC = new UsuarioJDBC();

        //inserar usuario

     /*   Usuario usuario = new Usuario("Dani","144");
        usuarioJDBC.insertar(usuario);
*/

        //modificar usuario
      /*  Usuario usuario = new Usuario("Aldo","176",3);
        usuarioJDBC.actualizar(usuario);
        */


       //eliminar registro

        usuarioJDBC.eliminar(new Usuario(3));
        // listado de usuario

        List<Usuario> usuarioList =  usuarioJDBC.seleccionar();
        usuarioList.forEach(ob->System.out.println(ob));



    }
}
