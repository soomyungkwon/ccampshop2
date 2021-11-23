package org.zerock.shop.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.thymeleaf.util.StringUtils;
import org.zerock.shop.constant.ItemSellStatus;
import org.zerock.shop.entity.Item;
import org.zerock.shop.entity.QItem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("상품저장테스트")
   public void createItemTest(){

        Item item=new Item();

        item.setItemNm("테스트상품");
        item.setPrice(10000);
        item.setItemDetail("테스트상품상세설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());

        Item savedItem=itemRepository.save(item);

        System.out.println(savedItem.toString());
        System.out.println("-----------------------------------------------------------------------");
    }

    @Test
    @DisplayName("상품리스트 저장 테스트")
    public void createItemList(){
        for (int i=1; i<=10 ; i++){
            Item item=new Item();

            item.setItemNm("테스트상품" + i);
            item.setPrice(10000+i);
            item.setItemDetail("테스트상품 상세설명"+i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());

            Item savedItem=itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest(){
        this.createItemList();
        List<Item> itemList=itemRepository.findByItemNm("테스트상품2");

        for (Item item : itemList){
            System.out.println(item.toString());
        }
        System.out.println("---------------------------------------------------------------");
    }

    @Test
    @DisplayName("상품명, 상품상세설명 or 테스트")
    public void findByItemNmOrItemDetailTest(){
        this.createItemList();
        List<Item> itemList=itemRepository.findByItemNmOrItemDetail("테스트상품1", "테스트상품 상세설명5");

        for (Item item : itemList){
            System.out.println(item.toString());
        }
        System.out.println("---------------------------------------------------------------");
    }

    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest(){
        this.createItemList();
        List<Item> itemList=itemRepository.findByPriceLessThan(10005);

        for (Item item : itemList){
            System.out.println(item.toString());
        }
        System.out.println("---------------------------------------------------------------");
    }

    @Test
    @DisplayName("가격 내림차순 조회 테스트")
    public void findByPriceLessThanOrderByPriceDesc(){
        this.createItemList();
        List<Item> itemList=itemRepository.findByPriceLessThanOrderByPriceDesc(10005);

        for (Item item : itemList){
            System.out.println(item.toString());
        }
        System.out.println("---------------------------------------------------------------");
    }

    @Test
    @DisplayName(" @Query 를 이용한 상품 조회 테스트")
    public void findByItemDetailTest(){
        this.createItemList();
        List<Item> itemList=itemRepository.findByItemDetail("테스트상품 상세설명");

        for (Item item : itemList){
            System.out.println(item.toString());
        }
        System.out.println("---------------------------------------------------------------");
    }

    @Test
    @DisplayName("nativeQuery 속성을 이용한 상품 조회 테스트")
    public void findByItemDetailByNative(){
        this.createItemList();
        List<Item> itemList=itemRepository.findByItemDetailByNative("테스트상품 상세설명");

        for (Item item : itemList){
            System.out.println(item.toString());
        }
        System.out.println("---------------------------------------------------------------");
    }

    @Test
    @DisplayName("Querydsl 조회 테스트")
    public void queryDslTest(){
        this.createItemList();
        JPAQueryFactory queryFactory=new JPAQueryFactory(em);
        QItem qItem=QItem.item;
        JPQLQuery<Item> query=queryFactory.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%"+"테스트상품 상세설명"+"%"))
                .orderBy(qItem.price.desc());

        List<Item> itemList=query.fetch();

        for (Item item : itemList){
            System.out.println(item.toString());
        }
        System.out.println("---------------------------------------------------------------");
    }

    public void createItemList2(){

        for (int i=1 ; i<=5 ; i++){
            Item item=new Item();

            item.setItemNm("테스트 상품"+i);
            item.setPrice(10000+i);
            item.setItemDetail("테스트 상품 상세 설명"+i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());

            itemRepository.save(item);
        }

        for (int i=6 ; i<=10 ; i++){
            Item item=new Item();

            item.setItemNm("테스트상품"+i);
            item.setPrice(10000+i);
            item.setItemDetail("테스트상품 상세설명"+i);
            item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());

            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품 Querydsl 조회 테스트2")
    public void queryDslTest2(){

        this.createItemList();

        BooleanBuilder booleanBuilder=new BooleanBuilder();
        QItem item=QItem.item;
        String itemDetail="테스트상품 상세설명";
        int price=10003;
        String itemSellStat="SELL";

        booleanBuilder.and(item.itemDetail.like("%"+ itemDetail +"%"));
        booleanBuilder.and(item.price.gt(price));

        if (StringUtils.equals(itemSellStat, ItemSellStatus.SELL)){
            booleanBuilder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
        }

        Pageable pageable= PageRequest.of(0, 5);
        Page<Item> itemPagingResult=itemRepository.findAll(booleanBuilder, pageable);

        System.out.println("total elements: "+itemPagingResult.getTotalElements());
        System.out.println("------------------------------------------------------------");

        List<Item> resultItemList=itemPagingResult.getContent();

        for (Item resultItem : resultItemList){
            System.out.println(resultItem.toString());
        }
        System.out.println("------------------------------------------------------------");
    }

}