package wis.Runtime;

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;


import sun.jvmstat.monitor.MonitoredHost;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.MonitoredVmUtil;
import sun.jvmstat.monitor.VmIdentifier;

public class Ba {

	
	   public static void main(String[] args) throws Exception, Exception {
		
		   
		   
	       MonitoredHost local = MonitoredHost.getMonitoredHost("localhost");
	         // 取得所有在活动的虚拟机集合
	         Set<Object> vmlist = new HashSet<Object>(local.activeVms());
	         // 遍历集合，输出PID和进程名
	         for(Object process : vmlist) {
	             MonitoredVm vm = local.getMonitoredVm(new VmIdentifier("//" + process));
	             // 获取类名
	             String processname = MonitoredVmUtil.mainClass(vm, true);
	             System.out.println(process + " ------> " + processname);
	         }
	}
}
