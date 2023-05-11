package br.com.DAO;

import br.com.Entity.HorarioDeTrabalho;
import br.com.Entity.MarcacoesFeitas;
import br.com.Persistence.Conneciton;

import java.sql.PreparedStatement;
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
}

