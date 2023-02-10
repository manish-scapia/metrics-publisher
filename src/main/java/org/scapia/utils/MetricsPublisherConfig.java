package org.scapia.utils;

import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsPublisherConfig {

  private static final String nameSpace = "TestService";
  private static final AmazonCloudWatch amazonCloudWatch = AmazonCloudWatchClientBuilder.defaultClient();

  @Bean
  MetricsPublisher getMetricsPublisher() {
    return new MetricsPublisherFactory().getMetricsPublisher(nameSpace, amazonCloudWatch);
  }
}
