package br.com.DAO;

import br.com.Entity.MarcacoesFeitas;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.Persistence.Conneciton;


public class MarcacoesFeitasDAO {

	private Conneciton conn;
    private List<MarcacoesFeitas> marcacoesFeitasList;

    public MarcacoesFeitasDAO() {
    	this.conn = new Conneciton();
        marcacoesFeitasList = new ArrayList<>();
    }

    public void adicionarMarcacao(MarcacoesFeitas marcacao) {
        marcacoesFeitasList.add(marcacao);
    }

    public boolean removerMarcacao(int index) {
        if (index >= 0 && index < marcacoesFeitasList.size()) {
            marcacoesFeitasList.remove(index);
            return true;
        }
        return false;
    }

    public List<MarcacoesFeitas> listarMarcacoes() {
        return marcacoesFeitasList;
    }

    public MarcacoesFeitas buscarMarcacaoPorEntrada(String entrada) {
        for (MarcacoesFeitas marcacao : marcacoesFeitasList) {
            if (marcacao.getEntrada().equals(entrada)) {
                return marcacao;
            }
        }
        return null;
    }

    public boolean atualizarMarcacao(int index, MarcacoesFeitas marcacaoAtualizada) {
        if (index >= 0 && index < marcacoesFeitasList.size()) {
            marcacoesFeitasList.set(index, marcacaoAtualizada);
            return true;
        }
        return false;
    }
    
    public void adicionarMarcacaos(MarcacoesFeitas marcacao) {
    	String sql = "INSERT INTO MarcacoesFeitas (entrada, saida) VALUES (?, ?)";

        try (java.sql.Connection con = conn.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

        	stmt.setString(1, marcacao.getEntrada());
            stmt.setString(2, marcacao.getSaida());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
