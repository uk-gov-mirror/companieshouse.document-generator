package uk.gov.companieshouse.document.generator.company.report;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.companieshouse.api.model.company.CompanyProfileApi;
import uk.gov.companieshouse.api.model.filinghistory.FilingHistoryApi;
import uk.gov.companieshouse.document.generator.company.report.exception.ServiceException;
import uk.gov.companieshouse.document.generator.company.report.model.CompanyReport;
import uk.gov.companieshouse.document.generator.company.report.service.CompanyService;
import uk.gov.companieshouse.document.generator.company.report.service.FilingHistoryService;
import uk.gov.companieshouse.document.generator.interfaces.DocumentInfoService;
import uk.gov.companieshouse.document.generator.interfaces.exception.DocumentInfoException;
import uk.gov.companieshouse.document.generator.interfaces.model.DocumentInfoRequest;
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

@Service
public class CompanyReportDocumentInfoServiceImpl implements DocumentInfoService {

    public static final String MODULE_NAME_SPACE = "document-generator-company-report";

    private static final Logger LOG = LoggerFactory.getLogger(MODULE_NAME_SPACE);

    @Autowired
    private CompanyService companyService;

    @Autowired
    private FilingHistoryService filingHistoryService;

    @Override
    public DocumentInfoResponse getDocumentInfo(DocumentInfoRequest documentInfoRequest) throws DocumentInfoException {

        String resourceUri = documentInfoRequest.getResourceUri();
        String requestId = documentInfoRequest.getRequestId();

        final Map< String, Object > debugMap = new HashMap< >();
        debugMap.put("resource_uri", resourceUri);

        LOG.infoContext(requestId,"Started getting document info for company profile", debugMap);

        CompanyProfileApi companyProfileApi;
        FilingHistoryApi filingHistoryApi = null;

        String companyNumber = getCompanyNumberFromUri(resourceUri);

        try {
           companyProfileApi = companyService.getCompanyProfile(companyNumber);
        } catch (ServiceException se) {
            throw new DocumentInfoException("error occurred obtaining the company profile", se);
        }

        Iterator it = companyProfileApi.getLinks().entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            if(pair.getKey() == "filing_history") {

                try {
                    filingHistoryApi = filingHistoryService.getFilingHistory(companyNumber);
                }  catch (ServiceException se) {
                    throw new DocumentInfoException("error occurred obtaining the filing history", se);
                }
            } else if(pair.getKey() == "officers") {
                LOG.info("officers was present. But, there is no implementation to obtain the officers in the SDK yet");
            }
        }

        return createDocumentInfoResponse(companyProfileApi, filingHistoryApi);
    }

    private DocumentInfoResponse createDocumentInfoResponse(CompanyProfileApi companyProfileApi, FilingHistoryApi filingHistoryApi) throws DocumentInfoException {

        DocumentInfoResponse documentInfoResponse = new DocumentInfoResponse();

        documentInfoResponse.setData(createData(companyProfileApi, filingHistoryApi));
        documentInfoResponse.setAssetId("accounts");
        documentInfoResponse.setPath(createPathString());
        documentInfoResponse.setTemplateName("company-report.html");

        return documentInfoResponse;
    }

    private String createData(CompanyProfileApi companyProfileApi,
                              FilingHistoryApi filingHistoryApi) throws DocumentInfoException {

        CompanyReport companyReport = new CompanyReport();
        companyReport.setCompanyProfileApi(companyProfileApi);
        companyReport.setFilingHistoryApi(filingHistoryApi);

        return toJson(companyReport);
    }

    private String toJson(CompanyReport companyReport) throws DocumentInfoException {

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
            throw new DocumentInfoException(
                "Could not serialise Document Info for the company report for resource: "
                    + companyReport.getCompanyProfileApi().getCompanyNumber());
        }

        return reportToJson;
    }

    private String createPathString() {
        return String.format("/%s/%s", "accounts", getUniqueFileName());
    }

    public String getUniqueFileName() {
        UUID uuid = UUID.randomUUID();
        return "companyReport" + uuid.toString() + ".html";
    }

    private String getCompanyNumberFromUri(String resourceUri) {
        return  resourceUri.replaceAll("[^\\d.]", "");
    }
}
