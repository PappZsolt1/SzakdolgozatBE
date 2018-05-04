package myapp.SzakdolgozatBE.movie;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import myapp.SzakdolgozatBE.enums.AgeClassification;
import myapp.SzakdolgozatBE.enums.Genre;

@Entity
@Table(name = "Movie")
@NamedQueries({
    @NamedQuery(name = "getAllMovies", query = "SELECT m FROM Movie m")
})
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private double Rating;
    private int budget;
    private int mLength;
    private int releaseYear;
    
    @Lob
    private byte[] coverPicture;
    
    @Enumerated(EnumType.STRING)
    private AgeClassification ageClassification;

    @Enumerated(EnumType.STRING)
    private Genre genre;
    
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
        return Rating;
    }

    public void setRating(double Rating) {
        this.Rating = Rating;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getLength() {
        return mLength;
    }

    public void setLength(int length) {
        this.mLength = length;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public byte[] getCoverPicture() {
        return coverPicture;
    }

    public void setCoverPicture(byte[] coverPicture) {
        this.coverPicture = coverPicture;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "myapp.SzakdolgozatBE.Movie.Movie[ id=" + id + " ]";
    }
    
}