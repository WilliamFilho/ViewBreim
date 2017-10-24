package dao;

import java.io.Serializable;
import modelo.TipoRisco;


public class TipoRiscoDAO<T> extends DAOGenerico<TipoRisco> implements Serializable {

    public TipoRiscoDAO(){
        super();
        classePersistente = TipoRisco.class;
        ordem = "nome";
    }
}
