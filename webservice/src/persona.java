/**
 * Created by David on 9/5/2017.
 */
public class persona {
    private int id;
    private String nombre;
    private String apellido;
    public persona(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    //getter y setters basicos
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
