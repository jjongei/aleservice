package net.siriussoft.aleservice.crawler.service;

import net.siriussoft.aleservice.crawler.model.ApartmentTransaction;
import net.siriussoft.aleservice.crawler.repository.ApartmentTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class ApartmentTransactionService {

    @Autowired
    ApartmentTransactionRepository apartmentTransactionRepository;

    public ApartmentTransaction findByAptTrKey (String aptTrKey) {
        return apartmentTransactionRepository.findByAptTrKey(aptTrKey);
    }
/*

    public void checkDuplicateAndSave(ApartmentTransaction apartmentTransaction) {
        apartmentTransactionRepository.save(apartmentTransaction);
    }
*/

    public void save(ApartmentTransaction apartmentTransaction) {
        apartmentTransactionRepository.save(apartmentTransaction);
    }

    public void remove(ApartmentTransaction apartmentTransaction) {
        apartmentTransactionRepository.remove(apartmentTransaction);
    }

    public void removeForUpdateTransaction(String lawdCd, String transactionYear, String transactionMonth) {
        apartmentTransactionRepository.removeForUpdateTransaction(lawdCd, transactionYear, transactionMonth);
    }

    public void flush() {
        apartmentTransactionRepository.flush();
    }
}
