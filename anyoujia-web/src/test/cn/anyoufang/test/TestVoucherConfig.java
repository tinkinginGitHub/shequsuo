package cn.anyoufang.test;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class TestVoucherConfig {

  private ApplicationContext ac = null;

  @Before
  public void get() {
     ac = new ClassPathXmlApplicationContext("classpath:spring-dao.xml");
  }


    @Test
    public void test1() {
//      TbVoucherService service = (TbVoucherService) ac.getBean(TbVoucherService.class);
//        TbVoucher voucher = new TbVoucher();
//        voucher.setVoucherId("1");
//        voucher.setVoucherTimeUpdate(new Date());
//        voucher.setVoucherCreatedtime(new Date());
//        //voucher.setVoucherStatus("0");//0 表示安优房后台券配置完成状态
//        voucher.setVoucherAmount("100");//券金额
//        voucher.setVoucherAmountToDeductibleDays("30");//可抵扣时间
//        voucher.setVoucherAmountToDeductibleMoney("100");
//        voucher.setVoucherTimeAvailable("123");//券有效期
//        voucher.setVoucherPic("https://www.baidu.com");
//        voucher.setVoucherText("https://www.baidu.com");
//        service.save(voucher);
//        System.out.println("save successfully");
    }

    @Test
    public void test2() {
      ThreadPoolTaskExecutor  threadPoolTaskExecutor = (ThreadPoolTaskExecutor)ac.getBean("taskExecutor");
      int n = 20;
      for (int i = 0; i < n; i++) {
        threadPoolTaskExecutor.execute(new Runnable() {
          @Override
          public void run() {

          }
        });
        System.out.println("int i is " + i + ", now threadpool active threads totalnum is " + threadPoolTaskExecutor.getActiveCount());
        }
    }
}
