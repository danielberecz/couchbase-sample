package com.daniel.sample.configuration;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CouchbaseBucketConfig {

    @Bean
    public Bucket getBucket() {
        System.setProperty("com.couchbase.queryEnabled", "true");
        Cluster cluster = CouchbaseCluster.create("localhost");
        return cluster.openBucket("default");
    }

}
