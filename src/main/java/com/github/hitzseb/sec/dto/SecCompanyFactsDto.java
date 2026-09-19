package com.github.hitzseb.sec.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SecCompanyFactsDto {

    @JsonProperty("cik")
    private Object cik;

    @JsonProperty("entityName")
    private String entityName;

    @JsonProperty("facts")
    private FactsContainerDto facts;

    public Object getCik() { return cik; }
    public void setCik(Object cik) { this.cik = cik; }

    public String getEntityName() { return entityName; }
    public void setEntityName(String entityName) { this.entityName = entityName; }

    public FactsContainerDto getFacts() { return facts; }
    public void setFacts(FactsContainerDto facts) { this.facts = facts; }
}
