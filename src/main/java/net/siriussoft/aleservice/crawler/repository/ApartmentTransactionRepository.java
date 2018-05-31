package net.siriussoft.aleservice.crawler.repository;

import net.siriussoft.aleservice.crawler.model.ApartmentTransaction;

public interface ApartmentTransactionRepository {

    public ApartmentTransaction findByAptTrKey(String aptTrKey);

    public void save(ApartmentTransaction apartmentTransaction);

    public void remove(ApartmentTransaction apartmentTransaction);

    public void removeForUpdateTransaction(String lawdCd, String transactionYear, String transactionMonth);

    public void flush();
}
