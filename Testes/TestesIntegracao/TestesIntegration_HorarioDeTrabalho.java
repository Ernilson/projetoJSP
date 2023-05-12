package TestesIntegracao;

import java.util.List;

import br.com.DAO.HoraDeTrabalhoDAO;
import br.com.Entity.HorarioDeTrabalho;

public class TestesIntegration_HorarioDeTrabalho {

	public static void main(String[] args) {
		HorarioDeTrabalho ht = new HorarioDeTrabalho();
		HoraDeTrabalhoDAO htDao = new HoraDeTrabalhoDAO();
		try {
			ht.setCpf("69779848134");
			ht.setEntrada("09:00");
			ht.setIntervaloInicio("12:00");
			ht.setIntervaloFim("13:00");
			ht.setSaida("21:00");	
			
			htDao.adicionarHorarioDeTrabalho(ht);
			
		} catch (Exception e) {
			System.out.println(e);
		}	

//		try {
//			List<HorarioDeTrabalho> horarios = htDao.listarTodosHorariosDeTrabalho();
//
//			for (HorarioDeTrabalho hts : horarios) {
//				System.out.println("Entrada: " + ht.getEntrada() + ", Saída: " + hts.getSaida());
//			}
//
//		} catch (Exception e) {
//			System.out.println(e);
//		}
	}
}
