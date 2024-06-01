package dgcd.studies.reference.app;

import org.slf4j.Logger;

public class Utils {

    public static void logWithPrefix(Logger log, String prefix) {
        if (log.isDebugEnabled()) {
            log.debug("{} - debug", prefix);
        } else {
            log.info("{} - info", prefix);
        }
    }

}
