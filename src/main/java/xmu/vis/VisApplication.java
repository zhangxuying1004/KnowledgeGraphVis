package xmu.vis;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VisApplication {
    public static void main(String[] args) {
        SpringApplication.run(VisApplication.class, args);
    }
}

//import org.apache.commons.lang.StringUtils;

//import java.util.*;
//
//public class VisApplication {
//    public static void main(String[] args) {
//        int level = 0;
//        int otherInfoNum = 2;
//        Map<String, Object> map = new HashMap<String,Object>();
//        List<String> list = new ArrayList<String>(Arrays.asList("基地","舰队",""+level));
//        List<String> tempList = new ArrayList<String>();
//
//        tempList.addAll(list);
//        for (int i = 0; i < otherInfoNum; i++) {
//            tempList.remove(tempList.size() - 1);
//        }
//        System.out.println("list:"+list);
//        System.out.println("tempList:" + tempList);
//    }
//}