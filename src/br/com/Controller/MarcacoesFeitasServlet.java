package br.com.Controller;

import br.com.DAO.CalculoAtrasoDAO;
import br.com.DAO.HoraDeTrabalhoDAO;
import br.com.DAO.MarcacoesFeitasDAO;
import br.com.Entity.HorarioDeTrabalho;
import br.com.Entity.MarcacoesFeitas;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MarcacoesFeitasServlet")
public class MarcacoesFeitasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private MarcacoesFeitasDAO marcacoesFeitasDAO;
    private HoraDeTrabalhoDAO horaDeTrabalhoDAO;
    public void init() {
        marcacoesFeitasDAO = new MarcacoesFeitasDAO();
        horaDeTrabalhoDAO = new HoraDeTrabalhoDAO();
        listarMarcacoes();
    }
    
 // Para que as marcações sejam inicilizados ao abrir a tela
    private void listarMarcacoes() {
    	 List<MarcacoesFeitas> marcacoes = marcacoesFeitasDAO.listarTodasMarcacoesFeitas();
    	 getServletContext().setAttribute("marcacoes", marcacoes);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        try {
        	adicionarMarcacao(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
       
    }

    private void adicionarMarcacao(HttpServletRequest request, HttpServletResponse response)
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
//        }

        MarcacoesFeitas horario = new MarcacoesFeitas();
        horario.setCpf(cpf);
        horario.setEntrada(entrada);
        horario.setIntervaloInicio(intervaloInicio);
        horario.setIntervaloFim(intervaloFim);
        horario.setSaida(saida);

        marcacoesFeitasDAO.adicionarMarcacao(horario);

        listarMarcacoes(request, response);
}

    private void removerMarcacao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      	String cpf = request.getParameter("cpf");
        marcacoesFeitasDAO.removerMarcacoesFeitas(cpf);
        listarMarcacoes(request, response);
    }

    private void listarMarcacoes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<MarcacoesFeitas> marcacoes = marcacoesFeitasDAO.listarTodasMarcacoesFeitas();
        request.setAttribute("marcacoes", marcacoes);
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
                	removerMarcacao(request, response);
                    break;
                case "list":
                	listarMarcacoes(request, response);
                    break;
                
                default:
                	listarMarcacoes(request, response);
                    break;
            }
        } else {
        	listarMarcacoes(request, response);
        }
    }
}

