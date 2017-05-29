package Domain;

/**
 * Created by root on 18/05/17.
 */
public class Achievement {

    private Data _challenge[];
    int _challenges = 0;


    /**
     * //Constructor de los logros
     * @param _size Recibe un objeto tipo data con el tamaño de logros que hay
     */
    public Achievement(int _size) {
        _challenge = new Data[_size];

    }

    /**
     * //Set del logro que se hizo
     * @param _Challenges Set de un objetivo tipo datos que recibe una cantidad y aumenta a medida que se agregan
     */
    public void set_challenges(Data _Challenges){
        _challenge[_challenges] = _Challenges;
        _challenges++;
    }

    /**
     * //Get del objeto reto que tiene los datos
     * @return Retorna un objeto tipo logro que tiene los datos
     */
    public Data[] get_challenge() {
        return _challenge;
    }

    /**
     * Get de un Reto en especifico
     * @return Retorna un reto especifico
     */
    public int get_challenges() {
        return _challenges;
    }
}
