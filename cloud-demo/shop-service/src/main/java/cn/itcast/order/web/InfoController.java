package cn.itcast.order.web;

import cn.itcast.feign.common.Result;
import cn.itcast.order.common.MyPage;
import cn.itcast.order.common.PageSearchData;
import cn.itcast.order.domain.GoodsTag;
import cn.itcast.order.domain.Medium;
import cn.itcast.order.domain.Publisher;
import cn.itcast.order.service.InfoService;
import cn.itcast.order.util.MyPageTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class InfoController {
    @Autowired
    private InfoService infoService;

    @PostMapping("/addPublisher")
    public Result addPublisher(@RequestBody Publisher publisher) {
        try{
            infoService.addPublisher(publisher.getName(),publisher.getDetails());
            return Result.succ(publisher.getName(),"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(300,e.getMessage());
        }
    }

    @PostMapping("/updatePublisher")
    public Result updatePublisher(@RequestBody Publisher publisher) {
        try{
            infoService.updatePublisher(publisher.getName(),publisher.getDetails());
            return Result.succ(publisher.getName(),"信息修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(300,e.getMessage());
        }
    }

    @PostMapping("/deletePublisher")
    public Result deletePublisher(@RequestBody String name) {
        try{
            infoService.deletePublisher(name);
            return Result.succ(name,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(300,e.getMessage());
        }
    }

    @PostMapping("/getAllPublisherName")
    public Result getAllPublisherName() {
        try{
            List<Publisher> publisherList = infoService.getAllPublisher();
            List<String> names = new ArrayList<>();
            for(Publisher publisher : publisherList){
                names.add(publisher.getName());
            }
            return Result.succ(names);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(302,e.getMessage());
        }
    }

    @PostMapping("/searchPublisherPage")
    public Result getAllPublisher(@RequestBody PageSearchData searchData) {
        try{
            List<Publisher> publisherList = infoService.getAllPublisher();
            MyPage<Publisher> publisherPage = MyPageTool.getPage(publisherList,searchData.getPageSize(),searchData.getPageNum());
            return Result.succ(publisherPage);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(302,e.getMessage());
        }
    }


    @PostMapping("/addMedium")
    public Result addMedium(@RequestBody String type) {
        try{
            infoService.addMedium(type);
            return Result.succ(type,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(300,e.getMessage());
        }
    }

    @PostMapping("/deleteMedium")
    public Result deleteMedium(@RequestBody String name) {
        try{
            infoService.deleteMedium(name);
            return Result.succ(name,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(300,e.getMessage());
        }
    }

    @PostMapping("/getAllMedium")
    public Result getAllMedium() {
        try{
            List<Medium> media = infoService.getAllMedium();
            List<String> names = new ArrayList<>();
            for(Medium medium : media){
                names.add(medium.getType());
            }
            return Result.succ(names);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(302,e.getMessage());
        }
    }

    @PostMapping("/addGoodsTag")
    public Result addGoodsTag(@RequestBody String name) {
        try{
            infoService.addGoodsTag(name);
            return Result.succ(name,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(300,e.getMessage());
        }
    }

    @PostMapping("/deleteGoodsTag")
    public Result deleteGoodsTag(@RequestBody String name) {
        try{
            infoService.deleteGoodsTag(name);
            return Result.succ(name,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(300,e.getMessage());
        }
    }

    @PostMapping("/getAllGoodsTag")
    public Result getAllGoodsTag() {
        try{
            List<GoodsTag> goodsTags = infoService.getAllGoodsTag();
            List<String> names = new ArrayList<>();
            for(GoodsTag tag : goodsTags){
                names.add(tag.getName());
            }
            return Result.succ(names);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(302,e.getMessage());
        }
    }
}
