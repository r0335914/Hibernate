/*
De helper klasse zal gebruikt worden om Hibernate queries uit te voeren op de database. De query wordt geschreven in
Hibernate Query Language (HQL). In de klasse zitten vershillende methodes de queries maken en runnen
*/

package dvdrental;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class FilmHelper {

    Session session = null;

    public FilmHelper() {
        /*
        Hier wordt de Hibernate sessie gemaakt en opgezet door de getSessionFactory
        */
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    /*
    De onderstaande methodes worden later gebruikt in de JSP klasses. Om de verbinding met de databank testen, 
    rechts klikken op hibernate.cfg.xml --> run HQL query
    */
    
    /*Het ophalen van de films waarvan het ID tussen een bepaalde begin en einde ligt, zodat we op de JSP pagina
    met next en forward kunnen werken
    */
    public List getFilmTitles(int startID, int endID){
        List<Film> filmList = null;
        try{
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Film as film where film.filmId between "+ startID+ " and "+endID+"");
            filmList = (List<Film>) q.list();
        }catch(Exception e){
            e.printStackTrace();
        }
        return filmList;
    }
    
    /*
    Deze method returned alle actors die in een bepaalde film meespelen, paramter wordt ingestuurd
    */
    public List getActorsById(int filmId){
        List<Actor> actorList = null;
        try{
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Actor as actor where actor.actorId in (select filmActor.actor.actorId from FilmActor as filmActor where filmActor.film.filmId=" + filmId + ")");
            actorList = (List<Actor>) q.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return actorList;
    }
    
    /*
    Deze method returned de categorieën die bij een bepaalde film horen 
    */
    public Category getCategoryByID(int filmId){
        List<Category> categoryList = null;
        try{
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Category as category where category.categoryId in (select filmCat.category.categoryId from FilmCategory as filmCat where filmCat.film.filmId="+filmId +")");
            categoryList=(List<Category>) q.list();
        }catch(Exception e){
            e.printStackTrace();
        }
        return categoryList.get(0);
    }
    
    /*
    Deze method returned alle info over een bepaalde film die met als parameter wordt binnengestuurd
    */
    public Film getFilmByID(int filmId){
        
        Film film = null;
        
        try{
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Film af silm where film.filmId=" + filmId);
            film = (Film) q.uniqueResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return film;
    }
    
    /*
    Deze method returned alle filmen die overstemmen met een bepaalde taal. Deze taal wordt ook binnengestuurd
    met een parameter
    */
    public String getLangByID(int langId){
        Language language = null;
        
        try{
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Language as lang where lang.languageId=" + langId);
            language = (Language) q.uniqueResult();  
        }catch (Exception e){
            e.printStackTrace();
        }
        return language.getName();
    }
    
}
