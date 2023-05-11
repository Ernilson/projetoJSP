package br.com.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MarcacoesFeitas implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String entrada;
    private String saida;
    
    public MarcacoesFeitas(String entrada, String saida) {
        this.entrada = entrada;
        this.saida = saida;
    }
    
    public MarcacoesFeitas() {
		// TODO Auto-generated constructor stub
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
}

class MarcacoesFeitasList {
    private List<MarcacoesFeitas> marcacoes;

    public MarcacoesFeitasList() {
        marcacoes = new ArrayList<>();
    }

    public void adicionarMarcacao(MarcacoesFeitas marcacao) {
        marcacoes.add(marcacao);
    }

    public boolean removerMarcacao(int index) {
        if (index >= 0 && index < marcacoes.size()) {
            marcacoes.remove(index);
            return true;
        }
        return false;
    }

    public void listarMarcacoes() {
        for (MarcacoesFeitas marcacao : marcacoes) {
            System.out.println("Entrada: " + marcacao.getEntrada() + " - Saída: " + marcacao.getSaida());
        }
    }

}
