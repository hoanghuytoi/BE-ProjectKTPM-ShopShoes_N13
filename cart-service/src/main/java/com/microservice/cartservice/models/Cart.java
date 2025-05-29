package com.microservice.cartservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CART")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARTID")
    private Long cartId;
    
    @Column(name = "TOTAL")
    private BigDecimal total;
    
    @Column(name = "USER_ID")
    private Long userId;
    
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartDetails> cartDetails = new ArrayList<>();
    
    public void calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        if (cartDetails != null) {
            for (CartDetails detail : cartDetails) {
                if (detail != null && detail.getTotal() != null) {
                    total = total.add(detail.getTotal());
                }
            }
        }
        this.total = total;
    }
}