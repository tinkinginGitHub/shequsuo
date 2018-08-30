//package cn.anyoufang.task;
//
//
//import cn.anyoufang.controller.CCbBankController;
//import cn.anyoufang.util.WeatherUtil;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AirAndAqiTask {
//
//    @Scheduled(cron = "0 0 */1 * * ? ") // 每一个小时执行一次
//    public void taskCycle() {
//        String str= WeatherUtil.getAirCondtionInformation("52");
//        CCbBankController.airCondition.put("52",str);
//        String aq = WeatherUtil.getAqiInformation("52");
//        CCbBankController.aqi.put("52",aq);
//    }
//}
