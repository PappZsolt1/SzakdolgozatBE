package myapp.SzakdolgozatBE.episode;

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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import myapp.SzakdolgozatBE.actor.Actor;
import myapp.SzakdolgozatBE.comment.Comment;
import myapp.SzakdolgozatBE.rating.Rating;
import myapp.SzakdolgozatBE.season.Season;

@Entity
@Table(name = "Episode")
@NamedQueries({
    @NamedQuery(name = "getSeasonEpisodes", query = "SELECT e FROM Episode e WHERE e.season = :season")
})
public class Episode implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String title;
    
    private int numberOfRatings;
    
    private int sumOfRatings;
    
    private double rating;
    
    private String releaseDate;
    
    private int eLength;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    @JsonBackReference(value = "season-episode")
    private Season season;

    @ManyToMany
    @JoinTable(name = "Episode_Actor",
            joinColumns = @JoinColumn(name = "Episode_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "Actor_id", referencedColumnName = "id"))
    //@JsonManagedReference(value = "episode-actor")
    private List<Actor> actors;
    
    @OneToMany(mappedBy = "episode")
    @JsonBackReference(value = "episode-comment")
    private List<Comment> comments;
    
    @OneToMany(mappedBy = "episode")
    //@JsonManagedReference(value = "episode-rating")
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getLength() {
        return eLength;
    }

    public void setLength(int length) {
        this.eLength = length;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
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
        if (!(object instanceof Episode)) {
            return false;
        }
        Episode other = (Episode) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "myapp.SzakdolgozatBE.episode.Episode[ id=" + id + " ]";
    }

}
