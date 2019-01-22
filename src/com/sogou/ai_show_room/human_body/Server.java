package com.sogou.ai_show_room.human_body;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class Server {
	public static void main(String[] args) throws TTransportException {
		Server server = new Server();

		TServerSocket tServerSocket = new TServerSocket(9090);
		TThreadPoolServer.Args targs = new TThreadPoolServer.Args(tServerSocket);
		TBinaryProtocol.Factory factory = new TBinaryProtocol.Factory();
		TProcessorFactory tProcessorFactory = server.getProcessorFactory();
		targs.protocolFactory(factory);
		targs.processorFactory(tProcessorFactory);
		TThreadPoolServer tThreadPoolServer = new TThreadPoolServer(targs);
		System.out.println("start server...");
		tThreadPoolServer.serve();
	}

	private TProcessorFactory getProcessorFactory() {

		TProcessorFactory tProcessorFactory = new TProcessorFactory(null) {
			@Override
			public TProcessor getProcessor(TTransport trans) {

				return new ThriftIpc.Processor<ThriftIpc.Iface>(new ThriftIpc.Iface() {

					@Override
					public List<PeopleTrack> GetPeoplesTrack() throws TException {
						System.out.println("GetPeoplesTrack server...");
						
						List<PeopleTrack> res = new ArrayList<>();
						PeopleTrack peopleTrack = new PeopleTrack();
						peopleTrack.people_id = "1";
						peopleTrack.current_pos.x = 11;
						peopleTrack.current_pos.y = 22;
						peopleTrack.current_pos.z = 33;

						res.add(peopleTrack);

						return res;
					}
				});
			}
		};

		return tProcessorFactory;
	}
}
