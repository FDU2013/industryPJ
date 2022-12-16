package cn.itcast.order.service.impl;

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
    public List<Goods> getAllGoods() {
        return goodsRepository.findAll();
    }

    @Override
    public List<Goods> findGoodsByMedium(String medium) {
        return goodsRepository.findByMedium(medium);
    }

    @Override
    public List<Goods> findGoodsByTag(String tag) {
        return goodsRepository.findByTag(tag);
    }

    @Override
    public List<Goods> searchGoodsByMediumAndTag(List<String> medium, List<String> tag, String search) {
        List<Goods> temp;
        if(medium == null || medium.size() == 0){
            if(tag == null || tag.size() == 0){
                temp = goodsRepository.findAll();
            } else {
                temp = goodsRepository.findByTagIn(tag);
            }
        } else {
          if(tag == null || tag.size() == 0){
              temp = goodsRepository.findByMediumIn(medium);
          } else {
              temp = goodsRepository.findByMediumInAndTagIn(medium, tag);
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
