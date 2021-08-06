package TDA;

public interface ILista<T> {
    void Add(T value);
    void Insert(int pos, T value)throws FueraDeRango;
    T Get(int pos) throws FueraDeRango, ColeccionVacia;
    void Delete(int pos)throws FueraDeRango, ColeccionVacia;
    boolean Empty();
    int Long();
    void Show(int i) throws FueraDeRango, ColeccionVacia;
}