package lt.vu.services;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Emailee {
    private Integer id;
    private String email;

    public Emailee(Integer id, String email){
        this.id = id;
        this.email = email;
    }
}
