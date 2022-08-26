package main;

import java.util.ArrayList;

public class Agenda {
	private Long idUsuario = 0L;
	private ArrayList<Evento> eventos;

	public Agenda() {
		
	}

	public Agenda(Long idUsuario) {
		this.idUsuario = idUsuario;
		this.eventos = new ArrayList<Evento>();
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void addEvento(Evento evento) {
		int lastIdx = this.eventos.size();
		evento.setId(lastIdx);
		this.eventos.add(evento);
	}

	public ArrayList<Evento> listarEventos() {
		ArrayList<Evento> lista = new ArrayList<Evento>();
		for (Evento e : this.eventos) {
			if (e.getIsActive()) {
				lista.add(e);
			}
		}
		return lista;
	}

	public void editarEvento(Evento evento, Integer id) {
		for (Evento e : this.eventos) {
			if (e.getId() == id) {
				e.setNome(evento.getNome());
				e.setData(evento.getData());
				e.setHorario(evento.getHorario());
				e.setNotificacao(evento.getNotificacao());
			}
		}
	}

	public void deletarEvento(Integer id) {
		
		for (Evento e : this.eventos) {
			if (e.getId() == id) {
				e.setIsActive(false);
				System.out.println("Evento deletado!");
			}
		}
	}

	public void imprimirEventos() {
		for (Evento e : this.eventos) {
			if (e.getIsActive()) {
				System.out.println(e);
			}
		}
	}
	
	public Evento getByIdEvento(Integer id) {
		for (Evento e : this.eventos) {
			if (e.getId() == id) {
				System.out.println("Evento encontrado: " + e);
				return e;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		String mensagem = "Agenda [idUsuario=" + idUsuario + "\n Eventos:{\n";
		for (Evento e : this.eventos) {
			if (e.getIsActive()) {
				mensagem += e + "\n";
			}
		}
		mensagem += "}\n\n";
		return mensagem;
	}
	
}
