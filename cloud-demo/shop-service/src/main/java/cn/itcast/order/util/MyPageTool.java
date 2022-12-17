package cn.itcast.order.util;

import cn.itcast.order.common.MyPage;

import java.util.ArrayList;
import java.util.List;

public class MyPageTool {
    public static <T> MyPage<T> getPage(List<T> allData, Integer size, Integer pageNum){
        MyPage<T> ans = new MyPage<>();
        ans.setTotal(allData.size());
        ans.setRecords(new ArrayList<>());
        for(int i=(pageNum-1)*size; i<pageNum*size && i<ans.getTotal() && i>=0;i++){
            ans.getRecords().add(allData.get(i));
        }
        return ans;
    }
}
