The container image exposes two ports:

- 9092 for client communication. That is necessary for producers and consumers to connect.
- 9093 for internal, inter-broker communication.


Those are used in the entry point script to derive values for broker settings in server.properties:

- REPLICAS - used as an iterator boundary to set the controller.quorum.voters property to a list of brokers.
- SERVICE and NAMESPACE - used to derive the CoreDNS name for each broker in the cluster for setting controller.quorum.voters, listeners and advertised.listeners.
- SHARE_DIR - used to set log.dirs; The directories in which the Kafka data is stored.
- CLUSTER_ID is the unique identifier for the Kafka cluster.
- DEFAULT_REPLICATION_FACTOR is the cluster-wide default replication factor.
- DEFAULT_MIN_INSYNC_REPLICAS is the cluster-wise default in-sync replicas size.