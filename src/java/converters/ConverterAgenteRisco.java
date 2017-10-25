
package converters;

import jpa.EntityManagerUtil;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.AgenteRisco;


@FacesConverter(value = "converterAgenteRisco")
public class ConverterAgenteRisco implements Converter, Serializable {

    // converte da tela para o objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return EntityManagerUtil.getEntityManager().find(AgenteRisco.class, Integer.parseInt(string));
    }

    // converte do objeto para tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        AgenteRisco obj = (AgenteRisco) o;
        return obj.getId().toString();
    }

}
