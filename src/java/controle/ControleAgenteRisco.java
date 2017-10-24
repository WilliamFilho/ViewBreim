
package controle;

import dao.AgenteRiscoDAO;
import dao.EpiDAO;
import dao.ExameDAO;
import dao.TipoRiscoDAO;
import util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.AgenteRisco;
import modelo.EPI;
import modelo.Exame;
import modelo.TipoRisco;


@ManagedBean(name = "controleAgenteRisco")
@ViewScoped
public class ControleAgenteRisco implements Serializable {
    
    private AgenteRiscoDAO<AgenteRisco> dao;
    private AgenteRisco objeto;
    private TipoRiscoDAO<TipoRisco> daoTipoRisco;
    private ExameDAO daoExame;
    private Exame exame;
    private EpiDAO daoEpi;
    private EPI epi;

    
    public ControleAgenteRisco(){
        dao = new AgenteRiscoDAO<>();
        daoTipoRisco = new TipoRiscoDAO<>();  
        daoExame = new ExameDAO();
        daoEpi = new EpiDAO();
    }
    
    public String listar(){
        return "/privado/agenterisco/listar?faces-redirect=true";
    }
    
    public void adicionarExame(){
        if (exame != null){
            if (!objeto.getExames().contains(exame)){
                objeto.getExames().add(exame);
                Util.mensagemInformacao("Exame adicionado com sucesso!");
            } else {
                Util.mensagemErro("Este exame já existe na sua lista!");
            }
        }
    }
    
    public void removerExame(int index){
        exame = objeto.getExames().get(index);
        objeto.getExames().remove(exame);
        Util.mensagemInformacao("Exame removido com sucesso!");
    }
    
    public void adicionarEpi(){
        if (epi != null){
            if (!objeto.getEpis().contains(epi)){
                objeto.getEpis().add(epi);
                Util.mensagemInformacao("Epi adicionado com sucesso!");
            } else {
                Util.mensagemErro("Este Epi já existe na sua lista!");
            }
        }
    }
    
    public void removerEpi(int index){
        epi = objeto.getEpis().get(index);
        objeto.getEpis().remove(epi);
        Util.mensagemInformacao("Epi removido com sucesso!");
    }
    
    
    public void novo(){
        objeto = new AgenteRisco();        
    }
    
    public void salvar(){
        boolean persistiu = false;
        if (objeto.getId() == null){
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu){
            Util.mensagemInformacao(dao.getMensagem());            
        } else {
            Util.mensagemErro(dao.getMensagem());            
        }
    }
    
    public void editar(Integer id){
        objeto = dao.localizar(id);        
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if (dao.remove(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public AgenteRiscoDAO getDao() {
        return dao;
    }

    public void setDao(AgenteRiscoDAO dao) {
        this.dao = dao;
    }

    public AgenteRisco getObjeto() {
        return objeto;
    }

    public void setObjeto(AgenteRisco objeto) {
        this.objeto = objeto;
    }    

    public TipoRiscoDAO<TipoRisco> getDaoTipoRisco() {
        return daoTipoRisco;
    }

    public void setDaoTipoRisco(TipoRiscoDAO<TipoRisco> daoTipoRisco) {
        this.daoTipoRisco = daoTipoRisco;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }


    /**
     * @return the daoExame
     */
    public ExameDAO getDaoExame() {
        return daoExame;
    }

    /**
     * @param daoExame the daoExame to set
     */
    public void setDaoExame(ExameDAO daoExame) {
        this.daoExame = daoExame;
    }

    /**
     * @return the daoEpi
     */
    public EpiDAO getDaoEpi() {
        return daoEpi;
    }

    /**
     * @param daoEpi the daoEpi to set
     */
    public void setDaoEpi(EpiDAO daoEpi) {
        this.daoEpi = daoEpi;
    }

    /**
     * @return the epi
     */
    public EPI getEpi() {
        return epi;
    }

    /**
     * @param epi the epi to set
     */
    public void setEpi(EPI epi) {
        this.epi = epi;
    }

    
}
