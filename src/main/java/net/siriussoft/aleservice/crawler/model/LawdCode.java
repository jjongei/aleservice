package net.siriussoft.aleservice.crawler.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Access(AccessType.FIELD)
@Entity
@Table(name="tcd_lawd")
public class LawdCode {

    @Id
    @Column(name="dong_cd", columnDefinition = "char(20)", nullable = false)
    private String dongCd;

    @Column(name="dong_name", columnDefinition = "char(256)", nullable = false)
    private String dongName;

    @Column(name="lawd_cd", columnDefinition = "char(10)", nullable = false)
    private String lawdCd;

    @Column(name="is_city_cd", columnDefinition = "char(1)", nullable = false)
    private String isCityCd;

    @Column(name="is_lawd_cd", columnDefinition = "char(1)", nullable = false)
    private String isLawdCd;

    @Column(name="is_active", columnDefinition = "char(1)", nullable = false)
    private String isActive;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date", columnDefinition="datetime", nullable = false )
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_date", columnDefinition="datetime", nullable = false )
    private Date modifiedDate;
}
