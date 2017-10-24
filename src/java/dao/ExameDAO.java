package dao;

import java.io.Serializable;
import modelo.Exame;


public class ExameDAO<T> extends DAOGenerico<Exame> implements Serializable {

    public ExameDAO(){
        super();
        classePersistente = Exame.class;
        ordem = "nome";
    }
}
