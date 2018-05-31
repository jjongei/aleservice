package net.siriussoft.aleservice.crawler.repository;

import com.querydsl.jpa.JPQLQuery;
import net.siriussoft.aleservice.crawler.model.Apartment;
import net.siriussoft.aleservice.crawler.model.QApartment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class ApartmentRepositoryImpl extends QuerydslRepositorySupport implements ApartmentRepository {

    public ApartmentRepositoryImpl () {
        super(Apartment.class);
    }

    @Override
    public Apartment findByAptKey(String aptKey) {
        Apartment result = null;
        QApartment qApartment = QApartment.apartment;

        if (StringUtils.isNotEmpty(aptKey)) {
            JPQLQuery query = this.from(qApartment);
            query.where(qApartment.aptKey.eq(aptKey));
            result = (Apartment)query.fetchOne();
        }
        return result;
    }

    @Override
    public void save(Apartment apartment) {
        this.getEntityManager().persist(apartment);
    }

    @Override
    public void remove(Apartment apartment) {
        this.getEntityManager().remove(apartment);
    }
}
