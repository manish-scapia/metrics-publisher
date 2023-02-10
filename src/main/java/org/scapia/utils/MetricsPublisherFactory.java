package org.scapia.utils;

import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import lombok.NonNull;

public class MetricsPublisherFactory {

  public static MetricsPublisher getMetricsPublisher(@NonNull String nameSpace,
      @NonNull AmazonCloudWatch amazonCloudWatch) {
    return CloudWatchMetricsPublisher.builder()
        .cloudWatchNamespace(nameSpace)
        .cloudwatch(amazonCloudWatch)
        .build();
  }
}
