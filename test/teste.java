
import javax.swing.JOptionPane;
import util.WebServiceCep;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author williamfilho
 */
public class teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WebServiceCep webServiceCep = WebServiceCep.searchCep("65930000");
        //A ferramenta de busca ignora qualquer caracter que n?o seja n?mero.

        //caso a busca ocorra bem, imprime os resultados.
         if (webServiceCep.wasSuccessful()) {
            //System.out.println("Cep: " + webServiceCep.getCep());
            //System.out.println("Logradouro: " + webServiceCep.getLogradouroFull());
            //System.out.println("Bairro: " + webServiceCep.getBairro());
            System.out.println("Cidade: "
                    + webServiceCep.getCidade() + "/" + webServiceCep.getUf());

            //caso haja problemas imprime as exce??es.
        } else {
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());

            JOptionPane.showMessageDialog(null, "DescriÁ„o do erro: " + webServiceCep.getResultText());
        }
    }
    
}
