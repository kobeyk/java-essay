package com.appleyk.designpatterns;

/**
 * <p>越努力，越幸运 -- 排序类</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class Sorter<T> {

    public void sort(T[] array, MyComparator<T> comparator){
        for (int i = 0; i < array.length; i++) {
            for (int j = i+1;j<array.length;j++){
              if( comparator.compare(array[i],array[j])>0){
                  T temp = array[i];
                  array[i] = array[j];
                  array[j] = temp;
              }
            }
        }
    }
}
