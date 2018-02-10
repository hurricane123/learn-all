package com.hurricane.learn.jdk.socket.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Random;

import org.apache.log4j.Logger;

public class Client implements Runnable{
	private static int PORT;
	private static Logger logger = Logger.getLogger(Server.class);
	private static AsynchronousSocketChannel  socketChannel;
	public Client(int clientPort) throws Exception {
		socketChannel = AsynchronousSocketChannel.open();
		socketChannel.setOption(StandardSocketOptions.TCP_NODELAY, true);  
		PORT = clientPort;
	}

	@Override
	public void run() {
		socketChannel.connect(new InetSocketAddress("127.0.0.1", PORT), null, new ConnectCompleteHandler());
		while (true) {
			try {
				logger.info("客户端开始向服务端写入数据");
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				buffer.wrap("来自客户端的信息".getBytes());
//				buffer.flip();
				socketChannel.write(buffer, null, new WriteCompleteHandler());
				ByteBuffer getBuffer = ByteBuffer.allocate(1024);
				socketChannel.read(getBuffer, getBuffer, new ReadCompleteHandler());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private class ConnectCompleteHandler implements CompletionHandler<Void, String>{

		@Override
		public void completed(Void result, String attachment) {
			logger.info("客户端连接成功");
		}

		@Override
		public void failed(Throwable exc, String attachment) {
			logger.info("客户端连接失败,再去请求连接");
			socketChannel.connect(new InetSocketAddress("127.0.0.1", PORT), null, new ConnectCompleteHandler());
		}
		
	}
	
	private class WriteCompleteHandler implements CompletionHandler<Integer, String>{

		@Override
		public void completed(Integer result, String attachment) {
			logger.info("写入服务器端完成");
		}

		@Override
		public void failed(Throwable exc, String attachment) {
			logger.info("写入服务器端失败");
		}
		
	}
	
	private class ReadCompleteHandler implements CompletionHandler<Integer, ByteBuffer>{

		@Override
		public void completed(Integer result, ByteBuffer attachment) {
			byte[] buffer = new byte[1024];
			attachment.flip();
			attachment.get(buffer,0,attachment.limit());
			logger.info("客户端读取到服务端的消息，内容为："+new String(buffer));
		}

		@Override
		public void failed(Throwable exc, ByteBuffer attachment) {
			logger.info("客户端读取服务端数据失败");
		}
		
	}


}
