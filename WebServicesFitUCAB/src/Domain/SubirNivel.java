package Domain;

/**
 * Created by root on 27/05/17.
 */
public class SubirNivel {
    public String _subirNivel;
    public Boolean _subir;

    public Boolean get_subir() {
        return _subir;
    }

    public void set_subir(Boolean _subir) {
        this._subir = _subir;
    }

    /**
     * Get del atributo Nivel
     * @return Get del Nivel del usuario actual
     */
    public String get_subirNivelNivel() {
        return _subirNivel;
    }

    /**
     * Set del parametro Nivel
     * @param _SubirNivel establece el _nivel del usuario actual como contenido del atributo _nivel
     */
    public void setSubirNivel(String _SubirNivel) {
        this._subirNivel = _SubirNivel;

    }

    /**
     * Metodo contructor de la clase Nivel
     * @Param  null
     *
     */
    public SubirNivel() {
        this._subir= Boolean.FALSE;
        this._subirNivel = "";
    }
}
