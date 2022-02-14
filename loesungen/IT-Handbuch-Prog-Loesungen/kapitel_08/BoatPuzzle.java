import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
* Logikrätsel (Fluss überqueren) per Breitensuche lösen
*/
public class BoatPuzzle implements PathSearchable<BoatState> {
    /**
    * Zustand gültig?
    *
    * Ungültig, wenn Wolf und Schaf oder Schaf und Kohl allein bleiben
    * Ansonsten gültig
    *
    * @param state Zu prüfender Zustand
    * @return true wenn gültig, ansonsten false
    */
    private boolean isLegal(BoatState state) {
        if (state.getWolf() == state.getSheep() && state.getBoat() != state.getSheep()) {
           return false;
        }
        if (state.getSheep() == state.getCabbage() && state.getBoat() != state.getCabbage()) {
           return false;
        }
        return true;
    }

    /**
    * Einen Zustand als Nachfolger hinzufügen
    *
    * Der Zustand wird nur hinzugefügt, wenn er gültig ist
    *
    * @param states Liste von Zuständen
    * @param newState Eventuell hinzuzufügender Zustand
    */
    private void addState(List<BoatState> states, BoatState newState) {
        if (isLegal(newState)) {
            states.add(newState);
        }
    }

    /**
    * Nachfolger eines Zustands ermitteln
    *
    * @param state Aktueller Zustand
    * @return Liste der Nachfolger
    */
    @Override
    public List<BoatState> successors(BoatState state) {
        List<BoatState> successors = new ArrayList<>();
        for (int i = BoatState.START; i <= BoatState.GOAL; i++) {
            int newPosition = (i == BoatState.START ? BoatState.GOAL : BoatState.START);
            if (state.getBoat() == i) {
                addState(
                    successors,
                    new BoatState(
                        state.getWolf(),
                        state.getSheep(),
                        state.getCabbage(),
                        newPosition
                    )
                );
                if (state.getWolf() == i) {
                    addState(
                        successors,
                        new BoatState(
                            newPosition,
                            state.getSheep(),
                            state.getCabbage(),
                            newPosition
                        )
                    );
                }
                if (state.getSheep() == i) {
                    addState(
                        successors,
                        new BoatState(
                            state.getWolf(),
                            newPosition,
                            state.getCabbage(),
                            newPosition
                        )
                    );
                }
                if (state.getCabbage() == i) {
                    addState(
                        successors,
                        new BoatState(
                            state.getWolf(),
                            state.getSheep(),
                            newPosition,
                            newPosition
                        )
                    );
                }
            }
        }
        return successors;
    }

    /**
    * Testen, ob ein Zustand das Ziel ist
    *
    * @param state Zu prüfender Zustand
    * @return true wenn Ziel, andernfalls false
    */
    @Override
    public boolean isGoal(BoatState state) {
        return state.getWolf() == BoatState.GOAL &&
               state.getSheep() == BoatState.GOAL &&
               state.getCabbage() == BoatState.GOAL &&
               state.getBoat() == BoatState.GOAL;
    }

    /**
    * Heuristik für A* (hier nicht benötigt)
    */
    @Override
    public double heuristic(BoatState state) {
        return 0;
    }

    /**
    * Eine einzelne Fahrt ausgeben
    *
    * @param from Zustand bei Abfahrt
    * @param to Zustand bei Ankunft
    */
    public static void printRoute(BoatState from, BoatState to) {
        if (from != null) {
            String result;
            if (from.getWolf() != to.getWolf()) {
                result = "Fahrt mit Wolf";
            } else if (from.getSheep() != to.getSheep()) {
                result = "Fahrt mit Schaf";
            } else if (from.getCabbage() != to.getCabbage()) {
                result = "Fahrt mit Kohl";
            } else {
                result = "Leerfahrt";
            }
            result += " vom ";
            if (from.getBoat() == BoatState.START) {
                result += "Start- zum Zielufer";
            } else {
                result += "Ziel- zum Startufer";
            }
            System.out.println(result);
        }
    }

    /**
    * Hauptprogramm - Initialisierung, Ausführung, Ausgabe
    */
    public static void main(String[] args) {
        BoatState start = new BoatState();
        Node<BoatState> solution = PathSearch.bfs(start, new BoatPuzzle());
        if (solution != null) {
            List<BoatState> path = new ArrayList<BoatState>(solution.toPath());
            Collections.reverse(path);
            BoatState previous = null;
            for (BoatState state: path) {
                printRoute(previous, state);
                System.out.println(state);
                previous = state;
            }
        } else {
            System.out.println("Keine Lösung gefunden.");
        }
    }
}
