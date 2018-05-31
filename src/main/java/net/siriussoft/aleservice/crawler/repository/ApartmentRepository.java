package net.siriussoft.aleservice.crawler.repository;

import net.siriussoft.aleservice.crawler.model.Apartment;

public interface ApartmentRepository {
    public Apartment findByAptKey(String aptKey);

    public void save(Apartment apartment);

    public void remove(Apartment apartment);
}
