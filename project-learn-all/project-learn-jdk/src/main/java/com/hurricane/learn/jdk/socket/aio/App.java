package com.hurricane.learn.jdk.socket.aio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Future;


public class App {

	public static void main(String[] args) throws Exception {
		Thread serverThread = new Thread(new Server(8888));
		Thread clientThread = new Thread(new Client(8888));
		serverThread.start();
//		Thread.sleep(300);
//		clientThread.start();
	}
	/**
	 * 使用CompletionHandler异步回调读取文件
	 * @throws Exception
	 */
	public void readFileTypeTwo() throws Exception {
		Path path = Paths.get("target/classes/log4j.properties");
	    AsynchronousFileChannel channel = AsynchronousFileChannel.open(path);
	    ByteBuffer buffer = ByteBuffer.allocate(1024);
	    channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
	        @Override
	        public void completed(Integer result, ByteBuffer attachment) {
	        	System.out.println("读取到的长度："+result);
	        	attachment.flip();
	        	byte[] buffer = new byte[1024];
	        	attachment.get(buffer,0,attachment.limit());
	        	System.out.println(new String(buffer));
	        	System.out.println(Thread.currentThread().getName() + " read success!");
	        }

	        @Override
	        public void failed(Throwable exc, ByteBuffer attachment) {
	            System.out.println("read error");
	        }
	    });

	    while (true){
	        System.out.println(Thread.currentThread().getName() + " sleep");
	        Thread.sleep(1000);
	    }
	}
	
	/**
	 * AIO方式读取文件
	 * channel.read(buffer,0)方式
	 * @throws Exception
	 */
	public void readFileTypeOne() throws Exception {
		Path path = Paths.get("target/classes/log4j.properties");
		AsynchronousFileChannel channel = AsynchronousFileChannel.open(path);
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		Future<Integer> future = channel.read(buffer,0);
		while (!future.isDone()){
			System.out.println("I'm idle");
		}
		Integer readNumber = future.get();
		if (readNumber>0) {
			buffer.flip();
			CharBuffer charBuffer = CharBuffer.allocate(1024);
			CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
			decoder.decode(buffer,charBuffer,false);
			charBuffer.flip();
			String data = new String(charBuffer.array(),0, charBuffer.limit());
			System.out.println("read number:" + readNumber);
			System.out.println(data);
		}
	}


}
