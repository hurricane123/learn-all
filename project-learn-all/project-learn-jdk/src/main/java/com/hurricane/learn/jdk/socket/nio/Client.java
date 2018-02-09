package com.hurricane.learn.jdk.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.apache.log4j.Logger;

public class Client implements Runnable{
	private Logger LOGGER = Logger.getLogger(Client.class.getName());
	private SocketChannel socketChannel;
	private Selector selector;
	private String msg = null;
	private volatile boolean status = false;
	private boolean isConnected = false;
	public Client(int port) {
		try {
			socketChannel = SocketChannel.open();
			selector = Selector.open();
			socketChannel.configureBlocking(false);
			status = true;
			LOGGER.info("客户端初始化完成");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		try {
			while (true) {
				if (socketChannel.finishConnect()) {//这里用来读取服务器端发送过来的消息
					LOGGER.info("客户端成功连接");
					socketChannel.register(selector, SelectionKey.OP_READ);
					selector.select(1000);
					Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
					while (iterator.hasNext()) {
						SelectionKey selectionKey = (SelectionKey) iterator.next();
						iterator.remove();
						LOGGER.info("客户端选择器选择到感兴趣的键值");
						LOGGER.info("客户端选择器感兴趣的键值:"+selectionKey.interestOps());
						if (selectionKey.isReadable()) {
							SocketChannel channel = (SocketChannel) selectionKey.channel();
							ByteBuffer buffer = ByteBuffer.allocate(1024);
							channel.read(buffer);
							buffer.flip();
							byte[] bytes = new byte[buffer.remaining()];
							buffer.get(bytes);
							String string = new String(bytes);
							LOGGER.info("客户端接收到消息："+string);
						}
					}
				}else {
					Thread.sleep(100);
					LOGGER.info("客户端还未成功连接");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 连接服务器端的方法
	 */
	public void connect() {
		try {
			LOGGER.info("客户端开始连接");
			if (!socketChannel.connect(new InetSocketAddress("127.0.0.1", 8888))) {
				LOGGER.info("客户端连接失败，将客户端注册为连接状态");
				socketChannel.register(selector, SelectionKey.OP_CONNECT);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMsg(String msg) {
		try {
//			socketChannel.configureBlocking(false);
			isConnected = true;
			this.msg = msg;
			LOGGER.info("开始写入数据");
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			byte[] bytes = msg.getBytes();
			buffer.put(bytes);
			buffer.flip();
			socketChannel.write(buffer);
			LOGGER.info("客户端写入数据结束");
			LOGGER.info("将客户端注册为可读状态");
			socketChannel.register(selector, SelectionKey.OP_READ);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
}
