package proxies;

import common.Message;

public interface SharedRegionProxy {

    public Message processAndReply(Message msg);
    public boolean isRunning();

}
