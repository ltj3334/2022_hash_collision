package hash_collision;
public class App {  
    public static void main(String[] args) {
        HashTable t = new HashTable(11);
    
        t.put("LEE", new Student("Lee Youngseok", "Computer", 2020, 4));
        t.put("KIM", new Student("Kim Younghee", "AI", 2011, 3));
        t.put("PARK", new Student("Park Chulsoo", "Electronics", 2020, 4));
        t.put("CHOI", new Student("Choi Cheesoo", "Mechanical", 2019, 3));
        t.put("LEE", new Student("Lee Chulsoo", "Software", 2019, 3));
        t.put("HONG", new Student("Hong Gildong","Chemical",2018,4));
        t.put("YANG", new Student("Yang Gildong","Vision",2021,4));

    }
  }
  

class Student {
    public String name, department;
    public int id, gpa;
  
    public Student(String n, String d, int i, int g) {
      name = n; department = d; id = i; gpa = g;
    }
  
    public String toString() {
      return "("+name+","+department+","+id+","+gpa+")";
    }
  }

class HashTable implements Map {

    private Entry[] entries;
    private int size, used;
    private float loadFactor;
    private final Entry Dummy = new Entry(null,null);  // dummy

    public HashTable(int capacity, float loadFactor) {
        entries = new Entry[capacity];
        this.loadFactor = loadFactor;
    }

    public HashTable(int capacity) {
        this(capacity, 0.75F);
    }

    public HashTable() {
        this(101);
    }

    public Object get(Object key) {
        int h = hash(key);
        for (int i = 0; i < entries.length; i++) {
            int j = nextProbe(h,i);
            Entry entry = entries[j];
            if (entry == null) break;
            if (entry == Dummy) continue;
            if (entry.key.equals(key)) return entry.value;  // success
        }
        return null;  // failure: key not found
    }

    public Object put(Object key, Object value) {
        if (used > loadFactor*entries.length) rehash();
        int h = hash(key);
        for (int i = 0; i < entries.length; i++) {
            int j = nextProbe(h,i);
            if (i != 0) System.out.println("Collision!");
            Entry entry = entries[j];
            if (entry == null) {
                /* 코드 작성 */
            }
            if (entry == Dummy) continue;

            if (entry.key.equals(key)) {
                /* 코드 작성 */
            }
        }
        return null;
    }

    public Object remove(Object key) {
        int h = hash(key);
        for (int i = 0; i<entries.length; i++) {
            int j = nextProbe(h,i);
            Entry entry = entries[j];
            if (entry == null) break;
            if (entry == Dummy) continue;
            if (entry.key.equals(key)) {
               /* 코드 작성 */
            }
        }
        return null;  
    }

    public int size() {
        return size;
    }

    private class Entry {
        Object key, value;
        Entry(Object k, Object v) { key = k; value = v; }
    }

    private int hash(Object key) {
        if (key == null) throw new IllegalArgumentException();
        return (key.hashCode() & 0x7FFFFFFF) % entries.length;
    }

    private int nextProbe(int h, int i) {
        return (h + i) % entries.length;      // Linear Probing
    }

    private void rehash() {
        Entry[] oldEntries = entries;
        entries = new Entry[2*oldEntries.length+1];
        for (int k = 0; k < oldEntries.length; k++) {
            Entry entry = oldEntries[k];
            if (entry == null || entry == Dummy) continue;
            int h = hash(entry.key);
            for (int i = 0; i < entries.length; i++) {
                int j = nextProbe(h,i);
                if (entries[j] == null) {
                    entries[j] = entry;
                    break;
                }
            }
        }
        used = size;
    }
}

interface Entry {
    public Object getKey();
    // RETURN: key;
    // POST: key is the first object in this ordered pair;
    public Object getValue();
    // RETURN: value;
    // POST: value is the second object in this ordered pair;
    public void setValue(Object value);
    // POST: value is the second object in this ordered pair;
  }
  
interface Map {
    public Object get(Object key);
    // RETURN: value;
    // POST: if value!=null, then (key,value) is in this map;
    //       if value==null, then no record in this map has the given key;
    public Object put(Object key, Object value);
    // RETURN: oldValue;
    // POST: if oldValue==null, then (key,value) is in this map;
    //       if oldValue!=null, then (key,oldValue) was in this map;
    public Object remove(Object key);
    // RETURN: oldValue;
    // POST: if oldValue==null, no record in this map has the given key;
    //       if oldValue!=null, then (key,oldValue) was in this map;
    public int size();
    // RETURN: n;
    // POST: this map contains n records;
}
