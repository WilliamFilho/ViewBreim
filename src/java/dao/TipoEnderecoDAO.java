package dao;

import modelo.TipoEndereco;
import java.io.Serializable;


public class TipoEnderecoDAO<T> extends DAOGenerico<TipoEndereco> implements Serializable {

    public TipoEnderecoDAO() {
        super();
        classePersistente = TipoEndereco.class;
        ordem = "descricao";
    }
}
