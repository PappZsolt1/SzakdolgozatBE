package myapp.SzakdolgozatBE.movie;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.MyValidator;
import myapp.SzakdolgozatBE.Wrapper;
import myapp.SzakdolgozatBE.actor.Actor;
import myapp.SzakdolgozatBE.actor.ActorDAO;
import myapp.SzakdolgozatBE.ageClassification.AgeClassificationDAO;
import myapp.SzakdolgozatBE.comment.Comment;
import myapp.SzakdolgozatBE.comment.CommentDAO;
import myapp.SzakdolgozatBE.genre.GenreDAO;
import myapp.SzakdolgozatBE.rating.Rating;
import myapp.SzakdolgozatBE.rating.RatingDAO;

@Stateless
public class MovieService {

    @Inject
    MovieDAO dao;
    
    @Inject
    AgeClassificationDAO ageClassificationDao;
    
    @Inject
    GenreDAO genreDao;
    
    @Inject
    ActorDAO actorDao;
    
    @Inject
    RatingDAO ratingDao;
    
    @Inject
    CommentDAO commentDao;
    
    MyValidator val = new MyValidator();
    
    public Movie addMovie(Movie movie) throws MyValidationException {
        if (movie.getId() != null
                || val.validateText(movie.getTitle(), 200) == false
                || ageClassificationDao.getAgeClassification(movie.getAgeClassification().getId()) == null
                || genreDao.getGenre(movie.getGenre().getId()) == null
                || movie.getRatings() != null
                || val.validateNumber(movie.getBudget(), 0, 2000000000) == false
                || val.validateNumber(movie.getReleaseYear(), 1850, 2100) == false
                || val.validateLength(movie.getmLength()) == false
                || val.validateText(movie.getImageUrl(), 1000) == false) {
            throw new MyValidationException();
        } else {
            return dao.addMovie(movie);
        }
    }

    public Movie getMovie(long id) throws MyValidationException {
        Movie tmp = dao.getMovie(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            return tmp;
        }
    }
    
    public boolean checkIfExists(long id) {
        Movie tmp = dao.getMovie(id);
        return (tmp != null);
    }
    
    public List<Actor> getMovieActors(long id) throws MyValidationException {
        Movie tmp = dao.getMovie(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            return tmp.getActors();
        }
    }
    
    public Actor addActorToMovie(long id, long actorId) throws MyValidationException {
        Movie tmp1 = dao.getMovie(id);
        if (tmp1 == null) {
            throw new MyValidationException();
        } else {
            Actor tmp2 = actorDao.getActor(actorId);
            if (tmp2 == null || tmp1.getActors().contains(tmp2) == true) {
                throw new MyValidationException();
            } else {
                tmp1.getActors().add(tmp2);
                dao.modifyMovie(tmp1);
                tmp2.getMovies().add(tmp1);
                actorDao.modifyActor(tmp2);
                return tmp2;
            }
        }
    }
    
    public Actor removeActorFromMovie(long id, long actorId) throws MyValidationException {
        Movie tmp1 = dao.getMovie(id);
        if (tmp1 == null) {
            throw new MyValidationException();
        } else {
            Actor tmp2 = actorDao.getActor(actorId);
            if (tmp2 == null) {
                throw new MyValidationException();
            } else {
                tmp1.getActors().remove(tmp2);
                dao.modifyMovie(tmp1);
                tmp2.getMovies().remove(tmp1);
                actorDao.modifyActor(tmp2);
                return tmp2;
            }
        }
    }

    public List<Movie> getAllMovies() {
        return dao.getAllMovies();
    }
    
    public Wrapper getResultMovies(int page, int size, String title) throws MyValidationException {
        if (page < 1 || val.validateSize(size) == false || val.validateText(title, 200) == false) {
            throw new MyValidationException();
        }
        int offset = (page - 1) * size;
        List<Movie> results = dao.getResultMovies(offset, size, title);
        long total = dao.getNumberOfResultMovies(title);
        if (total > 0 && offset >= total) {
            throw new MyValidationException();
        }
        return new Wrapper(results, total);
    }

    public void deleteMovie(long id) throws MyValidationException {
        Movie tmp = dao.getMovie(id);
        if (tmp == null || canBeDeleted(id) == false) {
            throw new MyValidationException();
        } else {
            List<Comment> comments = commentDao.getAllMovieComments(tmp);
            for (Comment comment : comments) {
                commentDao.deleteComment(comment.getId());
            }
            dao.deleteMovie(id);
        }
    }

    public Movie modifyMovie(Movie movie) throws MyValidationException {
        if (movie.getId() == null
                || val.validateText(movie.getTitle(), 200) == false
                || ageClassificationDao.getAgeClassification(movie.getAgeClassification().getId()) == null
                || genreDao.getGenre(movie.getGenre().getId()) == null
                || val.validateNumber(movie.getBudget(), 0, 1000000000) == false
                || val.validateNumber(movie.getReleaseYear(), 1850, 2100) == false
                || val.validateLength(movie.getmLength()) == false
                || val.validateText(movie.getImageUrl(), 1000) == false) {
            throw new MyValidationException();
        } else if (dao.getMovie(movie.getId()) == null) {
            throw new MyValidationException();
        } else {
            return dao.modifyMovie(movie);
        }
        
    }

    public void changeRating(long id, byte rating) throws MyValidationException {
        Movie tmp = dao.getMovie(id);
        if (tmp == null || val.validateNumber(rating, 1, 10) == false) {
            throw new MyValidationException();
        } else {
            tmp.setNumberOfRatings(tmp.getNumberOfRatings() + 1);
            tmp.setSumOfRatings(tmp.getSumOfRatings() + rating);
            tmp.setRating((double)tmp.getSumOfRatings() / (double)tmp.getNumberOfRatings());
            dao.modifyMovie(tmp);
            Rating r = new Rating();
            r.setRating(rating);
            r.setMovie(tmp);
            //r.setMyUser(myUser);
            ratingDao.addRating(r);
        }
    }
    
    public boolean canBeDeleted(long id) throws MyValidationException {
        Movie tmp = dao.getMovie(id);
        if (tmp == null) {
            throw new MyValidationException();
        } else {
            return tmp.getActors().isEmpty();
        }
    }
}
