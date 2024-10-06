package org.sample.doping.configuration;

import com.couchbase.client.java.codec.JsonTranscoder;
import com.couchbase.client.java.codec.Transcoder;
import com.couchbase.client.java.env.ClusterEnvironment;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CouchbaseCacheManagerBuilderCustomizer;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.cache.CouchbaseCacheConfiguration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;


@Configuration
@Getter
@EnableCaching
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Value("${couchbase.properties.connection-string}")
    private String couchbaseConnectionString;

    @Value("${couchbase.properties.username}")
    private String couchbaseUsername;

    @Value("${couchbase.properties.password}")
    private String couchbasePassword;

    @Value("${couchbase.properties.bucket-name}")
    private String couchbaseBucketName;

    @Value("${couchbase.properties.scope-name}")
    private String couchbaseScopeName;

    @Value("${couchbase.properties.cacheable-collection}")
    private String cacheableCollection;

    @Override
    public String getConnectionString() {
        return getCouchbaseConnectionString();
    }

    @Override
    public String getUserName() {
        return getCouchbaseUsername();
    }

    @Override
    public String getPassword() {
        return getCouchbasePassword();
    }

    @Override
    public String getBucketName() {
        return getCouchbaseBucketName();
    }

    @Override
    public String getScopeName() {
        return getCouchbaseScopeName();
    }

    @Override
    protected void configureEnvironment(ClusterEnvironment.Builder builder) {
        var timeouts = new CouchbaseProperties.Timeouts();

        // timeout configurations..
        builder.timeoutConfig(config -> config.kvTimeout(timeouts.getKeyValue())
                .analyticsTimeout(timeouts.getAnalytics())
                .kvDurableTimeout(timeouts.getKeyValueDurable())
                .queryTimeout(timeouts.getQuery())
                .viewTimeout(timeouts.getView())
                .searchTimeout(timeouts.getSearch())
                .managementTimeout(timeouts.getManagement())
                .connectTimeout(timeouts.getConnect())
                .disconnectTimeout(timeouts.getDisconnect()));
    }

    @Bean
    public CouchbaseCacheManagerBuilderCustomizer couchbaseCacheManagerBuilderCustomizer() {
        var couchbaseObjectMapper = couchbaseObjectMapper(new ObjectMapper());
        var objectMapperSerializer = new CouchbaseJsonSerializer(couchbaseObjectMapper);
        var transcoder = JsonTranscoder.create(objectMapperSerializer);

        return builder -> builder.withCacheConfiguration("ONE_HOUR", getCacheConfiguration(Duration.ofSeconds(3600),
                                getCacheableCollection(), transcoder));
    }

    private ObjectMapper couchbaseObjectMapper(ObjectMapper objectMapper) {
        var couchbaseObjectMapper = objectMapper.copy();

        couchbaseObjectMapper.activateDefaultTyping(
                couchbaseObjectMapper.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY);

        return couchbaseObjectMapper;
    }

    private CouchbaseCacheConfiguration getCacheConfiguration(Duration ttl, String collection,
                                                              Transcoder transcoder) {

        return CouchbaseCacheConfiguration.defaultCacheConfig()
                .valueTranscoder(transcoder)
                .collection(collection)
                .entryExpiry(ttl);
    }
}