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
     * @param totalCaloria Total de calorias consumidas
     * @param totalAgua Total de vasos de agua consumidos
     */
    public Home(float totalCaloria, int totalAgua) {
        _totalCaloria = totalCaloria;
        _totalAgua = totalAgua;
    }

    public float getTotalCaloria() {
        return _totalCaloria;
    }

    public void setTotalCaloria(float totalCaloria) {
        _totalCaloria = totalCaloria;
    }

    public int getTotalAgua() {
        return _totalAgua;
    }

    public void setTotalAgua(int totalAgua) {
        _totalAgua = totalAgua;
    }

}
