package com.unla.agroecologiaiot.models;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MetricTypeModel {
    private long metricTypeId;

    private String code;
    private String description;
}
