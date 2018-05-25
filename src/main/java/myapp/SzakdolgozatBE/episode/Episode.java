package myapp.SzakdolgozatBE.episode;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import myapp.SzakdolgozatBE.actor.Actor;
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
    
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    
    private int eLength;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;

    @ManyToMany
    @JoinTable(name = "Episode_Actor",
            joinColumns = @JoinColumn(name = "Episode_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "Actor_id", referencedColumnName = "id"))
    private List<Actor> actors;

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
        numberOfRatings++;
        sumOfRatings += rating;
        this.rating = sumOfRatings / numberOfRatings;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
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
