package com.sogou.ai_show_room.human_body;

import java.util.List;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class HumanBody {
    private volatile boolean isStoped = true;

    public void start(final String ip, final int port, final Callback cb) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TTransport tProtocol = getTTransport(ip, port);
                    if (null != tProtocol) {
                        TProtocol protocol = new TBinaryProtocol(tProtocol);
                        ThriftIpc.Client client = new ThriftIpc.Client(protocol);

                        isStoped = false;

                        while (!isStoped) {
                            List<PeopleTrack> list = client.GetPeoplesTrack();

                            if (null != list && list.size() > 0) {
                                cb.callback(0, list);
                            } else {
                                // LogUtil.i("human", "client.GetPeoplesTrack res is empty");
                            }

                            Thread.sleep(100);
                        }
                    } else {
                        cb.callback(-1, "TTransport == null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    cb.callback(-1, e.getMessage());
                }
            }
        }).start();
    }

    public void stop() {
        isStoped = true;
    }

    private TTransport getTTransport(String ip, int port) throws Exception {
        try {
            TSocket tSocket = new TSocket(ip, port);
            TFramedTransport.Factory factory = new TFramedTransport.Factory();
            TTransport tTransport = factory.getTransport(tSocket);
            if (!tTransport.isOpen()) {
                tTransport.open();
            }
            return tTransport;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static interface Callback {
        void callback(int errorCode, Object msg);
    }
}
