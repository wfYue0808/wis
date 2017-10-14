package wis.Runtime;

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.MonitoredHost;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.MonitoredVmUtil;
import sun.jvmstat.monitor.VmIdentifier;

public class Ca {

	public static void main(String[] args) throws Exception, Exception {
	   System.out.println(getProcess("observer.jar"));
		
	}
	
	
	
    public static int getProcess(String cls) throws MonitorException, URISyntaxException {
        if(cls == null) {
            return -1;
        }

        // 获取监控主机
        MonitoredHost local = MonitoredHost.getMonitoredHost("localhost");
        // 取得所有在活动的虚拟机集合
        Set<?> vmlist = new HashSet<Object>(local.activeVms());
        // 遍历集合，输出PID和进程名
        for(Object process : vmlist) {
            MonitoredVm vm = local.getMonitoredVm(new VmIdentifier("//" + process));
            // 获取类名
            String processname = MonitoredVmUtil.mainClass(vm, true);
            if(cls.equals(processname)) {
                return ((Integer)process).intValue();
            }
        }
        return -1;
    }
}
