package dev.aditya.productcatalogservice.Model;


import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@MappedSuperclass
public abstract class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Date lastUpdatedAt;
    private Date createdAt;

    //@Enumerated(EnumType.STRING) //Without this the table stores the enm in Integer format -> Active =0,Deleted =1,Inactive=2 etc.
    //What if tomorrow the list becomes -> Archive,Active,Deleted,Inactive -> This won't change the mapping in the table. So the elements which were supposed to be Active will now become Archive.
    //Always store in string which is Human Readable.
    private ModelStatus status;

    public Base()
        {
           status=ModelStatus.ACTIVE;
           createdAt=new Date();
           lastUpdatedAt=createdAt;
        }
}
