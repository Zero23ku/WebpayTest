package cl.principal.webpaytesting.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "server", ignoreUnknownFields = false)
public class ApplicationProperties {
    private String port;
    private Compression compression;

    public Compression getCompression() {
        return compression;
    }

    public void setCompression(Compression compression) {
        this.compression = compression;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public static class Compression{
        private Boolean enabled;
        private String mimeTypes;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public String getMimeTypes() {
            return mimeTypes;
        }

        public void setMimeTypes(String mimeTypes) {
            this.mimeTypes = mimeTypes;
        }
    }
}
