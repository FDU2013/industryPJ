package cn.itcast.order.service.impl;

import cn.itcast.order.common.GoodsStatus;
import cn.itcast.order.domain.Goods;
import cn.itcast.order.repository.GoodsRepository;
import cn.itcast.order.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {
    GoodsRepository goodsRepository;
    @Autowired
    GoodServiceImpl(GoodsRepository goodsRepository){
        this.goodsRepository = goodsRepository;
    }
    @Override
    public void addGoods(Goods goods) throws Exception {
        if(goodsRepository.findByGoodsId(goods.getGoodsId()) != null){
            throw new Exception("ID重复！");
        }
        goods.setStatus(GoodsStatus.ForSale);
        goodsRepository.save(goods);
    }

    @Override
    public void updateGoods(Goods goods) throws Exception {
        Goods oldGoods = goodsRepository.findByGoodsId(goods.getGoodsId());
        if(oldGoods == null){
            throw new Exception("商品id错误或不存在。");
        }
        String name = goods.getName();
        if(name != null && !name.equals("")){
            oldGoods.setName(name);
        }
        String publisher = goods.getPublisher();
        if(publisher != null && !publisher.equals("")){
            oldGoods.setPublisher(publisher);
        }
        String medium = goods.getMedium();
        if(medium != null && !medium.equals("")){
            oldGoods.setMedium(medium);
        }
        String tag = goods.getTag();
        if(tag != null && !tag.equals("")){
            oldGoods.setTag(tag);
        }
        String imageUrl = goods.getImageUrl();
        if(imageUrl != null && !imageUrl.equals("")){
            oldGoods.setImageUrl(imageUrl);
        }
        String details = goods.getDetails();
        if(details != null && !details.equals("")){
            oldGoods.setDetails(details);
        }
        BigDecimal price = goods.getPrice();
        if(price != null && price.compareTo(new BigDecimal(0)) > 0){
            oldGoods.setPrice(price);
        }
        GoodsStatus status = goods.getStatus();
        if(status != null){
            oldGoods.setStatus(status);
        }
        goodsRepository.save(oldGoods);
    }

    @Override
    public Goods findGoodsById(String id) throws Exception {
        Goods goods = goodsRepository.findByGoodsId(id);
        if(goods == null){
            throw new Exception("该商品不存在！");
        }
        return goods;
    }

    @Override
    public List<Goods> findGoodsByIdIn(List<String> id) throws Exception {
        return goodsRepository.findByGoodsIdIn(id);
    }

    @Override
    public List<Goods> getAllGoods() {
        return goodsRepository.findAll();
    }

    @Override
    public List<Goods> getAllGoodsByStatus(GoodsStatus status) {
        return goodsRepository.findByStatus(status);
    }

    @Override
    public List<Goods> findGoodsByMediumAndStatus(String medium, GoodsStatus status) {
        return goodsRepository.findByMediumAndStatus(medium, status);
    }

    @Override
    public List<Goods> findGoodsByTagAndStatus(String tag, GoodsStatus status) {
        return goodsRepository.findByTagAndStatus(tag, status);
    }

    @Override
    public List<Goods> searchGoodsByMediumAndTagAndStatus(List<String> medium, List<String> tag, String search, GoodsStatus status) {
        List<Goods> temp;
        if(medium == null || medium.size() == 0){
            if(tag == null || tag.size() == 0){
                temp = goodsRepository.findAll();
            } else {
                temp = goodsRepository.findByTagInAndStatus(tag, status);
            }
        } else {
          if(tag == null || tag.size() == 0){
              temp = goodsRepository.findByMediumInAndStatus(medium, status);
          } else {
              temp = goodsRepository.findByMediumInAndTagInAndStatus(medium, tag, status);
          }
        }
        if(search == null || search.equals("")){
            return temp;
        }
        List<Goods> res = new ArrayList<>();
        for(Goods goods : temp){
            String aim = goods.getName();
            if(aim.contains(search)) res.add(goods);
        }
        return res;
    }
}
