package cn.anyoufang.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author wantianwu
 * db查找房源参数集
 */
public class SearchParam  implements Serializable {
    /**
     * 最低价格
     */
    private Integer minPrice;

    /**
     * 最高价格
     */
    private Integer maxPrice;

    /**
     * 大于区间最大价格
     */
    private Integer intPrice;

    /**
     * 优房方式
     */
    private String superior;

    /**
     * 户型
     */
    private String rooms;

    /**
     * 附近定位
     */
    private String gis;

    /**
     * 地铁线路
     */
    private List<String> subway;

    /**
     * 付款方式
     */
    private String[] paymentType;

    /**
     * 是否支持券
     */
    private String voucher;

    /**
     * 排序方式
     */
    private String order;

    /**
     * 区域
     */
    private String areas;

    /**
     * 区域2
     */
    private String areas2;

    /**
     * 最小纬度
     */
    private Double min_latitude;

    /**
     * 最大纬度
     */
    private Double max_latitude;

    /**
     * 最小经度
     */
    private Double min_longitude;

    /**
     * 最大经度
     */
    private Double max_longitude;

    /**
     * 特色多选
     */
    private List<String> multiculture;

    public String getAreas2() {
        return areas2;
    }

    public void setAreas2(String areas2) {
        this.areas2 = areas2;
    }

    public SearchParam(Integer minPrice, Integer maxPrice, Integer intPrice,
            String superior, String rooms, String gis, List<String> subway,
            String[] paymentType, String voucher, String order, String areas,
            Double min_latitude, Double max_latitude, Double min_longitude,
            Double max_longitude, List<String> multiculture) {
        super();
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.intPrice = intPrice;
        this.superior = superior;
        this.rooms = rooms;
        this.gis = gis;
        this.subway = subway;
        this.paymentType = paymentType;
        this.voucher = voucher;
        this.order = order;
        this.areas = areas;
        this.min_latitude = min_latitude;
        this.max_latitude = max_latitude;
        this.min_longitude = min_longitude;
        this.max_longitude = max_longitude;
        this.multiculture = multiculture;
    }

    public SearchParam() {
        super();
    }

    public List<String> getMulticulture() {
        return multiculture;
    }

    public void setMulticulture(List<String> multiculture) {
        this.multiculture = multiculture;
    }

    public Double getMin_latitude() {
        return min_latitude;
    }

    public void setMin_latitude(Double min_latitude) {
        this.min_latitude = min_latitude;
    }

    public Double getMax_latitude() {
        return max_latitude;
    }

    public void setMax_latitude(Double max_latitude) {
        this.max_latitude = max_latitude;
    }

    public Double getMin_longitude() {
        return min_longitude;
    }

    public void setMin_longitude(Double min_longitude) {
        this.min_longitude = min_longitude;
    }

    public Double getMax_longitude() {
        return max_longitude;
    }

    public void setMax_longitude(Double max_longitude) {
        this.max_longitude = max_longitude;
    }

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }


    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getIntPrice() {
        return intPrice;
    }

    public void setIntPrice(Integer intPrice) {
        this.intPrice = intPrice;
    }

    public String getSuperior() {
        return superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getGis() {
        return gis;
    }

    public void setGis(String gis) {
        this.gis = gis;
    }

    public List<String> getSubway() {
        return subway;
    }

    public void setSubway(List<String> subway) {
        this.subway = subway;
    }


    public String[] getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String[] paymentType) {
        this.paymentType = paymentType;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
