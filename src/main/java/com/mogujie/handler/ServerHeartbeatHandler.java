package com.mogujie.handler;

import com.mogujie.protocol.HeartbeatInfo;
import com.mogujie.protocol.ResponseMessage;
import com.mogujie.protocol.ReportMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Calendar;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author pingchun@meili-inc.com
 * @since 2019/1/31
 */
public class ServerHeartbeatHandler extends ChannelInboundHandlerAdapter {

    private Map<String,HeartbeatInfo> infos = new ConcurrentHashMap<String, HeartbeatInfo>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String clientId = UUID.randomUUID().toString();
        Integer time =  (int) (Calendar.getInstance().getTime().getTime()/1000);
        HeartbeatInfo heartbeatInfo = new HeartbeatInfo();
        heartbeatInfo.setClientId(clientId);
        heartbeatInfo.setActiveTime(time);
        heartbeatInfo.setLastReportTime(time);
        infos.put(clientId,heartbeatInfo);

        System.out.println("new client:"+clientId);
        super.channelActive(ctx);
        ResponseMessage heartbeatResponse = new ResponseMessage();
        heartbeatResponse.setClientId(clientId);
        ctx.writeAndFlush(heartbeatResponse);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ReportMessage reportMessage = (ReportMessage) msg;

        Integer time =  (int) (Calendar.getInstance().getTime().getTime()/1000);
        HeartbeatInfo heartbeatInfo = infos.get(reportMessage.getClientId());
        heartbeatInfo.setActiveTime(time);
        heartbeatInfo.setLastReportTime(time);
        System.out.println("receive heartbeat:"+reportMessage.getClientId()+",Time:"+time);

        ResponseMessage heartbeatResponse = new ResponseMessage();
        heartbeatResponse.setClientId(reportMessage.getClientId());
        ctx.writeAndFlush(heartbeatResponse);
        super.channelRead(ctx, msg);
    }
}
