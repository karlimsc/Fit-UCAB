package Domain;

/**
 * Created by root on 27/05/17.
 */
public class Nivel {
    public int _nivel;


    /**
     * Get del atributo Nivel
     * @return Get del Nivel del usuario actual
     */
    public int getNivel() {
        return _nivel;
    }

    /**
     * Set del parametro Nivel
     * @param _nivel establece el _nivel del usuario actual como contenido del atributo _nivel
     */
    public void setNivel(int _nivel) {
        this._nivel = _nivel;

    }

    /**
     * Metodo contructor de la clase Nivel
     * @Param  null
     *
     */
    public Nivel() {
        this._nivel = 0;
    }
}
