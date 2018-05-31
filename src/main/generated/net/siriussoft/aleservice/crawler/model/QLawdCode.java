package net.siriussoft.aleservice.crawler.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLawdCode is a Querydsl query type for LawdCode
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLawdCode extends EntityPathBase<LawdCode> {

    private static final long serialVersionUID = 809836793L;

    public static final QLawdCode lawdCode = new QLawdCode("lawdCode");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final StringPath dongCd = createString("dongCd");

    public final StringPath dongName = createString("dongName");

    public final StringPath isActive = createString("isActive");

    public final StringPath isCityCd = createString("isCityCd");

    public final StringPath isLawdCd = createString("isLawdCd");

    public final StringPath lawdCd = createString("lawdCd");

    public final DateTimePath<java.util.Date> modifiedDate = createDateTime("modifiedDate", java.util.Date.class);

    public QLawdCode(String variable) {
        super(LawdCode.class, forVariable(variable));
    }

    public QLawdCode(Path<? extends LawdCode> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLawdCode(PathMetadata metadata) {
        super(LawdCode.class, metadata);
    }

}

