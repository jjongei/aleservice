package net.siriussoft.aleservice.crawler.repository;

import net.siriussoft.aleservice.crawler.model.LawdCode;

import java.util.List;

public interface LawdCodeRepository {

    public LawdCode findByDongCd (String dongCd);

    public void save(LawdCode lawdCode);

    public void remove(LawdCode lawdCode);

    public List<LawdCode> getValidLawdCodeList();
}
