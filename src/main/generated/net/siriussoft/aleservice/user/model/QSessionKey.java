package net.siriussoft.aleservice.user.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSessionKey is a Querydsl query type for SessionKey
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QSessionKey extends BeanPath<SessionKey> {

    private static final long serialVersionUID = 594157766L;

    public static final QSessionKey sessionKey = new QSessionKey("sessionKey");

    public final StringPath value = createString("value");

    public QSessionKey(String variable) {
        super(SessionKey.class, forVariable(variable));
    }

    public QSessionKey(Path<? extends SessionKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSessionKey(PathMetadata metadata) {
        super(SessionKey.class, metadata);
    }

}

