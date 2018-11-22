package com.sangrade.base.util.Collection;

import java.util.*;

public class CollectionBuilder {

    public static SetBuilder setBuilder;
    public static ListBuilder listBuilder;
    public static MapBuilder mapBuilder;

    static {
        setBuilder = new SetBuilder();
        listBuilder = new ListBuilder<>();
        mapBuilder = new MapBuilder<>();
    }


    public static final class SetBuilder<T>{
        private Set<T> set;
        private SetBuilder(){
            set = new HashSet<>();
        }

        public CollectionBuilder.SetBuilder add(T arg){
            set.add(arg);
            return this;
        }

        public CollectionBuilder.SetBuilder add(T ...args){
            for(T t : args){
                set.add(t);
            }
            return this;
        }

        public Set<T> getSet(){
            return this.set;
        }
    }

    public static final class ListBuilder<T>{
        private List<T> list;

        private ListBuilder(){
            list = new ArrayList<T>();
        }

        public CollectionBuilder.ListBuilder add(T arg){
            list.add(arg);
            return this;
        }
        public CollectionBuilder.ListBuilder add(T ...args){
            for(T t :args){
                list.add(t);
            }
            return this;
        }

        public List<T> get(){
            return list;
        }
    }

    public static final class  MapBuilder<K,V>{
        private HashMap<K,V> map;

        private MapBuilder(){
            map = new HashMap<>();
        }

        public CollectionBuilder.MapBuilder put(K k,V v){
            map.put(k,v);
            return this;
        }

        public HashMap<K, V> getMap() {
            return map;
        }

    }

}
