package com.github.hitzseb.sec.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConceptDto {

    @JsonProperty("label")
    private String label;

    @JsonProperty("units")
    private Map<String, List<FactUnitItemDto>> units;

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public Map<String, List<FactUnitItemDto>> getUnits() { return units; }
    public void setUnits(Map<String, List<FactUnitItemDto>> units) { this.units = units; }
}
