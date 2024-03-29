package dvdrental;
// Generated 2-apr-2014 14:31:37 by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Language generated by hbm2java
 */
public class Language  implements java.io.Serializable {


     private Byte languageId;
     private String name;
     private Date lastUpdate;
     private Set<Film> filmsForOriginalLanguageId = new HashSet<Film>(0);
     private Set<Film> filmsForLanguageId = new HashSet<Film>(0);

    public Language() {
    }

	
    public Language(String name, Date lastUpdate) {
        this.name = name;
        this.lastUpdate = lastUpdate;
    }
    public Language(String name, Date lastUpdate, Set<Film> filmsForOriginalLanguageId, Set<Film> filmsForLanguageId) {
       this.name = name;
       this.lastUpdate = lastUpdate;
       this.filmsForOriginalLanguageId = filmsForOriginalLanguageId;
       this.filmsForLanguageId = filmsForLanguageId;
    }
   
    public Byte getLanguageId() {
        return this.languageId;
    }
    
    public void setLanguageId(Byte languageId) {
        this.languageId = languageId;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Date getLastUpdate() {
        return this.lastUpdate;
    }
    
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    public Set<Film> getFilmsForOriginalLanguageId() {
        return this.filmsForOriginalLanguageId;
    }
    
    public void setFilmsForOriginalLanguageId(Set<Film> filmsForOriginalLanguageId) {
        this.filmsForOriginalLanguageId = filmsForOriginalLanguageId;
    }
    public Set<Film> getFilmsForLanguageId() {
        return this.filmsForLanguageId;
    }
    
    public void setFilmsForLanguageId(Set<Film> filmsForLanguageId) {
        this.filmsForLanguageId = filmsForLanguageId;
    }




}


