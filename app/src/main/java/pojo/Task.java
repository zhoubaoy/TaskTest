package pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */

public class Task implements Serializable {
    public String approvedDateTime;
    public String description;
    public String endDate;
    public int entityId;
    public boolean isDelete;
    public boolean isPubished;
    public String pubishDateTime;
    public String startDate;
    public String title;
    public Company company;
    public List<Image> images;
    public List<Product> products;
    public List<Reward> rewards;
    public List<Tag> tags;

    public class Company implements Serializable
    {
        //public List<Object> addresses;
        public String contactNo;
        public String description;
        public int entityId;
        public String fax;
        public List<Image> logos;
        public String name;
        public String verifyStatus;
    }
    public class Image implements Serializable
    {
        public String caption;
        public int entityId;
        public String uploadedDateTime;
    }
    public class Product implements Serializable
    {
        public String description;
        public int entityId;
        public String merchantLink;
        public int price;
        public String productName;
    }

    public class Reward implements Serializable
    {
        public int entityId;
        public String expireDate;
        public List<Image> images;
        public String instruction;
        public int minShares;
        public String rewardType;
        public String title;
        public float value;
    }

    public class Tag implements Serializable
    {
        public int entityId;
        public String name;
    }
}
