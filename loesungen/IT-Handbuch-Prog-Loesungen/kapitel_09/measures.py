import re

"""
measure_map ist ein Dictionary, das den Umrechnungsfaktor
der jeweiligen Maßeinheit in mm enthält.
Indem der Wert zunächst mit dem Umrechnungsfaktor der
Quelleinheit multipliziert und dann durch den Faktor der
Zieleinheit dividiert wird, wird jeder Wert zunächst in mm
und anschließend in die gewünschte Einheit konvertiert.
"""
def convert(value, from_measure, to_measure):
    measure_map = {
        'mm': 1,
        'cm': 10,
        'dm': 100,
        'm': 1000,
        'km': 1000000
    }
    return value * measure_map[from_measure] / measure_map[to_measure]

print("Maßeinheiten: mm, cm, dm, m, km")
print("Wert Quell-Maßeinheit Ziel-Maßeinheit (x zum Beenden)")
while True:
    eingabe = input("> ")
    if eingabe == 'x':
        break
    pattern = re.compile("^(\d+(\.\d+)?)\s*([mcdk]?m)\s+([mcdk]?m)$")
    m = pattern.match(eingabe)
    if m:
        value = float(m.group(1))
        from_measure = m.group(3)
        to_measure = m.group(4)
        result = convert(value, from_measure, to_measure)
        print("{}{} = {}{}".format(value, from_measure, result, to_measure))
    else:
        print("Ungültige Eingabe.")
