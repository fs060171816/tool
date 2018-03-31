public class Counter {
 
    public static int count = 0;
    
    private static ThreadLocal<Integer> ts = new ThreadLocal<Integer>(){
    	protected Integer initialValue(){
    		System.out.println("init-----------------");
    		return 0;
    	}
    };
 
    public static void inc() {
 
        //这里延迟1毫秒，使得结果明显
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }
 
//        count++;
//        ts.
        System.out.println(ts.get());
        ts.set(ts.get().intValue()+1);
    }
 
    public static void main(String[] args) {
//    	ts.set(0);
 
        //同时启动1000个线程，去进行i++计算，看看实际结果
 
        for (int i = 0; i < 10; i++){
            new Thread(new Runnable(){
				public void run() {
					Counter.inc();
				}
			}).start();
        }
 
        //这里每次运行的值都有可能不同,可能为1000
        System.out.println("运行结果:Counter.count=" + Counter.ts.get());
    }
}