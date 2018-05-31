package net.siriussoft.aleservice.crawler.model;

import lombok.Getter;
import lombok.Setter;
import net.siriussoft.aleservice.common.util.DateUtil;
import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tap_apartment_transaction_history")
public class ApartmentTransactionHistory {

    public ApartmentTransactionHistory() {

    }

    public ApartmentTransactionHistory(String lawdCd, String dealYmd) {
        this.lawdCd = lawdCd;
        this.dealYmd = dealYmd;
        this.isComplete = "F";
        this.startDate = DateUtil.getCurrentDate();
        this.createdDate = DateUtil.getCurrentDate();
        this.modifiedDate = DateUtil.getCurrentDate();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="apt_tr_history_key", columnDefinition = "int")
    Long aptTrHistoryKey;

    @Column(name="lawd_cd", columnDefinition = "char(10)")
    private String lawdCd;

    @Column(name="deal_ymd", columnDefinition = "char(10)")
    private String dealYmd;

    @Column(name="total_count", columnDefinition = "int")
    private int totalCount;

    @Column(name="insert_count", columnDefinition = "int")
    private int insertCount;

    @Column(name = "is_complete", columnDefinition = "char(1)")
    private String isComplete;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="start_date", columnDefinition = "datetime")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="end_Date", columnDefinition = "datetime")
    private Date endDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date", columnDefinition="datetime")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_date", columnDefinition="datetime")
    private Date modifiedDate;



}
