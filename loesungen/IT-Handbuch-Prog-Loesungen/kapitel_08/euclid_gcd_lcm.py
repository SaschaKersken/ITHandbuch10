def gcd(m, n):
    while n != 0:
        m, n = n, m % n
    return m

def lcm(m, n):
    return m * n / gcd(m, n)

if __name__ == '__main__':
    while True:
        m_input = input("Erste Zahl: ")
        n_input = input("Zweite Zahl: ")
        try:
            m = int(m_input)
            n = int(n_input)
        except ValueError:
            print("Bitte nur Zahlen eingeben!")
            continue
        print(f"GGT von {m} und {n}: {gcd(m, n)}")
        print(f"KGV von {m} und {n}: {lcm(m, n)}")
        again = input("Noch mal (j/n)? ")
        if again == "n" or again == "N":
            break
