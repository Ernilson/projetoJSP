package TestesIntegracao;

import java.util.List;

import br.com.DAO.CalculoAtrasoDAO;
import br.com.Entity.CalculoAtraso;

public class TestesIntegration_Atraso {

	public static void main(String[] args) {
		CalculoAtraso cca = new CalculoAtraso();
		CalculoAtrasoDAO ccaDao = new CalculoAtrasoDAO();
		
		List<CalculoAtraso> horario = ccaDao.listarTodosCalculoAtraso();

		for (CalculoAtraso mfs : horario) {
			System.out.println("CPF : " + mfs.getCpf() + "Entrada: " + mfs.getEntrada() + ", Sa�da: " + mfs.getSaida()); 
		}
	}
}
