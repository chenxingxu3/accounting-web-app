package web.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="kaccounts")
public class KAccount implements Serializable {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    private String name;
    private String type;
    private String mode;
    @Column(name="money", columnDefinition="decimal(10,2) default '100.00'")
    private BigDecimal money;
    private Timestamp date; 
    private String remark;
    
    @ManyToOne
    private KUser kuser;

    public KAccount() {
        
    }

    public KAccount(String name, String type, String mode, BigDecimal money, Timestamp date, String remark,
            KUser kuser) {
        super();
        this.name = name;
        this.type = type;
        this.mode = mode;
        this.money = money;
        this.date = date;
        this.remark = remark;
        this.kuser = kuser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public KUser getKuser() {
        return kuser;
    }

    public void setKuser(KUser kuser) {
        this.kuser = kuser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    

}
