
package TDA;

public class ListaDE<T> implements ILista<T>{

    protected NodoDE<T> head;
    protected NodoDE<T> end;
    protected int length;


    public ListaDE(){
        head = end = null;
        length = 0;
    }

    @Override
    public void Add(T value) {
        if (Empty())
            head = end = new NodoDE<T>(value, null, null);
        else {
            NodoDE<T> newNDe = new NodoDE<T>(value, null, end);
            end.setNext(newNDe);
            end = newNDe;
        }
        length++;
    }

    @Override
    public void Insert(int pos, T value) throws FueraDeRango {
        if (pos < 0 || pos >= length+1)
            throw new FueraDeRango("Error!!! Posición fuera de rango");
        if(pos == 0) {
            NodoDE<T> newN = new NodoDE<T>(value, head, null);
            head.setPrevious(newN);
            head = newN;
        } else {
            if(pos == length) {
                NodoDE<T> newN = new NodoDE<T>(value, null, end);
                end.setNext(newN);
                end = end.getNext();
            } else {
                NodoDE<T> cursor = head;

                while (pos-- !=1) {
                    cursor = cursor.getNext();
                }

                cursor.setNext(new NodoDE<T>(value, cursor.getNext(), cursor));
            }
        }
        length++;
    }

    @Override
    public T Get(int pos) throws FueraDeRango, ColeccionVacia {
        if(pos < 0 || pos >= length)
            throw new FueraDeRango("Error!!! Posición fuera de rango");

        if(Empty())
            throw new ColeccionVacia("Lista Vacía");

        NodoDE<T> cursor = head;
        while(pos-- !=0)
            cursor = cursor.getNext();

        return cursor.getInfo();
    }

    @Override
    public void Delete(int pos) throws FueraDeRango, ColeccionVacia {
        if (pos < 0 || pos >= length)
            throw new FueraDeRango("Error!!! Posición fuera de rango");
        if (pos == 0) {
            head.getNext().setPrevious(null);
            head = head.getNext();
        } else {
            if (pos == length) {
                end.getPrevious().setNext(null);
                end = end.getPrevious();
            } else {
                NodoDE<T> cursor = head;

                while (pos-- >=1) {
                    cursor = cursor.getNext();
                }

                cursor.setNext(cursor.getNext().getNext());
            }
        }
        length--;
    }

    @Override
    public boolean Empty() {
        return head == null;
    }

    @Override
    public int Long() {
        return length;
    }

    @Override
    public String toString() {
        String cadena = "";
        NodoDE<T> cursor = head;
        int pos = length-1;

        while(pos-- !=0){
            cadena += cursor.getInfo() + ", ";
            cursor = cursor.getNext();
        }
        cadena += cursor.getInfo();

        return cadena;
    }

    public void InvertirLista(){
        int pos = length;
        ListaDE<T> auxSe = new ListaDE<>();

        while (pos-- > 0) {
            try {
                auxSe.Add(Get(pos));
            } catch (FueraDeRango | ColeccionVacia e) {
                e.printStackTrace();
            }
        }

        int r = -1;
        NodoDE<T> cursor = head;

        while (++r < length) {
            try {
                cursor.setInfo(auxSe.Get(r));
            } catch (FueraDeRango | ColeccionVacia e) {
                e.printStackTrace();
            }

            if (cursor.getNext() == null)
                end = cursor;
            else
                cursor = cursor.getNext();
        }
    }

    @Override
    public void Show(int f) throws FueraDeRango, ColeccionVacia {
        System.out.println(Get(f));
    }
}