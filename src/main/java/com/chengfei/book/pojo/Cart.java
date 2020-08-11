package com.chengfei.book.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    //private Integer totalCount;
    //private BigDecimal totalPrice;
    //key是商品编号，value是商品信息
    private Map<Integer,CartItem> items=new LinkedHashMap<>();

    public void addItem(CartItem cartItem){
        //先看购物车中是否添加过此商品
        CartItem item = items.get(cartItem.getId());
        if (item==null){
            items.put(cartItem.getId(),cartItem);
        }else {
            item.setCount(item.getCount()+1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }

    }
    public void deleteItem(Integer id){
        items.remove(id);
    }
    public void clear(){
        items.clear();
    }
    public void updateCount(Integer id,Integer count){
        //先看商品中是否有此商品，如果有，修改商品数量，更新总金额
        CartItem cartItem=items.get((id));
        if(cartItem!=null){
            cartItem.setCount(count);//修改商品数量
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));//更新总金额
        }
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Cart() {
    }

    public Integer getTotalCount() {
        Integer totalCount=0;
        for(CartItem value:items.values()){
            totalCount+=value.getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice=new BigDecimal(0);
        for(CartItem value:items.values()){
            totalPrice=totalPrice.add(value.getTotalPrice());
        }
        return totalPrice;
    }



    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
