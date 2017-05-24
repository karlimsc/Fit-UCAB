package Domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase home modulo 02
 */

@XmlRootElement
public class Home {

    private float _totalCaloria;
    private int _totalAgua;

    /**
     * Constructor vacio
     */
    public Home() {
    }

    /**
     * Constructor con todos los atributos
     * @param _totalCaloria
     * @param _totalAgua
     */
    public Home(float _totalCaloria, int _totalAgua) {
        this._totalCaloria = _totalCaloria;
        this._totalAgua = _totalAgua;
    }

    public float getTotalCaloria() {
        return _totalCaloria;
    }

    public void setTotalCaloria(float _totalCaloria) {
        this._totalCaloria = _totalCaloria;
    }

    public int getTotalAgua() {
        return _totalAgua;
    }

    public void setTotalAgua(int _totalAgua) {
        this._totalAgua = _totalAgua;
    }
}
