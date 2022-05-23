
import java.io.*;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.datatype.jsr310.*;

public class Json {

    public static void SaveToFile(File file, Object a) throws StreamWriteException, DatabindException, IOException {
        writer.writeValue(file, a);
    }

    public static <A> A ReadFromFile(File file, TypeReference<A> type) throws IllegalArgumentException, IOException {
        return mapper.readValue(file, type);
    }

    private static ObjectMapper mapper = getDefaultMapper();

    private static ObjectMapper getDefaultMapper() {
        ObjectMapper defaultMapper = new ObjectMapper();
        defaultMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        defaultMapper.registerModule(new JavaTimeModule());
        return defaultMapper;
    }

    private static ObjectWriter writer = mapper.writer().with(SerializationFeature.INDENT_OUTPUT);

}
