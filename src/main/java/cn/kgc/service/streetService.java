package cn.kgc.service;

import cn.kgc.domain.street;

import java.util.List;

public interface streetService {
    List<street> getStreetByDistrictId(Integer disstrictId);

}

