package dgcd.studies.reference.techinfo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class TechInfoServiceImpl implements TechInfoService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");


    private final Map<String, String> map;


    public TechInfoServiceImpl(Map<String, String> additionalParams) {
        var tmpMap = new HashMap<String, String>();

        try {
            tmpMap.put("hostname", InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        tmpMap.put("javaVersion", System.getProperty("java.version"));
        tmpMap.put("javaVmInfo", String.format(
                "%s - %s (build %s, %s)",
                System.getProperty("java.vm.name"),
                System.getProperty("java.vendor"),
                System.getProperty("java.vm.version"),
                System.getProperty("java.vm.info")));

        if (additionalParams != null && !additionalParams.isEmpty()) {
            tmpMap.putAll(additionalParams);
        }

        this.map = tmpMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (o1, o2) -> o1, LinkedHashMap::new));
    }


    @Override
    public Map<String, String> getTechinfoMap() {
        var resultMap = new LinkedHashMap<>(map);
        resultMap.put("currentTime", LocalDateTime.now().format(FORMATTER));
        return Collections.unmodifiableMap(resultMap);
    }

}
