package com.mogujie.protocol;

/**
 * @author pingchun@meili-inc.com
 * @since 2019/1/31
 */
public abstract class Message {
    private int type;

    public int getType() {
        return type;
    }

    public Message(int type) {
        this.type = type;
    }

    public abstract void decode(byte[] data);

    public abstract byte[] encode(Message message);
}
