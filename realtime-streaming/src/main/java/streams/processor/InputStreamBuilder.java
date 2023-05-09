package streams.processor;

import org.apache.kafka.streams.kstream.KStream;

public class InputStreamBuilder {

    private final KStream<String, String> inputStream;

    public InputStreamBuilder(KStream<String, String> inputStream) {
        this.inputStream = inputStream;
    }

    public void setup() {
        this.inputStream
            .mapValues(changeJson -> {

            })
    }
}
