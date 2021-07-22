import java.sql.*;
import java.util.Arrays;

public class TestMySqlJDBC {
    public static void main(String[] args) {
        // conectar a db mysql
        //1- cadena de conexion
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true"; // test es la bd creada en el workbench

        //2-cuando se trabaja en jdbc se especifica la clase con la que vamos a trabajar mysql
        //puede que no sea requerida en otros trabajos locales salvo en app web
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //3 - crear objeto conection
            Connection conexion = DriverManager.getConnection(url,"root","r16c95m.jaja");

            //4 crear obj statement para ejecutar sentencia sobre la tabla de db

            Statement instruccion = conexion.createStatement(); // creo obj statement;

            //5 ejecutamos una instruccion

            var sql = "SELECT id_persona, nombre, apellido, email, telefono FROM persona";

            //6 ejecutar instruccion
            ResultSet resultado = instruccion.executeQuery(sql);

            while (resultado.next()){
                System.out.println("Id persona: " + resultado.getInt("id_persona"));
                System.out.print(" Nombre : "+ resultado.getString("nombre"));
                System.out.print(" Apellido : "+ resultado.getString("apellido"));
                System.out.print(" Email : "+ resultado.getString("email"));
                System.out.print(" Telefono : "+ resultado.getString("telefono"));
                System.out.println(" ");
            }

            resultado.close();
            instruccion.close();
            conexion.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
}
