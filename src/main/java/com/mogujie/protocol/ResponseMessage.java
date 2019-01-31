package com.mogujie.protocol;

import static com.mogujie.util.Constants.TYPE_RESPONSE;

/**
 * @author pingchun@meili-inc.com
 * @since 2019/1/31
 */
public class ResponseMessage  extends Message{
    private String clientId;

    public ResponseMessage(){
        super(TYPE_RESPONSE);
    }
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public void decode(byte[] data) {
        this.clientId = new String(data);
    }

    @Override
    public byte[] encode(Message message) {
        return this.clientId.getBytes();
    }
}
