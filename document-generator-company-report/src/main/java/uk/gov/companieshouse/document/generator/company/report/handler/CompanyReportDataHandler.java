package uk.gov.companieshouse.document.generator.company.report.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriTemplate;
import uk.gov.companieshouse.api.ApiClient;
import uk.gov.companieshouse.api.error.ApiErrorResponseException;
import uk.gov.companieshouse.api.handler.exception.URIValidationException;
import uk.gov.companieshouse.api.model.charges.ChargesApi;
import uk.gov.companieshouse.api.model.company.CompanyProfileApi;
import uk.gov.companieshouse.api.model.filinghistory.FilingHistoryApi;
import uk.gov.companieshouse.api.model.insolvency.InsolvencyApi;
import uk.gov.companieshouse.api.model.officers.OfficersApi;
import uk.gov.companieshouse.api.model.psc.PscsApi;
import uk.gov.companieshouse.document.generator.company.report.exception.HandlerException;
import uk.gov.companieshouse.document.generator.company.report.exception.MapperException;
import uk.gov.companieshouse.document.generator.company.report.exception.ServiceException;
import uk.gov.companieshouse.document.generator.company.report.mapping.mappers.CompanyReportMapper;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.CompanyReportApiData;
import uk.gov.companieshouse.document.generator.company.report.mapping.model.document.CompanyReport;
import uk.gov.companieshouse.document.generator.company.report.service.ApiClientService;
import uk.gov.companieshouse.document.generator.company.report.service.CompanyService;
import uk.gov.companieshouse.document.generator.interfaces.model.DocumentInfoResponse;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import static uk.gov.companieshouse.document.generator.company.report.CompanyReportDocumentInfoServiceImpl.MODULE_NAME_SPACE;

@Component
public class CompanyReportDataHandler {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ApiClientService apiClientService;

    @Autowired
    private CompanyReportMapper companyReportMapper;

    private static final Logger LOG = LoggerFactory.getLogger(MODULE_NAME_SPACE);

    private static final String NOT_FOUND_API_DATA = "No data found in %s api for link: ";

    private static final UriTemplate GET_COMPANY_URI = new UriTemplate("/company/{companyNumber}");


    /**
     * Get Company Report data from the different resources associated with company number, and
     * map the data for document generation
     *
     * @param resourceUri the resource uri of the company accounts
     * @param requestId the id of the request
     * @return a populated {@link DocumentInfoResponse} object
     * @throws HandlerException throws a custom handler exception
     */
    public DocumentInfoResponse getCompanyReportData(String resourceUri, String requestId)
        throws HandlerException {

        String companyNumber = getCompanyNumberFromUri(resourceUri);

        try {
            LOG.infoContext(requestId, "Getting data for report for company number: " + companyNumber, getDebugMap(companyNumber));
            return createDocumentInfoResponse(companyNumber);
        } catch (URIValidationException | ApiErrorResponseException | MapperException e) {
            LOG.errorContext(requestId,"Failed to get data for report for company number " + companyNumber, e, getDebugMap(companyNumber));
            throw new HandlerException(e.getMessage(), e.getCause());
        }
    }

    private DocumentInfoResponse createDocumentInfoResponse(String companyNumber) throws HandlerException,
        URIValidationException, ApiErrorResponseException, MapperException {

        DocumentInfoResponse documentInfoResponse = new DocumentInfoResponse();

        documentInfoResponse.setData(getCompanyReportData(companyNumber));
        documentInfoResponse.setAssetId("accounts");
        documentInfoResponse.setPath(createPathString());
        documentInfoResponse.setTemplateName("company-report.html");

        return documentInfoResponse;
    }

