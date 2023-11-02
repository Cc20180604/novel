package com.cc.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Hots {
    Integer id;
    String name;
    Integer num;
}