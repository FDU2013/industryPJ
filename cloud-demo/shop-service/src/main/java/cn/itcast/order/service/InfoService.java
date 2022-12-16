package cn.itcast.order.service;

import cn.itcast.order.domain.Goods;
import cn.itcast.order.domain.GoodsTag;
import cn.itcast.order.domain.Medium;
import cn.itcast.order.domain.Publisher;

import java.util.List;

public interface InfoService {
    void addPublisher(String name, String details) throws Exception;
    void addMedium(String name) throws Exception;
    void addGoodsTag(String name) throws Exception;

    List<Publisher> getAllPublisherA();
    List<Medium> getAllMedium();
    List<GoodsTag> getAllGoodsTag();
}
