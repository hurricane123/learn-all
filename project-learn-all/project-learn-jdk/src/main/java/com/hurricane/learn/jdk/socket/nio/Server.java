package com.hurricane.learn.jdk.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

public class Server implements Runnable{
	private static int serverPort = 8888;
	private Selector selector;
	private ServerSocketChannel serverSocketChannel;
	private volatile boolean status = false;
	private static Logger LOGGER = Logger.getLogger(Server.class.getName());
	public Server(int port) {
		try {
			serverPort = port;
			selector = Selector.open();
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", serverPort), 1024);
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			status = true;
			LOGGER.info("服务器已启动在端口号："+serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		status = false;
	}
	
	@Override
	public void run() {
		while (status) {
			try {
				int length = selector.select(1000);
				if (length==0) {
					LOGGER.info("等待超时");
//					LOGGER.info(selector.);
				}else if (length>0) {
					LOGGER.info("选择到合适的连接,连接数："+length);
				}else {
					LOGGER.info("发生异常"+length);
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectedKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey selectionKey = (SelectionKey) iterator.next();
				iterator.remove();
//				LOGGER.info("感兴趣的事件有："+selectionKey.interestOps());
//				selectionKey.interestOps(selectionKey.interestOps());
//				LOGGER.info("感兴趣的事件2有："+selectionKey.interestOps());
				try {
					handleKey(selectionKey);
				} catch (Exception e) {
					if (selectionKey!=null) {
						selectionKey.cancel();
						if (selectionKey.channel()!=null) {
							try {
								selectionKey.channel().close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

	private void handleKey(SelectionKey selectionKey) {
		try {
			if (selectionKey.isValid()) {
//				selectionKey.is
				if (selectionKey.isAcceptable()) {
					ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
					SocketChannel sc = ssc.accept();
					LOGGER.info("服务器接收客户端连接");
					sc.configureBlocking(false);
					sc.register(selector, SelectionKey.OP_READ);
				}
				if (selectionKey.isReadable()) {
					LOGGER.info("客户端通道现在可读");
					SocketChannel sc = (SocketChannel) selectionKey.channel();
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					int length = sc.read(buffer);
					if(length!=-1) {
						buffer.flip();
						byte[] bytes = new byte[buffer.remaining()];
						buffer.get(bytes);
						String msg = new String(bytes);
						LOGGER.info("服务器端接收到信息："+msg);
						//再将接收到的消息传回客户端
						buffer.flip();
						sc.write(buffer);
						LOGGER.info("服务器端将接收到的消息回写到客户端");
					}else {
						selectionKey.cancel();
						sc.close();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
