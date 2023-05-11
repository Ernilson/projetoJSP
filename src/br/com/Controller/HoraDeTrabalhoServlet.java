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
                adicionarHorario(request, response);
                break;
            case "remove":
                removerHorario(request, response);
                break;
            default:
                listarHorarios(request, response);
        }
    }

    private void adicionarHorario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String entrada = request.getParameter("entrada");
        String saida = request.getParameter("saida");
        HorarioDeTrabalho horario = new HorarioDeTrabalho(entrada, saida);
        boolean adicionado = horaDeTrabalhoDAO.adicionarHorario(horario);

        if (!adicionado) {
            // Ação a ser executada quando não é possível adicionar um novo horário.
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
