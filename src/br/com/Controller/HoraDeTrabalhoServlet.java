package br.com.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.DAO.CalculoAtrasoDAO;
import br.com.DAO.HoraDeTrabalhoDAO;
import br.com.DAO.MarcacoesFeitasDAO;
import br.com.Entity.HorarioDeTrabalho;
import br.com.Entity.MarcacoesFeitas;

@WebServlet("/HoraDeTrabalhoServlet")
public class HoraDeTrabalhoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private HoraDeTrabalhoDAO horaDeTrabalhoDAO;

    public void init() {
        horaDeTrabalhoDAO = new HoraDeTrabalhoDAO();
        horaDeTrabalho();
    }

    private void horaDeTrabalho() {
   	 List<HorarioDeTrabalho> hdt = horaDeTrabalhoDAO.listarTodosHorariosDeTrabalho();
   	 getServletContext().setAttribute("hdt", hdt);
   }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	try {
            adicionarHorario(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void adicionarHorario(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	String cpf = request.getParameter("cpf");
        String entrada = request.getParameter("entrada");
        String intervaloInicio = request.getParameter("intervaloInicio");
        String intervaloFim = request.getParameter("intervaloFim");
        String saida = request.getParameter("saida");

//        if (cpf == null || cpf.isEmpty() ||
//            entrada == null || entrada.isEmpty() ||
//            intervaloInicio == null || intervaloInicio.isEmpty() ||
//            intervaloFim == null || intervaloFim.isEmpty() ||
//            saida == null || saida.isEmpty()) {
//        	 throw new Exception("Todos os campos devem ser preenchidos");
        //}

        HorarioDeTrabalho horario = new HorarioDeTrabalho();
        horario.setCpf(cpf);
        horario.setEntrada(entrada);
        horario.setIntervaloInicio(intervaloInicio);
        horario.setIntervaloFim(intervaloFim);
        horario.setSaida(saida);

        horaDeTrabalhoDAO.adicionarHorarioDeTrabalho(horario);

        listarHorarios(request, response);
}

    private void removerHorario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String cpf = request.getParameter("cpf");
        horaDeTrabalhoDAO.removerHorarioDeTrabalho(cpf);
        listarHorarios(request, response);
    }

    private void listarHorarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<HorarioDeTrabalho> horarios = horaDeTrabalhoDAO.listarTodosHorariosDeTrabalho();
        request.setAttribute("horarios", horarios);
        request.getRequestDispatcher("controleDeHora.jsp").forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        	//http://localhost:8080/RegisterPoint/HoraDeTrabalhoServlet?action=list
        if (action != null) {
            switch (action) {
                case "delete":
              //      removerHorario(request, response);
                    break;
                case "lista":
                    listarHorarios(request, response);
                    break;
                
                default:
                    listarHorarios(request, response);
                    break;
            }
        } else {
            listarHorarios(request, response);
        }
    }

}
