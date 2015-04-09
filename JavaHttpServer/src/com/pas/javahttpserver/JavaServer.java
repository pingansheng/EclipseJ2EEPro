package com.pas.javahttpserver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;

public class JavaServer {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		ServerSocket sc = new ServerSocket(2012);
		// 提示一句话
		System.out.println("Waiting in the 9999....");

		while (true) {
			Socket s = sc.accept();
			System.out.println("A request happen," + s.getInetAddress());
			OutputStream os = s.getOutputStream();
			BufferedReader br = new BufferedReader(new FileReader("./aa.html"));

			String buf = "";
			while ((buf = br.readLine()) != null) {
				os.write(buf.getBytes());
			}
			br.close();
			os.close();
			s.close();
		}

	}

}
