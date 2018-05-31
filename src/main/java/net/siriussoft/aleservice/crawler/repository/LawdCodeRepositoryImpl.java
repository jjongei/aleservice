package net.siriussoft.aleservice.crawler.repository;

import com.querydsl.jpa.JPQLQuery;
import net.siriussoft.aleservice.crawler.model.LawdCode;
import net.siriussoft.aleservice.crawler.model.QLawdCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LawdCodeRepositoryImpl extends QuerydslRepositorySupport implements LawdCodeRepository {

    public LawdCodeRepositoryImpl() {
        super(LawdCode.class);
    }

    @Override
    public LawdCode findByDongCd(String dongCd) {
        LawdCode result = null;
        QLawdCode qLawdCode = QLawdCode.lawdCode;

        if(StringUtils.isNotEmpty(dongCd)) {
            JPQLQuery query = this.from(qLawdCode);
            query.where(qLawdCode.dongCd.eq(dongCd));
            result = (LawdCode) query.fetchOne();
        }

        return result;
    }

    @Override
    public void save(LawdCode lawdCode) {
        this.getEntityManager().persist(lawdCode);
    }

    @Override
    public void remove(LawdCode lawdCode) {
        this.getEntityManager().remove(lawdCode);
    }

    @Override
    public List<LawdCode> getValidLawdCodeList() {
        List<LawdCode> result = null;
        QLawdCode qLawdCode = QLawdCode.lawdCode;

        JPQLQuery query = this.from(qLawdCode);
        query.where(qLawdCode.isCityCd.eq("F")
                .and(qLawdCode.isLawdCd.eq("T")
                        .and(qLawdCode.isActive.eq("T"))));
        result = query.fetch();
        return result;
    }

}
