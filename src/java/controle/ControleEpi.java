
package controle;

import dao.EpiDAO;
import util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.EPI;


@ManagedBean(name = "controleEpi")
@SessionScoped
public class ControleEpi implements Serializable {
    
    private EpiDAO<EPI> dao;
    private EPI objeto;
    
    public ControleEpi(){
        dao = new EpiDAO<>();
    }
    
    public String listar(){
        return "/privado/epi/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new EPI();
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
    
    public EpiDAO getDao() {
        return dao;
    }

    public void setDao(EpiDAO dao) {
        this.dao = dao;
    }

    public EPI getObjeto() {
        return objeto;
    }

    public void setObjeto(EPI objeto) {
        this.objeto = objeto;
    }    
}
