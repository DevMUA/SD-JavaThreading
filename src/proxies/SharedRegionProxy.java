package proxies;

import common.Message;

/**
 * The interface Shared region proxy.
 */
public interface SharedRegionProxy {

    /**
     * Process and reply message.
     *
     * @param msg the msg
     * @return the message
     */
    public Message processAndReply(Message msg);

    /**
     * Is running boolean.
     *
     * @return the boolean
     */
    public boolean isRunning();

}
