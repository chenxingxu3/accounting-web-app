package web.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="kusers")
public class KUser implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    private String name;
    private String password;
    private String sex;
    private String email;
    
    @OneToMany(mappedBy="kuser")
    private List<KAccount> kaccounts = new ArrayList<>();

    public KUser() {
        
    }

    

    public KUser(String name, String password, String sex, String email) {
        super();
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.email = email;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public List<KAccount> getKaccounts() {
        return kaccounts;
    }



    public void setKaccounts(List<KAccount> kaccounts) {
        this.kaccounts = kaccounts;
    }



    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }

    
    

    
    
    
}
