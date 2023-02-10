package org.scapia.utils;

import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.MetricDatum;
import com.amazonaws.services.cloudwatch.model.PutMetricDataRequest;
import com.amazonaws.services.cloudwatch.model.StandardUnit;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CloudWatchMetricsPublisher implements MetricsPublisher {

  private AmazonCloudWatch cloudwatch;
  private String cloudWatchNamespace;

  @Override
  public void publishCountMetrics(final String apiName, final String externalService,
      final String metricName) {
    List<Dimension> dimensions = new ArrayList<Dimension>();
    dimensions.add(new Dimension().withName(externalService.toString()).withValue(externalService.toString()));
    dimensions.add(new Dimension().withName("ApiName").withValue(apiName));

    MetricDatum datum = new MetricDatum()
        .withMetricName(metricName.toString())
        .withValue(1.0)
        .withUnit(StandardUnit.Count)
        .withDimensions(dimensions);

    PutMetricDataRequest request = new PutMetricDataRequest()
        .withNamespace(this.cloudWatchNamespace)
        .withMetricData(datum);

    this.cloudwatch.putMetricData(request);
  }

  @Override
  public void publishLatencyMetrics(final String apiName, final String externalService,
      final String metricName, final Long latency) {
    List<Dimension> dimensions = new ArrayList<Dimension>();
    dimensions.add(new Dimension().withName(externalService.toString()).withValue(externalService.toString()));
    dimensions.add(new Dimension().withName("ApiName").withValue(apiName));
    dimensions.add(new Dimension().withName("latency").withValue(String.valueOf(latency)));

    MetricDatum datum = new MetricDatum()
        .withMetricName(metricName.toString())
        .withValue(1.0)
        .withUnit(StandardUnit.Count)
        .withDimensions(dimensions);

    PutMetricDataRequest request = new PutMetricDataRequest()
        .withNamespace(this.cloudWatchNamespace)
        .withMetricData(datum);

    this.cloudwatch.putMetricData(request);
  }


}


