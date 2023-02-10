package org.scapia.utils;

public interface MetricsPublisher {

  void publishCountMetrics(String apiName, String externalService, String metricName);

  void publishLatencyMetrics(String apiName, String externalService, String metricName,
      Long latency);
}
