package myapp.SzakdolgozatBE.article;

import java.util.ArrayList;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.Wrapper;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class ArticleServiceTest {
    
    @Mock
    ArticleDAO daoMock;
    
    @InjectMocks
    ArticleService service;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        when(daoMock.saveArticle(any(Article.class))).thenAnswer(i -> i.getArguments()[0]);
        when(daoMock.saveAgainArticle(any(Article.class))).thenAnswer(i -> i.getArguments()[0]);
        when(daoMock.publishNewArticle(any(Article.class))).thenAnswer(i -> i.getArguments()[0]);
        when(daoMock.publishSavedArticle(any(Article.class))).thenAnswer(i -> i.getArguments()[0]);
        
        Article saved = new Article();
        saved.setId(1L);
        saved.setTitle("mentett cím");
        saved.setContent("mentett tartalom");
        saved.setSaved(true);
        when(daoMock.getArticle(any(long.class))).thenReturn(saved);
        
        when(daoMock.getPublishedArticles(any(int.class), any(int.class))).thenReturn(new ArrayList<>());
        when(daoMock.getNumberOfPublishedArticles()).thenReturn(77L);
    }
    
    public Article getArticle() {
        Article toSave = new Article();
        toSave.setTitle("cím");
        toSave.setContent("tartalom");
        return toSave;
    }

    @Test
    public void testSaveArticle1() throws Exception {
        Article toSave = getArticle();
        Article saved = service.saveArticle(toSave);
        assertEquals(toSave.getTitle(), saved.getTitle());
        assertEquals(saved.isSaved(), true);
    }
    
    @Test
    public void testSaveArticle2() throws Exception {
        Article toSave = getArticle();
        toSave.setId(1L);
        toSave.setSaved(true);
        Article saved = service.saveArticle(toSave);
        assertEquals(toSave.getTitle(), saved.getTitle());
        assertEquals(saved.isSaved(), true);
    }
    
    @Test(expected = MyValidationException.class)
    public void testSaveArticleException1() throws Exception {
        Article toSave = getArticle();
        toSave.setTitle(" cím");
        service.saveArticle(toSave);
    }
    
    @Test(expected = MyValidationException.class)
    public void testSaveArticleException2() throws Exception {
        Article toSave = getArticle();
        toSave.setUsername("a");
        service.saveArticle(toSave);
    }
    
    @Test(expected = MyValidationException.class)
    public void testSaveArticleException3() throws Exception {
        when(daoMock.getArticle(any(long.class))).thenReturn(null);
        Article toSave = getArticle();
        toSave.setId(1L);
        toSave.setSaved(true);
        service.saveArticle(toSave);
    }
    
    @Test(expected = MyValidationException.class)
    public void testSaveArticleException4() throws Exception {
        Article toSave = getArticle();
        toSave.setSaved(true);
        service.saveArticle(toSave);
    }
    
    @Test
    public void testPublishArticle1() throws Exception {
        Article toPublish = getArticle();
        toPublish.setUsername("valaki");
        Article published = service.publishArticle(toPublish);
        assertEquals(toPublish.getTitle(), published.getTitle());
        assertEquals(published.isPublished(), true);
    }
    
    @Test
    public void testPublishArticle2() throws Exception {
        Article toPublish = getArticle();
        toPublish.setUsername("valaki");
        toPublish.setId(1L);
        toPublish.setSaved(true);
        Article published = service.publishArticle(toPublish);
        assertEquals(toPublish.getTitle(), published.getTitle());
        assertEquals(published.isPublished(), true);
        assertEquals(published.isSaved(), false);
    }
    
    @Test(expected = MyValidationException.class)
    public void testPublishArticleException1() throws Exception {
        Article toPublish = getArticle();
        service.publishArticle(toPublish);
    }
    
    @Test(expected = MyValidationException.class)
    public void testPublishArticleException2() throws Exception {
        Article toPublish = getArticle();
        toPublish.setTitle(" cím");
        toPublish.setUsername("valaki");
        service.publishArticle(toPublish);
    }
    
    @Test(expected = MyValidationException.class)
    public void testPublishArticleException3() throws Exception {
        when(daoMock.getArticle(any(long.class))).thenReturn(null);
        Article toPublish = getArticle();
        toPublish.setId(1L);
        toPublish.setSaved(true);
        toPublish.setUsername("valaki");
        service.publishArticle(toPublish);
    }
    
    @Test(expected = MyValidationException.class)
    public void testPublishArticleException4() throws Exception {
        Article toPublish = getArticle();
        toPublish.setId(1L);
        toPublish.setUsername("valaki");
        service.publishArticle(toPublish);
    }

    @Test
    public void testGetSavedArticles() throws Exception {
        service.getSavedArticles();
        verify(daoMock).getSavedArticles();
    }

    @Test
    public void testGetPublishedArticles() throws Exception {
        Wrapper tmp = service.getPublishedArticles(1, 10);
        assertEquals(tmp.getTotal(), 77);
        assertNotNull(tmp.getResults());
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetPublishedArticlesException1() throws Exception {
        service.getPublishedArticles(0, 10);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetPublishedArticlesException2() throws Exception {
        service.getPublishedArticles(1, 23);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetPublishedArticlesException3() throws Exception {
        service.getPublishedArticles(4, 30);
    }

    @Test
    public void testDeleteArticle() throws Exception {
        service.deleteArticle(1);
        verify(daoMock).deleteArticle(1);
    }
    
    @Test(expected = MyValidationException.class)
    public void testDeleteArticleException() throws Exception {
        when(daoMock.getArticle(any(long.class))).thenReturn(null);
        service.deleteArticle(1);
    }

    @Test
    public void testGetArticle() throws Exception {
        Article tmp = service.getArticle(1);
        assertEquals((long)tmp.getId(), 1L);
        assertEquals(tmp.getTitle(), "mentett cím");
        assertEquals(tmp.getContent(), "mentett tartalom");
        assertEquals(tmp.isSaved(), true);
    }
    
    @Test(expected = MyValidationException.class)
    public void testGetArticleException() throws Exception {
        when(daoMock.getArticle(any(long.class))).thenReturn(null);
        service.getArticle(1);
    }

    @Test
    public void testValidateArticle() throws Exception {
        Article tmp = getArticle();
        assertEquals(service.validateArticle(tmp), true);
        tmp.setTitle(" cím");
        assertEquals(service.validateArticle(tmp), false);
        tmp.setTitle("cím");
        tmp.setContent(" tartalom");
        assertEquals(service.validateArticle(tmp), false);
        tmp.setContent("tartalom");
        tmp.setPublished(true);
        assertEquals(service.validateArticle(tmp), false);
        tmp.setPublished(false);
        tmp.setPublishDate("2000. 01. 01. 12:00:00");
        assertEquals(service.validateArticle(tmp), false);
    }
    
}
