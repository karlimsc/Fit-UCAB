package Domain;

/**
 * Created by root on 26/05/17.
 */
public class Size {
    int _Logrado;
    int _NoLogrado;

    /**
     * Set del atributo Logrado
     * @param _logrado establece el valor de retos realizados y logrados
     */
    public void setLogrado(int _logrado) {
        _Logrado = _logrado;
    }

    /**
     * Set del atributo NoLogrado
     * @param _noLogrado establece el valor de retos realizados pero No logrados
     */
    public void setNoLogrado(int _noLogrado) {
        _NoLogrado = _noLogrado;
    }

    /**
     * Get del atributo Logrado
     * @return devuelve el valor de retos realizados y logrados
     */
    public int getLogrado() {
        return _Logrado;
    }

    /**
     *Get del atributo Nologrado
     * @return devuelve el valor de retos realizados pero No logrados
     */
    public int getNoLogrado() {
        return _NoLogrado;
    }

    public Size() {
        _Logrado = 0;
        _NoLogrado = 0;
    }

}
