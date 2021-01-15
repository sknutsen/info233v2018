package List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class LinkedListTest {
    private IList<Integer> intList;

    @BeforeEach
    void setUp() {
        intList = new LinkedList<>();
    }

    /**
     * Tester av rest()
     */
    @Nested
    class RestTests {

        /**
         * prøver rest på liste med elementer etter det første
         * forventer at rest() returnerer en liste med ett element "1"
         */
        @Test
        void restWithTail() {
            IList<String> liste = new LinkedList<>();
            liste.add("1");
            liste.put("2");
            IList<String> restList = liste.rest();
            assertEquals("1", restList.first());
            assertFalse(restList.contains("2"));
        }

        /**
         * prøver rest på liste uten elementer etter det første
         * forventer at rest() returnerer en tom liste med null som verdi
         */
        @Test
        void restWithNoTail() {
            IList<String> liste = new LinkedList<>();
            liste.add("1");
            IList<String> restList = liste.rest();
            assertNull(restList);
        }

        /**
         * prøver rest på en tom liste
         * forventer NullPointerException
         */
        @Test
        void restOnEmptyList() {
            assertThrows(NullPointerException.class, () -> intList.rest());
        }

    }

    /**
     * Tester av add()
     */
    @Nested
    class AddTests {

        /**
         * prøver add på tom liste med NULL som verdi
         * forventer at add() returnerer false og at ingenting blir lagt til listen
         */
        @Test
        void addNullToEmptyList() {
            assertFalse(intList.add(null));
            assertFalse(intList.contains(null));
            assertEquals(0, intList.size());
        }

        /**
         * prøver add på tom liste
         * forventer at add() returnerer true og at listen kun inneholder 1
         */
        @Test
        void addToEmpty() {
            assertTrue(intList.add(1));
            Iterator<Integer> iterator = intList.iterator();
            int next = iterator.next();
            assertEquals(1, next);
            assertTrue(intList.contains(1));
            assertEquals(1, intList.size());
        }

        /**
         * prøver add på liste med ett element
         * forventer at add() returnerer true og at rekkefølgen på listen blir 1 2
         */
        @Test
        void addToOne() {
            IList<Integer> values = new LinkedList<>(1);
            assertTrue(values.add(2));
            assertTrue(values.contains(1));
            assertTrue(values.contains(2));
            assertEquals(2, values.size());

            //itererer gjennom listen for å se om alt er plassert der det skal være
            Iterator<Integer> iterator = values.iterator();
            int next = iterator.next();
            assertEquals(1, next);
            next = iterator.next();
            assertEquals(2, next);
        }

        /**
         * prøver add på liste med flere elementer
         * forventer at add() returnerer true og at rekkefølgen på listen blir 1 2 3
         */
        @Test
        void addToMany() {
            IList<Integer> ints = new LinkedList<>(2);
            IList<Integer> values = new LinkedList<>(1, ints);
            assertTrue(values.add(3));
            assertTrue(values.contains(1));
            assertTrue(values.contains(2));
            assertTrue(values.contains(3));
            assertEquals(3, values.size());

            //itererer gjennom listen for å se om alt er plassert der det skal være
            Iterator<Integer> iterator = values.iterator();
            int next = iterator.next();
            assertEquals(1, next);
            next = iterator.next();
            assertEquals(2, next);
            next = iterator.next();
            assertEquals(3, next);
        }

    }

    /**
     * Tester av put()
     */
    @Nested
    class PutTests {

        /**
         * prøver put på tom liste med NULL som verdi
         * forventer at put() returnerer false og at ingenting blir lagt til listen
         */
        @Test
        void putNullToEmptyList() {
            assertFalse(intList.put(null));
            assertFalse(intList.contains(null));
            assertEquals(0, intList.size());
        }

        /**
         * prøver put på tom liste
         * forventer at put() returnerer true og at listen bare inneholder elementet 1
         */
        @Test
        void putToEmptyList() {
            assertTrue(intList.put(1));
            assertTrue(intList.contains(1));
            assertEquals(1, intList.size());
            int firstValue = intList.first();
            assertEquals(1, firstValue);
        }

        /**
         * prøver put på liste med ett element
         * forventer at put() returnerer true, og at rekkefølgen på elementene i listen blir 2 1
         */
        @Test
        void putToListWithOneElement() {
            IList<Integer> values = new LinkedList<>(1);
            assertTrue(values.put(2));
            assertTrue(values.contains(1));
            assertTrue(values.contains(2));
            assertEquals(2, values.size());

            //itererer gjennom listen for å se om alt er plassert der det skal være
            Iterator<Integer> iterator = values.iterator();
            int next = iterator.next();
            assertEquals(2, next);
            next = iterator.next();
            assertEquals(1, next);
        }

        /**
         * prøver put på liste med flere elmenter
         * forventer at put() returnerer true, og at rekkefølgen på elementene i listen blir 3 1 2
         */
        @Test
        void putToListWithManyElements() {
            IList<Integer> ints = new LinkedList<>(2);
            IList<Integer> values = new LinkedList<>(1, ints);
            assertTrue(values.put(3));
            assertTrue(values.contains(1));
            assertTrue(values.contains(2));
            assertTrue(values.contains(3));
            assertEquals(3, values.size());

            //itererer gjennom listen for å se om alt er plassert der det skal være
            Iterator<Integer> iterator = values.iterator();
            int next = iterator.next();
            assertEquals(3, next);
            next = iterator.next();
            assertEquals(1, next);
            next = iterator.next();
            assertEquals(2, next);
        }

    }

    /**
     * Tester av first()
     */
    @Nested
    class FirstTests {

        /**
         * prøver å hente det første elementet fra en liste uten elemnter
         * forventer NullPointerException
         */
        @Test
        void firstOnEmptyList() {
            assertThrows(NullPointerException.class, () -> intList.first());
        }

        /**
         * prøvere å hente det første elementet fra en liste med bare ett element
         * forventer at 1 blir returnert
         */
        @Test
        void firstOnListWithOneElement() {
            intList.add(1);
            int i = intList.first();
            assertEquals(1, i);
        }

        /**
         * prøver å hente det første elementet fra en liste med flere elementer
         * forventer at 3 blir returnert
         */
        @Test
        void firstOnListWithManyElements() {
            intList.add(1);
            intList.add(2);
            intList.put(3);
            int i = intList.first();
            assertEquals(3, i);
        }

    }

    /**
     * Tester av remove()
     */
    @Nested
    class RemoveTests {

        /**
         * prøver å bruke remove på en tom liste
         * forventer NoSuchElementException
         */
        @Test
        void removeOnEmptyList() {
            assertThrows(NoSuchElementException.class, () -> intList.remove());
        }

        /**
         * prøver å fjerne et element fra en liste med bare ett element
         * forventer at det første elementet blir fjernet
         */
        @Test
        void removeOnListWithOneElement() {
            intList.add(1);
            int i = intList.remove();
            assertEquals(1, i);
            assertEquals(0, intList.size());
            assertFalse(intList.contains(1));
        }

        /**
         * prøver å fjerne et element fra en liste med flere elementer
         * forventer at det første elementet blir fjernet og erstattet av den neste noden,
         * og at resten av listen er uendret
         */
        @Test
        void removeOnListWithManyElements() {
            intList.add(1);
            intList.add(2);
            intList.put(3);
            int i = intList.remove();
            assertEquals(3, i);
            assertEquals(2, intList.size());

            //itererer gjennom listen for å se om alt er plassert der det skal være
            Iterator<Integer> iterator = intList.iterator();
            int next = iterator.next();
            assertEquals(1, next);
            next = iterator.next();
            assertEquals(2, next);
        }

    }

    /**
     * Tester av remove(object)
     */
    @Nested
    class RemoveSpecificTests {

        /**
         * prøver å fjerne et spesifisert element fra en tom liste
         * forventer at false blir returnert
         */
        @Test
        void removeSpecificElementFromEmptyList() {
            assertFalse(intList.remove(1));
        }

        /**
         * prøver å fjerne et spesifikt element fra en liste med bare det
         * forventer at elementet blir fjernet
         */
        @Test
        void removeSpecificElementFromListWithOneElement() {
            intList.add(1);
            assertTrue(intList.remove(1));
            assertEquals(0, intList.size());
            assertFalse(intList.contains(1));
        }

        /**
         * Prøver å fjerne et spesifisert element fra an liste med mer enn det ønskede element
         * forventer at elementet blir fjernet og erstattet av den neste noden, og at resten av listen er uendret
         */
        @Test
        void removeSpecificElementFromListWithMultipleElements() {
            intList.add(1);
            intList.add(2);
            intList.put(3);
            assertTrue(intList.remove(1));
            assertEquals(2, intList.size());

            //itererer gjennom listen for å se om alt er plassert der det skal være
            Iterator<Integer> iterator = intList.iterator();
            int next = iterator.next();
            assertEquals(3, next);
            next = iterator.next();
            assertEquals(2, next);
        }

    }

    /**
     * Tester av contains()
     */
    @Nested
    class ContainsTests {


        /**
         * ser om contains() returnerer true når listen inneholder det ønskede elementet
         */
        @Test
        void containsTrue() {
            intList.add(2);
            intList.put(3);
            assertTrue(intList.contains(2));
            assertTrue(intList.contains(3));
        }

        /**
         * ser om contains() returnerer false når listen ikke inneholder det ønskede elementet
         */
        @Test
        void containsFalse() {
            assertFalse(intList.contains(3));
        }

    }

    /**
     * Tester av isEmpty()
     */
    @Nested
    class isEmptyTests {

        /**
         * ser om isEmpty() returnerer true for en liste som er tom
         */
        @Test
        void isEmptyTrue() {
            assertTrue(intList.isEmpty());
            assertEquals(0, intList.size());
        }

        /**
         * Ser om isEmpty() returnerer false på en liste som ikke er tom
         */
        @Test
        void isEmptyFalse() {
            intList.add(1);
            assertEquals(1, intList.size());
            assertFalse(intList.isEmpty());
        }

    }

    /**
     * Tester av append()
     */
    @Nested
    class AppendTests {

        /**
         * prøver å legge til en liste med 2 elementer til på slutten av en annen liste
         * forventer at intlist får totalt 4 elementer, altså 12 11 1 2 i denne rekkefølgen
         */
        @Test
        void append() {
            IList<Integer> tempList = new LinkedList<>();
            tempList.add(1);
            tempList.add(2);
            intList.put(11);
            intList.put(12);
            intList.append(tempList);
            //ser om størrelsen på den returnerte listen tilsvarer de som ble lagt sammen
            assertEquals(4, intList.size());

            //itererer gjennom listen for å se om alt er plassert der det skal være
            Iterator<Integer> iterator = intList.iterator();
            int next = iterator.next();
            assertEquals(12, next);
            next = iterator.next();
            assertEquals(11, next);
            next = iterator.next();
            assertEquals(1, next);
            next = iterator.next();
            assertEquals(2, next);
        }

        /**
         * prøver append() med tom liste som input
         * forventer at innholdet i intList er uendret
         */
        @Test
        void appendEmptyInputList() {
            IList<Integer> tempList = new LinkedList<>();
            intList.put(11);
            intList.put(12);
            intList.append(tempList);
            //ser om størrelsen på den returnerte listen tilsvarer de som ble lagt sammen
            assertEquals(2, intList.size());

            //itererer gjennom listen for å se om alt er plassert der det skal være
            Iterator<Integer> iterator = intList.iterator();
            int next = iterator.next();
            assertEquals(12, next);
            next = iterator.next();
            assertEquals(11, next);
        }

    }

    /**
     * Tester av prepend()
     */
    @Nested
    class PrependTests {

        /**
         * prøver å legge til en liste med 2 elementer til på begynnelsen av en annen liste
         * forventer at intlist får totalt 4 elementer, altså 1 2 12 11 i denne rekkefølgen
         */
        @Test
        void prepend() {
            IList<Integer> tempList = new LinkedList<>();
            tempList.add(1);
            tempList.add(2);
            intList.put(11);
            intList.put(12);
            intList.prepend(tempList);
            //ser om størrelsen på den returnerte listen tilsvarer de som ble lagt sammen
            assertEquals(4, intList.size());

            //itererer gjennom listen for å se om alt er plassert der det skal være
            Iterator<Integer> iterator = intList.iterator();
            int next = iterator.next();
            assertEquals(1, next);
            next = iterator.next();
            assertEquals(2, next);
            next = iterator.next();
            assertEquals(12, next);
            next = iterator.next();
            assertEquals(11, next);
        }

        /**
         * prøver prepend() med tom liste som input
         * forventer at innholdet i intList er uendret
         */
        @Test
        void prependEmptyInputList() {
            IList<Integer> tempList = new LinkedList<>();
            intList.put(11);
            intList.put(12);
            intList.prepend(tempList);
            //ser om størrelsen på den returnerte listen tilsvarer de som ble lagt sammen
            assertEquals(2, intList.size());

            //itererer gjennom listen for å se om alt er plassert der det skal være
            Iterator<Integer> iterator = intList.iterator();
            int next = iterator.next();
            assertEquals(12, next);
            next = iterator.next();
            assertEquals(11, next);
        }

    }

    /**
     * Tester av concat()
     */
    @Nested
    class ConcatTests {

        /**
         * tester concat() med én tom liste og en som ikke er tom
         * forventer at den returnerte listen bare inneholder elementene til den listen som ikke er tom
         */
        @Test
        void concatWithOneEmptyList() {
            //initialiserer listene som skal bli brukt og sjekker at den tomme listen faktisk er tom i
            //tilfelle bug i konstruktør
            IList<Integer> emptyList = new LinkedList<>();
            assertTrue(emptyList.isEmpty());
            IList<Integer> tempList = new LinkedList<>();
            tempList.add(1);
            tempList.add(2);
            //kjører concat()
            IList<Integer> newList = intList.concat(tempList, emptyList);
            //ser om størrelsen på den returnerte listen tilsvarer de som ble lagt sammen
            assertEquals(2, newList.size());

            //itererer gjennom listen for å se om alt er plassert der det skal være
            Iterator<Integer> iterator = newList.iterator();
            int next = iterator.next();
            assertEquals(1, next);
            next = iterator.next();
            assertEquals(2, next);
        }

        /**
         * tester concat() med to tomme lister
         * forventer at den returnerte listen er også tom
         */
        @Test
        void concatTwoEmptyLists() {
            //initialiserer listene som skal bli brukt og sjekker at de faktisk er tom i tilfelle bug i
            //konstruktør
            IList<Integer> emptyList = new LinkedList<>();
            assertTrue(emptyList.isEmpty());
            IList<Integer> tempList = new LinkedList<>();
            assertTrue(tempList.isEmpty());
            //kjører concat()
            IList<Integer> newList = intList.concat(tempList, emptyList);
            //ser om størrelsen på den returnerte listen tilsvarer de som ble lagt sammen
            assertEquals(0, newList.size());

            //itererer gjennom listen for å se om alt er plassert der det skal være
            Iterator<Integer> iterator = newList.iterator();
            assertThrows(NoSuchElementException.class, iterator::next);
            assertFalse(iterator.hasNext());
        }

        /**
         * tester concat() med én liste
         * forventer at concat() returnerer en liste som kun inneholder de samme elementene som tempList
         */
        @Test
        void concatOneList() {
            //initialiserer liste som skal bli brukt
            IList<Integer> tempList = new LinkedList<>();
            tempList.add(1);
            tempList.add(2);
            //kjører concat()
            IList<Integer> newList = intList.concat(tempList);
            //ser om størrelsen på den returnerte listen tilsvarer de som ble lagt sammen
            assertEquals(2, newList.size());

            //itererer gjennom listen for å se om alt er plassert der det skal være
            Iterator<Integer> iterator = newList.iterator();
            int next = iterator.next();
            assertEquals(1, next);
            next = iterator.next();
            assertEquals(2, next);
        }

        /**
         * test som prøver å legge sammen 2 lister
         * forventer at den returnerte listen har innholdet til de sammenslåtte listene med elementene til den siste
         * isten først
         */
        @Test
        void concatTwoList() {
            //initialiserer listene som skal bli brukt
            IList<Integer> tempList = new LinkedList<>();
            IList<Integer> secondList = new LinkedList<>();
            tempList.add(1);
            tempList.add(2);
            secondList.add(3);
            secondList.add(4);
            //kjører concat()
            IList<Integer> newList = intList.concat(tempList, secondList);
            //ser om størrelsen på den returnerte listen tilsvarer de som ble lagt sammen
            assertEquals(4, newList.size());

            //itererer gjennom listen for å se om alt er plassert der det skal være
            Iterator<Integer> iterator = newList.iterator();
            int next = iterator.next();
            assertEquals(3, next);
            next = iterator.next();
            assertEquals(4, next);
            next = iterator.next();
            assertEquals(1, next);
            next = iterator.next();
            assertEquals(2, next);
        }

        /**
         * test som prøver å legge sammen 3 lister
         * forventer at den returnerte listen har innholdet til de sammenslåtte listene med elementene til den siste
         * isten først
         */
        @Test
        void concatThreeList() {
            //initialiserer listene som skal bli brukt
            IList<Integer> tempList = new LinkedList<>();
            IList<Integer> secondList = new LinkedList<>();
            IList<Integer> thirdList = new LinkedList<>();
            tempList.add(1);
            tempList.add(2);
            secondList.add(3);
            secondList.add(4);
            thirdList.add(5);
            thirdList.add(6);
            //kjører concat()
            IList<Integer> newList = intList.concat(tempList, secondList, thirdList);
            //ser om størrelsen på den returnerte listen tilsvarer de som ble lagt sammen
            assertEquals(6, newList.size());

            //itererer gjennom listen for å se om alt er plassert der det skal være
            Iterator<Integer> iterator = newList.iterator();
            int next = iterator.next();
            assertEquals(5, next);
            next = iterator.next();
            assertEquals(6, next);
            next = iterator.next();
            assertEquals(3, next);
            next = iterator.next();
            assertEquals(4, next);
            next = iterator.next();
            assertEquals(1, next);
            next = iterator.next();
            assertEquals(2, next);
        }

    }

    /**
     * Tester av sort()
     */
    @Nested
    class SortTests {

        /**
         * prøver å sortere en liste med integer verdier i stigende rekkefølge
         */
        @Test
        void sortIntegers() {
            // Se oppgave 8
            List<Integer> values = Arrays.asList(3, 8, 4, 7, 10, 6, 1, 2, 9, 5);

            for (Integer value : values) {
                intList.add(value);
            }
            intList.sort(Comparator.comparingInt(x -> x));

            int n = intList.remove();
            while (intList.size() > 0) {
                int m = intList.remove();
                if (n > m) {
                    fail("Integer list is not sorted.");
                }
                n = m;
            }
        }

        /**
         * prøver å sortere en liste med integer verdier i synkende rekkefølge
         */
        @Test
        void sortIntegersReverse() {
            // Se oppgave 8
            List<Integer> values = Arrays.asList(3, 8, 4, 7, 10, 6, 1, 2, 9, 5);

            for (Integer value : values) {
                intList.add(value);
            }
            intList.sort(Comparator.reverseOrder());

            int n = intList.remove();
            while (intList.size() > 0) {
                int m = intList.remove();
                if (n < m) {
                    fail("Integer list is not sorted.");
                }
                n = m;
            }
        }

        /**
         * prøver å sortere en liste med string verdier i rekkefølge a til å
         */
        @Test
        void sortStrings() {
            // Se oppgave 8
            IList<String> list = new LinkedList<>();
            List<String> values = Arrays.asList("g", "f", "a", "c", "b", "d", "e", "i", "j", "h", "å", "z");

            for (String value : values) {
                list.add(value);
            }
            list.sort(Comparator.naturalOrder());

            String n = list.remove();
            while (list.size() > 0) {
                String m = list.remove();
                if (n.compareTo(m) > 0) {
                    fail("String list is not sorted.");
                }
                n = m;
            }
        }

        /**
         * prøver å sortere en liste med string verdier i rekkefølge å til a
         */
        @Test
        void sortStringsReverse() {
            // Se oppgave 8
            IList<String> list = new LinkedList<>();
            List<String> values = Arrays.asList("g", "f", "a", "c", "b", "d", "e", "i", "j", "h");

            for (String value : values) {
                list.add(value);
            }
            list.sort(Comparator.reverseOrder());

            String n = list.remove();
            while (list.size() > 0) {
                String m = list.remove();
                if (n.compareTo(m) < 0) {
                    fail("String list is not sorted.");
                }
                n = m;
            }
        }

    }

    /**
     * Tester av filter()
     */
    @Nested
    class FilterTests {

        /**
         * test som prøver å fjerne alle integer verdier med rest på 1 etter halvering
         */
        @Test
        void oppg9_filter() {
            // Se oppgave 9
            List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

            IList<Integer> list = new LinkedList<>();
            for (Integer value : values) {
                list.add(value);
            }

            list.filter(n -> n % 2 == 1);

            assertFalse(list.contains(1));
            assertFalse(list.contains(3));
            assertFalse(list.contains(5));
            assertFalse(list.contains(7));
            assertFalse(list.contains(9));

            int n;
            while (list.size() > 0) {
                n = list.remove();
                if (n % 2 == 1) {
                    fail("List contains filtered out elements.");
                }
            }
        }

        /**
         * test som prøver å se om filter() fjerner alle string verdier som møter kriteriene
         */
        @Test
        void filterStringsByContents() {
            // Se oppgave 9
            List<String> strings = Arrays.asList("a", "b", "c", "d", "e", "f", "c", "c", "b");

            IList<String> list = new LinkedList<>();
            for (String s : strings) {
                list.add(s);
            }

            list.filter(s -> s.equals("c"));
            list.filter(s -> s.equals("b"));

            //kontrollerer om listen fremdeles har elementene som ikke skulle bli slettet
            assertTrue(list.contains("a"));
            assertTrue(list.contains("d"));
            assertTrue(list.contains("e"));
            assertTrue(list.contains("f"));
            //ser om de relevante elementene har blitt slettet
            assertFalse(list.contains("c"));
            assertFalse(list.contains("b"));
            //kontrollerer størrelsen på listen
            assertEquals(4, list.size());

            //videre sjekk om de slettede elementene fremdeles er i listen
            String n;
            while (list.size() > 0) {
                n = list.remove();
                if (n.equals("c") || n.equals("b")) {
                    fail("List contains filtered out elements.");
                }
            }
        }

        /**
         * prøver å fjerne alle tall som har verdi høyere enn 10
         * forventer at intList bare inkluderer 1 2 3 4 5 6 7 8 9 10
         */
        @Test
        void filterGreaterThanTen() {
            List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
            for (Integer value : values) {
                intList.add(value);
            }
            //kontrollerer at listen har innhold
            assertFalse(intList.isEmpty());
            //fjerner alle verdier etter 10, altså 11 til 16.
            intList.filter(n -> n > 10);
            assertEquals(10, intList.size());

            int n;
            while (intList.size() > 0) {
                n = intList.remove();
                if (n > 10) {
                    fail("List contains filtered out elements.");
                }
            }
        }

    }

    /**
     * Tester av map()
     */
    @Nested
    class MapTests {

        /**
         * tester om metoden kan konvertere en liste med string verdier til integer
         */
        @Test
        void mapInt() {
            // Se oppgave 10
            IList<String> list = new LinkedList<>();
            list.add("1");
            list.add("2");
            list.add("3");
            list.add("4");
            list.add("5");

            IList<Integer> result = list.map(Integer::parseInt);
            List<Integer> target = Arrays.asList(1, 2, 3, 4, 5);
            for (Integer t : target) {
                if (!result.remove().equals(t)) {
                    fail("Result of map gives the wrong value.");
                }
            }
        }

        /**
         * tester om metoden kan konvertere en liste med integer verdier til string
         */
        @Test
        void mapString() {
            // Se oppgave 10
            IList<Integer> list = new LinkedList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            list.add(5);

            IList<String> result = list.map(String::valueOf);
            List<String> target = Arrays.asList("1", "2", "3", "4", "5");
            for (String t : target) {
                if (!result.remove().equals(t)) {
                    fail("Result of map gives the wrong value.");
                }
            }
        }

    }

    /**
     * Tester av Reduce()
     */
    @Nested
    class ReduceTests {

        /**
         * prøver å legge sammen tallene i en liste til én integer med hjelp av summering
         * forventer at result er lik 15
         */
        @Test
        void reduceInts() {
            // Se oppgave 11
            List<Integer> values = Arrays.asList(1, 2, 3, 4, 5);

            IList<Integer> list = new LinkedList<>();
            for (Integer value : values) {
                list.add(value);
            }

            int result = list.reduce(0, Integer::sum);

            assertEquals(5 * ((1 + 5) / 2), result); //5 * ((1 + 5) / 2) = 15
        }

        /**
         * test som prøver å finne det tallet med størst verdi
         * forventer at result er lik 5
         */
        @Test
        void reduceIntsMax() {
            // Se oppgave 11
            List<Integer> values = Arrays.asList(1, 2, 3, 4, 5);

            IList<Integer> list = new LinkedList<>();
            for (Integer value : values) {
                list.add(value);
            }

            int result = list.reduce(0, Integer::max);

            assertEquals(5, result);
        }

        /**
         * prøver å legge sammen en liste med string verdier til ett ord
         * forventer at result er lik "test"
         */
        @Test
        void reduceStrings() {
            // Se oppgave 11
            List<String> values = Arrays.asList("e", "s", "t");
            IList<String> list = new LinkedList<>();
            for (String s : values) {
                list.add(s);
            }

            String result = list.reduce("t", (acc, s) -> acc + s);

            assertEquals("test", result);
        }

    }

    /**
     * tester om size() returnerer forventet vardipå forskjellige stadier
     */
    @Test
    void size() {
        //ser om size() returnerer 0 når listen er tom
        assertEquals(0, intList.size());
        intList.add(1);
        //ser om size() returnerer 1 når listen har ett element
        assertEquals(1, intList.size());
        intList.add(2);
        //ser om size() returnerer 2 når listen har to elementer
        assertEquals(2, intList.size());
    }

    /**
     * Tester av clear()
     */
    @Nested
    class ClearTests {

        /**
         * Ser om clear fjerner alle elementene i en liste med elementer
         * forventer at intList er tom på slutten
         */
        @Test
        void clear() {
            intList.add(1);
            intList.add(2);
            intList.add(3);
            intList.add(4);
            intList.add(5);
            intList.add(6);
            intList.clear();
            assertEquals(0, intList.size());
            assertTrue(intList.isEmpty());
            assertFalse(intList.contains(1));
            assertThrows(NullPointerException.class, () -> intList.first());
        }

        /**
         * Ser om clear() gjør noe uventet når listen er tom fra før av
         * forventer at intList fortsetter å være tom
         */
        @Test
        void clearEmptyList() {
            intList.clear();
            assertEquals(0, intList.size());
            assertTrue(intList.isEmpty());
            assertFalse(intList.contains(1));
            assertThrows(NullPointerException.class, () -> intList.first());
        }

    }

    /**
     * Tester av Iterator
     */
    @Nested
    class IteratorTests {

        /**
         * tester oppførsel til iterator på en tom liste
         */
        @Test
        void emptyListIterator() {
            //instansierer iteratoren på en tom liste
            Iterator<Integer> iterator = intList.iterator();
            //ser om next() kaster forventet exception
            assertThrows(NoSuchElementException.class, iterator::next);
            //ser om hasNext() returnerer false i tom liste
            assertFalse(iterator.hasNext());

            //legger til et element for å se om dette endrer på iteratoren sin interne liste
            intList.add(1);
            //Antar at den instansierte iteratoren ikke blir påvirket av listen sin oppdatering
            //ser om next() kaster forventet exception
            assertThrows(NoSuchElementException.class, iterator::next);
            //ser om hasNext() returnerer false i tom liste
            assertFalse(iterator.hasNext());
        }

        /**
         * tester iterator sin oppførsel på en liste med elementer
         */
        @Test
        void iterator() {
            intList.add(1);
            intList.add(2);
            intList.add(3);
            //instansierer iteratoren på en liste med 3 elementer
            Iterator<Integer> iterator = intList.iterator();

            int next = iterator.next();
            //tester om next() returnerer det første elementet i listen
            assertEquals(1, next);
            next = iterator.next();
            //tester om next() endrer verdi
            assertEquals(2, next);
            //tester om hasNext() returnerer true med flere elementer igjen
            assertTrue(iterator.hasNext());
            next = iterator.next();
            //tester om next() endrer verdi igjen
            assertEquals(3, next);
            //tester om next() kaster forventet exception når det ikke er flere element igjen
            assertThrows(NoSuchElementException.class, iterator::next);
            //ser om hasNext() returnerer false når det ikke er flere elementer igjen
            assertFalse(iterator.hasNext());
            //tester om remove() gjør det som er forventet
            assertThrows(UnsupportedOperationException.class, iterator::remove);
        }

    }

    /*@Test
    void ex1_FastSort() {
        // Se ekstraoppgave 1
        Random r = new Random();
        IList<Integer> list = new LinkedList<>();
        for (int n = 0; n < 1000000; n++) {
            list.add(r.nextInt());
        }

        assertTimeout(Duration.ofSeconds(2), () -> list.sort(Integer::compare));

        int n = list.remove();
        for(int m = list.remove(); !list.isEmpty(); m = list.remove()) {
            if (n > m) {
                fail("List is not sorted");
            }
            n = m;
        }
    }*/
}