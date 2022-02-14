/**
* Einzelner Zweig eines Binärbaums, und der Baum selbst
*/
public class Branch<T extends Comparable<? super T>> {
    /**
    * Wert des aktuellen Knotens
    */
    private T value;

    /**
    * Linker Nachfolger, falls gesetzt, sonst null
    */
    private Branch<T> left = null;

    /**
    * Rechter Nachfolger, falls gesetzt, sonst null
    */
    private Branch<T> right = null;

    /**
    * Konstruktor, der nur einen Wert entgegennimmt
    *
    * @param value Der zu speichernde Wert
    */
    public Branch(T value) {
        this.value = value;
    }

    /**
    * Konstruktor mit Wert und Nachfolgern
    *
    * @param value Der zu speichernde Wert
    * @param left Der linke Nachfolger
    * @param right Der rechte Nachfolger
    */
    public Branch(T value, Branch<T> left, Branch<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    /**
    * Den Wert ändern
    *
    * @param value Der neue zu speichernde Wert
    */
    public void setValue(T value) {
        this.value = value;
    }

    /**
    * Den linken Nachfolger setzen
    *
    * @param left Der linke Nachfolger
    */
    public void setLeft(Branch<T> left) {
        this.left = left;
    }

    /**
    * Den rechten Nachfolger setzen
    *
    * @param right Der rechte Nachfolger
    */
    public void setRight(Branch<T> right) {
        this.right = right;
    }

    /**
    * Den Wert auslesen
    *
    * @return Aktueller Wert
    */
    public T getValue() {
        return value;
    }

    /**
    * Den linken Nachfolger auslesen
    *
    * @return Linker Nachfolger
    */
    public Branch<T> getLeft() {
        return left;
    }

    /**
    * Den rechten Nachfolger auslesen
    *
    * @return Rechter Nachfolger
    */
    public Branch<T> getRight() {
        return right;
    }

    /**
    * Den gesamten Teilbaum unterhalb des aktuellen Knotens ausgeben
    */
    public void print() {
        this.print(this, 0);
    }

    /**
    * Den aktuellen Teilbaum um 90° gedreht ausgeben
    */
    private void print(Branch<T> current, int indentation) {
        // Rechten Nachfolger rekursiv ausgeben, falls vorhanden
        if (current.getRight() != null) {
            this.print(current.getRight(), indentation + 4);
        }
        // Aktuellen Knoten eingerückt ausgeben
        for (int i = 0; i < indentation; i++) {
            System.out.print(" ");
        }
        System.out.println(current.getValue());
        // Linken Nachfolger rekursiv ausgeben, falls vorhanden
        if (current.getLeft() != null) {
            this.print(current.getLeft(), indentation + 4);
        }
    }
}

