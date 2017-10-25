package dao;

import java.io.Serializable;
import modelo.RiscoEspecifico;


public class AgenteRiscoEspDAO<T> extends DAOGenerico<RiscoEspecifico> implements Serializable {

    public AgenteRiscoEspDAO() {
        super();
        classePersistente = RiscoEspecifico.class;
        ordem = "nome";
    }
}
