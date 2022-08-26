package main;

import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TrataCliente implements Runnable {

	static int op = -1;
	private Socket soquete_cliente;
	private ObjectOutputStream saida;
	private ObjectInputStream entrada;

	public TrataCliente(Socket soquete_cliente) throws Exception {
		super();
		this.soquete_cliente = soquete_cliente;
		this.saida = new ObjectOutputStream(this.soquete_cliente.getOutputStream());
		this.entrada = new ObjectInputStream(this.soquete_cliente.getInputStream());
	}

	public void enviar_mensagem(Object mensagem) throws Exception {
		this.saida.writeObject(mensagem);
	}

	public Object receber_mensagem() throws Exception {
		return this.entrada.readObject();
	}

	public void finalizar() throws IOException {
		this.soquete_cliente.close();
	}

	@Override
	public void run() {
		try {
			String mensagem;

			String ler;
			Long idUsuario = 1L;

			Agenda agenda = new Agenda(++idUsuario);

			Integer notificacao = Notificacao.QUINZE_MINUTOS;

			do {
				enviar_mensagem(menu());
				op = (Integer) receber_mensagem();

				switch (op) {

				case 1:
					Evento e = new Evento();
					mensagem = "**********************************\n" + "*     Cadastrando Novo Evento    *\n"
							+ "*     Informe nome do Evento     *\n";
					enviar_mensagem(mensagem);
					ler = (String) receber_mensagem();
					e.setNome(ler);
					try {
						mensagem = "*     Informe a data do Evento   *\n" + "*     Neste formato dd/MM/yyyy   *\n";
						enviar_mensagem(mensagem);
						ler = (String) receber_mensagem();
						DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						Date date = df.parse(ler);
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date);
						e.setData(calendar);

						mensagem = "*    Informe um Horario       *\n" + "*    NESTE FORMATO    HH:mm   *";
						enviar_mensagem(mensagem);
						ler = (String) receber_mensagem();

						df = new SimpleDateFormat("HH:mm");
						date = df.parse(ler);
						e.setHorario(date);

					} catch (Exception ex) {
						ex.printStackTrace();
					}

					e.setNotificacao(notificacao);
					agenda.addEvento(e);
					mensagem = "**********************************\n";
					enviar_mensagem(mensagem);

					break;

				case 2:
					mensagem = "**********************************\n" + "*     Para editar um Evento      *\n"
							+ "*     Informe o ID do Evento     *\n";
					enviar_mensagem(mensagem);
					int id = (Integer) receber_mensagem();
					e = new Evento();

					mensagem = "*     Informe nome do Evento     *\n";
					enviar_mensagem(mensagem);
					ler = (String) receber_mensagem();
					e.setNome(ler);

					try {
						mensagem = "*     Informe a data do Evento   *\n" + "*     Neste formato dd/MM/yyyy   *\n";
						enviar_mensagem(mensagem);
						ler = (String) receber_mensagem();
						DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						Date date = df.parse(ler);
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date);
						e.setData(calendar);

						mensagem = "*    Informe um Horario       *\n" + "*    NESTE FORMATO    HH:mm   *";
						enviar_mensagem(mensagem);
						ler = (String) receber_mensagem();

						df = new SimpleDateFormat("HH:mm");
						date = df.parse(ler);
						e.setHorario(date);

						agenda.editarEvento(e, id);
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					agenda.editarEvento(e, id);
					mensagem = "**********************************\n";
					enviar_mensagem(mensagem);

					break;

				case 3:
					mensagem = "**********************************\n" + "*     Para Buscar um Evento      *\n"
							+ "*     Informe o ID do Evento     *\n";
					enviar_mensagem(mensagem);
					id = (Integer) receber_mensagem();
					e = agenda.getByIdEvento(id);
					mensagem = e.toString() + "\n**********************************\n";
					enviar_mensagem(mensagem);

					break;

				case 4:
					mensagem = "**********************************\n" + "*        Listando Eventos        *\n";
					enviar_mensagem(agenda.toString());
					mensagem = "**********************************\n";
					enviar_mensagem(mensagem);

					break;

				case 5:
					mensagem = "**********************************\n" + "*     Para Deletar um Evento     *\n"
							+ "*     Informe o ID do Evento     *\n";
					enviar_mensagem(mensagem);
					id = (Integer) receber_mensagem();
					agenda.deletarEvento(id);

					mensagem = "**********************************\n";
					enviar_mensagem(mensagem);

					break;
				case 6:
					
					break;

				default:
					mensagem = "**********************************\n" + "********OPÇÃO INVALIDA************\n"
							+ "**********************************\n";
					enviar_mensagem(mensagem);

				}

			} while (op != 6);

			mensagem = "**********************************\n" + "***************FIM****************\n"
					+ "**********************************\n";
			enviar_mensagem(mensagem);
			finalizar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String menu() {
		String mensagem = "**********************************\n" + "*               Menu             *\n"
				+ "*    1- Cadastrar novo Evento    *\n" + "*    2- Editar um Evento         *\n"
				+ "*    3- Buscar um Evento         *\n" + "*    4- Imprimir Lista de Evento *\n"
				+ "*    5- Deletar um Evento        *\n" + "*    6- SAIR                     *\n"
				+ "**********************************\n";
		return mensagem;

	}
}
