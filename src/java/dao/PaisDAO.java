package dao;

import modelo.Pais;
import java.io.Serializable;


public class PaisDAO<T> extends DAOGenerico<Pais> implements Serializable {

    public PaisDAO(){
        super();
        classePersistente = Pais.class;
        ordem = "nome";
    }
}
