package pl.akiba.wsclient.client.factory;

/**
 * 
 * @author sobczakt
 */
public class JettyHttpClientConf {

    private final Integer timeout;
    private final Integer maxRetries;
    private final Integer maxThreads;
    private final Integer idleTimeout;
    private final Integer connectorType;
    private final Boolean connectBlocking;
    private final Integer maxConnectionsPerAddress;

    private JettyHttpClientConf(Integer timeout, Integer maxRetries, Integer maxThreads, Integer idleTimeout,
            Integer connectorType, Boolean connectBlocking, Integer maxConnectionsPerAddress) {
        this.timeout = timeout;
        this.maxRetries = maxRetries;
        this.maxThreads = maxThreads;
        this.idleTimeout = idleTimeout;
        this.connectorType = connectorType;
        this.connectBlocking = connectBlocking;
        this.maxConnectionsPerAddress = maxConnectionsPerAddress;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public Integer getMaxRetries() {
        return maxRetries;
    }

    public Integer getMaxThreads() {
        return maxThreads;
    }

    public Integer getIdleTimeout() {
        return idleTimeout;
    }

    public Integer getConnectorType() {
        return connectorType;
    }

    public Boolean getConnectBlocking() {
        return connectBlocking;
    }

    public Integer getMaxConnectionsPerAddress() {
        return maxConnectionsPerAddress;
    }

    public static class Builder {

        private Integer timeout;
        private Integer maxRetries;
        private Integer maxThreads;
        private Integer idleTimeout;
        private Integer connectorType;
        private Boolean connectBlocking;
        private Integer maxConnectionsPerAddress;

        public Builder withTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder withMaxRetries(int maxRetries) {
            this.maxRetries = maxRetries;
            return this;
        }

        public Builder withMaxThreads(int maxThreads) {
            this.maxThreads = maxThreads;
            return this;
        }

        public Builder withIdleTimeout(int idleTimeout) {
            this.idleTimeout = idleTimeout;
            return this;
        }

        public Builder withConnectorType(int connectorType) {
            this.connectorType = connectorType;
            return this;
        }

        public Builder withConnectBlocking(boolean connectBlocking) {
            this.connectBlocking = connectBlocking;
            return this;
        }

        public Builder withMaxConnectionsPerAddress(int maxConnectionsPerAddress) {
            this.maxConnectionsPerAddress = maxConnectionsPerAddress;
            return this;
        }

        public JettyHttpClientConf build() {
            return new JettyHttpClientConf(timeout, maxRetries, maxThreads, idleTimeout, connectorType,
                    connectBlocking, maxConnectionsPerAddress);
        }

    }

}
