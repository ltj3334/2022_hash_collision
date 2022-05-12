package hash_collision;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testInheritance() {
        HashTable t = new HashTable(11);

        assertTrue(t instanceof Map);
    }

    @Test
    public void testHashTable() {
        HashTable t = new HashTable(11);
        t.put("LEE", new Student("Lee Youngseok", "Computer", 2020, 4));
        t.put("KIM", new Student("Kim Younghee", "AI", 2011, 3));
        t.put("PARK", new Student("Park Chulsoo", "Electronics", 2020, 4));
        t.put("CHOI", new Student("Choi Cheesoo", "Mechanical", 2019, 3));
        assertEquals(4, t.size());
        assertEquals(t.get("LEE").toString(), "(Lee Youngseok,Computer,2020,4)");

        t.put("LEE", new Student("Lee Chulsoo", "Software", 2019, 3));
        assertEquals(t.get("LEE").toString(), "(Lee Chulsoo,Software,2019,3)");
        t.put("HONG", new Student("Hong Gildong","Chemical",2018,4));
        t.put("YANG", new Student("Yang Gildong","Vision",2021,4));

        assertEquals(6, t.size());
    }

    public void testHashTableRemove() {
        HashTable t = new HashTable(11);
        t.put("YANG", new Student("Yang Gildong","Vision",2021,4));
        assertEquals(1, t.size());
        
        t.remove("YANG");
        assertEquals(null,t.get("YANG"));
    }

}
