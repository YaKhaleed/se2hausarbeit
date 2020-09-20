package org.se2.ai.model.dao;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author zmorin2s
 */

public class SucheTitel {
    private static final List<String> liste = SucheDAO.getInstance().getMarkeInTitel();

    public List<String> getTitelAnzeige() {
        return liste;
    }

    public int count() {
        return liste.size();
    }

    public int count(String filter) {
        return (int) getTitelAnzeige().stream()
                .filter(job -> filter == null || job
                        .toLowerCase().startsWith(filter.toLowerCase())
                        || job.toLowerCase().contains(filter.toLowerCase())
                )
                .count();
    }

    public Stream<String> fetch(String filter, int offset, int limit) {
        return getTitelAnzeige().stream()
                .filter(job -> filter == null || job
                        .toLowerCase().startsWith(filter.toLowerCase()) || job
                        .toLowerCase().contains(filter.toLowerCase())
                )
                .skip(offset).limit(limit);
    }


}


