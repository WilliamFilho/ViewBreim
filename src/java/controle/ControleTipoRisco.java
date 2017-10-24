
package controle;

import dao.TipoRiscoDAO;
import util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.TipoRisco;


@ManagedBean(name = "controleTipoRisco")
@SessionScoped
public class ControleTipoRisco implements Serializable {
    
    private TipoRiscoDAO<TipoRisco> dao;
    private TipoRisco objeto;
    
    public ControleTipoRisco(){
        dao = new TipoRiscoDAO<>();
    }
    
    public String listar(){
        return "/privado/tiporisco/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new TipoRisco();
        return "formulario?faces-redirect=true";        
    }
    
    public String salvar(){
        boolean persistiu = false;
        if (objeto.getId() == null){
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id){
        objeto = dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if (dao.remove(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public TipoRiscoDAO getDao() {
        return dao;
    }

    public void setDao(TipoRiscoDAO dao) {
        this.dao = dao;
    }

    public TipoRisco getObjeto() {
        return objeto;
    }

    public void setObjeto(TipoRisco objeto) {
        this.objeto = objeto;
    }    
}
