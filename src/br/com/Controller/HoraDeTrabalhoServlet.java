package br.com.Controller;

import br.com.DAO.HoraDeTrabalhoDAO;
import br.com.Entity.HorarioDeTrabalho;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HoraDeTrabalhoServlet")
public class HoraDeTrabalhoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HoraDeTrabalhoDAO horaDeTrabalhoDAO;

    public void init() {
        horaDeTrabalhoDAO = new HoraDeTrabalhoDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        switch (action) {
            case "add":
			try {
				adicionarHorario(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                break;
            case "remove":
                removerHorario(request, response);
                break;
            default:
                listarHorarios(request, response);
        }
    }

    private void adicionarHorario(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	String cpf = request.getParameter("cpf");
        String entrada = request.getParameter("entrada");
        String intervaloInicio = request.getParameter("intervaloInicio");
        String intervaloFim = request.getParameter("intervaloFim");
        String saida = request.getParameter("saida");
        HorarioDeTrabalho horario = new HorarioDeTrabalho();
        horario.getCpf();
        horario.getEntrada();
        horario.getIntervaloInicio();
        horario.getIntervaloFim();
        horario.getSaida();
        horaDeTrabalhoDAO.listarHorarioDeTrabalhoPorCpf(horario);

        if (horario.equals(null)|| horario.equals("")) {
        	 throw new Exception("Este Campo é deve ser preenchido");
        }
        
        listarHorarios(request, response);
    }

    private void removerHorario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("index"));
        horaDeTrabalhoDAO.removerHorario(index);
        listarHorarios(request, response);
    }

    private void listarHorarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<HorarioDeTrabalho> horarios = horaDeTrabalhoDAO.listarHorarios();
        request.setAttribute("horarios", horarios);
        request.getRequestDispatcher("listarHorarios.jsp").forward(request, response);
    }
}
