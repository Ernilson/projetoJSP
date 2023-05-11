package br.com.DAO;

import br.com.Entity.HorarioDeTrabalho;
import br.com.Entity.MarcacoesFeitas;
import br.com.Persistence.Conneciton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoraDeTrabalhoDAO {
	
	private Conneciton conn;
    private List<HorarioDeTrabalho> horarios;

    public HoraDeTrabalhoDAO() {
    	this.conn = new Conneciton();
        horarios = new ArrayList<>(3);
    }

    public boolean adicionarHorario(HorarioDeTrabalho horario) {
        if (horarios.size() < 3) {
            horarios.add(horario);
            return true;
        }
        return false;
    }

    public boolean removerHorario(int index) {
        if (index >= 0 && index < horarios.size()) {
            horarios.remove(index);
            return true;
        }
        return false;
    }

    public List<HorarioDeTrabalho> listarHorarios() {
        return horarios;
    }
    
    public void adicionarHorarioDeTrabalho(HorarioDeTrabalho horario) {
    	String sql = "INSERT INTO HorarioTrabalho (entrada, saida) VALUES (?, ?)";

        try (java.sql.Connection con = conn.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

        	stmt.setString(1, horario.getEntrada());
            stmt.setString(2, horario.getSaida());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<HorarioDeTrabalho> listarTodosHorariosDeTrabalho() {
        String sql = "SELECT entrada, saida FROM HorarioTrabalho";
        List<HorarioDeTrabalho> horarios = new ArrayList<>();

        try (java.sql.Connection con = conn.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String entrada = rs.getString("entrada");
                    String saida = rs.getString("saida");
                    
                    HorarioDeTrabalho horario = new HorarioDeTrabalho();
                    horario.setEntrada(entrada);
                    horario.setSaida(saida);

                    horarios.add(horario);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return horarios; // Retorna a lista de horários
    }

    
    public HorarioDeTrabalho listarHorarioDeTrabalhoPorId(int id) {
        String sql = "SELECT entrada, saida FROM HorarioTrabalho WHERE id = ?";

        try (java.sql.Connection con = conn.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String entrada = rs.getString("entrada");
                    String saida = rs.getString("saida");
                    
                    HorarioDeTrabalho horario = new HorarioDeTrabalho();
                    horario.setEntrada(entrada);
                    horario.setSaida(saida);

                    return horario;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Retorna null se nenhum HorarioDeTrabalho for encontrado com o ID fornecido
    }

}

