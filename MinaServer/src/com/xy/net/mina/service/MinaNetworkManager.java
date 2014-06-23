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
 * mina��ܵ�������(TCP������)������Ϸ������IoService
 */
public class MinaNetworkManager implements Runnable {
	/**
	 *�������ӹ��������췽��������TCP���������߳� 
	 */
	
	private Logger log = Logger.getLogger(MinaNetworkManager.class);
	public MinaNetworkManager() {
		new Thread(this).start();
	}

	/**
	 * ��������service
	 */
	public void startNetwork() throws Exception {
		//IoAcceptor acceptor = new NioSocketAcceptor(1);//����processor����Ϊ5��cpu����+1��
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
	 * �ر�����
	 */
	public void stopNetwork() {
		return;
	}

	/**
	 * �����������ã����綨ʱ�����
	 */
	public void run() {

	}
}
