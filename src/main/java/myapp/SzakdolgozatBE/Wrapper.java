package myapp.SzakdolgozatBE;

import java.util.List;

public class Wrapper {
    
    private List results;
    
    private long total;

    public Wrapper(List results, long total) {
        this.results = results;
        this.total = total;
    }

    public List getResults() {
        return results;
    }

    public void setResults(List results) {
        this.results = results;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
