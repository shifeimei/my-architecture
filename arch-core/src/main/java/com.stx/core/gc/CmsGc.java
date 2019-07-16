package com.stx.core.gc;

/**
 * @author tongxiang.stx
 * @date 2019/07/16
 */
public class CmsGc {
    public static void main(String[]args)throws Exception{
        System.out.println("full gc start");
        int i = 0;
        while ( i++ < 100) {
            byte[] a = new byte[1024 * 1024 * 2000];
            //System.gc();
            Thread.sleep(5000);
        }

        System.out.println("full gc finish");
    }

    /**
     * 采用的参数：
     * -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCApplicationStoppedTime -XX:+UseConcMarkSweepGC -XX:+CMSScavengeBeforeRemark
     */

    /**
     * 普通程序gc
     * [GC (CMS Initial Mark) [1 CMS-initial-mark: 2048420K(3414036K)] 2048420K(4027476K), 0.0001676 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
     Total time for which application threads were stopped: 0.0002249 seconds, Stopping threads took: 0.0000158 seconds
     [CMS-concurrent-mark-start]
     [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
     [CMS-concurrent-preclean-start]
     [CMS-concurrent-preclean: 0.005/0.005 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     [GC (CMS Final Remark) [YG occupancy: 0 K (613440 K)][GC (CMS Final Remark) [ParNew: 0K->0K(613440K), 0.0042869 secs] 2048420K->2048420K(4027476K), 0.0043050 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
     [Rescan (parallel) , 0.0015956 secs][weak refs processing, 0.0000101 secs][class unloading, 0.0002680 secs][scrub symbol table, 0.0004149 secs][scrub string table, 0.0001204 secs][1 CMS-remark: 2048420K(3414036K)] 2048420K(4027476K), 0.0067987 secs] [Times: user=0.04 sys=0.00, real=0.01 secs]
     Total time for which application threads were stopped: 0.0068472 seconds, Stopping threads took: 0.0000139 seconds
     [CMS-concurrent-sweep-start]
     [CMS-concurrent-sweep: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     [CMS-concurrent-reset-start]
     [CMS-concurrent-reset: 0.004/0.004 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
     */

    /**
     * system.gc()调用
     *
     * [GC (CMS Initial Mark) [1 CMS-initial-mark: 2048421K(3414036K)] 2048421K(4027476K), 0.0003319 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     Total time for which application threads were stopped: 0.0003895 seconds, Stopping threads took: 0.0000208 seconds
     [CMS-concurrent-mark-start]
     [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
     [CMS-concurrent-preclean-start]
     [CMS-concurrent-preclean: 0.005/0.005 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     [CMS-concurrent-abortable-preclean-start]
     Total time for which application threads were stopped: 0.0000615 seconds, Stopping threads took: 0.0000162 seconds
     [GC (Allocation Failure) [ParNew: 10906K->0K(613440K), 0.0062584 secs][CMS[CMS-concurrent-abortable-preclean: 0.301/5.005 secs] [Times: user=0.35 sys=0.00, real=5.01 secs]
     (concurrent mode failure): 2048421K->420K(3414036K), 0.0071797 secs] 2059328K->420K(4027476K), [Metaspace: 3288K->3288K(1056768K)], 0.0138436 secs] [Times: user=0.06 sys=0.00, real=0.02 secs]
     Total time for which application threads were stopped: 0.0140550 seconds, Stopping threads took: 0.0000169 seconds
     [Full GC (System.gc()) [CMS: 2048420K->2048420K(3414036K), 0.2989853 secs] 2048420K->2048420K(4027476K), [Metaspace: 3288K->3288K(1056768K)], 0.2993562 secs] [Times: user=0.30 sys=0.00, real=0.30 secs]
     Total time for which application threads were stopped: 0.2994701 seconds, Stopping threads took: 0.0000130 seconds
     */
}
