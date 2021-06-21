from sys import argv

def gcd(m, n):
    # Trivialer Fall: m und n gleich
    if m == n:
        return m
    # Größeren und kleieren Wert ermitteln
    smaller = m if m < n else n
    greater = m if m > n else n
    # Minimum aus smaller und greater // 2
    max_divisor = greater // 2 if greater // 2 < smaller else smaller
    # Jeden möglichen Teiler testen
    for i in range(max_divisor, 1, -1):
        # Sind beide Werte durch i teilbar?
        if m % i == 0 and n % i == 0:
            return i
    # Hier bleibt nur noch die 1
    return 1

if __name__ == '__main__':
    m = int(argv[1])
    n = int(argv[2])
    print(f"Der GGT von {m} und {n} ist {gcd(m, n)}.")
