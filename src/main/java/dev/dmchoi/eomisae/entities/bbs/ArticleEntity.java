package dev.dmchoi.eomisae.entities.bbs;

import dev.dmchoi.eomisae.interfaces.IEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ArticleEntity implements IEntity<ArticleEntity> {
    public static ArticleEntity build() {
        return new ArticleEntity();
    }

    private int index;
    private int userIndex;
    private int boardIndex;
    private String boardUrlName;
    private Date writtenAt;
    private String title;
    private String tag;
    private String url;
    private String discountCode;
    private String shippingInfo;
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 받아온 문자열의 orderDate 값을 Date 형식으로 포맷하여준다.
    private Date orderDate;
    private String shoppingMall;
    private String contact;
    private String brand;
    private String productName;
    private String productSize;
    private String mySize;
    private String exchangeSize;
    private String currencyForProduct;
    private String productPrice;
    private String currencyForPurchasing;
    private String purchaseProductPrice;
    private String currencyForSailing;
    private String saleProductPrice;
    private String outer;
    private String top;
    private String bottom;
    private String shoes;
    private String acc;
    private String content;
    private int view;
    private int like;
    private int buy;
    private int categoryIndex;
    private String gender;
    private String productStatus;
    private String tradeMethod;
    private int blindStatus;

    public int getIndex() {
        return index;
    }

    public ArticleEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public int getUserIndex() {
        return userIndex;
    }

    public ArticleEntity setUserIndex(int userIndex) {
        this.userIndex = userIndex;
        return this;
    }

    public int getBoardIndex() {
        return boardIndex;
    }

    public ArticleEntity setBoardIndex(int boardIndex) {
        this.boardIndex = boardIndex;
        return this;
    }

    public String getBoardUrlName() {
        return boardUrlName;
    }

    public ArticleEntity setBoardUrlName(String boardUrlName) {
        this.boardUrlName = boardUrlName;
        return this;
    }

    public Date getWrittenAt() {
        return writtenAt;
    }

    public ArticleEntity setWrittenAt(Date writtenAt) {
        this.writtenAt = writtenAt;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ArticleEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public ArticleEntity setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ArticleEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public ArticleEntity setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
        return this;
    }

    public String getShippingInfo() {
        return shippingInfo;
    }

    public ArticleEntity setShippingInfo(String shippingInfo) {
        this.shippingInfo = shippingInfo;
        return this;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public ArticleEntity setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public String getShoppingMall() {
        return shoppingMall;
    }

    public ArticleEntity setShoppingMall(String shoppingMall) {
        this.shoppingMall = shoppingMall;
        return this;
    }

    public String getContact() {
        return contact;
    }

    public ArticleEntity setContact(String contact) {
        this.contact = contact;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public ArticleEntity setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public ArticleEntity setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getProductSize() {
        return productSize;
    }

    public ArticleEntity setProductSize(String productSize) {
        this.productSize = productSize;
        return this;
    }

    public String getMySize() {
        return mySize;
    }

    public ArticleEntity setMySize(String mySize) {
        this.mySize = mySize;
        return this;
    }

    public String getExchangeSize() {
        return exchangeSize;
    }

    public ArticleEntity setExchangeSize(String exchangeSize) {
        this.exchangeSize = exchangeSize;
        return this;
    }

    public String getCurrencyForProduct() {
        return currencyForProduct;
    }

    public ArticleEntity setCurrencyForProduct(String currencyForProduct) {
        this.currencyForProduct = currencyForProduct;
        return this;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public ArticleEntity setProductPrice(String productPrice) {
        this.productPrice = productPrice;
        return this;
    }

    public String getCurrencyForPurchasing() {
        return currencyForPurchasing;
    }

    public ArticleEntity setCurrencyForPurchasing(String currencyForPurchasing) {
        this.currencyForPurchasing = currencyForPurchasing;
        return this;
    }

    public String getPurchaseProductPrice() {
        return purchaseProductPrice;
    }

    public ArticleEntity setPurchaseProductPrice(String purchaseProductPrice) {
        this.purchaseProductPrice = purchaseProductPrice;
        return this;
    }

    public String getCurrencyForSailing() {
        return currencyForSailing;
    }

    public ArticleEntity setCurrencyForSailing(String currencyForSailing) {
        this.currencyForSailing = currencyForSailing;
        return this;
    }

    public String getSaleProductPrice() {
        return saleProductPrice;
    }

    public ArticleEntity setSaleProductPrice(String saleProductPrice) {
        this.saleProductPrice = saleProductPrice;
        return this;
    }

    public String getOuter() {
        return outer;
    }

    public ArticleEntity setOuter(String outer) {
        this.outer = outer;
        return this;
    }

    public String getTop() {
        return top;
    }

    public ArticleEntity setTop(String top) {
        this.top = top;
        return this;
    }

    public String getBottom() {
        return bottom;
    }

    public ArticleEntity setBottom(String bottom) {
        this.bottom = bottom;
        return this;
    }

    public String getShoes() {
        return shoes;
    }

    public ArticleEntity setShoes(String shoes) {
        this.shoes = shoes;
        return this;
    }

    public String getAcc() {
        return acc;
    }

    public ArticleEntity setAcc(String acc) {
        this.acc = acc;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public int getView() {
        return view;
    }

    public ArticleEntity setView(int view) {
        this.view = view;
        return this;
    }

    public int getLike() {
        return like;
    }

    public ArticleEntity setLike(int like) {
        this.like = like;
        return this;
    }

    public int getBuy() {
        return buy;
    }

    public ArticleEntity setBuy(int buy) {
        this.buy = buy;
        return this;
    }

    public int getCategoryIndex() {
        return categoryIndex;
    }

    public ArticleEntity setCategoryIndex(int categoryIndex) {
        this.categoryIndex = categoryIndex;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public ArticleEntity setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public ArticleEntity setProductStatus(String productStatus) {
        this.productStatus = productStatus;
        return this;
    }

    public String getTradeMethod() {
        return tradeMethod;
    }

    public ArticleEntity setTradeMethod(String tradeMethod) {
        this.tradeMethod = tradeMethod;
        return this;
    }

    public int getBlindStatus() {
        return blindStatus;
    }

    public ArticleEntity setBlindStatus(int blindStatus) {
        this.blindStatus = blindStatus;
        return this;
    }

    @Override
    public ArticleEntity clone() {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.index = this.index;
        articleEntity.userIndex = this.userIndex;
        articleEntity.boardIndex = this.boardIndex;
        articleEntity.boardUrlName = this.boardUrlName;
        articleEntity.writtenAt = this.writtenAt;
        articleEntity.title = this.title;
        articleEntity.tag = this.tag;
        articleEntity.url = this.url;
        articleEntity.discountCode = this.discountCode;
        articleEntity.shippingInfo = this.shippingInfo;
        articleEntity.orderDate = this.orderDate;
        articleEntity.shoppingMall = this.shoppingMall;
        articleEntity.contact = this.contact;
        articleEntity.brand = this.brand;
        articleEntity.productName = this.productName;
        articleEntity.productSize = this.productSize;
        articleEntity.mySize = this.mySize;
        articleEntity.exchangeSize = this.exchangeSize;
        articleEntity.currencyForProduct = this.currencyForProduct;
        articleEntity.productPrice = this.productPrice;
        articleEntity.currencyForPurchasing = this.currencyForPurchasing;
        articleEntity.purchaseProductPrice = this.purchaseProductPrice;
        articleEntity.currencyForSailing = this.currencyForSailing;
        articleEntity.saleProductPrice = this.saleProductPrice;
        articleEntity.outer = this.outer;
        articleEntity.top = this.top;
        articleEntity.bottom = this.bottom;
        articleEntity.shoes = this.shoes;
        articleEntity.acc = this.acc;
        articleEntity.content = this.content;
        articleEntity.view = this.view;
        articleEntity.like = this.like;
        articleEntity.buy = this.buy;
        articleEntity.categoryIndex = this.categoryIndex;
        articleEntity.gender = this.gender;
        articleEntity.productStatus = this.productStatus;
        articleEntity.tradeMethod = this.tradeMethod;
        articleEntity.blindStatus = this.blindStatus;
        return articleEntity;
    }

    @Override
    public void copyValuesOf(ArticleEntity articleEntity) {
        this.index = articleEntity.index;
        this.userIndex = articleEntity.userIndex;
        this.boardIndex = articleEntity.boardIndex;
        this.boardUrlName = articleEntity.boardUrlName;
        this.writtenAt = articleEntity.writtenAt;
        this.title = articleEntity.title;
        this.tag = articleEntity.tag;
        this.url = articleEntity.url;
        this.discountCode = articleEntity.discountCode;
        this.shippingInfo = articleEntity.shippingInfo;
        this.orderDate = articleEntity.orderDate;
        this.shoppingMall = articleEntity.shoppingMall;
        this.contact = articleEntity.contact;
        this.brand = articleEntity.brand;
        this.productName = articleEntity.productName;
        this.productSize = articleEntity.productSize;
        this.mySize = articleEntity.mySize;
        this.exchangeSize = articleEntity.exchangeSize;
        this.currencyForProduct = articleEntity.currencyForProduct;
        this.productPrice = articleEntity.productPrice;
        this.currencyForPurchasing = articleEntity.currencyForPurchasing;
        this.purchaseProductPrice = articleEntity.purchaseProductPrice;
        this.currencyForSailing = articleEntity.currencyForSailing;
        this.saleProductPrice = articleEntity.saleProductPrice;
        this.outer = articleEntity.outer;
        this.top = articleEntity.top;
        this.bottom = articleEntity.bottom;
        this.shoes = articleEntity.shoes;
        this.acc = articleEntity.acc;
        this.content = articleEntity.content;
        this.view = articleEntity.view;
        this.like = articleEntity.like;
        this.buy = articleEntity.buy;
        this.categoryIndex = articleEntity.categoryIndex;
        this.gender = articleEntity.gender;
        this.productStatus = articleEntity.productStatus;
        this.tradeMethod = articleEntity.tradeMethod;
        this.blindStatus = articleEntity.blindStatus;
    }
}
