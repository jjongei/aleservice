package net.siriussoft.aleservice.crawler.repository;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import net.siriussoft.aleservice.crawler.model.ApartmentTransaction;
import net.siriussoft.aleservice.crawler.model.QApartmentTransaction;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.jpa.event.internal.core.JpaAutoFlushEventListener;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class ApartmentTransactionRepositoryImpl extends QuerydslRepositorySupport implements ApartmentTransactionRepository {

    public ApartmentTransactionRepositoryImpl() {
        super(ApartmentTransaction.class);
    }

    @Override
    public ApartmentTransaction findByAptTrKey(String aptTrKey) {
        ApartmentTransaction result = null;
        QApartmentTransaction qApartmentTransaction = QApartmentTransaction.apartmentTransaction;

        if(StringUtils.isNotEmpty(aptTrKey)) {
            JPQLQuery query = this.from(qApartmentTransaction);
            query.where(qApartmentTransaction.aptTrKey.eq(aptTrKey));
            result = (ApartmentTransaction) query.fetchOne();
        }
        return result;
    }

    @Override
    public void save(ApartmentTransaction apartmentTransaction) {
        this.getEntityManager().persist(apartmentTransaction);
    }

    @Override
    public void remove(ApartmentTransaction apartmentTransaction) {
        this.getEntityManager().remove(apartmentTransaction);
    }

    @Override
    public void flush() {
        this.getEntityManager().flush();
        this.getEntityManager().clear();
    }

    @Override
    public void removeForUpdateTransaction(String lawdCd, String transactionYear, String transactionMonth) {
        QApartmentTransaction qApartmentTransaction = QApartmentTransaction.apartmentTransaction;
        JPADeleteClause deleteQuery = new JPADeleteClause(this.getEntityManager(), qApartmentTransaction);

        if(StringUtils.isNotEmpty(lawdCd)
                && StringUtils.isNotEmpty(transactionYear)
                && StringUtils.isNotEmpty(transactionMonth)) {
            /*
            JPAQuery query = new JPAQuery(this.getEntityManager());
            query.from(qApartmentTransaction);
            */
            deleteQuery.where(qApartmentTransaction.lawdCd.eq(lawdCd)
                        .and(qApartmentTransaction.transactionYear.eq(transactionYear)
                                .and(qApartmentTransaction.transactionMonth.eq(transactionMonth))));
            Long affectRow = deleteQuery.execute();
            System.out.println("Delete :" + affectRow + " for update data + lawdCd : " + lawdCd + " transactionYear : " + transactionYear + " transactionMonth : " + transactionMonth);
        }
        //return affectRow;
    }
}