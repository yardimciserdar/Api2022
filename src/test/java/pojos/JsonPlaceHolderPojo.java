package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // farklılıklar için sıkıntı olmasın diye bilinmeyenleri atla.
public class JsonPlaceHolderPojo {
    /*
    1) Tum Keyler için private varriable'lar oluşturuyoruz
    2) tum parametrelerle ve parametresiz constructurlarımızı oluştururyoruz
    3) getters ve setters larımız oluşturuyoruz
    4) Create toString  metodumuz oluşturuyoruz.
     */
    /*
     farklı key value ikililerin uyuşmazlığıonı görmezden gelmek için
     @JsonIgnoreProperties(ignoreUnknown = true) anotation ını pojo classımızın başına yazarak çözebiliriz

     */

    // 1) Tum Keyler için private varriable'lar oluşturuyoruz

    private Integer userId;
    private String title;
    private  Boolean completed;


    // 2) tum parametrelerle ve parametresiz constructurlarımızı oluştururyoruz


    public JsonPlaceHolderPojo() {

    }

    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    //3) getters ve setters larımız oluşturuyoruz


    public Integer getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    // 4) Create toString  metodumuz oluşturuyoruz.


    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}