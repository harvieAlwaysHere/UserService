package top.harvie.ProjectTeam.conf;

import java.lang.reflect.Field;

public class Tools {

    //工具方法：对照Obj1和Obj2，将Obj2中的不为null的属性更新到Obj1中
    public static void update(Object object1,Object object2)throws Exception{
        Class cls = object2.getClass();
        Field[] fields = cls.getDeclaredFields();
        for(int i=0; i<fields.length; i++){
            Field f = fields[i];
            f.setAccessible(true);
            if(f.get(object2)!=null){
                f.set(object1,f.get(object2));
            }
        }

    }
}
