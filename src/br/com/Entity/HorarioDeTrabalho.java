package br.com.Entity;

import java.util.ArrayList;
import java.util.List;

public class HorarioDeTrabalho {

	private String entrada;
    private String saida;

    public HorarioDeTrabalho() {
		// TODO Auto-generated constructor stub
	}
    
    public HorarioDeTrabalho(String entrada, String saida) {
        this.entrada = entrada;
        this.saida = saida;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public static class HorarioDeTrabalhoList {
        private List<HorarioDeTrabalho> horarios;

        public HorarioDeTrabalhoList() {
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

        public void listarHorarios() {
            for (HorarioDeTrabalho horario : horarios) {
                System.out.println("Entrada: " + horario.getEntrada() + " - Saída: " + horario.getSaida());
            }
        }
    }
}
