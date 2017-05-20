package Domain;


public class Registry {

    private int _idRegistry;
    private int _weight;
    private String _height;
    private int _registryPoint;

    public Registry(){};
    public Registry(int idRegistry, int weight,String height,int registryPoint)
    {
     _idRegistry = idRegistry;
     _weight = weight;
     _height = height;
     _registryPoint = registryPoint;
    };

    public Registry( int weight,String height,int registryPoint)
    {

        _weight = weight;
        _height = height;
        _registryPoint = registryPoint;
    };

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
