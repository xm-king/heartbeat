package com.mogujie.launcher;

import com.mogujie.codec.HeartbeatCodec;
import com.mogujie.handler.ServerHeartbeatHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import static io.netty.channel.ChannelOption.SO_BACKLOG;

/**
 * @author pingchun@meili-inc.com
 * @since 2019/1/31
 */
public class Server {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(SO_BACKLOG,100)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new HeartbeatCodec());
                        p.addLast(new ServerHeartbeatHandler());
                    }
                });
        try{
            ChannelFuture future =bootstrap.bind(8888).sync();
            System.out.println("Server start:...");
            //future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
