package domain;

public class Usuario {
    private String userName;
    private String password;
    private int id_usuario;

    public Usuario() {
    }

    public Usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Usuario(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Usuario(String userName, String password, int id_usuario) {
        this.userName = userName;
        this.password = password;
        this.id_usuario = id_usuario;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", id_usuario=" + id_usuario +
                '}';
    }
}
