package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDatesPojo {
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
    private String checkin;
    private String checkout;

    // 2) tum parametrelerle ve parametresiz constructurlarımızı oluştururyoruz

    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public BookingDatesPojo() {
    }

    //3) Getters ve Setters'larımızı oluşturuyoruz.


    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    //4) toString() methodumuzu oluşturuyoruz.


    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
