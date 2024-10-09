package com.rest.service.core.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.text.StringSubstitutor;
import com.google.common.collect.ImmutableMap;
import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
public class ServiceMongo {

    private static final Logger log = LoggerFactory.getLogger(ServiceMongo.class);

    @Autowired
    private MongoDBConfiguration mongoDBConfiguration;

    @Value("${spring.data.mongodb.uri:mongodb://localhost:27017/rest_service}")
    private String fullDatabaseEndpointUrl;

    @Bean
    @Primary
    public MongoDatabaseFactory mongoDbFactory() {
        return new SimpleMongoClientDatabaseFactory(mongoClient(), getDatabaseName());
    }

    @Bean
    @Primary
    public MongoTemplate mongoTemplate() {
        MappingMongoConverter converter =
                new MappingMongoConverter(mongoDbFactory(), new MongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null)); // Removes the _class field
        return new MongoTemplate(mongoDbFactory(), converter);
    }

    @Bean
    @Primary
    public MongoClient mongoClient() {
        String connectionURL = mongoConnectionString(fullDatabaseEndpointUrl, mongoDBConfiguration.getUser(), mongoDBConfiguration.getDbName());
        ConnectionString connectionString = new ConnectionString(connectionURL);
        setFullDatabaseEndpointUrl(connectionURL);  // Storing the final connection URL
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        log.info("Connecting to MongoDB at {}", connectionString.getHosts());
        return MongoClients.create(settings);
    }

    private String mongoConnectionString(final String connectionString, final MongoDBConfiguration.User user, String dbName) {
        try {
            Map<String, String> values = ImmutableMap.of(
                    "username", URLEncoder.encode(user.getName(), StandardCharsets.UTF_8.toString()),
                    "password", URLEncoder.encode(user.getPassword(), StandardCharsets.UTF_8.toString()),
                    "authdb_name", user.getAuthdb(),
                    "db_name", dbName
            );
            return new StringSubstitutor(values, "$<", ">").replace(connectionString);
        } catch (UnsupportedEncodingException e) {
            log.error("Failed to URL encode the username or password", e);
            throw new IllegalStateException("Error building MongoDB connection string", e);  // Fails fast on error
        }
    }

    protected String getDatabaseName() {
        return mongoDBConfiguration.getDbName();
    }

    public String getFullDatabaseEndpointUrl() {
        return fullDatabaseEndpointUrl;
    }

    public void setFullDatabaseEndpointUrl(String fullDatabaseEndpointUrl) {
        this.fullDatabaseEndpointUrl = fullDatabaseEndpointUrl;
    }
}
