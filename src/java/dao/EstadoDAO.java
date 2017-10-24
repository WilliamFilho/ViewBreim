package dao;

import modelo.Estado;
import java.io.Serializable;


public class EstadoDAO<T> extends DAOGenerico<Estado> implements Serializable {

    public EstadoDAO() {
        super();
        classePersistente = Estado.class;
        ordem = "nome";
    }
}
