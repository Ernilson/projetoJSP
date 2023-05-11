package TestesIntegracao;

import br.com.DAO.HoraDeTrabalhoDAO;
import br.com.Entity.HorarioDeTrabalho;

public class TestesIntegration_HorarioDeTrabalho {

	public static void main(String[] args) {
		HorarioDeTrabalho ht = new HorarioDeTrabalho();
		HoraDeTrabalhoDAO htDao = new HoraDeTrabalhoDAO();
		
		try {			
			ht.setEntrada("09:00");
			ht.setSaida("21:00");			
			htDao.adicionarHorarioDeTrabalho(ht);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
		
	}
}
