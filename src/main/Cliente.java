package main;

import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Cliente {

	private Socket soquete;
	private ObjectOutputStream saida;
	private ObjectInputStream entrada;
	private static Cliente cliente;
	private static Scanner scann;

	@SuppressWarnings("static-access")
	public Cliente(String endereco, int porta) throws Exception {
		super();
		this.soquete = new Socket(endereco, porta);
		this.saida = new ObjectOutputStream(this.soquete.getOutputStream());
		this.entrada = new ObjectInputStream(this.soquete.getInputStream());
		this.scann = new Scanner(System.in);
	}

	public void enviar_mensagem(Object mensagem) throws Exception {
		this.saida.writeObject(mensagem);
	}

	public Object receber_mensagem() throws Exception {
		return this.entrada.readObject();
	}

	public void finalizar() throws IOException {
		this.soquete.close();
	}

	public static void main(String[] args) throws Exception {
		String ler;
		int op = -1;
		cliente = new Cliente("127.0.0.1", 15500);
		while (op != 6) {
			System.out.println((String) cliente.receber_mensagem());
			ler = scann.nextLine();
			op = Integer.valueOf(ler);
			cliente.enviar_mensagem(Integer.valueOf(ler));

			opcoes(op);

		}

		System.out.println((String) cliente.receber_mensagem());

		cliente.finalizar();
	}

	public static void opcoes(int op) {
		try {
			String mensagem;

			String ler;
			switch (op) {

			case 1:
				// envia nome
				System.out.println((String) cliente.receber_mensagem());
				ler = scann.nextLine();
				cliente.enviar_mensagem(String.valueOf(ler));
				// envia data
				System.out.println((String) cliente.receber_mensagem());
				ler = scann.nextLine();
				cliente.enviar_mensagem(String.valueOf(ler));
				// envia horario
				System.out.println((String) cliente.receber_mensagem());
				ler = scann.nextLine();
				cliente.enviar_mensagem(String.valueOf(ler));

				System.out.println((String) cliente.receber_mensagem());

				break;

			case 2:
				// edita informa ID
				System.out.println((String) cliente.receber_mensagem());
				ler = scann.nextLine();
				cliente.enviar_mensagem(Integer.valueOf(ler));
				// informa nome
				System.out.println((String) cliente.receber_mensagem());
				ler = scann.nextLine();
				cliente.enviar_mensagem(String.valueOf(ler));
				// informa data
				System.out.println((String) cliente.receber_mensagem());
				ler = scann.nextLine();
				cliente.enviar_mensagem(String.valueOf(ler));
				// informa horario
				System.out.println((String) cliente.receber_mensagem());
				ler = scann.nextLine();
				cliente.enviar_mensagem(String.valueOf(ler));

				System.out.println((String) cliente.receber_mensagem());
				break;

			case 3:
				// envia id
				System.out.println((String) cliente.receber_mensagem());
				ler = scann.nextLine();
				cliente.enviar_mensagem(Integer.valueOf(ler));

				System.out.println((String) cliente.receber_mensagem());

				break;

			case 4:

				System.out.println((String) cliente.receber_mensagem());
				System.out.println((String) cliente.receber_mensagem());

				break;

			case 5:
				// envia id
				System.out.println((String) cliente.receber_mensagem());
				ler = scann.nextLine();
				cliente.enviar_mensagem(Integer.valueOf(ler));

				System.out.println((String) cliente.receber_mensagem());

				break;

			default:

				System.out.println((String) cliente.receber_mensagem());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
