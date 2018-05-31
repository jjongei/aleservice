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
@Table(name="tap_apartment")
public class Apartment {

    @Id
    @Column(name="apt_key", columnDefinition="nvarchar(256)", nullable = false)
    private String aptKey;

    @Column(name="lawd_cd", columnDefinition = "char(10)", nullable = false)
    private String lawdCd;

    @Column(name="lawd_name", columnDefinition = "nvarchar(256)", nullable = false)
    private String lawdName;

    @Column(name="dong_cd", columnDefinition = "char(20)", nullable = false)
    private String dongCd;

    @Column(name="dong_name", columnDefinition = "nvarchar(256)", nullable = false)
    private String dongName;

    @Column(name="jibun", columnDefinition = "char(10)", nullable = false)
    private String jibun;

    @Column(name="apt_name", columnDefinition = "nvarchar(256)", nullable = false)
    private String aptName;

    @Column(name="apt_built_year", columnDefinition = "char(10)", nullable = false)
    private String aptBuiltYear;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date", columnDefinition="datetime", nullable = false )
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_date", columnDefinition="datetime", nullable = false )
    private Date modifiedDate;
}
