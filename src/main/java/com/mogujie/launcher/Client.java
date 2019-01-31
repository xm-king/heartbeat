package com.mogujie.launcher;

import com.mogujie.codec.HeartbeatCodec;
import com.mogujie.handler.ClientHeartbeatHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;


import static io.netty.channel.ChannelOption.TCP_NODELAY;

/**
 * @author pingchun@meili-inc.com
 * @since 2019/1/31
 */
public class Client {
    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new HeartbeatCodec());
                        ch.pipeline().addLast(new ClientHeartbeatHandler());
                    }
                });
        try{
            bootstrap.connect("127.0.0.1",8888);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
