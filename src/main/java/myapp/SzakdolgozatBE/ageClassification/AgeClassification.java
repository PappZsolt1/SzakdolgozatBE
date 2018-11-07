package myapp.SzakdolgozatBE.ageClassification;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import myapp.SzakdolgozatBE.movie.Movie;
import myapp.SzakdolgozatBE.series.Series;

//enum
@Entity
@Table(name = "AgeClassification")
@NamedQueries({
    @NamedQuery(name = "getAllAgeClassifications", query = "SELECT a FROM AgeClassification a")
})
public class AgeClassification implements Serializable {

    @Id
    @SequenceGenerator(name = "gen", initialValue = 1000, allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "ageClassification")
    @JsonBackReference(value = "movie-ageClassification")
    private List<Movie> movies;

    @OneToMany(mappedBy = "ageClassification")
    @JsonBackReference(value = "series-ageClassification")
    private List<Series> series;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AgeClassification other = (AgeClassification) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
