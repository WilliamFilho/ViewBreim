package controle;

import dao.AgenteRiscoDAO;
import dao.AgenteRiscoEspDAO;
import dao.EpiDAO;
import dao.ExameDAO;
import dao.TipoRiscoDAO;
import util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.AgenteRisco;
import modelo.EPI;
import modelo.Exame;
import modelo.TipoRisco;
import util.UtilRelatorios;

@ManagedBean(name = "controleAgenteRisco")
@ViewScoped
public class ControleAgenteRisco implements Serializable {

    private AgenteRiscoDAO<AgenteRisco> dao;
    private AgenteRisco objeto;
    private TipoRiscoDAO<TipoRisco> daoTipoRisco;
    private ExameDAO daoExame;
    private Exame exame;
    private EpiDAO daoEpi;
    private AgenteRiscoEspDAO daoAgenteRiscoEsp;
    private EPI epi;
    private List<Exame> exames;
    private List<EPI> epis;

    public ControleAgenteRisco() {
        dao = new AgenteRiscoDAO<>();
        daoTipoRisco = new TipoRiscoDAO<>();
        daoExame = new ExameDAO();
        daoAgenteRiscoEsp = new AgenteRiscoEspDAO<>();
        daoEpi = new EpiDAO();
        exames = new ArrayList<>();
        epis = new ArrayList<>();
    }

    public String listar() {
        return "/privado/agenterisco/listar?faces-redirect=true";
    }

    public void imprimeAgenteRisco() {
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("AgenteRisco", parametros, dao.getListaTodos());
    }

    public void imprimirAgenteRiscoById(Integer id) {
        objeto = dao.localizar(id);
        List<AgenteRisco> lista = new ArrayList<>();
        lista.add(objeto);
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("AgenteRisco", parametros, lista);
    }

    public void adicionarExame() {
        /*if (exame != null){
            if (!objeto.getExames().contains(exame)){
                objeto.getExames().add(exame);
                Util.mensagemInformacao("Exame adicionado com sucesso!");
            } else {
                Util.mensagemErro("Este exame já existe na sua lista!");
            }
        }*/
        if (exames != null) {
            exames.forEach((Exame selectedExame) -> {
                if (!objeto.getExames().contains(selectedExame)) {
                    Util.mensagemInformacao("Exame " + selectedExame.getNome() + " adicionado com sucesso!");
                    objeto.getExames().add(selectedExame);
                } else {
                    Util.mensagemErro("Este exame " + selectedExame.getNome() + " já existe na sua lista!");
                }
            });
        }

    }

    public void removerExame(int index) {
        exame = objeto.getExames().get(index);
        objeto.getExames().remove(exame);
        Util.mensagemInformacao("Exame removido com sucesso!");
    }

    public void adicionarEpi() {
        if (getEpis() != null) {
            getEpis().forEach((EPI selectedEpi) -> {
                if (!objeto.getEpis().contains(selectedEpi)) {
                    Util.mensagemInformacao("EPI " + selectedEpi.getNome() + " adicionado com sucesso!");
                    objeto.getEpis().add(selectedEpi);
                } else {
                    Util.mensagemErro("Este EPI " + selectedEpi.getNome() + " já existe na sua lista!");
                }
            });
        }
    }

    public void removerEpi(int index) {
        epi = objeto.getEpis().get(index);
        objeto.getEpis().remove(epi);
        Util.mensagemInformacao("Epi removido com sucesso!");
    }

    public void novo() {
        objeto = new AgenteRisco();
    }

    public void salvar() {
        boolean persistiu = false;
        if (objeto.getId() == null) {
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public void editar(Integer id) {
        objeto = dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        if (dao.remove(objeto)) {
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

    /**
     * @return the exames
     */
    public List<Exame> getExames() {
        return exames;
    }

    /**
     * @param exames the exames to set
     */
    public void setExames(List<Exame> exames) {
        this.exames = exames;
    }

    /**
     * @return the epis
     */
    public List<EPI> getEpis() {
        return epis;
    }

    /**
     * @param epis the epis to set
     */
    public void setEpis(List<EPI> epis) {
        this.epis = epis;
    }

    /**
     * @return the daoAgenteRiscoEsp
     */
    public AgenteRiscoEspDAO getDaoAgenteRiscoEsp() {
        return daoAgenteRiscoEsp;
    }

    /**
     * @param daoAgenteRiscoEsp the daoAgenteRiscoEsp to set
     */
    public void setDaoAgenteRiscoEsp(AgenteRiscoEspDAO daoAgenteRiscoEsp) {
        this.daoAgenteRiscoEsp = daoAgenteRiscoEsp;
    }

}
