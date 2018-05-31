package net.siriussoft.aleservice.crawler.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QApartmentTransactionHistory is a Querydsl query type for ApartmentTransactionHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QApartmentTransactionHistory extends EntityPathBase<ApartmentTransactionHistory> {

    private static final long serialVersionUID = 966817150L;

    public static final QApartmentTransactionHistory apartmentTransactionHistory = new QApartmentTransactionHistory("apartmentTransactionHistory");

    public final NumberPath<Long> aptTrHistoryKey = createNumber("aptTrHistoryKey", Long.class);

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final StringPath dealYmd = createString("dealYmd");

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final NumberPath<Integer> insertCount = createNumber("insertCount", Integer.class);

    public final StringPath isComplete = createString("isComplete");

    public final StringPath lawdCd = createString("lawdCd");

    public final DateTimePath<java.util.Date> modifiedDate = createDateTime("modifiedDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final NumberPath<Integer> totalCount = createNumber("totalCount", Integer.class);

    public QApartmentTransactionHistory(String variable) {
        super(ApartmentTransactionHistory.class, forVariable(variable));
    }

    public QApartmentTransactionHistory(Path<? extends ApartmentTransactionHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApartmentTransactionHistory(PathMetadata metadata) {
        super(ApartmentTransactionHistory.class, metadata);
    }

}

