package dao;

import java.io.Serializable;
import modelo.AgenteRisco;


public class AgenteRiscoDAO<T> extends DAOGenerico<AgenteRisco> implements Serializable {

    public AgenteRiscoDAO() {
        super();
        classePersistente = AgenteRisco.class;
        ordem = "nome";
    }
}
