package pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/24.
 */

public class People implements Serializable {
    public int head;
    public String name;
    public String stata = "offine";
    public boolean isChecked;

    public People(int head,String name)
    {
        this.head = head;
        this.name = name;
    }
}
