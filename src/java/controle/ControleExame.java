
package controle;

import dao.ExameDAO;
import util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Exame;


@ManagedBean(name = "controleExame")
@SessionScoped
public class ControleExame implements Serializable {
    
    private ExameDAO<Exame> dao;
    private Exame objeto;
    
    public ControleExame(){
        dao = new ExameDAO<>();
    }
    
    public String listar(){
        return "/privado/exame/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Exame();
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
    
    public ExameDAO getDao() {
        return dao;
    }

    public void setDao(ExameDAO dao) {
        this.dao = dao;
    }

    public Exame getObjeto() {
        return objeto;
    }

    public void setObjeto(Exame objeto) {
        this.objeto = objeto;
    }    
}
