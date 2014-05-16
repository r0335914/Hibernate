/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdrental;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Gert
 */

/*
Hier wordt een ManagedBean gemaakt. Deze zal gebruikt worden voor de data te tonen op de JSF pagina, en in de helper class om
data op te halen.
*/
@ManagedBean
@SessionScoped
public class FilmController {

    int startId;
    int endId;
    DataModel filmTitles; // wordt gebruikt om een lijst te sorteren, met een custom separator.
    FilmHelper helper; // de helper klasse met alle query's in
    private int recordCount = 1000; //er zijn 1000 titels
    private int pageSize = 10; // er we tonen er 10 per pag
    
    private Film current;
    private int selectedItemIndex;
    
    
    /* maakt een nieuwe instantie van de FilmController */
    public FilmController() {
        helper = new FilmHelper();
        startId = 1;
        endId = 10;
    }
    
    //wanneer er 2 paramters worden meegegeven
    public FilmController(int startId, int endId){
        helper = new FilmHelper();
        this.startId = startId;
        this.endId = endId;
    }
    
    //de geselecteerde film returnen
    public Film getSelected(){
        if(current == null){
            current = new Film();
            selectedItemIndex = -1;
        }
        return current;
    }
    
    //de filmtitels ophalen die binnen een bepaald begin en einde liggen
    public DataModel getFilmTitles(){
        if(filmTitles == null){
            filmTitles = new ListDataModel(helper.getFilmTitles(startId, endId));
        }
        return filmTitles;
    }
    
    //"Refresht" het Datamodel 
    void recreateModel(){
        filmTitles = null;
    }
    
    
    //Hieronder staat code om te navigeren door de films met next en previous
    
    //Om te bepalen of er nog een volgende pagina is
    public boolean isHasNextPage(){
        if(endId + pageSize<=recordCount){
            return true;
        }
        return false;
    }
     //Om te bepalen of er nog een vorige pagina is
    public boolean isHasPreviousPage(){
        if(startId-pageSize > 0){
            return true;
        }
        return false;
    }
    
    //Om effectief naar de volgende pagina tegaan
    public String next(){
        startId = endId+1;
        endId = endId + pageSize;
        recreateModel();
        return "index";
    }
    
    //Om effectief een pagina terug te gaan
    public String previous(){
        startId = startId - pageSize;
        endId = endId - pageSize;
        recreateModel();
        return "index";
    }
    
    //De pagesize returen (ingesteld op 10)
    public int getPageSize(){
        return pageSize;
    }
    
    //Zorgt ervoor dat de JSF navigation handler een pagina genaamd "browse" probeert te openen
    public String prepareView(){
        current = (Film) getFilmTitles().getRowData();
        return "browse";
    }
    
    //Zorgt ervoor dat de JSP navigation handler een pagina genaamd "index" probeert te openen
    public String prepareList(){
        recreateModel();
        return "index";
    }

    //Deze methode gaat in de helper klasse extra film details ophalen
    public String getLanguage(){
        int langID = current.getLanguageByLanguageId().getLanguageId().intValue();
        String language = helper.getLangByID(langID);
        return language;
    }
    
    //Deze methode gaat in de helper klasse extra film details ophalen
    public String getActors(){
        List actors = helper.getActorsById(current.getFilmId());
        StringBuffer totalCast = new StringBuffer();
        for(int i = 0; i<actors.size();i++){
            Actor actor = (Actor) actors.get(i);
            totalCast.append(actor.getFirstName());
            totalCast.append(" ");
            totalCast.append(actor.getLastName());
            totalCast.append(" ");
        }
        return totalCast.toString();
    }
    
    //Deze methode gaat in de helper klasse extra film details ophalen
    public String getCategory(){
        Category category = helper.getCategoryByID(current.getFilmId());
        return category.getName();
    }
}
