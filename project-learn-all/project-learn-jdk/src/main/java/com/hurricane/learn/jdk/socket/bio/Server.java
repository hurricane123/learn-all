package com.hurricane.learn.jdk.socket.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 为什么采用PrintWriter与BufferedReader的组合？
 * @author Hurricane
 *
 */
public class Server implements Runnable{
	private Socket socket;
	private static ExecutorService executorService = Executors.newFixedThreadPool(3);
	public Server(Socket socket) {
		this.socket = socket;
	}
	private static int PORT = 8888;
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT);
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("有连接请求接入");
				executorService.submit(new Server(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			if (socket!=null) {
				System.out.println("连接池开始处理连接请求");
				BufferedReader reader = null;
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				OutputStream outputStream = socket.getOutputStream();
				PrintWriter writer = new PrintWriter(outputStream,true);
				String line;
				try {
					while ((line=reader.readLine())!=null) {
						System.out.println(line);
//						outputStream.write(line.getBytes());
						writer.println("来自服务器端的响应： "+line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("请求处理完成");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
