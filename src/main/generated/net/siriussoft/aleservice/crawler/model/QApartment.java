package net.siriussoft.aleservice.crawler.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QApartment is a Querydsl query type for Apartment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QApartment extends EntityPathBase<Apartment> {

    private static final long serialVersionUID = 510408584L;

    public static final QApartment apartment = new QApartment("apartment");

    public final StringPath aptBuiltYear = createString("aptBuiltYear");

    public final StringPath aptKey = createString("aptKey");

    public final StringPath aptName = createString("aptName");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final StringPath dongCd = createString("dongCd");

    public final StringPath dongName = createString("dongName");

    public final StringPath jibun = createString("jibun");

    public final StringPath lawdCd = createString("lawdCd");

    public final StringPath lawdName = createString("lawdName");

    public final DateTimePath<java.util.Date> modifiedDate = createDateTime("modifiedDate", java.util.Date.class);

    public QApartment(String variable) {
        super(Apartment.class, forVariable(variable));
    }

    public QApartment(Path<? extends Apartment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApartment(PathMetadata metadata) {
        super(Apartment.class, metadata);
    }

}

