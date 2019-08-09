package uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.registers;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.registers.items.Register;

public class CompanyRegisters {

    @JsonProperty("directors")
    private Register directors;

    @JsonProperty("secretaries")
    private Register secretaries;

    @JsonProperty("persons_with_significant_control")
    private Register pscs;

    @JsonProperty("usual_residential_address")
    private Register ura;

    @JsonProperty("members")
    private Register members;

    @JsonProperty("llp_members")
    private Register llpMembers;

    @JsonProperty("llp_usual_residential_address")
    private Register llpUra;

    public Register getDirectors() {
        return directors;
    }

    public void setDirectors(Register directors) {
        this.directors = directors;
    }

    public Register getSecretaries() {
        return secretaries;
    }

    public void setSecretaries(Register secretaries) {
        this.secretaries = secretaries;
    }

    public Register getPscs() {
        return pscs;
    }

    public void setPscs(Register pscs) {
        this.pscs = pscs;
    }

    public Register getUra() {
        return ura;
    }

    public void setUra(Register ura) {
        this.ura = ura;
    }

    public Register getMembers() {
        return members;
    }

    public void setMembers(Register members) {
        this.members = members;
    }

    public Register getLlpMembers() {
        return llpMembers;
    }

    public void setLlpMembers(Register llpMembers) {
        this.llpMembers = llpMembers;
    }

    public Register getLlpUra() {
        return llpUra;
    }

    public void setLlpUra(Register llpUra) {
        this.llpUra = llpUra;
    }
}
