package cn.itcast.order.service;

import cn.itcast.order.domain.Goods;
import cn.itcast.order.domain.GoodsTag;
import cn.itcast.order.domain.Medium;
import cn.itcast.order.domain.Publisher;

import java.util.List;

public interface InfoService {
    void addPublisher(String name, String details) throws Exception;
    void addMedium(String type) throws Exception;
    void addGoodsTag(String name) throws Exception;
    void updatePublisher(String name, String detailsToUpdate) throws Exception;
    //detailsToUpdate==null/""时不更新该属性
    void updateMedium(String oldType, String newType) throws Exception;
    void updateGoodsTag(String oldName, String newName) throws Exception;
    void deletePublisher(String name) throws Exception;
    void deleteMedium(String type) throws Exception;
    void deleteGoodsTag(String name) throws Exception;

    //删改部分可不用，但都已实现

    List<Publisher> getAllPublisher();
    List<Medium> getAllMedium();
    List<GoodsTag> getAllGoodsTag();
}
