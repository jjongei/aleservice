package net.siriussoft.aleservice.crawler.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QApartmentTransaction is a Querydsl query type for ApartmentTransaction
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QApartmentTransaction extends EntityPathBase<ApartmentTransaction> {

    private static final long serialVersionUID = 168148534L;

    public static final QApartmentTransaction apartmentTransaction = new QApartmentTransaction("apartmentTransaction");

    public final StringPath aptArea = createString("aptArea");

    public final StringPath aptBuiltYear = createString("aptBuiltYear");

    public final StringPath aptKey = createString("aptKey");

    public final StringPath aptName = createString("aptName");

    public final StringPath aptTrKey = createString("aptTrKey");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final StringPath dongCd = createString("dongCd");

    public final StringPath dongName = createString("dongName");

    public final StringPath floor = createString("floor");

    public final StringPath jibun = createString("jibun");

    public final StringPath lawdCd = createString("lawdCd");

    public final StringPath lawdName = createString("lawdName");

    public final DateTimePath<java.util.Date> modifiedDate = createDateTime("modifiedDate", java.util.Date.class);

    public final StringPath transactionAmount = createString("transactionAmount");

    public final StringPath transactionDay = createString("transactionDay");

    public final StringPath transactionMonth = createString("transactionMonth");

    public final StringPath transactionYear = createString("transactionYear");

    public QApartmentTransaction(String variable) {
        super(ApartmentTransaction.class, forVariable(variable));
    }

    public QApartmentTransaction(Path<? extends ApartmentTransaction> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApartmentTransaction(PathMetadata metadata) {
        super(ApartmentTransaction.class, metadata);
    }

}

