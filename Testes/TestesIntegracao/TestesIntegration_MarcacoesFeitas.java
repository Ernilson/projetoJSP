package TestesIntegracao;

import br.com.DAO.MarcacoesFeitasDAO;
import br.com.Entity.MarcacoesFeitas;

public class TestesIntegration_MarcacoesFeitas {

	public static void main(String[] args) {
		
		MarcacoesFeitas mf = new MarcacoesFeitas();
		MarcacoesFeitasDAO mfdao = new MarcacoesFeitasDAO();
		try {			
			mf.setEntrada("10:00");
			mf.setSaida("22:00");			
			mfdao.adicionarMarcacaos(mf);
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
