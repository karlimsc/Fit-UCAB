package edu.ucab.desarrollo.fitucab.common.entities;

/**
 * Clase LevelUp del modulo 09 que se encarga de guardar y subir de nivel al usuario.
 * @author David Garcia, Juan Mendez, Mario Salazar
 * @version 2.0
 */
public class LevelUp extends Entity{
    private String _levelUp;
    private Boolean _up;

    /**
     * Contructor vacio de la clase LevelUp
     */
    public LevelUp() {
    }

    /**
     * Constructor que inicializa todos los atributos.
     * @param levelUp Establece el nivel de usuario actual.
     * @param up TODO: Definir aqui tambien
     * @see Entity
     */
    public LevelUp(String levelUp, Boolean up) {
        _levelUp = levelUp;
        _up = up;
    }

    public String getLevelUp() {
        return _levelUp;
    }

    public void setLevelUp(String levelUp) {
        _levelUp = levelUp;
    }

    public Boolean getUp() {
        return _up;
    }

    public void setUp(Boolean up) {
        _up = up;
    }
}
