package producer;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.net.URI;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class WikimediaProducer {
    private static final Properties props = new Properties();
    static ResourceBundle appRb = ResourceBundle.getBundle("application");
    static ResourceBundle producerRb = ResourceBundle.getBundle("producer");
    private static final String url = appRb.getString("url");
    private static final String topic = appRb.getString("input.topic");
    private static final int runtime = Integer.getInteger(appRb.getString("run.time"));

    public static void main(String[] args) throws InterruptedException {

        // create producer properties
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
            appRb.getString("bootstrap.servers"));
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
            StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
            StringSerializer.class.getName());
//        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
//            appRb.getString("schema.registry.url"));

        // set producer configs
        props.setProperty(ProducerConfig.LINGER_MS_CONFIG,
            producerRb.getString("linger.ms"));
        props.setProperty(ProducerConfig.BATCH_SIZE_CONFIG,
            producerRb.getString("batch.size"));
        props.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG,
            producerRb.getString("compression.type"));

        // create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        EventHandler eventHandler = new WikimediaHandler(producer, topic);
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();

        // start the producer in another thread
        eventSource.start();

        // producer for n minutes and block the program until then
        TimeUnit.MINUTES.sleep(runtime);
    }
}
