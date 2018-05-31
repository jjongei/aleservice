package net.siriussoft.aleservice.crawler.repository;

import net.siriussoft.aleservice.crawler.model.Apartment;
import net.siriussoft.aleservice.crawler.model.ApartmentTransactionHistory;

import java.util.List;

public interface ApartmentTransactionHistoryRepository {

    public ApartmentTransactionHistory findPreviousTransaction(ApartmentTransactionHistory apartmentTransactionHistory);

    public void save(ApartmentTransactionHistory apartmentTransactionHistory);

    public void update(ApartmentTransactionHistory apartmentTransactionHistory);

    public void remove(ApartmentTransactionHistory apartmentTransactionHistory);

    public void flush();
}
