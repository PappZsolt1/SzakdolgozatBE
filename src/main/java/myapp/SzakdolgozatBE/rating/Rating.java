package myapp.SzakdolgozatBE.rating;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import myapp.SzakdolgozatBE.episode.Episode;
import myapp.SzakdolgozatBE.movie.Movie;
import myapp.SzakdolgozatBE.myUser.MyUser;

@Entity
@Table(name = "Rating")
@NamedQueries({
    @NamedQuery(name = "getMovieRating", query = "SELECT r FROM Rating r WHERE r.myUser = :user AND r.movie = :movie"),
    @NamedQuery(name = "getEpisodeRating", query = "SELECT r FROM Rating r WHERE r.myUser = :user AND r.episode = :episode")
})
public class Rating implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private byte rating;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myUser_id")
    @JsonBackReference(value = "myUser-rating")
    private MyUser myUser;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    @JsonBackReference(value = "movie-rating")
    private Movie movie;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "episode_id")
    @JsonBackReference(value = "episode-rating")
    private Episode episode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
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
        if (!(object instanceof Rating)) {
            return false;
        }
        Rating other = (Rating) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "myapp.SzakdolgozatBE.rating.Rating[ id=" + id + " ]";
    }
    
}