    private String getCompanyReportData(String companyNumber) throws HandlerException,
        URIValidationException, ApiErrorResponseException, MapperException {

        CompanyReportApiData companyReportApiData = new CompanyReportApiData();

        CompanyProfileApi companyProfileApi = getCompanyProfile(companyNumber);
        companyReportApiData.setCompanyProfileApi(companyProfileApi);

        ApiClient apiClient = apiClientService.getApiClient();

        String errorString = "company report";

        Iterator it = companyProfileApi.getLinks().entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            try {
                if(pair.getKey() == "filing_history") {
                    errorString = "filing history";
                    FilingHistoryApi filingHistoryApi = apiClient.filingHistory()
                        .list(GET_COMPANY_URI.expand(companyNumber).toString() + "/filing-history").execute().getData();
                    companyReportApiData.setFilingHistoryApi(filingHistoryApi);
                }

                else if (pair.getKey() == "insolvency") {
                    errorString ="insolvency";
                    InsolvencyApi insolvencyApi = apiClient.insolvency()
                        .get(GET_COMPANY_URI.expand(companyNumber).toString() + "/insolvency").execute().getData();
                    companyReportApiData.setInsolvencyApi(insolvencyApi);
                }

                else if(pair.getKey() == "officers") {
                    errorString = "officers";
                    OfficersApi officersApi = apiClient.officers()
                        .list(GET_COMPANY_URI.expand(companyNumber).toString() + "/officers").execute().getData();
                    companyReportApiData.setOfficersApi(officersApi);
                }

                else if(pair.getKey() == "charges") {
                    errorString = "charges";
                    ChargesApi chargesApi = apiClient.charges()
                        .get(GET_COMPANY_URI.expand(companyNumber).toString() + "/charges").execute().getData();
                    companyReportApiData.setChargesApi(chargesApi);
                }

                else if(pair.getKey() == "persons-with-significant-control") {
                    errorString = "persons-with-significant-control";
                    PscsApi pscsApi = apiClient.pscs().list(GET_COMPANY_URI.expand(companyNumber).toString() +
                            "/persons-with-significant-control").execute().getData();
                    companyReportApiData.setPscsApi(pscsApi);
                }
            } catch (ApiErrorResponseException e) {
                handleException(e, errorString, companyNumber);
            }
        }

        return toJson(companyReportMapper.mapCompanyReport(companyReportApiData));
    }

    private CompanyProfileApi getCompanyProfile(String companyNumber) throws HandlerException {

        try {
            return companyService.getCompanyProfile(companyNumber);
        } catch (ServiceException se) {
            throw new HandlerException("error occurred obtaining the company profile", se);
        }
    }

    private String toJson(CompanyReport companyReport) throws HandlerException {

        String reportToJson;
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("dd-MMMM-yyyy")));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("dd-MMMM-yyyy")));
        mapper.registerModule(javaTimeModule);
        mapper.setDateFormat(new SimpleDateFormat("dd-MMMM-yyyy"));

        try {
            reportToJson = mapper.writeValueAsString(companyReport);
        } catch (JsonProcessingException e) {
            throw new HandlerException(
                "Could not serialise Document Info for the company report for: "
                    + companyReport.getRegistrationInformation().getCompanyNumber() + ": "
                    + companyReport.getRegistrationInformation().getCompanyName());
        }

        return reportToJson;
    }

    private String getCompanyNumberFromUri(String resourceUri) {
        return  resourceUri.replaceAll("[^\\d.]", "");
    }

    private String createPathString() {
        return String.format("/%s/%s", "accounts", getUniqueFileName());
    }

    public String getUniqueFileName() {
        UUID uuid = UUID.randomUUID();
        return "companyReport" + uuid.toString() + ".html";
    }

    private void handleException(ApiErrorResponseException e, String text, String companyNumber)
        throws ApiErrorResponseException {

        if (e.getStatusCode() == HttpStatus.NOT_FOUND.value()) {
            LOG.info(String.format(NOT_FOUND_API_DATA, text, companyNumber), getDebugMap(companyNumber));
        } else {
            throw e;
        }
    }

    private Map<String, Object> getDebugMap(String companyNumber) {
        Map<String, Object> logMap = new HashMap<>();
        logMap.put("COMPANY_NUMBER", companyNumber);

        return logMap;
    }
}
