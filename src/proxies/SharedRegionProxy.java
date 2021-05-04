package proxies;

import Common.Message;

public interface SharedRegionProxy {

    public Message processAndReply(Message msg);
    public boolean isRunning();

}
