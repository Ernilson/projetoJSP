package br.com.Controller;

import br.com.DAO.CalculoAtrasoDAO;
import br.com.DAO.HoraDeTrabalhoDAO;
import br.com.Entity.CalculoAtraso;
import br.com.Entity.HorarioDeTrabalho;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalculoAtrasoServlet")
public class CalculoAtrasoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private CalculoAtrasoDAO calculoAtrasoDAO;
    private HoraDeTrabalhoDAO horaDeTrabalhoDAO;
   
    public void init() {
        calculoAtrasoDAO = new CalculoAtrasoDAO();
        horaDeTrabalhoDAO = new HoraDeTrabalhoDAO();
        atraso();
    }
    
    private void atraso() {
      	 List<CalculoAtraso> atraso = calculoAtrasoDAO.listarTodosCalculoAtraso();
      	 getServletContext().setAttribute("atraso", atraso);
      }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("calcularAtraso".equals(action)) {
            String cpf = request.getParameter("cpf");
            calculoAtrasoDAO.calcularEInserirAtraso(cpf);
        }
    }    

    private void listarAtrasos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {    	 
    	List<CalculoAtraso> atraso = calculoAtrasoDAO.listarTodosCalculoAtraso();
        request.setAttribute("atraso", atraso);
        request.getRequestDispatcher("controleDeHora.jsp").forward(request, response);
    }
    
    private void listarHorarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cpf = request.getParameter("cpf");
        List<HorarioDeTrabalho> horarios = (List<HorarioDeTrabalho>) horaDeTrabalhoDAO.buscarHorarioDeTrabalhoPorCpf(cpf);
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
                	listarAtrasos(request, response);
                    break;
                case "buscarPorCpf":
                  //  buscarAtrasoPorCpf(request, response);
                    break;
                
                default:
                	listarAtrasos(request, response);
                    break;
            }
        } else {
        	listarAtrasos(request, response);
        }
    }
}

