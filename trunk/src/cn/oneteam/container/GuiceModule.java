package cn.oneteam.container;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.oneteam.dispatcher.Mapping;
import cn.oneteam.interceptor.InterceptorList;

import com.google.inject.Binder;
import com.google.inject.Module;

public class GuiceModule implements Module {
    public void configure(Binder binder) {
    	try {
    		List<Class> mappingClass = this.initMappings();
    		for( Class c : mappingClass){
    			binder.bind(c);
    		}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private static List<Class> initMappings() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		ClassLoader cld = Thread.currentThread().getContextClassLoader();
		URL resource = cld.getResource("/");
		File dirs = new File(resource.getFile());
		findClass(dirs,"");
		return findMappings(classList);
    }
	private static void findClass(File dirs,String basePack)
	throws ClassNotFoundException {
		File[] childs = dirs.listFiles();
		for (int i = 0; i < childs.length; i++) {
			String packPath =basePack+childs[i].getName()+".";
			if (childs[i].isDirectory()) {
				findClass(childs[i],packPath);
			} else {
				String className = childs[i].getName();
				if (className.endsWith(".class")) {
					packPath=packPath.replace(".class.", "");
					classList.add(Class.forName(packPath));
				}
			}
		}
	}
	
	private static ArrayList<Class> findMappings(ArrayList<Class> classLst)
		throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		ArrayList<Class> mappingList = new ArrayList<Class>();
		for (int c = 0; c < classLst.size(); c++) {
			Class clazz = classLst.get(c);
				Method mArr[] = clazz.getDeclaredMethods();
				for (Method method:mArr) {
					Mapping action = (Mapping) method.getAnnotation(Mapping.class);
					if (action != null) {
						if(!mappingList.contains(clazz)){
							mappingList.add(clazz);
						}
						String[] values = action.value();
						if(values.length == 2){
							InterceptorList.addActInt(clazz.getName()+"_"+method.getName(), values[1]);
						}
					}
				}
		}
		return mappingList;
	}
	private static ArrayList<Class> classList = new ArrayList<Class>();
}