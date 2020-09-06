package orh.gnsg.gms.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.log.SysoCounter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orh.gnsg.gms.domain.Revenue;
import orh.gnsg.gms.domain.RevenueReport;
import orh.gnsg.gms.domain.enumeration.REVTYPE;
import orh.gnsg.gms.repository.RevenueReportRepository;
import orh.gnsg.gms.repository.RevenueRepository;
import orh.gnsg.gms.repository.search.RevenueReportSearchRepository;
import orh.gnsg.gms.service.RevenueReportService;

/**
 * Service Implementation for managing {@link RevenueReport}.
 */
@Service
@Transactional
public class RevenueReportServiceImpl implements RevenueReportService {
    private final Logger log = LoggerFactory.getLogger(RevenueReportServiceImpl.class);

    private final RevenueReportRepository revenueReportRepository;

    @Autowired
    RevenueRepository revenueRepository;

    private final RevenueReportSearchRepository revenueReportSearchRepository;

    public RevenueReportServiceImpl(
        RevenueReportRepository revenueReportRepository,
        RevenueReportSearchRepository revenueReportSearchRepository
    ) {
        this.revenueReportRepository = revenueReportRepository;
        this.revenueReportSearchRepository = revenueReportSearchRepository;
    }

    /**
     * Save a revenueReport.
     *
     * @param revenueReport the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RevenueReport save(RevenueReport revenueReport) {
        log.debug("Request to save RevenueReport : {}", revenueReport);
        List<Revenue> objectList = null;
        if (revenueReport.getRevType() != null && revenueReport.getRevType() == (REVTYPE.ALL)) {
            objectList = revenueRepository.findByDateBetween(revenueReport.getStartDate(), revenueReport.getEndDate());
        } else {
            objectList =
                revenueRepository.findByRevTypeAndDateBetween(
                    revenueReport.getRevType(),
                    revenueReport.getStartDate(),
                    revenueReport.getEndDate()
                );
        }

        System.out.println("Arraylist data from database >>>>>>>>>>>>>>>>>>>>>>>>>  : \n" + objectList);

        String json = CsvHelper.ListJson(objectList);

        String json1 = CsvHelper.ListAsJson(objectList);

        System.out.println("Json from  old api  ???????????????????????????: " + json);
        System.out.println("Json from  new  api  ???????????????????????????: " + json1);
        try {
            revenueReport.setReport(
                CsvToPdfConverter.csvToPdfConverter(
                    json.getBytes(),
                    new ReportObj("Revenue ", revenueReport.getStartDate(), revenueReport.getEndDate(), 6576575.9)
                )
            );
            revenueReport.setReportContentType("application/pdf");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RevenueReport result = revenueReportRepository.save(revenueReport);
        revenueReportSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the revenueReports.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<RevenueReport> findAll() {
        log.debug("Request to get all RevenueReports");
        return revenueReportRepository.findAll();
    }

    /**
     * Get one revenueReport by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RevenueReport> findOne(Long id) {
        log.debug("Request to get RevenueReport : {}", id);
        return revenueReportRepository.findById(id);
    }

    /**
     * Delete the revenueReport by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RevenueReport : {}", id);

        revenueReportRepository.deleteById(id);
        revenueReportSearchRepository.deleteById(id);
    }

    /**
     * Search for the revenueReport corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<RevenueReport> search(String query) {
        log.debug("Request to search RevenueReports for query {}", query);
        return StreamSupport
            .stream(revenueReportSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}
