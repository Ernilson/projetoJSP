package br.com.Controller;

import br.com.DAO.MarcacoesFeitasDAO;
import br.com.Entity.MarcacoesFeitas;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MarcacoesFeitasServlet")
public class MarcacoesFeitasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MarcacoesFeitasDAO marcacoesFeitasDAO;

    public void init() {
        marcacoesFeitasDAO = new MarcacoesFeitasDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        switch (action) {
            case "add":
                adicionarMarcacao(request, response);
                break;
            case "remove":
                removerMarcacao(request, response);
                break;
            case "update":
                atualizarMarcacao(request, response);
                break;
            default:
                listarMarcacoes(request, response);
        }
    }

    private void adicionarMarcacao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String entrada = request.getParameter("entrada");
        String saida = request.getParameter("saida");
        MarcacoesFeitas marcacao = new MarcacoesFeitas(entrada, saida);
        marcacoesFeitasDAO.adicionarMarcacao(marcacao);
        listarMarcacoes(request, response);
    }

    private void removerMarcacao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("index"));
        marcacoesFeitasDAO.removerMarcacao(index);
        listarMarcacoes(request, response);
    }

    private void atualizarMarcacao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("index"));
        String entrada = request.getParameter("entrada");
        String saida = request.getParameter("saida");
        MarcacoesFeitas marcacaoAtualizada = new MarcacoesFeitas(entrada, saida);
        marcacoesFeitasDAO.atualizarMarcacao(index, marcacaoAtualizada);
        listarMarcacoes(request, response);
    }

    private void listarMarcacoes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<MarcacoesFeitas> marcacoesFeitasList = marcacoesFeitasDAO.listarMarcacoes();
        request.setAttribute("marcacoesFeitasList", marcacoesFeitasList);
        request.getRequestDispatcher("listarMarcacoesFeitas.jsp").forward(request, response);
    }
}

