package net.siriussoft.aleservice.crawler.repository;

import com.querydsl.jpa.JPQLQuery;
import net.siriussoft.aleservice.crawler.model.Apartment;
import net.siriussoft.aleservice.crawler.model.ApartmentTransactionHistory;
import net.siriussoft.aleservice.crawler.model.QApartment;
import net.siriussoft.aleservice.crawler.model.QApartmentTransactionHistory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApartmentTransactionHistoryRepositoryImpl extends QuerydslRepositorySupport implements ApartmentTransactionHistoryRepository {

    public ApartmentTransactionHistoryRepositoryImpl() {
        super(ApartmentTransactionHistory.class);
    }

    @Override
    public ApartmentTransactionHistory findPreviousTransaction(ApartmentTransactionHistory apartmentTransactionHistory){
        ApartmentTransactionHistory result = apartmentTransactionHistory;
        QApartmentTransactionHistory qApartmentTransactionHistory = QApartmentTransactionHistory.apartmentTransactionHistory;
        String lawdCd = apartmentTransactionHistory.getLawdCd();
        String dealYmd = apartmentTransactionHistory.getDealYmd();

        if (StringUtils.isNotEmpty(lawdCd) && StringUtils.isNotEmpty(dealYmd)) {
            JPQLQuery query = this.from(qApartmentTransactionHistory);
            query.where(qApartmentTransactionHistory.lawdCd.eq(lawdCd).and(qApartmentTransactionHistory.dealYmd.eq(dealYmd)));
            result = (ApartmentTransactionHistory)query.fetchOne();

            if(result == null) {
                result = apartmentTransactionHistory;
            }
        }
        return result;
    }

    @Override
    public void save(ApartmentTransactionHistory apartmentTransactionHistory) {
        this.getEntityManager().persist(apartmentTransactionHistory);
    }

    public void update(ApartmentTransactionHistory apartmentTransactionHistory) {
        this.getEntityManager().merge(apartmentTransactionHistory);
    }

    @Override
    public void remove(ApartmentTransactionHistory apartmentTransactionHistory) {
        this.getEntityManager().remove(apartmentTransactionHistory);
    }

    @Override
    public void flush() {
        this.getEntityManager().flush();
        this.getEntityManager().clear();
    }
}
