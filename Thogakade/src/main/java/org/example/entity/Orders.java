package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "Orders")
public class Orders implements SuperEntity {
    @Id
    private String id;
    private String customerId;
}
