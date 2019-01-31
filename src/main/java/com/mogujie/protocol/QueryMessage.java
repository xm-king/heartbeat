package com.mogujie.protocol;

import static com.mogujie.util.Constants.TYPE_QUERY;

/**
 * @author pingchun@meili-inc.com
 * @since 2019/1/31
 */
public class QueryMessage extends Message{

    public QueryMessage(){
        super(TYPE_QUERY);
    }

    @Override
    public void decode(byte[] data) {
    }

    @Override
    public byte[] encode(Message message) {
        return new byte[0];
    }
}
