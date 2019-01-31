package com.mogujie.handler;

import com.mogujie.protocol.ReportMessage;
import com.mogujie.protocol.ResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author pingchun@meili-inc.com
 * @since 2019/1/31
 */
public class ClientHeartbeatHandler extends ChannelInboundHandlerAdapter {
    /**
     * 客户端ID
     */
    private String clientId;

    protected final ScheduledExecutorService timerExecutor = new ScheduledThreadPoolExecutor(1);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        timerExecutor.scheduleAtFixedRate(() -> {
                ReportMessage reportMessage = new ReportMessage();
                reportMessage.setClientId(clientId);
                reportMessage.setStatus("OKOKOK");
                ctx.writeAndFlush(reportMessage);
        },3000,1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ResponseMessage response = (ResponseMessage) msg;
        this.clientId = response.getClientId();
        System.out.println(response.getClientId());
        //super.channelRead(ctx, msg);
    }
}
