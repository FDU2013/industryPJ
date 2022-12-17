package cn.itcast.order.service.impl;

import cn.itcast.order.domain.GoodsTag;
import cn.itcast.order.domain.Medium;
import cn.itcast.order.domain.Publisher;
import cn.itcast.order.repository.GoodsTagRepository;
import cn.itcast.order.repository.MediumRepository;
import cn.itcast.order.repository.PublisherRepository;
import cn.itcast.order.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {
    PublisherRepository publisherRepository;
    MediumRepository mediumRepository;
    GoodsTagRepository goodsTagRepository;
    @Autowired
    InfoServiceImpl(PublisherRepository publisherRepository, MediumRepository mediumRepository, GoodsTagRepository goodsTagRepository){
        this.publisherRepository = publisherRepository;
        this.mediumRepository = mediumRepository;
        this.goodsTagRepository = goodsTagRepository;
    }
    @Override
    public void addPublisher(String name, String details) throws Exception {
        if(publisherRepository.findByName(name) != null) {
            throw new Exception("出版社已存在");
        }
        publisherRepository.save(new Publisher(name, details));
    }

    @Override
    public void addMedium(String name) throws Exception {
        if(mediumRepository.findByType(name) != null) {
            throw new Exception("介质已存在");
        }
        mediumRepository.save(new Medium(name));
    }

    @Override
    public void addGoodsTag(String name) throws Exception {
        if(goodsTagRepository.findByName(name) != null) {
            throw new Exception("标签/类别已存在");
        }
        goodsTagRepository.save(new GoodsTag(name));
    }

    @Override
    public void updatePublisher(String name, String detailsToUpdate) throws Exception {
        Publisher publisher = publisherRepository.findByName(name);
        if(publisher == null){
            throw new Exception("该出版社不存在");
        }
        if(detailsToUpdate != null) {
            publisher.setDetails(detailsToUpdate);
            publisherRepository.save(publisher);
        }
    }

    @Override
    public void updateMedium(String oldType, String newType) throws Exception {
        Medium medium = mediumRepository.findByType(oldType);
        if(medium == null){
            throw new Exception("该介质不存在");
        }
        mediumRepository.save(new Medium(newType));
        mediumRepository.delete(medium);
    }

    @Override
    public void updateGoodsTag(String oldName, String newName) throws Exception {
        GoodsTag goodsTag = goodsTagRepository.findByName(oldName);
        if(goodsTag == null){
            throw new Exception("该介质不存在");
        }
        goodsTagRepository.save(new GoodsTag(newName));
        goodsTagRepository.delete(goodsTag);
    }

    @Override
    public void deletePublisher(String name) throws Exception {
        Publisher publisher = publisherRepository.findByName(name);
        if(publisher == null){
            throw new Exception("该出版社不存在");
        }
        publisherRepository.delete(publisher);
    }

    @Override
    public void deleteMedium(String type) throws Exception {
        Medium medium = mediumRepository.findByType(type);
        if(medium == null){
            throw new Exception("该介质不存在");
        }
        mediumRepository.delete(medium);
    }

    @Override
    public void deleteGoodsTag(String name) throws Exception {
        GoodsTag goodsTag = goodsTagRepository.findByName(name);
        if(goodsTag == null){
            throw new Exception("该标签/类型不存在");
        }
        goodsTagRepository.delete(goodsTag);
    }

    @Override
    public List<Publisher> getAllPublisher() {
        return publisherRepository.findAll();
    }

    @Override
    public List<Medium> getAllMedium() {
        return mediumRepository.findAll();
    }

    @Override
    public List<GoodsTag> getAllGoodsTag() {
        return goodsTagRepository.findAll();
    }
}
