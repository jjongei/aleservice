package net.siriussoft.aleservice.crawler.service;

import net.siriussoft.aleservice.crawler.model.ApartmentTransactionHistory;
import net.siriussoft.aleservice.crawler.repository.ApartmentTransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ApartmentTransactionHistoryService {

    @Autowired
    ApartmentTransactionHistoryRepository apartmentTransactionHistoryRepository;

    public ApartmentTransactionHistory findPreviousTransaction(ApartmentTransactionHistory apartmentTransactionHistory) {
        return apartmentTransactionHistoryRepository.findPreviousTransaction(apartmentTransactionHistory);
    }

    public void save(ApartmentTransactionHistory apartmentTransactionHistory){
        apartmentTransactionHistoryRepository.save(apartmentTransactionHistory);
    }

    public void update(ApartmentTransactionHistory apartmentTransactionHistory){
        apartmentTransactionHistoryRepository.update(apartmentTransactionHistory);
    }

    public void remove(ApartmentTransactionHistory apartmentTransactionHistory) {
        apartmentTransactionHistoryRepository.remove(apartmentTransactionHistory);
    }

    public void persistDataWithCommit(ApartmentTransactionHistory apartmentTransactionHistory){
        apartmentTransactionHistoryRepository.save(apartmentTransactionHistory);
        apartmentTransactionHistoryRepository.flush();
    }

    public void flush() {
        apartmentTransactionHistoryRepository.flush();
    }
}