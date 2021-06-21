import re
  
class CharArray:

    CHAR_MAP = {'0': 0, '1': 1, '2': 2, '3': 3, '4': 4, '5': 5,
        '6': 6, '7': 7, '8': 8, '9': 9, ' ': 10,
        'a': 11, 'b': 12, 'c': 13, 'd': 14, 'e': 15,
        'f': 16, 'g': 17, 'h': 18, 'i': 19, 'j': 20,
        'k': 21, 'l': 22, 'm': 23, 'n': 24, 'o': 25,
        'p': 26, 'q': 27, 'r': 28, 's': 29, 't': 30,
        'u': 31, 'v': 32, 'w': 33, 'x': 34, 'y': 35,
        'z': 36, 'ä': 37, 'ö': 38, 'ü': 39, 'ß': 40
    }
    EXCLUDE = re.compile('[^0-9 a-zäöüß]+')
    BLANKS = re.compile(' +')

    def normalize(self, text):
        # Kleinschreibung
        text = text.lower()
        # Sonderzeichen ersetzen
        text = self.EXCLUDE.sub(' ', text)
        # Mehrere Leerzeichen durch eins ersetzen
        text = self.BLANKS.sub(' ', text)
        return text

    def process(self, text):
        text = self.normalize(text)
        result = [self.CHAR_MAP[char] for char in text]
        return result

    def stats(self, text):
        chars = self.process(text)
        result = [0 for _ in range(0, 41)]
        for code in chars:
            result[code] += 1
        return result


if __name__ == '__main__':
    ca = CharArray()
    text = "Überraschung: Süßer die Glocken nie klingen / als zu der Weihnachtszeit"
    print(ca.normalize(text))
    print(ca.process(text))
    print(ca.stats(text))

