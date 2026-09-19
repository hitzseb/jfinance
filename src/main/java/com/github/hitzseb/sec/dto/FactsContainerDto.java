package com.github.hitzseb.sec.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FactsContainerDto {

    @JsonProperty("us-gaap")
    private Map<String, ConceptDto> usGaap;

    @JsonProperty("dei")
    private Map<String, ConceptDto> dei;

    public Map<String, ConceptDto> getUsGaap() { return usGaap; }
    public void setUsGaap(Map<String, ConceptDto> usGaap) { this.usGaap = usGaap; }

    public Map<String, ConceptDto> getDei() { return dei; }
    public void setDei(Map<String, ConceptDto> dei) { this.dei = dei; }
}