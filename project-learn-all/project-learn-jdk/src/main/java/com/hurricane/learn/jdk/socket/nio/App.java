package com.hurricane.learn.jdk.socket.nio;

import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class App {
	/**
	 * nio不用再消息结束添加回车，会自动写入
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread server = new Thread(new Server(8888));
		Client clientRun = new Client(8888);
		Thread client = new Thread(clientRun);
		clientRun.connect();
		server.start();
		client.start();
		Thread.sleep(1000);
		clientRun.sendMsg("Hello NIO");
		clientRun.sendMsg("Hello NIO");
		clientRun.sendMsg("Hello NIO");
	}
}
