package myapp.SzakdolgozatBE.series;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import myapp.SzakdolgozatBE.ageClassification.AgeClassification;
import myapp.SzakdolgozatBE.genre.Genre;
import myapp.SzakdolgozatBE.season.Season;

@Entity
@Table(name = "Series")
@NamedQueries({
    @NamedQuery(name = "getAgeClassificationSeries", query = "SELECT s FROM Series s WHERE s.ageClassification = :ageClassification"),
    @NamedQuery(name = "getGenreSeries", query = "SELECT s FROM Series s WHERE s.genre = :genre"),
    @NamedQuery(name = "getResultSeries", query = "SELECT s FROM Series s WHERE s.title LIKE CONCAT('%', :title, '%') ORDER BY s.title ASC"),
    @NamedQuery(name = "getNumberOfResultSeries", query = "SELECT COUNT(s.id) FROM Series s WHERE s.title LIKE CONCAT('%', :title, '%')")
})
public class Series implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "gen")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    private Long id;
    
    private String title;
    
    private double rating;
    
    private int releaseYear;
    
    @Column(length = 2000)
    private String imageUrl;
        
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ageClassification_id")
    private AgeClassification ageClassification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;
    
    @OneToMany(mappedBy = "series")
    private List<Season> seasons;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public AgeClassification getAgeClassification() {
        return ageClassification;
    }

    public void setAgeClassification(AgeClassification ageClassification) {
        this.ageClassification = ageClassification;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Series)) {
            return false;
        }
        Series other = (Series) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "myapp.SzakdolgozatBE.series.Series[ id=" + id + " ]";
    }
    
}
