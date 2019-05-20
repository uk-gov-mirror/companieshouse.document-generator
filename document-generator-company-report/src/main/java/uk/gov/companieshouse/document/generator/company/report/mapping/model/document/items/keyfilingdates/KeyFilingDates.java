package uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.keyfilingdates;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.keyfilingdates.items.AccountingReferenceDate;

import java.time.LocalDate;

@JsonInclude(Include.NON_NULL)
public class KeyFilingDates {

    @JsonProperty("accounting_reference_date")
    private AccountingReferenceDate accountingReferenceDate;

    @JsonProperty("last_accounts_made_up_to")
    private LocalDate lastAccountsMadeUpTo;

    @JsonProperty("accounts_type")
    private String accountsType;

    @JsonProperty("next_accounts_due")
    private LocalDate nextAccountsDue;

    @JsonProperty("last_confirmation_statement")
    private LocalDate lastConfirmationStatement;

    @JsonProperty("next_confirmation_statement")
    private LocalDate nextConfirmationStatement;

    @JsonProperty("last_members_list")
    private LocalDate lastMembersList;

    public AccountingReferenceDate getAccountingReferenceDate() {
        return accountingReferenceDate;
    }

    public void setAccountingReferenceDate(
        AccountingReferenceDate accountingReferenceDate) {
        this.accountingReferenceDate = accountingReferenceDate;
    }

    public LocalDate getLastAccountsMadeUpTo() {
        return lastAccountsMadeUpTo;
    }

    public void setLastAccountsMadeUpTo(LocalDate lastAccountsMadeUpTo) {
        this.lastAccountsMadeUpTo = lastAccountsMadeUpTo;
    }

    public String getAccountsType() {
        return accountsType;
    }

    public void setAccountsType(String accountsType) {
        this.accountsType = accountsType;
    }

    public LocalDate getNextAccountsDue() {
        return nextAccountsDue;
    }

    public void setNextAccountsDue(LocalDate nextAccountsDue) {
        this.nextAccountsDue = nextAccountsDue;
    }

    public LocalDate getLastConfirmationStatement() {
        return lastConfirmationStatement;
    }

    public void setLastConfirmationStatement(LocalDate lastConfirmationStatement) {
        this.lastConfirmationStatement = lastConfirmationStatement;
    }

    public LocalDate getNextConfirmationStatement() {
        return nextConfirmationStatement;
    }

    public void setNextConfirmationStatement(LocalDate nextConfirmationStatement) {
        this.nextConfirmationStatement = nextConfirmationStatement;
    }

    public LocalDate getLastMembersList() {
        return lastMembersList;
    }

    public void setLastMembersList(LocalDate lastMembersList) {
        this.lastMembersList = lastMembersList;
    }
}
