package uk.gov.companieshouse.document.generator.company.report.mapping.mappers.mortgagechargedetails;

import org.mapstruct.Mapper;
import org.springframework.web.context.annotation.RequestScope;
import uk.gov.companieshouse.api.model.charges.TransactionsApi;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.items.mortgagechargedetails.items.Transaction;

import java.util.List;

@RequestScope
@Mapper(componentModel = "spring")
public interface ApiToTransactionMapper {

    Transaction apiToTransaction(TransactionsApi transactionsApi);

    List<Transaction> apiToTransaction(List<TransactionsApi> transactionsApi);
}
