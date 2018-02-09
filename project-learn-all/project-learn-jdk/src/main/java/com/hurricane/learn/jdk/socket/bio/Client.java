package com.hurricane.learn.jdk.socket.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
/**
 * 为什么采用PrintWriter与BufferedReader的组合？
 * @author Hurricane
 *
 */
public class Client {
	private static String HOST = "127.0.0.1";
	private static int PORT = 8888;
	public static void main(String[] args) throws InterruptedException, IOException {
		Socket socket = null;
		try {
			socket = new Socket(HOST, PORT);
			OutputStream outputStream = socket.getOutputStream();
			InputStream inputStream = socket.getInputStream();
			PrintWriter writer = new PrintWriter(outputStream,true);//用autoFlush参数指定自动写入输出流
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			while (true) {
				writer.println("来自客户端的消息");
				System.out.println(reader.readLine());
				Thread.sleep(3000);
			}
		} catch (IOException e) {
			if (socket!=null) {
				socket.close();
				System.out.println("关闭socket客户端连接");
			}
			e.printStackTrace();
		}finally{
			socket = null;
		}
	}
}
