package TestesIntegracao;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import br.com.DAO.CalculoAtrasoDAO;
import br.com.DAO.HoraDeTrabalhoDAO;
import br.com.DAO.MarcacoesFeitasDAO;
import br.com.Entity.HorarioDeTrabalho;
import br.com.Entity.MarcacoesFeitas;
import java.sql.Time;

public class TestesIntegration_HorarioDeTrabalho {

	public static void main(String[] args) {

//		MarcacoesFeitas mf = new MarcacoesFeitas();
//		MarcacoesFeitasDAO mfdao = new MarcacoesFeitasDAO();
		HoraDeTrabalhoDAO hdtdao = new HoraDeTrabalhoDAO();
		HorarioDeTrabalho hdt = new HorarioDeTrabalho();
		CalculoAtrasoDAO cadao = new CalculoAtrasoDAO();
//		try {
//			hdt.setCpf("6977984031");
//			hdt.setEntrada("08:00");
//			hdt.setIntervaloInicio("12:00");
//			hdt.setIntervaloFim("18:00");
//			hdt.setSaida("17:00");
//			
//
//			hdtdao.adicionarHorarioDeTrabalho(hdt);
//			
//			//System.out.println(cadao.calcularDiferenca(hdt, mf));
//
//		} catch (Exception e) {
//			System.out.println(e);
//		}

//---------------------------------------Metodo para Listar todos os registros-----------------------------------------------------

//		try {
//			List<MarcacoesFeitas> horario = mfdao.listarTodasMarcacoesFeitas();
//
//			for (MarcacoesFeitas mfs : horario) {
//				System.out.println("CPF : " + mf.getCpf() + "Entrada: " + mf.getEntrada() + ", Sa�da: " + mfs.getSaida() + 
//						", IntervaloInicio: " + mfs.getIntervaloInicio() + ", IntervaloFim: " + mfs.getIntervaloFim());
//				
//			}
//
//		} catch (Exception e) {
//			System.out.println(e);
//		}
	//----------------------------------------------Metodo para buscar por CPF--------------------------------------------	
		
		     String T = "6977984031";
//		     MarcacoesFeitas mf2 = mfdao.buscarMarcacoesFeitasPorCpf(T);
//        System.out.println(mf2.getCpf()+" - "+mf2.getEntrada()+" - "+ mf2.getSaida());
//        Long T = 6977984031L;

        System.out.println(calculoDeHorasExtras(T));
		// -------------------------------Metodo para apagar o registros pelo cpf-----------------------------------------------------

//		mf.setCpf("6977984031");
//		mfdao.removerMarcacoesFeitas(mf.getCpf());        
        
}


	public static Time calculoDeHorasExtras(String cpf) {
	    MarcacoesFeitasDAO mfdao = new MarcacoesFeitasDAO();
	    MarcacoesFeitas mf = mfdao.buscarMarcacoesFeitasPorCpf(cpf);
	    HoraDeTrabalhoDAO hdtdao = new HoraDeTrabalhoDAO();
	    HorarioDeTrabalho hdt = hdtdao.buscarHorarioDeTrabalhoPorCpf(cpf);

	    if (mf != null && hdt != null) {
	        LocalTime timeMf = LocalTime.parse(mf.getEntrada());
	        LocalTime timeHdt = LocalTime.parse(hdt.getEntrada());

	        long minutesBetween = ChronoUnit.MINUTES.between(timeMf, timeHdt);
	        long hours = minutesBetween / 60; // get the number of hours
	        long minutes = minutesBetween % 60; // get the remaining minutes

	        if (minutesBetween == 0) {
	            return Time.valueOf("00:00:00");
	        } else {
	            String difference = String.format("%02d:%02d:00", hours, minutes);
	            return Time.valueOf(difference);
	        }
	    }

	    return null;
	}





}
