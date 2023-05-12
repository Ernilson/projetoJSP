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

    public void adicionarHorario(HorarioDeTrabalho horario) {
        String sql = "INSERT INTO HorarioTrabalho (cpf, entrada, inicio_Intervalo, fim_Intervalo, saida) VALUES (?, ?, ?, ?, ?)";

        try (java.sql.Connection con = conn.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, horario.getCpf());
            stmt.setString(2, horario.getEntrada());
            stmt.setString(3, horario.getIntervaloInicio());
            stmt.setString(4, horario.getIntervaloFim());
            stmt.setString(5, horario.getSaida());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    	String sql = "INSERT INTO HorarioTrabalho (cpf, entrada, inicio_Intervalo, fim_Intervalo, saida) VALUES (?, ?, ?, ?, ?)";

        try (java.sql.Connection con = conn.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
        	
        	stmt.setString(1, horario.getCpf());
        	stmt.setString(2, horario.getEntrada());
        	stmt.setString(3, horario.getIntervaloInicio());
        	stmt.setString(4, horario.getIntervaloFim());
            stmt.setString(5, horario.getSaida());
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

    
    public HorarioDeTrabalho listarHorarioDeTrabalhoPorCpf(HorarioDeTrabalho cpf) {
        String sql = "SELECT cpf, entrada, inicio_Intervalo, fim_Intervalo, saida FROM HorarioTrabalho WHERE cpf = ?";

        try (java.sql.Connection con = conn.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setObject(1, cpf.getCpf());            

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                	String  cpfResult = rs.getString("cpf");
                    String entrada = rs.getString("entrada");
                    String inicio_Intervalo = rs.getString("inicio_Intervalo");
                    String fim_Intervalo = rs.getString("fim_Intervalo");
                    String saida = rs.getString("saida");

                    HorarioDeTrabalho horario = new HorarioDeTrabalho();
                    horario.setCpf(cpfResult);
                    horario.setEntrada(entrada);
                    horario.setIntervaloInicio(inicio_Intervalo);
                    horario.setIntervaloFim(fim_Intervalo);
                    horario.setSaida(saida);

                    return horario;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Retorna null se nenhum HorarioDeTrabalho for encontrado com o cpf fornecido
    }

}

