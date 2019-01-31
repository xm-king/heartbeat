package com.mogujie.codec;

import com.mogujie.protocol.Message;
import com.mogujie.protocol.ReportMessage;
import com.mogujie.protocol.ResponseMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

import static com.mogujie.util.Constants.TYPE_QUERY;
import static com.mogujie.util.Constants.TYPE_REPORT;
import static com.mogujie.util.Constants.TYPE_RESPONSE;

/**
 * @author pingchun@meili-inc.com
 * @since 2019/1/31
 */
public class HeartbeatCodec extends ByteToMessageCodec<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getType());
        out.writeBytes(msg.encode(msg));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int type = in.readInt();
        byte[] data = new byte[in.readableBytes()];
        in.readBytes(data);
        switch (type){
            case TYPE_REPORT:
                ReportMessage reportMessage = new ReportMessage();
                reportMessage.decode(data);
                out.add(reportMessage);
                break;
            case TYPE_QUERY:
                break;
            case TYPE_RESPONSE:
                ResponseMessage responseMessage = new ResponseMessage();
                responseMessage.decode(data);
                out.add(responseMessage);
                break;
            default:
                    break;
        }
    }
}
