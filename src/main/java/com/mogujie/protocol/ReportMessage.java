package com.mogujie.protocol;

import java.nio.ByteBuffer;
import java.util.Arrays;

import static com.mogujie.util.Constants.TYPE_REPORT;

/**
 * @author pingchun@meili-inc.com
 * @since 2019/1/31
 */
public class ReportMessage  extends Message {

    private String clientId;
    private String status;

    public ReportMessage(){
        super(TYPE_REPORT);
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void decode(byte[] data) {
        byte[] clientIds = new byte[36];
        System.arraycopy(data,0,clientIds,0,clientIds.length);
       this.clientId = new String(clientIds);

    }

    @Override
    public byte[] encode(Message message) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(256);
        byteBuffer.put(clientId.getBytes());
        byteBuffer.put(status.getBytes());
        return byteBuffer.array();
    }
}
