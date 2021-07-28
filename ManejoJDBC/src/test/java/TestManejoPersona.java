import datos.PersonaJDBC;
import domain.Persona;


import java.util.List;

public class TestManejoPersona {

    public static void main(String[] args) {
        PersonaJDBC personaJDBC = new PersonaJDBC();

        // insertar obj en db

       /* Persona personita = new Persona("Mami","Mendoza","mami@mail.com", "20202020");
        personaDAO.insertar(personita);*/

        //modificar registro de persona existente

      /*  Persona personaModificar = new Persona(4,"MamiDani", "Mendoza","danimami@mail.com","32323232");
        personaDAO.actualizar(personaModificar);*/

        //eliminar un registro
        Persona personaEliminar = new Persona(4);
        personaJDBC.eliminar(personaEliminar);

        List<Persona> persona = personaJDBC.seleccionar();
        persona.forEach(ob -> System.out.println(ob.toString()));

    }
}
