package com.hurricane.learn.jdk.socket.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;

public class Server implements Runnable{
	private AsynchronousServerSocketChannel serverSocketChannel;
	private static int PORT;
	private static Logger logger = Logger.getLogger(Server.class);
	private static int count = 0;
	public Server(int serverPort) {
		PORT = serverPort;
	}
	@Override
	public void run() {
		try {
			serverSocketChannel = AsynchronousServerSocketChannel.open();
			serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", PORT));
			serverSocketChannel.accept("aaa"+count, new AcceptHandler());
			logger.info("服务器初始化完成");
			while (true) {
				Thread.sleep(500);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel,String>{

		@Override
		public void completed(AsynchronousSocketChannel result,
				String attachment) {
			count++;
			logger.info("让服务器再去接收其他的请求");
			serverSocketChannel.accept("aaa"+count, new AcceptHandler());
			logger.info("服务器端接受到一个连接请求,这是第"+count+"个操作,请求码为"+attachment);
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			result.read(buffer, buffer, new ReadHandler(result));
//			try {
//				result.read(buffer).get(10, TimeUnit.SECONDS);
//			} catch (Exception e) {
//				e.printStackTrace();
//			} 
		}
		@Override
		public void failed(Throwable exc, String attachment) {
			logger.info("一个请求操作失败了");
			
		}
	}
	
	private class ReadHandler implements CompletionHandler<Integer, ByteBuffer>{
		private AsynchronousSocketChannel socketChannel;
		public ReadHandler(AsynchronousSocketChannel result) {
			socketChannel = result;
		}

		@Override
		public void completed(Integer result, ByteBuffer attachment) {
			System.out.println("读取到的长度为："+result);
			attachment.flip();
			byte[] buffer = new byte[attachment.remaining()];
			attachment.get(buffer,0,attachment.limit());
			String string = new String(buffer);
			logger.info("内容为："+string);
			attachment.flip();
			socketChannel.write(attachment, socketChannel, new WriteHandler(attachment));
		}
		
		@Override
		public void failed(Throwable exc, ByteBuffer attachment) {
			logger.info("一次读取操作失败");
		}
		
	}
	
	private class WriteHandler implements CompletionHandler<Integer,AsynchronousSocketChannel> {
		private ByteBuffer buffer;
		public WriteHandler(ByteBuffer attachment) {
			buffer = attachment;
		}

		@Override
		public void completed(Integer result, AsynchronousSocketChannel attachment) {
			if (buffer.hasRemaining()) {
				logger.info("上一次未完成发送的内容，继续发送");
				attachment.write(buffer, attachment, new WriteHandler(buffer));
			}
			logger.info("写回客户端成功");
		}

		@Override
		public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
			logger.info("写回客户端失败");
		}
		
	}
	
}
