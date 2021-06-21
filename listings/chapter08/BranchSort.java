import java.util.List;
import java.util.ArrayList;

/**
* Werte sortiert in einem Binärbaum speichern
*
* Sie können mit addValue(T) beliebig viele Werte hinzufügen.
* Die Methode getSorted() liefert die Werte jederzeit
* aufsteigend sortiert zurück.
*/
public class BranchSort<T extends Comparable<? super T>> {
    /**
    * Wurzelknoten des internen Binärbaums
    */
    private Branch<T> root = null;

    /**
    * Wurzelknoten auslesen
    *
    * @return Wurzelknoten
    */
    public Branch<T> getRoot() {
        return root;
    }

    /**
    * Einen Wert hinzufügen
    *
    * Wenn der Wurzelknoten noch nicht gesetzt ist, wird der Wert
    * als Wurzelknoten gesetzt, ansonsten wird er mithilfe der
    * internen Helfermethode recursiveInsert() einsortiert.
    *
    * @param value Der einzufügende Wert
    */
    public void addValue(T value) {
        if (root == null) {
            root = new Branch<>(value);
        } else {
            recursiveInsert(root, value);
        }
    }

    /**
    * Rekursive Helfermethode zum Einsortieren eines Werts
    *
    * @param branch Der aktuelle Knoten
    * @param value Der einzusortierende Wert
    */
    private void recursiveInsert(Branch<T> branch, T value) {
        // Größe des einzusortierenden Werts überprüfen
        if (value.compareTo(branch.getValue()) <= 0) {
            // Kleiner oder gleich aktueller Wert: nach links
            if (branch.getLeft() != null) {
                // Rekursiver Aufruf, falls linker Nachfolger
                recursiveInsert(branch.getLeft(), value);
                // Und Schluss
                return;
            }
            // Ansonsten neuen Knoten erzeugen und als linken Nachfolger setzen
            Branch<T> newBranch = new Branch<>(value);
            branch.setLeft(newBranch);
            // Und Schluss
            return;
        } else {
            // Größer als aktueller Wert: nach rechts
            if (branch.getRight() != null) {
                // Rekursiver Aufruf, falls rechter Nachfolger
                recursiveInsert(branch.getRight(), value);
                // Und Schluss
                return;
            }
            // Ansonsten neuen Knoten erzeugen und als rechten Nachfolger setzen
            Branch<T> newBranch = new Branch<>(value);
            branch.setRight(newBranch);
            // Und Schluss
            return;
        }
    }

    /**
    * Alle Elemente aufsteigend sortiert auslesen
    *
    * Intern wird eine neue Liste erzeugt, anschließend wird die interne
    * Helfermethode mit dem Wurzelknoten und der leeren Liste aufgerufen.
    *
    * @return Die Liste der sortierten Werte
    */
    public ArrayList<T> getSorted() {
        ArrayList<T> sorted = new ArrayList<>();
        recursiveRead(root, sorted);
        return sorted;
    }

    /**
    * Interne Helfermethode zum Auslesen der sortierten Werte
    *
    * @param branch Der aktuelle Knoten
    * @param list Die Liste, in die die Werte geschrieben werden
    */
    private void recursiveRead(Branch<T> branch, ArrayList<T> list) {
        // Rekursiver Aufruf für einen eventuellen linken Nachfolger
        if (branch.getLeft() != null) {
            recursiveRead(branch.getLeft(), list);
        }
        // Den aktuellen Wert an die Liste anfügen
        list.add(branch.getValue());
        // Rekursiver Aufruf für einen eventuellen rechten Nachfolger
        if (branch.getRight() != null) {
            recursiveRead(branch.getRight(), list);
        }
    }
}

