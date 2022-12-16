package cn.itcast.order.service;

import cn.itcast.order.domain.Goods;

import java.util.List;

public interface GoodService {
    void addGoods(Goods goods) throws Exception;
    void updateGoods(Goods goods) throws Exception;
    //update: 若传入goods的某个属性为null或空视为该属性不变，id主键必须传入且不可修改

    Goods findGoodsById(String id) throws Exception;
    List<Goods> getAllGoods();
    List<Goods> findGoodsByMedium(String medium);
    List<Goods> findGoodsByTag(String tag);
    List<Goods> searchGoodsByMediumAndTag(List<String> medium, List<String> tag, String search);
    /*medium/tag/search==null或size/值==0/"",视为忽略该查找条件
     *相当于使用sql中的select...in...
     */
}
