package com.xy.net.mina.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.xy.net.mina.protocol.MinaCodecFactory;

/**
 * mina框架的启动类(TCP长连接)，即游戏服务器IoService
 */
public class MinaNetworkManager implements Runnable {
	/**
	 *网络连接管理器构造方法，开启TCP网络连接线程 
	 */
	
	private Logger log = Logger.getLogger(MinaNetworkManager.class);
	public MinaNetworkManager() {
		new Thread(this).start();
	}

	/**
	 * 启动网络service
	 */
	public void startNetwork() throws Exception {
		//IoAcceptor acceptor = new NioSocketAcceptor(1);//设置processor个数为5（cpu核数+1）
		NioSocketAcceptor acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("Logger", new LoggingFilter());
		//acceptor.getFilterChain().addLast("myChin", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		//acceptor.getFilterChain().addLast("myChin", new ProtocolCodecFilter();
		
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MinaCodecFactory(Charset.forName("UTF-8"))));

		acceptor.setHandler(new MinaSessionHandler());
		acceptor.getSessionConfig().setReadBufferSize(1024 * 1024);
		acceptor.getSessionConfig().setReceiveBufferSize(1024 * 1024);
		acceptor.getSessionConfig().setSendBufferSize(1024 * 1024);
		acceptor.getSessionConfig().setTcpNoDelay(true);
		//acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 60 ); 
		int PORT = 9999;
		try {
			acceptor.bind(new InetSocketAddress(PORT));
			log.info("Port:" + PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 关闭网络
	 */
	public void stopNetwork() {
		return;
	}

	/**
	 * 用于其他作用，比如定时任务等
	 */
	public void run() {

	}
}
