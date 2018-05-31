package net.siriussoft.aleservice.crawler.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Access(AccessType.FIELD)
@Table(name = "tap_apartment_transaction")
public class ApartmentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apt_tr_key", columnDefinition = "bigint", nullable=false)
    private Long aptTrKey;

    @Column(name="apt_key", columnDefinition="nvarchar(256)", nullable = false)
    private String aptKey;

    @Column(name="apt_area", columnDefinition="char(10)")
    private String aptArea;

    @Column(name="transaction_year", columnDefinition="char(10)")
    private String transactionYear;

    @Column(name="transaction_month", columnDefinition="char(10)")
    private String transactionMonth;

    @Column(name="transaction_day", columnDefinition="char(10)")
    private String transactionDay;

    @Column(name="transaction_amount", columnDefinition="char(20)")
    private String transactionAmount;

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

    @Column(name="floor", columnDefinition = "char(4)", nullable = false)
    private String floor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date", columnDefinition="datetime", nullable = false )
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_date", columnDefinition="datetime", nullable = false )
    private Date modifiedDate;


}
