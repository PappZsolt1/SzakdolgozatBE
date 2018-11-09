package myapp.SzakdolgozatBE.movie;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import myapp.SzakdolgozatBE.actor.Actor;
import myapp.SzakdolgozatBE.ageClassification.AgeClassification;
import myapp.SzakdolgozatBE.comment.Comment;
import myapp.SzakdolgozatBE.genre.Genre;
import myapp.SzakdolgozatBE.rating.Rating;

@Entity
@Table(name = "Movie")
@NamedQueries({
    @NamedQuery(name = "getAllMovies", query = "SELECT m FROM Movie m"),
    @NamedQuery(name = "getAgeClassificationMovies", query = "SELECT m FROM Movie m WHERE m.ageClassification = :ageClassification"),
    @NamedQuery(name = "getGenreMovies", query = "SELECT m FROM Movie m WHERE m.genre = :genre")
})
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "gen")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    private Long id;
    
    private String title;
    
    private int numberOfRatings;
    
    private int sumOfRatings;
    
    private double rating;
    
    private int budget;
    
    private String mLength;
    
    private int releaseYear;

    @Lob
    private byte[] coverPicture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ageClassification_id")
    //@JsonManagedReference(value = "movie-ageClassification")
    private AgeClassification ageClassification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    //@JsonManagedReference(value = "movie-genre")
    private Genre genre;

    @ManyToMany
    @JoinTable(name = "Movie_Actor",
            joinColumns = @JoinColumn(name = "Movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "Actor_id", referencedColumnName = "id"))
    //@JsonManagedReference(value = "movie-actor")
    private List<Actor> actors;
    
    @OneToMany(mappedBy = "movie")
    @JsonBackReference(value = "movie-comment")
    private List<Comment> comments;
    
    @OneToMany(mappedBy = "movie")
    //@JsonManagedReference(value = "movie-rating")
    private List<Rating> ratings;

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

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public int getSumOfRatings() {
        return sumOfRatings;
    }

    public void setSumOfRatings(int sumOfRatings) {
        this.sumOfRatings = sumOfRatings;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getmLength() {
        return mLength;
    }

    public void setmLength(String mLength) {
        this.mLength = mLength;
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

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
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
