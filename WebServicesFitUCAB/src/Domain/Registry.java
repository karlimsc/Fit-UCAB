package Domain;

/**
 * Created by root on 14/05/17.
 */
public class Registry {

    private int _idRegistry;
    private int _weight;
    private String _height;
    private int _registryPoint;

    public Registry(){};

    public int getIdRegistry()
    {
        return _idRegistry;
    }
    public void setIdRegistry( int idRegistry)
    {
        this._idRegistry = idRegistry;
    }

    public int getWeight()
    {
        return _weight;
    }
    public void setWeight( int weight)
    {
        this._weight = weight;
    }

    public String getHeight()
    {
        return _height;
    }
    public void setHeight( String height)
    {
        this._height = height;
    }

    public int getRegistryPoint()
    {
        return _registryPoint;
    }
    public void setregistryPoint( int registryPoint)
    {
        this._registryPoint = registryPoint;
    }
}
