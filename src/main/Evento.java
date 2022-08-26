package main;

import java.util.Calendar;
import java.util.Date;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Evento implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private Calendar data;
	private Date horario;
	private Integer notificacao = Notificacao.QUINZE_MINUTOS;
	private Boolean isActive;

	public Evento() {
		this.isActive = true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public Integer getNotificacao() {
		return notificacao;
	}

	public void setNotificacao(Integer notificacao) {
		this.notificacao = notificacao;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat h = new SimpleDateFormat("HH:mm");
		return "Evento [id=" + id + ", nome=" + nome + ", data=" + d.format(data.getTime()) + ", horario="
				+ h.format(horario) + ", notificacao=" + notificacao + "]";
	}

}
