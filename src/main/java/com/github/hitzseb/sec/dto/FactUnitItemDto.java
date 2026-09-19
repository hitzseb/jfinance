package com.github.hitzseb.sec.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FactUnitItemDto {

    @JsonProperty("end")
    private String end;

    @JsonProperty("val")
    private String val;

    @JsonProperty("fy")
    private Integer fy;

    @JsonProperty("fp")
    private String fp;

    @JsonProperty("form")
    private String form;

    @JsonProperty("filed")
    private String filed;

    public String getEnd() { return end; }
    public void setEnd(String end) { this.end = end; }

    public String getVal() { return val; }
    public void setVal(String val) { this.val = val; }

    public Integer getFy() { return fy; }
    public void setFy(Integer fy) { this.fy = fy; }

    public String getFp() { return fp; }
    public void setFp(String fp) { this.fp = fp; }

    public String getForm() { return form; }
    public void setForm(String form) { this.form = form; }

    public String getFiled() { return filed; }
    public void setFiled(String filed) { this.filed = filed; }
}