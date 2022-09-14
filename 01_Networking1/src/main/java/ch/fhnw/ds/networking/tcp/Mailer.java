package ch.fhnw.ds.networking.tcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Mailer {

	static Scanner in;
	static Writer out;

	public static void main(String[] args) throws Exception {
		Socket s = new Socket("lmailer.fhnw.ch", 25);

		in = new Scanner(s.getInputStream());
		out = new PrintWriter(s.getOutputStream(), true /* autoFlush */);

		String hostName = InetAddress.getLocalHost().getHostName();
		System.out.println(hostName);

		receive();
		send("HELO " + hostName);
		receive();
		send("MAIL FROM: <hesham.ouda@students.fhnw.ch>");
		receive();
		send("RCPT TO: <hesham.ouda@outlook.com>");
		receive();
		send("DATA");
		receive();
		send("To: hesham.ouda@outlook.com");
		send("Subject: Meeting");
		send("Hallo Hesham, komm doch rasch in mein Buero.");
		send(".");
		receive();
		s.close();
	}

	private static void receive() throws IOException {
		if (in.hasNext()) {
			String line = in.nextLine();
			System.err.println(line);
		}
	}

	public static void send(String s) throws IOException {
		System.out.println(s);
		out.write(s.replaceAll("\n", "\r\n"));
		out.write("\r\n");
		out.flush();
	}
}
