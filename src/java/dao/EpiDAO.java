package dao;

import java.io.Serializable;
import modelo.EPI;


public class EpiDAO<T> extends DAOGenerico<EPI> implements Serializable {

    public EpiDAO(){
        super();
        classePersistente = EPI.class;
        ordem = "nome";
    }
}
